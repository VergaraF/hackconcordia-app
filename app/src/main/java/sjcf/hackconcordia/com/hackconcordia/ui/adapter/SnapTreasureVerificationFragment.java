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
import sjcf.hackconcordia.com.hackconcordia.model.SnapVerification;
import sjcf.hackconcordia.com.hackconcordia.model.User;
import sjcf.hackconcordia.com.hackconcordia.ui.fragment.adapter.MySnapTreasureViewAdapter;
import sjcf.hackconcordia.com.hackconcordia.ui.fragment.adapter.SnapTreasureVerificationViewAdapter;

public class SnapTreasureVerificationFragment extends Fragment {

    private Context mContext;

    private User mUser;
    private ArrayList<SnapVerification> mSnapVerifications;

    private SnapTreasureVerificationViewAdapter mAdapter;
    private View rootView;
    private RecyclerView mRecyclerView;

    public SnapTreasureVerificationFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_snap_treasure_verification, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.snap_treasure_verification_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        fetchData();
        return rootView;
    }

    private void fetchData() {
        mUser = getArguments().getParcelable(Keys.USER_PARCELABLE);
        mSnapVerifications = TestDatabaseConnector.getInstance().getSnapVerificationsForUser(mUser);
        mAdapter = new SnapTreasureVerificationViewAdapter(mContext, mSnapVerifications);
        mRecyclerView.setAdapter(mAdapter);
    }

}
