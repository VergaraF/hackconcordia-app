package sjcf.hackconcordia.com.hackconcordia.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import sjcf.hackconcordia.com.hackconcordia.Keys;
import sjcf.hackconcordia.com.hackconcordia.R;
import sjcf.hackconcordia.com.hackconcordia.model.SnapTreasure;
import sjcf.hackconcordia.com.hackconcordia.model.User;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    private User mUser;
    private SnapTreasure mSnapTreasure;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        Bundle extras = getIntent().getExtras();

        mUser = extras.getParcelable(Keys.USER_PARCELABLE);
        mSnapTreasure = extras.getParcelable(Keys.SNAP_TREASURE_PARCEABLE);
        Button button = (Button) findViewById(R.id.activityMaps_foundTreasureButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Intent intent = new Intent(MapsActivity.this, NewSnapToVerifyActivity.class);
                intent.putExtra(Keys.SNAP_TREASURE_PARCEABLE, mSnapTreasure);
                intent.putExtra(Keys.USER_PARCELABLE, mUser);
                startActivity(intent);
            }
        });

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
//        double latitude  = -33.87365;
//        double longitude = 151.20689;
        double latitude  = mSnapTreasure.lat;
        double longitude = mSnapTreasure.lng;

        LatLng treasureLocation = new LatLng(latitude,longitude);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(treasureLocation, 16));

        Circle circle = mMap.addCircle(new CircleOptions()
                .center(treasureLocation)
                .radius(300)
                .strokeColor(Color.RED));



    }
}
