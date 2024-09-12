// src\main\java\com\mycompany\sgdcliente\controller\FileController.java
package com.mycompany.sgdcliente.controller;

import com.mycompany.sgdcliente.model.FileModel;
import com.mycompany.sgdcliente.service.FileService;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileController {
    private static final Logger LOGGER = Logger.getLogger(FileController.class.getName());
    private final FileService fileService;

    public FileController(String host, int port) {
        this.fileService = new FileService(host, port);
    }

    public FileModel getFileDetails(String fileName) {
        try {
            return fileService.getFileDetails(fileName);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error al obtener detalles del archivo: " + fileName, e);
            return null;
        }
    }

    public void downloadFile(String fileName, File outputFile) {
        if (fileName == null || outputFile == null) {
            LOGGER.log(Level.WARNING, "Nombre del archivo o archivo de salida son nulos.");
            return;
        }
        
        try {
            fileService.downloadFile(fileName, outputFile);
            System.out.println("Archivo descargado exitosamente: " + fileName);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error al descargar el archivo: " + fileName, e);
        }
    }

    public void renameFile(String oldName, String newName) {
        if (oldName == null || newName == null) {
            LOGGER.log(Level.WARNING, "Nombres de archivos nulos.");
            return;
        }
        
        try {
            fileService.renameFile(oldName, newName);
            System.out.println("Archivo renombrado exitosamente de " + oldName + " a " + newName);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error al renombrar el archivo: " + oldName + " a " + newName, e);
        }
    }

    public void deleteFile(String fileName) {
        if (fileName == null) {
            LOGGER.log(Level.WARNING, "Nombre del archivo nulo.");
            return;
        }
        
        try {
            fileService.deleteFile(fileName);
            System.out.println("Archivo eliminado exitosamente: " + fileName);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error al eliminar el archivo: " + fileName, e);
        }
    }

    public FileModel[] getAllFiles() {
        try {
            return fileService.getAllFiles();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error al obtener todos los archivos", e);
            return new FileModel[0]; // Retornar un arreglo vacío en caso de error
        }
    }
}
