package sjcf.hackconcordia.com.hackconcordia.util;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import java.util.List;
import java.util.Locale;

/**
 * Created by MLH-Admin on 1/23/2016.
 */
public class LocationUtil {

    public String localityFinder(Context context, double lat, double lng) {

        Geocoder gcd = new Geocoder(context, Locale.getDefault());
        List<Address> addresses = gcd.getFromLocation(lat, lng, 0);
        if (addresses.size() > 0)
            System.out.println(addresses.get(0).getLocality());

    }
}
