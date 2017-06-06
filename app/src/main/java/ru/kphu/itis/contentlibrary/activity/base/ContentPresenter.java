package ru.kphu.itis.contentlibrary.activity.base;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.util.Log;

import java.util.List;

import me.tatarka.rxloader.RxLoader;
import me.tatarka.rxloader.RxLoaderManager;
import me.tatarka.rxloader.RxLoaderObserver;
import ru.kphu.itis.contentlibrary.model.entity.GalleryItem;
import ru.kphu.itis.contentlibrary.model.wrapper.GalleryItemWrapper;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ContentPresenter {

    private static final String LOADER_GALLERY_ITEMS = "gallery_items";

    private static final String LOG_TAG = "ContentPresenter";

    private RxLoaderManager loaderManager;

    private RxLoader<List<GalleryItem>> galleryItemsLoader;

    private ContentView view;

    public ContentPresenter(@NonNull RxLoaderManager loaderManager, @NonNull ContentView view) {
        this.loaderManager = loaderManager;
        this.view = view;
    }

    public void loadGalleryImages(@NonNull Context context){
        galleryItemsLoader = loaderManager.create(LOADER_GALLERY_ITEMS,
                Observable.create((Observable.OnSubscribe<List<GalleryItem>>) subscriber -> {
                    String[] projection = new String[]{
                            MediaStore.Images.Media._ID,
                            MediaStore.Images.Media.DATA
                    };
                    Uri imagesUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                    String sortBy = MediaStore.Images.Media.DATE_TAKEN;

                    Cursor cursor = context.getContentResolver().query(imagesUri,
                            projection,
                            null,
                            null,
                            sortBy
                    );
                    List<GalleryItem> galleryItems = new GalleryItemWrapper(cursor).getGalleryItems();
                    cursor.close();
                    subscriber.onNext(galleryItems);
                    subscriber.onCompleted();
                }

                ).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread()),
                new RxLoaderObserver<List<GalleryItem>>() {
                    @Override
                    public void onNext(List<GalleryItem> galleryItems) {
                        view.onGalleryImagesLoadingFinished(galleryItems);
                    }

                    @Override
                    public void onCompleted() {
                        galleryItemsLoader.clear();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(LOG_TAG, e.getMessage());
                        galleryItemsLoader.clear();
                    }
                }).restart();
    }
}
