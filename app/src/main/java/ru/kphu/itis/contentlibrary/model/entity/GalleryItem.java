package ru.kphu.itis.contentlibrary.model.entity;

/**
 * Created by Дмитрий on 10.05.2017.
 */

public class GalleryItem {

    private String filePath;

    private long id;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
