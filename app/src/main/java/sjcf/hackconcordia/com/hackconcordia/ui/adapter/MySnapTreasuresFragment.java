package sjcf.hackconcordia.com.hackconcordia.ui.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import sjcf.hackconcordia.com.hackconcordia.Keys;
import sjcf.hackconcordia.com.hackconcordia.R;
import sjcf.hackconcordia.com.hackconcordia.connector.TestDatabaseConnector;
import sjcf.hackconcordia.com.hackconcordia.model.SnapTreasure;
import sjcf.hackconcordia.com.hackconcordia.model.User;


public class MySnapTreasuresFragment extends Fragment {

    private User mUser;
    private ArrayList<SnapTreasure> mMySnapTreasures;

    public MySnapTreasuresFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fetchData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_snap_treasure, container, false);
        Log.i("MySnapTreasuresFragment", mUser.email);
        return view;
    }

    private void fetchData() {
        mUser = getArguments().getParcelable(Keys.USER_PARCELABLE);
        mMySnapTreasures = TestDatabaseConnector.getInstance().getSnapTreasuresForUser(mUser);
    }


}
