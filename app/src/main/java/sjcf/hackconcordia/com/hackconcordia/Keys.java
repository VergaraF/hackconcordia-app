package sjcf.hackconcordia.com.hackconcordia;


import com.cloudinary.Cloudinary;

import java.util.HashMap;
import java.util.Map;

public class Keys {
    public static final String USER_PARCELABLE = "USER_PARCELABLE";
    public static final String SNAP_TREASURE_PARCEABLE = "SNAP_TREASURE_PARCEABLE";
    public static final String SNAP_VERIFICATION_PARCEABLE = "SNAP_VERIFICATION_PARCEABLE";

    public static final String CLOUDINARY_GET_ENDPOINT = "http://res.cloudinary.com/heavenwinds/image/upload/";

    public static Map config;
    public static Cloudinary cloudinary;
    static {
        config = new HashMap();
        config.put("cloud_name", "heavenwinds");
        config.put("api_key", "674251262331853");
        config.put("api_secret", "d8r0ASovIaBgG3uMZQqpqjOAJN8");
        cloudinary = new Cloudinary(config);
    }


}
