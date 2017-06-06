package ru.kphu.itis.contentlibrary.content_instruments.image_gallery;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;

import ru.kphu.itis.contentlibrary.R;
import ru.kphu.itis.contentlibrary.content_instruments.image_gallery.base.ImageGalleryActivity;

public class DefaultImageGalleryActivity extends ImageGalleryActivity {

    private static final int GALLERY_COLUMNS_COUNT = 4;

    private GridLayoutManager galleryLayoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        galleryLayoutManager = new GridLayoutManager(this, GALLERY_COLUMNS_COUNT);
        galleryRecyclerView.setLayoutManager(galleryLayoutManager);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_image_gallery;
    }

    @Override
    protected int getGalleryRecyclerViewId() {
        return R.id.recycler_view_gallery;
    }

    @Override
    protected int getPreviewImageViewId() {
        return R.id.image_view_preview;
    }
}
