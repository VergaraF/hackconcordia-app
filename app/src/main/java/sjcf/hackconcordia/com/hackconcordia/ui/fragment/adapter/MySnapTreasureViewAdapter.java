package sjcf.hackconcordia.com.hackconcordia.ui.fragment.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import sjcf.hackconcordia.com.hackconcordia.R;
import sjcf.hackconcordia.com.hackconcordia.model.SnapTreasure;

import java.util.List;


public class MySnapTreasureViewAdapter extends RecyclerView.Adapter<MySnapTreasureViewAdapter.ViewHolder> {

    private final List<SnapTreasure> mValues;

    public MySnapTreasureViewAdapter(List<SnapTreasure> items) {
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
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public SnapTreasure mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
        }

    }
}
