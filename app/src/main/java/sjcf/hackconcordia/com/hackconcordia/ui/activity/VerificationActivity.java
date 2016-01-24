package sjcf.hackconcordia.com.hackconcordia.ui.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import sjcf.hackconcordia.com.hackconcordia.Keys;
import sjcf.hackconcordia.com.hackconcordia.R;

import sjcf.hackconcordia.com.hackconcordia.model.SnapVerification;
import sjcf.hackconcordia.com.hackconcordia.task.DownloadImageTask;

public class VerificationActivity extends AppCompatActivity {

    ImageView verificationImage;
    private SnapVerification mSnapVerification;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        verificationImage = (ImageView) findViewById(R.id.verificationImage);
        Picasso.with(this).load("http://i.imgur.com/xxBbgdQ.jpg").into(verificationImage);

        Bundle extras = getIntent().getExtras();
        mSnapVerification = extras.getParcelable(Keys.SNAP_VERIFICATION_PARCEABLE);



    }

}
