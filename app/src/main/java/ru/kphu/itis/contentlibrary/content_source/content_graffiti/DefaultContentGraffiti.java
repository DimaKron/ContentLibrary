package ru.kphu.itis.contentlibrary.content_source.content_graffiti;

import android.os.Parcel;

public class DefaultContentGraffiti extends ContentGraffiti {

    public DefaultContentGraffiti(){
        super();
    }

    protected DefaultContentGraffiti(Parcel in) {
        super(in);
    }

    public static final Creator<DefaultContentGraffiti> CREATOR = new Creator<DefaultContentGraffiti>() {

        @Override
        public DefaultContentGraffiti createFromParcel(Parcel in) {
            return new DefaultContentGraffiti(in);
        }

        @Override
        public DefaultContentGraffiti[] newArray(int size) {
            return new DefaultContentGraffiti[size];
        }
    };
}
