package sjcf.hackconcordia.com.hackconcordia.ui.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.net.URL;

import sjcf.hackconcordia.com.hackconcordia.R;

public class VerificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        verificationImage = (ImageView) findViewById(R.id.verificationImage);

        try {
            URL address = new URL("http://imgur.com/gallery/xxBbgdQ");
            Bitmap bmp = BitmapFactory.decodeStream(address.openConnection().getInputStream());
            //verificationImage.setImageBitmap(bmp);
        } catch (IOException e) {
            throw new RuntimeException();
        }


    }

}
