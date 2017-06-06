package ru.kphu.itis.contentlibrary.content_instruments.image_gallery.base;

import android.support.annotation.NonNull;

import java.util.List;

import ru.kphu.itis.contentlibrary.model.entity.GalleryItem;

public interface ImageGalleryView {

    void onLoadingFinished(@NonNull List<GalleryItem> galleryItems);

}
