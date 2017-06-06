package ru.kphu.itis.contentlibrary.content_source.content_video;

import android.os.Parcel;

public class DefaultContentVideo extends ContentVideo {

    public DefaultContentVideo(){
        super();
    }

    protected DefaultContentVideo(Parcel in) {
        super(in);
    }

    public static final Creator<DefaultContentVideo> CREATOR = new Creator<DefaultContentVideo>() {

        @Override
        public DefaultContentVideo createFromParcel(Parcel in) {
            return new DefaultContentVideo(in);
        }

        @Override
        public DefaultContentVideo[] newArray(int size) {
            return new DefaultContentVideo[size];
        }
    };
}
