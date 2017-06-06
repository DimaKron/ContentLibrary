package ru.kphu.itis.contentlibrary.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import ru.kphu.itis.contentlibrary.R;
import ru.kphu.itis.contentlibrary.activity.base.ContentActivity;

public class DefaultContentActivity extends ContentActivity {

    private static final int GALLERY_VIEW_COLUMNS_COUNT = 3;

    private RecyclerView.LayoutManager sourcesLayoutManager;

    private RecyclerView.LayoutManager galleryLayoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sourcesLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        sourcesRecyclerView.setLayoutManager(sourcesLayoutManager);

        galleryLayoutManager = new GridLayoutManager(this, GALLERY_VIEW_COLUMNS_COUNT);
        galleryRecyclerView.setLayoutManager(galleryLayoutManager);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_content;
    }

    @Override
    protected int getSourcesRecyclerViewId() {
        return R.id.recycler_view_sources;
    }

    @Override
    protected int getGalleryRecyclerViewId() {
        return R.id.recycler_view_gallery;
    }
}
