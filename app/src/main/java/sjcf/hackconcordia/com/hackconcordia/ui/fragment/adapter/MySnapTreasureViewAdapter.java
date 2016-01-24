package sjcf.hackconcordia.com.hackconcordia.ui.fragment.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import sjcf.hackconcordia.com.hackconcordia.R;
import sjcf.hackconcordia.com.hackconcordia.model.SnapTreasure;

import java.util.List;


public class MySnapTreasureViewAdapter extends RecyclerView.Adapter<MySnapTreasureViewAdapter.ViewHolder> {

    private Context mContext;

    private final List<SnapTreasure> mValues;

    public MySnapTreasureViewAdapter(Context context, List<SnapTreasure> items) {
        mContext = context;
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_my_snap_treasure_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mLocationTextView.setText(holder.mItem.localityName);
        String foundBy = holder.mItem.foundByUser.email;
        if (foundBy == null || foundBy.isEmpty()) {
            foundBy = mContext.getString(R.string.nobody_found);
        }
        holder.mFoundByUserTextView.setText(foundBy);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mLocationTextView;
        public final TextView mFoundByUserTextView;

        public SnapTreasure mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mLocationTextView = (TextView) mView.findViewById(R.id.fragment_my_snap_treasure_item_location);
            mFoundByUserTextView = (TextView) mView.findViewById(R.id.fragment_my_snap_treasure_item_found_by);
        }

    }
}
