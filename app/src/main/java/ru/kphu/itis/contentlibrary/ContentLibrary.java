package ru.kphu.itis.contentlibrary;

import android.support.annotation.NonNull;

public class ContentLibrary {

    @NonNull
    public static ContentLibraryRequest getInstance(){
        return new ContentLibraryRequest();
    }

}
