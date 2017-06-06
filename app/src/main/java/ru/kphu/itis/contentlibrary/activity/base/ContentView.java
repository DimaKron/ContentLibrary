package ru.kphu.itis.contentlibrary.activity.base;

import android.support.annotation.NonNull;

import java.util.List;

import ru.kphu.itis.contentlibrary.model.entity.GalleryItem;

public interface ContentView {

    void onGalleryImagesLoadingFinished(@NonNull List<GalleryItem> galleryItems);
}
