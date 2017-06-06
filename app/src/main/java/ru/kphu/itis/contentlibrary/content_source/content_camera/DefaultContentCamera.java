package ru.kphu.itis.contentlibrary.content_source.content_camera;

import android.os.Parcel;


public class DefaultContentCamera extends ContentCamera {

    public DefaultContentCamera(){
        super();
    }

    protected DefaultContentCamera(Parcel in) {
        super(in);
    }

    public static final Creator<DefaultContentCamera> CREATOR = new Creator<DefaultContentCamera>() {

        @Override
        public DefaultContentCamera createFromParcel(Parcel in) {
            return new DefaultContentCamera(in);
        }

        @Override
        public DefaultContentCamera[] newArray(int size) {
            return new DefaultContentCamera[size];
        }
    };
}
