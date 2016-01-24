package sjcf.hackconcordia.com.hackconcordia.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.clarifai.api.ClarifaiClient;
import com.clarifai.api.RecognitionRequest;
import com.clarifai.api.RecognitionResult;
import com.clarifai.api.Tag;
import com.clarifai.api.exception.ClarifaiException;
import com.cloudinary.Url;
import com.cloudinary.utils.ObjectUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import sjcf.hackconcordia.com.hackconcordia.Keys;
import sjcf.hackconcordia.com.hackconcordia.R;
import sjcf.hackconcordia.com.hackconcordia.model.SnapTreasure;
import sjcf.hackconcordia.com.hackconcordia.model.User;
import sjcf.hackconcordia.com.hackconcordia.util.LocationUtil;

/**
 * A simple Activity that performs recognition using the Clarifai API.
 */
public class NewSnapActivity extends Activity {
    private static final String TAG = NewSnapActivity.class.getSimpleName();

    //Authentication API Keys
    private static final String APP_ID = "IauPgYAri-pVuPvBvJ9ohodx_7RuMqJFg3lGAQYR";
    private static final String APP_SECRET = "7lkedBg9Q8yB96JzAhd821OxlNP9gOormqdDx5SC";

    private User mUser;
    private SnapTreasure mNewSnapTreasure;

    private final ClarifaiClient client = new ClarifaiClient(APP_ID, APP_SECRET);
    private Button postButton;
    private ImageView cameraButton;
    private ImageView imageView;
    private TextView textView;
    private TextView locationView;
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private InputStream stream;

    private double[] latLng;
    private String locality;
    private String url;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_snap);
        Bundle extras = getIntent().getExtras();
        mUser = extras.getParcelable(Keys.USER_PARCELABLE);

        imageView = (ImageView) findViewById(R.id.image_view);
        textView = (TextView) findViewById(R.id.text_view);
        postButton = (Button) findViewById(R.id.post_button);
        postButton.setEnabled(false);
        postButton.setVisibility(View.INVISIBLE);
        cameraButton = (ImageView) findViewById(R.id.camera_button);
        locationView = (TextView) findViewById(R.id.location_text);

        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Send an intent to launch the media picker.
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }
            }
        });
    }

    @Override protected void onActivityResult(int requestCode, int resultCode, final Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {

            new AsyncTask() {
                RecognitionResult result;
                Bitmap bitmap;

                @Override
                protected void onPreExecute() {
                    super.onPreExecute();
                    // The user took an image. Send it to Clarifai for recognition.
                    Log.d(TAG, "User took the image: " + intent.getData());
                    bitmap = loadBitmapFromUri(intent.getData());
                    if (bitmap != null) {
                        cameraButton.setVisibility(View.INVISIBLE);
                        imageView.setImageBitmap(bitmap);
                        textView.setTypeface(null, Typeface.NORMAL);
                        textView.setText("Recognizing...");
                        postButton.setVisibility(View.VISIBLE);
                        postButton.setEnabled(true);
                        postButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                // save to db
                                mNewSnapTreasure.save();
                                finish();
                            }
                        });
                        locality = LocationUtil.getLocalityName(NewSnapActivity.this);
                        latLng = LocationUtil.getLocation(NewSnapActivity.this);
                        locationView.setText(locality);
                        postButton.setEnabled(false);
                    } else {
                        textView.setText("Unable to load selected image.");
                    }
                }

                @Override
                protected Object doInBackground(Object[] params) {
                    try {
                        result =  recognizeBitmap(bitmap);
                        Map response = null;
                        response = Keys.cloudinary.uploader().upload(stream, ObjectUtils.emptyMap());
                        String signature = (String) response.get("public_id");
                        url = Keys.CLOUDINARY_GET_ENDPOINT + signature + ".jpg";
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return null;
                }

                @Override
                protected void onPostExecute(Object o) {
                    super.onPostExecute(o);
                    try {
                        updateUIForResult(result);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }.execute();
        }
    }

    /** Loads a Bitmap from a content URI returned by the media picker. */
    private Bitmap loadBitmapFromUri(Uri uri) {
        try {
            // The image may be large. Load an image that is sized for display. This follows best
            // practices from http://developer.android.com/training/displaying-bitmaps/load-bitmap.html
            BitmapFactory.Options opts = new BitmapFactory.Options();
            opts.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(getContentResolver().openInputStream(uri), null, opts);
            int sampleSize = 1;
            while (opts.outWidth / (2 * sampleSize) >= imageView.getWidth() && opts.outHeight / (2 * sampleSize) >= imageView.getHeight())
            {
                sampleSize *= 2;
            }

            opts = new BitmapFactory.Options();
            opts.inSampleSize = sampleSize;
            InputStream bitmapStream = getContentResolver().openInputStream(uri);
            stream = getContentResolver().openInputStream(uri);
            return BitmapFactory.decodeStream(bitmapStream, null, opts);
        } catch (IOException e) {
            Log.e(TAG, "Error loading image: " + uri, e);
        }
        return null;
    }

    /** Sends the given bitmap to Clarifai for recognition and returns the result. */
    private RecognitionResult recognizeBitmap(Bitmap bitmap) {
        try {
            // Scale down the image. This step is optional. However, sending large images over the
            // network is slow and  does not significantly improve recognition performance.
            Bitmap scaled = Bitmap.createScaledBitmap(bitmap, 320,
                    320 * bitmap.getHeight() / bitmap.getWidth(), true);

            // Compress the image as a JPEG.
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            scaled.compress(Bitmap.CompressFormat.JPEG, 90, out);
            byte[] jpeg = out.toByteArray();

            // Send the JPEG to Clarifai and return the result.
            return client.recognize(new RecognitionRequest(jpeg)).get(0);

        } catch (ClarifaiException e) {
            Log.e(TAG, "Clarifai error " + e.getCause() + e.getCause().toString(), e);

            return null;
        }
    }

    /** Updates the UI by displaying tags for the given result. */
    private void updateUIForResult(RecognitionResult result) throws IOException {
        if (result != null) {
            if (result.getStatusCode() == RecognitionResult.StatusCode.OK) {
                mNewSnapTreasure = new SnapTreasure();
                mNewSnapTreasure.createdByUser = mUser;
                mNewSnapTreasure.foundByUser = null;
                mNewSnapTreasure.lat = latLng[0];
                mNewSnapTreasure.lng = latLng[1];
                mNewSnapTreasure.localityName = locality;
                mNewSnapTreasure.tags = new ArrayList<>();
                mNewSnapTreasure.photoUrl = url;
                Log.i(TAG, mNewSnapTreasure.photoUrl);

                // Display the list of tags in the UI.
                StringBuilder b = new StringBuilder();
                for (Tag tag : result.getTags()) {
                    mNewSnapTreasure.tags.add(tag.getName());
                    b.append(b.length() > 0 ? ", " : "").append(tag.getName());
                }
                textView.setText("Tags:\n" + b);
            } else {
                Log.e(TAG, "Clarifai: " + result.getStatusMessage());
                textView.setText("Sorry, there was an error recognizing your image.");
            }
        } else {
            textView.setText("Sorry, there was an error recognizing your image.");
        }
        postButton.setEnabled(true);
    }
}