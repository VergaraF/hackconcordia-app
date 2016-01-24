package sjcf.hackconcordia.com.hackconcordia.connector;


import java.util.ArrayList;

import sjcf.hackconcordia.com.hackconcordia.model.SnapTreasure;
import sjcf.hackconcordia.com.hackconcordia.model.SnapVerification;
import sjcf.hackconcordia.com.hackconcordia.model.User;

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
            testSnapTreasure.pendingUsers = testPendingUsers;

            test.add(testSnapTreasure);
        }
        return test;
    }

    @Override
    public ArrayList<SnapTreasure> getSnapFindsForUser(User user) {
        int amount = 10;
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
            testSnapTreasure.pendingUsers = testPendingUsers;

            test.add(testSnapTreasure);
        }
        return test;
    }

    @Override
    public ArrayList<SnapVerification> getSnapVerificationsForUser(User user) {
        int amount = 1;
        ArrayList<SnapVerification> test = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            SnapVerification testSnap = new SnapVerification();
            User testTakenByUser = new User();
            testTakenByUser.email = "testTakenByUser@test.com";
            testTakenByUser.password = "password";
            testTakenByUser.points = 100;
            testSnap.photoUrl = null;
            testSnap.takenByUser = testTakenByUser;
        }
        return test;
    }
}
