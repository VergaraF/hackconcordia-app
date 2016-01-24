package sjcf.hackconcordia.com.hackconcordia.util;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by MLH-Admin on 1/23/2016.
 */
public class ImageDownloadHandler {


    public static void imageFetcher(Context context, String url, ImageView image) {
        Picasso.with(context).load(url).into(image);
    }

    // http://i.imgur.com/xxBbgdQ.jpg

    public static String urlConverter(String url) {
        String newURL = "http://i.imgur.com/";
        ArrayList<String> urlSegment = new ArrayList<String>();
        String temp = "";

        if(!url.isEmpty()) {
            for(int i = 0; i < url.length(); i++) {
                temp += url.charAt(i);
                if((url.charAt(i) == '/')) {
                    urlSegment.add(temp);
                    temp = "";
                }
            }
            urlSegment.add(temp);
            temp = "";
        }

        if(urlSegment.size() != 0) {
            temp = urlSegment.get(urlSegment.size()-1);
            newURL += temp;
            newURL += ".jpg";
        }

        return newURL;
    }


}
