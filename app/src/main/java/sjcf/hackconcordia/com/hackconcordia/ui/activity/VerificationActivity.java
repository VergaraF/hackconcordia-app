package sjcf.hackconcordia.com.hackconcordia.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import sjcf.hackconcordia.com.hackconcordia.Keys;
import sjcf.hackconcordia.com.hackconcordia.R;

import sjcf.hackconcordia.com.hackconcordia.model.SnapVerification;
import sjcf.hackconcordia.com.hackconcordia.model.User;

public class VerificationActivity extends AppCompatActivity {

    private ImageView mVerificationImage;
    private TextView mTakenByUserTextView;
    private Button mVerifyButton;

    private User mUser;
    private SnapVerification mSnapVerification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle extras = getIntent().getExtras();
        mSnapVerification = extras.getParcelable(Keys.SNAP_VERIFICATION_PARCEABLE);
        mUser = extras.getParcelable(Keys.USER_PARCELABLE);


        mVerificationImage = (ImageView) findViewById(R.id.content_verification_image);
        mTakenByUserTextView = (TextView) findViewById(R.id.content_verification_taken_by);
        mVerifyButton = (Button) findViewById(R.id.content_verification_verify);

        Picasso.with(this).load(mSnapVerification.photoUrl).into(mVerificationImage);
        mTakenByUserTextView.setText(getString(R.string.taken_by) + mSnapVerification.toBeVerifiedByUser.email);
        mVerifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSnapVerification.isVerified = 1;
                mSnapVerification.save();
                // TODO: save snap verification to database with the verified set to true
            }
        });





    }

}
