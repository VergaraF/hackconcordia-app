package sjcf.hackconcordia.com.hackconcordia.ui.adapter;


import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import sjcf.hackconcordia.com.hackconcordia.Keys;
import sjcf.hackconcordia.com.hackconcordia.R;
import sjcf.hackconcordia.com.hackconcordia.model.User;

public class FindSnapTreasuresFragment extends Fragment {

    private User mUser;

    public FindSnapTreasuresFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fetchData();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_find_snap_treasures, container, false);
        return view;
    }

    private void fetchData() {
        mUser = getArguments().getParcelable(Keys.USER_PARCELABLE);
    }
}
