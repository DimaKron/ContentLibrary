package ru.kphu.itis.contentlibrary.content_source.content_camera;

import android.os.Parcel;
import android.support.annotation.NonNull;

import ru.kphu.itis.contentlibrary.R;
import ru.kphu.itis.contentlibrary.content_source.ContentSource;
import ru.kphu.itis.contentlibrary.content_source.ContentSourceType;

public abstract class ContentCamera extends ContentSource {

    protected ContentCamera(){
        super();
    }

    protected ContentCamera(Parcel in){
        super(in);
    }

    @NonNull
    @Override
    public ContentSourceType getContentSourceType() {
        return ContentSourceType.TYPE_CAMERA;
    }

    @Override
    public int getDefaultIconResId() {
        return R.drawable.ic_photo_camera_white_48dp;
    }

    @Override
    public int getDefaultLabelResId() {
        return R.string.label_camera;
    }
}
