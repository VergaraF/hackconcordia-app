package sjcf.hackconcordia.com.hackconcordia.ui.adapter;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import sjcf.hackconcordia.com.hackconcordia.Keys;
import sjcf.hackconcordia.com.hackconcordia.R;
import sjcf.hackconcordia.com.hackconcordia.connector.TestDatabaseConnector;
import sjcf.hackconcordia.com.hackconcordia.model.SnapTreasure;
import sjcf.hackconcordia.com.hackconcordia.model.User;
import sjcf.hackconcordia.com.hackconcordia.ui.fragment.adapter.FindSnapTreasuresViewAdapter;

public class FindSnapTreasuresFragment extends Fragment {


    private Context mContext;

    private User mUser;
    private ArrayList<SnapTreasure> mFindSnapTreasures;

    private FindSnapTreasuresViewAdapter mAdapter;
    private View rootView;
    private RecyclerView mRecyclerView;


    public FindSnapTreasuresFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_find_snap_treasures, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.fragment_find_snap_treasures_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        fetchAndLoadData();
        return rootView;
    }

    private void fetchAndLoadData() {
        mUser = getArguments().getParcelable(Keys.USER_PARCELABLE);
        mFindSnapTreasures = TestDatabaseConnector.getInstance().getSnapFindsForUser(mUser);
        mAdapter = new FindSnapTreasuresViewAdapter(mContext, mFindSnapTreasures);
        mRecyclerView.setAdapter(mAdapter);
    }
}
