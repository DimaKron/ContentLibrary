package ru.kphu.itis.contentlibrary.content_source.content_gallery;

import android.os.Parcel;
import android.support.annotation.NonNull;

import ru.kphu.itis.contentlibrary.R;
import ru.kphu.itis.contentlibrary.content_source.ContentSource;
import ru.kphu.itis.contentlibrary.content_source.ContentSourceType;

public abstract class ContentGallery extends ContentSource{

    protected ContentGallery(){
        super();
    }

    protected ContentGallery(Parcel in) {
        super(in);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
    }

    @NonNull
    @Override
    public ContentSourceType getContentSourceType() {
        return ContentSourceType.TYPE_GALLERY;
    }

    @Override
    public int getDefaultIconResId() {
        return R.drawable.ic_photo_white_48dp;
    }

    @Override
    public int getDefaultLabelResId() {
        return R.string.label_gallery;
    }
}
