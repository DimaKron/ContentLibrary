package ru.kphu.itis.contentlibrary;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import ru.kphu.itis.contentlibrary.activity.DefaultContentActivity;
import ru.kphu.itis.contentlibrary.activity.base.ContentActivity;

public class ContentLibraryActivityRequest {

    private ContentLibraryRequest baseRequest;

    private Class activityClass;

    private boolean fastGalleryEnabled = true;

    public ContentLibraryActivityRequest(@NonNull ContentLibraryRequest baseRequest){
        this.baseRequest = baseRequest;
        this.activityClass = DefaultContentActivity.class;
    }

    public ContentLibraryActivityRequest(@NonNull ContentLibraryRequest baseRequest, @NonNull Class activityClass) {
        this.baseRequest = baseRequest;
        this.activityClass = activityClass;
    }

    @NonNull
    public ContentLibraryActivityRequest setFastGalleryImagesEnabled(boolean enabled){
        fastGalleryEnabled = enabled;
        return this;
    }

    @NonNull
    public Intent getIntent(@NonNull Context context){
        return new Intent(context, activityClass)
                .putParcelableArrayListExtra(ContentActivity.KEY_CONTENT_SOURCES, baseRequest.getContentSources())
                .putExtra(ContentActivity.KEY_FAST_GALLERY_ENABLED, fastGalleryEnabled);
    }

    public void start(@NonNull Activity activity, int requestCode){
        activity.startActivityForResult(getIntent(activity), requestCode);
    }
}
