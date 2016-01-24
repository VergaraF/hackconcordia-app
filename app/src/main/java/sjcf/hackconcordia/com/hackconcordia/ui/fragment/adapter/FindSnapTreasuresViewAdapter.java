package sjcf.hackconcordia.com.hackconcordia.ui.fragment.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import sjcf.hackconcordia.com.hackconcordia.Keys;
import sjcf.hackconcordia.com.hackconcordia.R;
import sjcf.hackconcordia.com.hackconcordia.model.SnapTreasure;
import sjcf.hackconcordia.com.hackconcordia.ui.activity.MainActivity;
import sjcf.hackconcordia.com.hackconcordia.ui.activity.MapsActivity;
import sjcf.hackconcordia.com.hackconcordia.ui.activity.NewSnapActivity;
import sjcf.hackconcordia.com.hackconcordia.util.StringUtil;

import java.util.List;


public class FindSnapTreasuresViewAdapter extends RecyclerView.Adapter<FindSnapTreasuresViewAdapter.ViewHolder> {

    private Context mContext;
    private final List<SnapTreasure> mValues;


    public FindSnapTreasuresViewAdapter(Context context, List<SnapTreasure> items) {
        mContext = context;
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_find_snap_treasures_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mUserTextView.setText(holder.mItem.createdByUser.email);
        holder.mTagsTextView.setText(StringUtil.toCommaString(holder.mItem.tags));
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final View mView;
        public final TextView mUserTextView;
        public final TextView mTagsTextView;

        public SnapTreasure mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mUserTextView = (TextView) mView.findViewById(R.id.fragment_find_snap_treasure_item_user);
            mTagsTextView = (TextView) mView.findViewById(R.id.fragment_find_snap_treasure_item_tags);
            mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent mapIntent = new Intent(mContext, MapsActivity.class);
                    mapIntent.putExtra(Keys.SNAP_TREASURE_PARCEABLE, mItem);
                    mapIntent.putExtra(Keys.USER_PARCELABLE, ((MainActivity)mContext).getLoggedInUser());
                    mContext.startActivity(mapIntent);
                }
            });
        }

    }
}
