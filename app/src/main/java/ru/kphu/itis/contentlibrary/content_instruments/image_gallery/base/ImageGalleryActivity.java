package ru.kphu.itis.contentlibrary.content_instruments.image_gallery.base;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.List;

import me.tatarka.rxloader.RxLoaderManager;
import ru.kphu.itis.contentlibrary.content_instruments.ContentInstrument;
import ru.kphu.itis.contentlibrary.content_source.ContentSourceType;
import ru.kphu.itis.contentlibrary.model.entity.GalleryItem;

public abstract class ImageGalleryActivity extends ContentInstrument implements ImageGalleryView {

    private ImageGalleryPresenter presenter;

    private ImageGalleryAdapter galleryAdapter;

    protected RecyclerView galleryRecyclerView;

    private ImageView previewImageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());

        presenter = new ImageGalleryPresenter(RxLoaderManager.get(this), this);

        galleryAdapter = new ImageGalleryAdapter();
        galleryAdapter.setItemClickListener(this::onImageGalleryClick);
        galleryRecyclerView = (RecyclerView) findViewById(getGalleryRecyclerViewId());
        galleryRecyclerView.setAdapter(galleryAdapter);

        previewImageView = (ImageView) findViewById(getPreviewImageViewId());

        presenter.loadGalleryImages(this);
    }

    private void onImageGalleryClick(String filePath) {
        showPreview(filePath);
    }

    private void showPreview(@NonNull String filePath) {
        Glide.with(this)
                .load(Uri.fromFile(new File(filePath)).toString())
                .dontAnimate()
                .into(previewImageView);
    }

    @Override
    protected void setResult(@NonNull String uriStr) {
        Intent result = new Intent();
        result.putExtra(KEY_CONTENT_SOURCE_TYPE, ContentSourceType.TYPE_GALLERY);
        result.putExtra(KEY_DATA, uriStr);
        setResult(RESULT_OK, result);
        finish();
    }

    @Override
    public void onLoadingFinished(@NonNull List<GalleryItem> galleryItems) {
        galleryAdapter.setData(galleryItems);

        if(!galleryItems.isEmpty()){
            showPreview(galleryItems.get(0).getFilePath());
        }
    }

    @LayoutRes
    protected abstract int getLayoutResId();

    @IdRes
    protected abstract int getGalleryRecyclerViewId();

    @IdRes
    protected abstract int getPreviewImageViewId();
}
