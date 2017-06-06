package ru.kphu.itis.contentlibrary.content_instruments;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;


public abstract class ContentInstrument extends AppCompatActivity{

    public static final String KEY_CONTENT_SOURCE_TYPE = "content_source_type";

    public static final String KEY_DATA = "data";

    protected abstract void setResult(@NonNull String uriStr);

}
