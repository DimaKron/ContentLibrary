package ru.kphu.itis.contentlibrary.activity.base;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import me.tatarka.rxloader.RxLoaderManager;
import ru.kphu.itis.contentlibrary.R;
import ru.kphu.itis.contentlibrary.content_instruments.image_gallery.DefaultImageGalleryActivity;
import ru.kphu.itis.contentlibrary.content_instruments.image_gallery.base.ImageGalleryActivity;
import ru.kphu.itis.contentlibrary.content_source.ContentSource;
import ru.kphu.itis.contentlibrary.content_source.ContentSourceType;
import ru.kphu.itis.contentlibrary.content_source.content_gallery.DefaultContentGallery;
import ru.kphu.itis.contentlibrary.model.entity.GalleryItem;


public abstract class ContentActivity extends AppCompatActivity implements ContentView {

    public static final String KEY_CONTENT_SOURCE_TYPE = "content_source_type";

    public static final String KEY_URI_STR = "uri";

    public static final String KEY_CONTENT_SOURCES = "content_sources";

    public static final String KEY_FAST_GALLERY_ENABLED = "fast_gallery_enabled";

    private static final int REQUEST_CODE_PERMISSION = 111;
    private static final int REQUEST_CODE_DOCUMENTS = 222;
    private static final int REQUEST_CODE_IMAGE_GALLERY = 333;

    private ArrayList<ContentSource> contentSources;

    private boolean fastGalleryEnabled;

    protected RecyclerView sourcesRecyclerView;

    protected RecyclerView galleryRecyclerView;

    private ContentSourcesAdapter contentSourcesAdapter;

    private FastGalleryAdapter fastGalleryAdapter;

    private ContentPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());

        presenter = new ContentPresenter(RxLoaderManager.get(this), this);

        contentSources = getIntent().getParcelableArrayListExtra(KEY_CONTENT_SOURCES);
        fastGalleryEnabled = getIntent().getBooleanExtra(KEY_FAST_GALLERY_ENABLED, false);

        contentSourcesAdapter = new ContentSourcesAdapter(contentSources);
        contentSourcesAdapter.setItemClickListener(this::onContentSourceClick);
        sourcesRecyclerView = (RecyclerView) findViewById(getSourcesRecyclerViewId());
        sourcesRecyclerView.setAdapter(contentSourcesAdapter);

        fastGalleryAdapter = new FastGalleryAdapter();
        fastGalleryAdapter.setItemClickListener(this::onFastGalleryClick);
        galleryRecyclerView = (RecyclerView) findViewById(getGalleryRecyclerViewId());
        galleryRecyclerView.setAdapter(fastGalleryAdapter);
        if(fastGalleryEnabled){
            galleryRecyclerView.setVisibility(View.VISIBLE);
            loadGalleryItems();
        }
    }

    private void loadGalleryItems(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkPermission()) {
                presenter.loadGalleryImages(this);
            } else {
                requestPermission();
            }
        } else {
            presenter.loadGalleryImages(this);
        }
    }

    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        return result == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.READ_EXTERNAL_STORAGE)) {
            Toast.makeText(this, R.string.permission_storage_rationale, Toast.LENGTH_LONG).show();
            galleryRecyclerView.setVisibility(View.GONE);
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE_PERMISSION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_PERMISSION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    presenter.loadGalleryImages(this);
                } else {
                    Toast.makeText(this, R.string.permission_storage_denied, Toast.LENGTH_LONG).show();
                    galleryRecyclerView.setVisibility(View.GONE);
                }
                break;
        }
    }

    @LayoutRes
    protected abstract int getLayoutResId();

    @IdRes
    protected abstract int getSourcesRecyclerViewId();

    @IdRes
    protected abstract int getGalleryRecyclerViewId();

    private void onContentSourceClick(@NonNull ContentSourceType sourceType){
        switch (sourceType){
            case TYPE_CAMERA:
                openCamera();
                break;
            case TYPE_GALLERY:
                openGalleryChoosing();
                break;
            case TYPE_VIDEO:
                openVideoChoosing();
                break;
            case TYPE_GRAFFITI:
                openGraffiti();
                break;
            case TYPE_DOCUMENT:
                openDocumentChoosing();
                break;
        }
    }

    private void onFastGalleryClick(@NonNull String filePath) {
        setResult(ContentSourceType.TYPE_GALLERY, Uri.fromFile(new File(filePath)).toString());
    }

    @Override
    public void onGalleryImagesLoadingFinished(@NonNull List<GalleryItem> galleryItems) {
        fastGalleryAdapter.setData(galleryItems);
    }

    private void setResult(@NonNull ContentSourceType sourceType, @NonNull String uriStr){
        Intent result = new Intent();
        result.putExtra(KEY_CONTENT_SOURCE_TYPE, sourceType);
        result.putExtra(KEY_URI_STR, uriStr);
        setResult(RESULT_OK, result);
        finish();
    }

    private void openDocumentChoosing(){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        startActivityForResult(intent, REQUEST_CODE_DOCUMENTS);
    }

    private void openCamera(){
    }

    private void openGalleryChoosing(){
        Intent intent = new Intent(this, DefaultImageGalleryActivity.class);
        startActivityForResult(intent, REQUEST_CODE_IMAGE_GALLERY);
    }

    private void openVideoChoosing(){

    }

    private void openGraffiti(){

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK){
            switch (requestCode){
                case REQUEST_CODE_DOCUMENTS:
                    setResult(ContentSourceType.TYPE_DOCUMENT, data.getDataString());
                    break;
                case REQUEST_CODE_IMAGE_GALLERY:
                    setResult(ContentSourceType.TYPE_GALLERY, data.getStringExtra(ImageGalleryActivity.KEY_DATA));
                    break;
            }
        }
    }
}
