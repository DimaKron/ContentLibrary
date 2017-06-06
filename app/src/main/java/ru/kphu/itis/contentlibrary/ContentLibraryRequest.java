package ru.kphu.itis.contentlibrary;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import ru.kphu.itis.contentlibrary.activity.base.ContentActivity;
import ru.kphu.itis.contentlibrary.activity.DefaultContentActivity;
import ru.kphu.itis.contentlibrary.content_source.ContentSource;
import ru.kphu.itis.contentlibrary.content_source.ContentSourceType;
import ru.kphu.itis.contentlibrary.content_source.content_camera.ContentCamera;
import ru.kphu.itis.contentlibrary.content_source.content_camera.DefaultContentCamera;
import ru.kphu.itis.contentlibrary.content_source.content_document.ContentDocument;
import ru.kphu.itis.contentlibrary.content_source.content_document.DefaultContentDocument;
import ru.kphu.itis.contentlibrary.content_source.content_gallery.ContentGallery;
import ru.kphu.itis.contentlibrary.content_source.content_gallery.DefaultContentGallery;
import ru.kphu.itis.contentlibrary.content_source.content_graffiti.ContentGraffiti;
import ru.kphu.itis.contentlibrary.content_source.content_graffiti.DefaultContentGraffiti;
import ru.kphu.itis.contentlibrary.content_source.content_video.ContentVideo;
import ru.kphu.itis.contentlibrary.content_source.content_video.DefaultContentVideo;

public class ContentLibraryRequest {

    private ArrayList<ContentSource> contentSources;

    private Map<ContentSourceType, Integer> iconIds;

    private Map<ContentSourceType, Integer> labelIds;

    public ContentLibraryRequest() {
        contentSources = new ArrayList<>();
        iconIds = new HashMap<>();
        labelIds = new HashMap<>();
    }

    public ContentLibraryRequest addSource(@NonNull ContentSource contentSource) {
        if (!existsSourceType(contentSource.getContentSourceType())) {
            contentSources.add(contentSource);
        }
        return this;
    }

    public ContentLibraryRequest addCamera() {
        return addCamera(new DefaultContentCamera());
    }

    public ContentLibraryRequest addCamera(@NonNull ContentCamera contentCamera) {
        return addSource(contentCamera);
    }

    public ContentLibraryRequest addGallery() {
        return addGallery(new DefaultContentGallery());
    }

    public ContentLibraryRequest addGallery(@NonNull ContentGallery contentGallery) {
        return addSource(contentGallery);
    }

    public ContentLibraryRequest addVideo() {
        return addVideo(new DefaultContentVideo());
    }

    public ContentLibraryRequest addVideo(@NonNull ContentVideo contentVideo) {
        return addSource(contentVideo);
    }

    public ContentLibraryRequest addGraffiti() {
        return addGraffiti(new DefaultContentGraffiti());
    }

    public ContentLibraryRequest addGraffiti(@NonNull ContentGraffiti contentGraffiti) {
        return addSource(contentGraffiti);
    }

    public ContentLibraryRequest addDocument() {
        return addDocument(new DefaultContentDocument());
    }

    public ContentLibraryRequest addDocument(@NonNull ContentDocument contentDocument) {
        return addSource(contentDocument);
    }

    public void setSourceIcon(@NonNull ContentSourceType sourceType, @DrawableRes int iconRes) {
        iconIds.put(sourceType, iconRes);
    }

    public void setCameraIcon(@DrawableRes int iconRes) {
        setSourceIcon(ContentSourceType.TYPE_CAMERA, iconRes);
    }

    public void setGalleryIcon(@DrawableRes int iconRes) {
        setSourceIcon(ContentSourceType.TYPE_GALLERY, iconRes);
    }

    public void setVideoIcon(@DrawableRes int iconRes) {
        setSourceIcon(ContentSourceType.TYPE_VIDEO, iconRes);
    }

    public void setGraffitiIcon(@DrawableRes int iconRes) {
        setSourceIcon(ContentSourceType.TYPE_GRAFFITI, iconRes);
    }

    public void setDocumentIcon(@DrawableRes int iconRes) {
        setSourceIcon(ContentSourceType.TYPE_DOCUMENT, iconRes);
    }

    public void setSourceLabel(@NonNull ContentSourceType sourceType, @StringRes int labelRes) {
        labelIds.put(sourceType, labelRes);
    }

    public void setCameraLabel(@StringRes int labelRes) {
        setSourceLabel(ContentSourceType.TYPE_CAMERA, labelRes);
    }

    public void setGalleryLabel(@StringRes int labelRes) {
        setSourceLabel(ContentSourceType.TYPE_GALLERY, labelRes);
    }

    public void setVideoLabel(@StringRes int labelRes) {
        setSourceLabel(ContentSourceType.TYPE_VIDEO, labelRes);
    }

    public void setGraffitiLabel(@StringRes int labelRes) {
        setSourceLabel(ContentSourceType.TYPE_GRAFFITI, labelRes);
    }

    public void setDocumentLabel(@StringRes int labelRes) {
        setSourceLabel(ContentSourceType.TYPE_DOCUMENT, labelRes);
    }

    @NonNull
    public ContentLibraryActivityRequest asActivity() {
        inflateContentSources();
        return new ContentLibraryActivityRequest(this);
    }

    @NonNull
    public ContentLibraryActivityRequest asActivity(@NonNull Class activityClass) {
        inflateContentSources();
        return new ContentLibraryActivityRequest(this, activityClass);
    }

    private void inflateContentSources() {
        inflateIconsForContentSources();
        inflateLabelsForContentSources();
    }

    private void inflateLabelsForContentSources() {
        for (Map.Entry<ContentSourceType, Integer> entry : labelIds.entrySet()) {
            ContentSource contentSource = findContentSource(entry.getKey());
            if (contentSource != null) {
                contentSource.setLabelResId(entry.getValue());
            }
        }
    }

    private void inflateIconsForContentSources() {
        for (Map.Entry<ContentSourceType, Integer> entry : iconIds.entrySet()) {
            ContentSource contentSource = findContentSource(entry.getKey());
            if (contentSource != null) {
                contentSource.setIconResId(entry.getValue());
            }
        }
    }

    @Nullable
    private ContentSource findContentSource(@NonNull ContentSourceType type) {
        for (ContentSource source : contentSources) {
            if (source.getContentSourceType().equals(type)) {
                return source;
            }
        }
        return null;
    }

    private boolean existsSourceType(@NonNull ContentSourceType type) {
        for (ContentSource source : contentSources) {
            if (source.getContentSourceType().equals(type)) {
                return true;
            }
        }
        return false;
    }

    @NonNull
    public ArrayList<ContentSource> getContentSources() {
        return contentSources;
    }

}
