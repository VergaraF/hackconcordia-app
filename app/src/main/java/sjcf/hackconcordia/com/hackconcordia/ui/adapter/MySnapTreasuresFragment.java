package sjcf.hackconcordia.com.hackconcordia.ui.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import sjcf.hackconcordia.com.hackconcordia.R;


public class MySnapTreasuresFragment extends Fragment {


    public MySnapTreasuresFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_snap_treasure, container, false);
        return view;
    }


}
