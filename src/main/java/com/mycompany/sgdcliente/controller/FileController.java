//src\main\java\com\mycompany\sgdcliente\controller\FileController.java
package com.mycompany.sgdcliente.controller;

import com.mycompany.sgdcliente.model.FileModel;
import com.mycompany.sgdcliente.service.FileService;

public class FileController {
    private final FileService fileService;

    public FileController() {
        this.fileService = new FileService();
    }

    public FileModel getFileDetails(String fileName) {
        return fileService.getFileDetails(fileName);
    }

    public void downloadFile(String fileName) {
        fileService.downloadFile(fileName);
    }

    public void renameFile(String oldName, String newName) {
        fileService.renameFile(oldName, newName);
    }

    public void deleteFile(String fileName) {
        fileService.deleteFile(fileName);
    }
}
