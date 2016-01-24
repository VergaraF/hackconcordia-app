package sjcf.hackconcordia.com.hackconcordia.connector;


import java.util.ArrayList;

import sjcf.hackconcordia.com.hackconcordia.model.SnapTreasure;
import sjcf.hackconcordia.com.hackconcordia.model.SnapVerification;
import sjcf.hackconcordia.com.hackconcordia.model.User;

/*
* Hardcoding 101
* How not to code
* */
public class TestDatabaseConnector implements Connectable {
    private static TestDatabaseConnector mConnector;

    private TestDatabaseConnector() {
    }

    public static Connectable getInstance() {
        if (mConnector == null) {
            mConnector = new TestDatabaseConnector();
        }
        return mConnector;
    }

    @Override
    public User getUser(String email, String password) {
        User testUser = new User();
        testUser.email = "testemail@test.com";
        testUser.password = "password";
        testUser.points = 100;
        return testUser;
    }

    @Override
    public ArrayList<SnapTreasure> getSnapTreasuresForUser(User user) {
        int amount = 3;
        ArrayList<SnapTreasure> test = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            SnapTreasure testSnapTreasure = new SnapTreasure();

            User testCreateUser = new User();
            testCreateUser.email = "testcreateemail" + i + "@test.com";
            testCreateUser.password = "password";
            testCreateUser.points = i * 100;

            User testFoundByUser = new User();
            testCreateUser.email = "testfoundemail" + i + "@test.com";
            testCreateUser.password = "password";
            testCreateUser.points = i * 100;

            testSnapTreasure.createdByUser = testCreateUser;
            testSnapTreasure.foundByUser = testFoundByUser;

            testSnapTreasure.lat = 0;
            testSnapTreasure.lng = 0;

            testSnapTreasure.localityName = "Test locality " + i;

            ArrayList<String> testTags = new ArrayList<>();
            testTags.add("tag 1");
            testTags.add("tag 2");
            testSnapTreasure.tags = testTags;

            ArrayList<User> testPendingUsers = new ArrayList<>();
            User testPendingUser = new User();
            testPendingUser.email = "testpendinguser@test.com";
            testPendingUser.password = "password";
            testPendingUser.points = 100;
            testPendingUsers.add(testPendingUser);

            test.add(testSnapTreasure);
        }
        return test;
    }

    @Override
    public ArrayList<SnapTreasure> getSnapFindsForUser(User user) {
        int amount = 4;
        ArrayList<SnapTreasure> test = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            SnapTreasure testSnapTreasure = new SnapTreasure();

            User testCreateUser = new User();
            testCreateUser.email = "testcreateemail" + i + "@test.com";
            testCreateUser.password = "password";
            testCreateUser.points = i * 100;

            User testFoundByUser = new User();
            testCreateUser.email = "testfoundemail" + i + "@test.com";
            testCreateUser.password = "password";
            testCreateUser.points = i * 100;

            testSnapTreasure.createdByUser = testCreateUser;
            testSnapTreasure.foundByUser = testFoundByUser;

            testSnapTreasure.lat = -33.87365;
            testSnapTreasure.lng = 151.20689;

            testSnapTreasure.localityName = "Test locality " + i;

            ArrayList<String> testTags = new ArrayList<>();
            testTags.add("tag 1");
            testTags.add("tag 2");
            testSnapTreasure.tags = testTags;

            test.add(testSnapTreasure);
        }
        return test;
    }

    @Override
    public ArrayList<SnapVerification> getSnapVerificationsForUser(User user) {
        int amount = 3;
        ArrayList<SnapVerification> test = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            SnapVerification testSnap = new SnapVerification();

            // add snapTreasure
            SnapTreasure testTreasure = new SnapTreasure();
            testTreasure.lat = -33.87365;
            testTreasure.lng = 151.20689;
            testTreasure.localityName = "Montreal";

            // add tags
            ArrayList<String> testTags = new ArrayList<>();
            testTags.add("Tag 1");
            testTags.add("Tag 2");
            testTreasure.tags = testTags;

            testSnap.snapTreasure = testTreasure;

            // add submittedByUser
            User testTakenByUser = new User();
            testTakenByUser.email = "testTakenByUser@test.com";
            testTakenByUser.password = "password";
            testTakenByUser.points = 100;
            testSnap.submittedByUser = testTakenByUser;

            // add toBeVerifiedByUser
            User testToBeVerifiedByUser = new User();
            testTakenByUser.email = "testToBeVerifiedByUser@test.com";
            testTakenByUser.password = "password";
            testTakenByUser.points = 100;
            testSnap.toBeVerifiedByUser = testToBeVerifiedByUser;

            testSnap.photoUrl = null;
            testSnap.isVerified = 0;
            test.add(testSnap);
        }
        return test;
    }
}
