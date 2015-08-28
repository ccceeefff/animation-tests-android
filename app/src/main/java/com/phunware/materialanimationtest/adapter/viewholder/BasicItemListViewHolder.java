package com.phunware.materialanimationtest.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.phunware.materialanimationtest.R;
import com.phunware.materialanimationtest.model.BasicItem;

/**
 * Created by cef on 8/27/15.
 */
public class BasicItemListViewHolder extends RecyclerView.ViewHolder {

    public ImageView mThumbnail;
    public TextView mTitle;
    public TextView mSubtitle;

    public BasicItemListViewHolder(View itemView) {
        super(itemView);
        mThumbnail = (ImageView) itemView.findViewById(R.id.thumbnail);
        mTitle = (TextView) itemView.findViewById(R.id.title);
        mSubtitle = (TextView) itemView.findViewById(R.id.subtitle);
    }

    public void bindData(BasicItem item){
        this.itemView.setBackgroundColor(item.getPrimaryColor());
        mTitle.setText(item.getTitle());
        mSubtitle.setText(item.getSubtitle());
        mTitle.setTextColor(item.getTextColor());
        mSubtitle.setTextColor(item.getTextColor());
        Glide.with(itemView.getContext()).load(item.getImageURL()).into(mThumbnail);
    }

}
