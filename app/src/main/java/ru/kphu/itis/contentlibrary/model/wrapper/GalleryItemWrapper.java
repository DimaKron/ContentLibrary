package ru.kphu.itis.contentlibrary.model.wrapper;

import android.database.Cursor;
import android.database.CursorWrapper;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import ru.kphu.itis.contentlibrary.model.entity.GalleryItem;

/**
 * Created by Дмитрий on 10.05.2017.
 */

public class GalleryItemWrapper extends CursorWrapper {

    public GalleryItemWrapper(Cursor cursor) {
        super(cursor);
    }

    @Nullable
    public GalleryItem getGalleryItem(){
        GalleryItem result = null;
        if (!isAfterLast() && !isBeforeFirst()){
            result = new GalleryItem();
            result.setId(getLong(getColumnIndex(MediaStore.Images.Media._ID)));
            result.setFilePath(getString(getColumnIndex(MediaStore.Images.Media.DATA)));
        }
        return result;
    }

    @NonNull
    public List<GalleryItem> getGalleryItems(){
        List<GalleryItem> result = new ArrayList<>();
        moveToFirst();
        while(!isAfterLast() && !isBeforeFirst()){
            result.add(getGalleryItem());
            moveToNext();
        }
        return result;
    }

}
