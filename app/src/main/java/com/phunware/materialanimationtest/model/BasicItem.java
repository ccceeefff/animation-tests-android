package com.phunware.materialanimationtest.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by cef on 8/27/15.
 */
public class BasicItem implements Parcelable {

    private String mTitle;
    private String mSubtitle;
    private int mPrimaryColor;
    private int mSecondaryColor;
    private int mTextColor;
    private String mImageURL;

    public BasicItem(){

    }

    public String getImageURL() {
        return mImageURL;
    }

    public void setImageURL(String mImageURL) {
        this.mImageURL = mImageURL;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getSubtitle() {
        return mSubtitle;
    }

    public void setSubtitle(String mSubtitle) {
        this.mSubtitle = mSubtitle;
    }

    public int getPrimaryColor() {
        return mPrimaryColor;
    }

    public void setPrimaryColor(int mPrimaryColor) {
        this.mPrimaryColor = mPrimaryColor;
    }

    public int getSecondaryColor() {
        return mSecondaryColor;
    }

    public void setSecondaryColor(int mSecondaryColor) {
        this.mSecondaryColor = mSecondaryColor;
    }

    public int getTextColor() {
        return mTextColor;
    }

    public void setTextColor(int mTextColor) {
        this.mTextColor = mTextColor;
    }

    public static Parcelable.Creator<BasicItem> CREATOR = new Creator<BasicItem>(){
        @Override
        public BasicItem createFromParcel(Parcel source) {
            return new BasicItem(source);
        }

        @Override
        public BasicItem[] newArray(int size) {
            return new BasicItem[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    public BasicItem(Parcel source){
        mTitle = source.readString();
        mSubtitle = source.readString();
        mImageURL = source.readString();
        mPrimaryColor = source.readInt();
        mSecondaryColor = source.readInt();
        mTextColor = source.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mTitle);
        dest.writeString(mSubtitle);
        dest.writeString(mImageURL);
        dest.writeInt(mPrimaryColor);
        dest.writeInt(mSecondaryColor);
        dest.writeInt(mTextColor);
    }

}
