package ru.kphu.itis.contentlibrary.content_source;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

public abstract class ContentSource implements Parcelable {

    private int iconResId;
    private int labelResId;

    protected ContentSource(){}

    protected ContentSource(Parcel in) {
        iconResId = in.readInt();
        labelResId = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(iconResId);
        dest.writeInt(labelResId);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @NonNull
    public abstract ContentSourceType getContentSourceType();

    @DrawableRes
    public abstract int getDefaultIconResId();

    @StringRes
    public abstract int getDefaultLabelResId();

    @DrawableRes
    public int getIconResId(){
        return iconResId == 0? getDefaultIconResId(): iconResId;
    }

    public void setIconResId(@DrawableRes int iconResId) {
        this.iconResId = iconResId;
    }

    @StringRes
    public int getLabelResId() {
        return labelResId == 0? getDefaultLabelResId(): labelResId;
    }

    public void setLabelResId(@StringRes int labelResId) {
        this.labelResId = labelResId;
    }
}
