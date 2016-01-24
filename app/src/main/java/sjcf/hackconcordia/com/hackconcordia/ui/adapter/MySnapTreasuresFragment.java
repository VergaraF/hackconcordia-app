package sjcf.hackconcordia.com.hackconcordia.ui.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import sjcf.hackconcordia.com.hackconcordia.Keys;
import sjcf.hackconcordia.com.hackconcordia.R;
import sjcf.hackconcordia.com.hackconcordia.connector.TestDatabaseConnector;
import sjcf.hackconcordia.com.hackconcordia.model.SnapTreasure;
import sjcf.hackconcordia.com.hackconcordia.model.User;
import sjcf.hackconcordia.com.hackconcordia.ui.fragment.adapter.MySnapTreasureViewAdapter;


public class MySnapTreasuresFragment extends Fragment {

    private Context mContext;

    private User mUser;
    private ArrayList<SnapTreasure> mMySnapTreasures;

    private MySnapTreasureViewAdapter mAdapter;
    private View rootView;
    private TextView mPointsTextView;
    private RecyclerView mRecyclerView;

    public MySnapTreasuresFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_my_snap_treasure, container, false);
        mPointsTextView = (TextView) rootView.findViewById(R.id.fragment_my_snap_treasure_points);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.fragment_my_snap_treasure_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        fetchAndLoadData();
        return rootView;
    }

    private void fetchAndLoadData() {
        mUser = getArguments().getParcelable(Keys.USER_PARCELABLE);
        mMySnapTreasures = TestDatabaseConnector.getInstance().getSnapTreasuresForUser(mUser);
        mAdapter = new MySnapTreasureViewAdapter(mContext, mMySnapTreasures);
        mRecyclerView.setAdapter(mAdapter);
        mPointsTextView.setText(mContext.getString(R.string.your_points) + mUser.points);
    }


}
