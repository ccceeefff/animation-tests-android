package com.phunware.materialanimationtest.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.phunware.materialanimationtest.R;
import com.phunware.materialanimationtest.adapter.viewholder.BasicItemListViewHolder;
import com.phunware.materialanimationtest.model.BasicItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cef on 8/27/15.
 */
public class BasicItemListAdapter extends RecyclerView.Adapter<BasicItemListViewHolder> {

    private List<BasicItem> mItems = new ArrayList<BasicItem>();
    private OnItemClickListener mOnClickListener = null;

    public BasicItemListAdapter(List<BasicItem> items){
        mItems = items;
    }

    public BasicItem getItem(int i){
        if(i < mItems.size()){
            return mItems.get(i);
        } else {
            return null;
        }
    }

    @Override
    public BasicItemListViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_character, viewGroup, false);
        return new BasicItemListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BasicItemListViewHolder basicItemListViewHolder, final int i) {
        basicItemListViewHolder.bindData(getItem(i));
        basicItemListViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mOnClickListener != null){
                    mOnClickListener.onItemClicked(BasicItemListAdapter.this, i, getItem(i));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mOnClickListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClicked(BasicItemListAdapter adapter, int pos, BasicItem item);
    }

}
