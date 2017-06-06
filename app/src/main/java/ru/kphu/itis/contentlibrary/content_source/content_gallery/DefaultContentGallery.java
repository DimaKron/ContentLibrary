package ru.kphu.itis.contentlibrary.content_source.content_gallery;

import android.os.Parcel;
import android.os.Parcelable;

import ru.kphu.itis.contentlibrary.content_source.ContentSource;

public class DefaultContentGallery extends ContentGallery {

    public DefaultContentGallery(){
        super();
    }

    protected DefaultContentGallery(Parcel in) {
        super(in);
    }

    public static final Creator<DefaultContentGallery> CREATOR = new Creator<DefaultContentGallery>() {

        @Override
        public DefaultContentGallery createFromParcel(Parcel in) {
            return new DefaultContentGallery(in);
        }

        @Override
        public DefaultContentGallery[] newArray(int size) {
            return new DefaultContentGallery[size];
        }
    };
}
