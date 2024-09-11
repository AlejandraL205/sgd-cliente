//src\main\java\com\mycompany\sgdcliente\model\FileModel.java
package com.mycompany.sgdcliente.model;

import java.util.Date;

public class FileModel {
    private String name;
    private String path;
    private Date lastModified;
    private long size;

    // Getters and Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }
}
