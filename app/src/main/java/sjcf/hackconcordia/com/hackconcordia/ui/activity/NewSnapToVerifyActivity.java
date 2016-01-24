package sjcf.hackconcordia.com.hackconcordia.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.clarifai.api.RecognitionResult;

import java.io.IOException;

import sjcf.hackconcordia.com.hackconcordia.Keys;
import sjcf.hackconcordia.com.hackconcordia.R;
import sjcf.hackconcordia.com.hackconcordia.model.SnapVerification;
import sjcf.hackconcordia.com.hackconcordia.model.User;
import sjcf.hackconcordia.com.hackconcordia.util.LocationUtil;

public class NewSnapToVerifyActivity extends AppCompatActivity {

    private User mUser;
    private SnapVerification mNewSnapVerification;

    private ImageView imageToSend;
    private ImageView cameraLauncher;
    private TextView locationView;
    private Button verifyButton;
    private TextView helpText;
    private final int REQUEST_IMAGE_CAPTURE = 1;
    private final String TAG = "NewSnapToVerifyActivity";

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_snap_to_verify);
        Bundle extras = getIntent().getExtras();
        mUser = extras.getParcelable(Keys.USER_PARCELABLE);
        mNewSnapVerification = new SnapVerification();
        mNewSnapVerification.snapTreasure = extras.getParcelable(Keys.SNAP_TREASURE_PARCEABLE);
        mNewSnapVerification.toBeVerifiedByUser = mNewSnapVerification.snapTreasure.createdByUser;
        mNewSnapVerification.submittedByUser = mUser;
        mNewSnapVerification.isVerified = 0;

        imageToSend = (ImageView) findViewById(R.id.imageView2);
        cameraLauncher = (ImageView) findViewById(R.id.camera_button2);
        locationView = (TextView) findViewById(R.id.location_place);
        verifyButton = (Button) findViewById(R.id.verify_button);
        helpText = (TextView) findViewById(R.id.helpText);
        verifyButton.setVisibility(View.INVISIBLE);
        verifyButton.setEnabled(false);

        cameraLauncher.setOnClickListener(new View.OnClickListener() {
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
    @Override protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bitmap bitmap = loadBitmapFromUri(intent.getData());
            if (bitmap != null) {
                cameraLauncher.setVisibility(View.INVISIBLE);
                imageToSend.setImageBitmap(bitmap);
                locationView.setText(LocationUtil.getLocalityName(this));
                verifyButton.setEnabled(true);
                verifyButton.setVisibility(View.VISIBLE);
                helpText.setVisibility(View.INVISIBLE);
                verifyButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //TODO: upload umage to the server.
                    }
                });
            }
        }
    }
    private Bitmap loadBitmapFromUri(Uri uri) {
        try {
            // The image may be large. Load an image that is sized for display. This follows best
            // practices from http://developer.android.com/training/displaying-bitmaps/load-bitmap.html
            BitmapFactory.Options opts = new BitmapFactory.Options();
            opts.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(getContentResolver().openInputStream(uri), null, opts);
            int sampleSize = 1;
            while (opts.outWidth / (2 * sampleSize) >= imageToSend.getWidth() && opts.outHeight / (2 * sampleSize) >= imageToSend.getHeight())
            {
                sampleSize *= 2;
            }

            opts = new BitmapFactory.Options();
            opts.inSampleSize = sampleSize;
            return BitmapFactory.decodeStream(getContentResolver().openInputStream(uri), null, opts);
        } catch (IOException e) {
            Log.e(TAG, "Error loading image: " + uri, e);
        }
        return null;
    }
}
