package ru.kphu.itis.contentlibrary.content_source.content_document;

import android.os.Parcel;

public class DefaultContentDocument extends ContentDocument {

    public DefaultContentDocument(){
        super();
    }

    protected DefaultContentDocument(Parcel in) {
        super(in);
    }

    public static final Creator<DefaultContentDocument> CREATOR = new Creator<DefaultContentDocument>() {

        @Override
        public DefaultContentDocument createFromParcel(Parcel in) {
            return new DefaultContentDocument(in);
        }

        @Override
        public DefaultContentDocument[] newArray(int size) {
            return new DefaultContentDocument[size];
        }
    };

}
