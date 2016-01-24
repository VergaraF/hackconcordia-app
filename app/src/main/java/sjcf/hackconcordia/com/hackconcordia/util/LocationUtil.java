package sjcf.hackconcordia.com.hackconcordia.util;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Locale;

/**
 * Created by MLH-Admin on 1/23/2016.
 */
public class LocationUtil {


    private static String TAG = "LocationUtil: ";
    public static final String COORDINATE_PRECISION = "#.###";


    public static String localityFinder(Context context, double lat, double lng) {

        Geocoder gcd = new Geocoder(context, Locale.getDefault());

        try {
            List<Address> addresses = gcd.getFromLocation(lat, lng, 1);
            if (addresses.size() > 0)
                return addresses.get(0).getLocality();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }


    public static double[] getLocation(Context context)
    {
        Location location = null;
        double[] currentLocation = new double[2];
        LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        LocationProvider lp = lm.getProvider(lm.NETWORK_PROVIDER);

        if(lm != null && lp != null && lm.isProviderEnabled(lp.getName())) {
            location = lm.getLastKnownLocation(lm.NETWORK_PROVIDER);
            currentLocation[0] = location.getLatitude();
            currentLocation[1] = location.getLongitude();

        }else{
            Log.i("Error: ", "Please enable location services");
        }
        return currentLocation;
    }

    public static String getLocalityName(Context con){
        double[] location = getLocation(con);
        double lat = location[0];
        double lng = location[1];
        return localityFinder(con, lat,lng);
    }
}
