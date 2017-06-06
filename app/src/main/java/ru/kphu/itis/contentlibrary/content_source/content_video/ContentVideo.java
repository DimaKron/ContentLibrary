package ru.kphu.itis.contentlibrary.content_source.content_video;

import android.os.Parcel;
import android.support.annotation.NonNull;

import ru.kphu.itis.contentlibrary.R;
import ru.kphu.itis.contentlibrary.content_source.ContentSource;
import ru.kphu.itis.contentlibrary.content_source.ContentSourceType;

public abstract class ContentVideo extends ContentSource{

    protected ContentVideo(){
        super();
    }

    protected ContentVideo(Parcel in) {
        super(in);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
    }

    @NonNull
    @Override
    public ContentSourceType getContentSourceType() {
        return ContentSourceType.TYPE_VIDEO;
    }

    @Override
    public int getDefaultIconResId() {
        return R.drawable.ic_video_library_white_48dp;
    }

    @Override
    public int getDefaultLabelResId() {
        return R.string.label_video;
    }
}
