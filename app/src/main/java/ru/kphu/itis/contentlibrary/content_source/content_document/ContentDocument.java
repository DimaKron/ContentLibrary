package ru.kphu.itis.contentlibrary.content_source.content_document;

import android.os.Parcel;
import android.support.annotation.NonNull;

import ru.kphu.itis.contentlibrary.R;
import ru.kphu.itis.contentlibrary.content_source.ContentSource;
import ru.kphu.itis.contentlibrary.content_source.ContentSourceType;

public abstract class ContentDocument extends ContentSource{

    protected ContentDocument(){
        super();
    }

    protected ContentDocument(Parcel in) {
        super(in);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
    }

    @NonNull
    @Override
    public ContentSourceType getContentSourceType() {
        return ContentSourceType.TYPE_DOCUMENT;
    }

    @Override
    public int getDefaultIconResId() {
        return R.drawable.ic_insert_drive_file_white_48dp;
    }

    @Override
    public int getDefaultLabelResId() {
        return R.string.label_document;
    }
}
