package com.gt.qiezikuaichuan.model;

/**
 * Created by 巴巴 on 2017/1/4.
 */

public class ImageFile {

    private String path;
    private String name;
    private long size;
    private String parentFilePath;

    public ImageFile() {
    }

    public ImageFile(String path, String name, long size, String parentFilePath) {
        this.path = path;
        this.name = name;
        this.size = size;
        this.parentFilePath = parentFilePath;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getParentFilePath() {
        return parentFilePath;
    }

    public void setParentFilePath(String parentFilePath) {
        this.parentFilePath = parentFilePath;
    }

    @Override
    public String toString() {
        return "ImageFile{" +
                "path='" + path + '\'' +
                ", name='" + name + '\'' +
                ", size=" + size +
                ", parentFilePath='" + parentFilePath + '\'' +
                '}';
    }
}
