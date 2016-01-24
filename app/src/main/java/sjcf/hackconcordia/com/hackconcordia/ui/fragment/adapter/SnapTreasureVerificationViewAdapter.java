package sjcf.hackconcordia.com.hackconcordia.ui.fragment.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import sjcf.hackconcordia.com.hackconcordia.Keys;
import sjcf.hackconcordia.com.hackconcordia.R;
import sjcf.hackconcordia.com.hackconcordia.model.SnapVerification;
import sjcf.hackconcordia.com.hackconcordia.ui.activity.MainActivity;
import sjcf.hackconcordia.com.hackconcordia.ui.activity.MapsActivity;
import sjcf.hackconcordia.com.hackconcordia.ui.activity.VerificationActivity;


import java.util.List;


public class SnapTreasureVerificationViewAdapter extends RecyclerView.Adapter<SnapTreasureVerificationViewAdapter.ViewHolder> {

    private Context mContext;
    private final List<SnapVerification> mValues;

    public SnapTreasureVerificationViewAdapter(Context context, List<SnapVerification> items) {
        mValues = items;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_snap_treasure_verification_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mLocationTextView.setText(holder.mItem.snapTreasure.localityName);
        holder.mVerificationTextView.setText(mContext.getString(R.string.needs_to_be_verified));
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mLocationTextView;
        public final TextView mVerificationTextView;


        public SnapVerification mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mLocationTextView = (TextView) mView.findViewById(R.id.fragment_snap_verification_item_location);
            mVerificationTextView = (TextView) mView.findViewById(R.id.fragment_snap_verification_item_verify_text);
            mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent mapIntent = new Intent(mContext, VerificationActivity.class);
                    mapIntent.putExtra(Keys.SNAP_VERIFICATION_PARCEABLE, mItem);
                    mapIntent.putExtra(Keys.USER_PARCELABLE, ((MainActivity) mContext).getLoggedInUser());
                    mContext.startActivity(mapIntent);
                }
            });
        }
    }
}
