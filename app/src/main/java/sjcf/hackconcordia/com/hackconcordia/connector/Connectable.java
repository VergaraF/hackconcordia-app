package sjcf.hackconcordia.com.hackconcordia.connector;

import java.util.ArrayList;

import sjcf.hackconcordia.com.hackconcordia.model.SnapTreasure;
import sjcf.hackconcordia.com.hackconcordia.model.SnapVerification;
import sjcf.hackconcordia.com.hackconcordia.model.User;

public interface Connectable {
    

    // Used when in login
    User getUser(String email, String password);

    // Used in MySnapTreasuresFragment
    ArrayList<SnapTreasure> getSnapTreasuresForUser(User user);

    // Used in FindSnapTreasuresFragment
    ArrayList<SnapTreasure> getSnapFindsForUser(User user);

    // Used in FindSnapTreasuresFragment
    ArrayList<SnapVerification> getSnapVerificationsForUser(User user);








}
