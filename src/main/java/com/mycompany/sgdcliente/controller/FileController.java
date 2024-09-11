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

    /**
     * Constructor para inicializar el controlador de archivos con el servicio de archivos.
     */
    public FileController(String host, int port) {
        this.fileService = new FileService(host, port);
    }

    /**
     * Obtiene los detalles de un archivo.
     * @param fileName Nombre del archivo para obtener detalles.
     * @return Un objeto FileModel que contiene los detalles del archivo.
     */
    public FileModel getFileDetails(String fileName) {
        try {
            return fileService.getFileDetails(fileName);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error al obtener detalles del archivo: " + fileName, e);
            System.err.println("Error al obtener detalles del archivo: " + fileName);
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Descarga un archivo.
     * @param fileName Nombre del archivo a descargar.
     * @param outputFile Archivo local donde se guardará el archivo descargado.
     */
    public void downloadFile(String fileName, File outputFile) {
        if (fileName == null || outputFile == null) {
            LOGGER.log(Level.WARNING, "Nombre del archivo o archivo de salida son nulos.");
            System.out.println("Nombre del archivo o archivo de salida son nulos.");
            return;
        }
        
        try {
            fileService.downloadFile(fileName, outputFile);
            System.out.println("Archivo descargado exitosamente: " + fileName);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error al descargar el archivo: " + fileName, e);
            System.err.println("Error al descargar el archivo: " + fileName);
            e.printStackTrace();
        }
    }

    /**
     * Renombra un archivo en el servidor.
     * @param oldName Nombre antiguo del archivo.
     * @param newName Nuevo nombre del archivo.
     */
    public void renameFile(String oldName, String newName) {
        if (oldName == null || newName == null) {
            LOGGER.log(Level.WARNING, "Nombres de archivos nulos.");
            System.out.println("Nombres de archivos nulos.");
            return;
        }
        
        try {
            fileService.renameFile(oldName, newName);
            System.out.println("Archivo renombrado exitosamente de " + oldName + " a " + newName);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error al renombrar el archivo: " + oldName + " a " + newName, e);
            System.err.println("Error al renombrar el archivo: " + oldName + " a " + newName);
            e.printStackTrace();
        }
    }

    /**
     * Elimina un archivo del servidor.
     * @param fileName Nombre del archivo a eliminar.
     */
    public void deleteFile(String fileName) {
        if (fileName == null) {
            LOGGER.log(Level.WARNING, "Nombre del archivo nulo.");
            System.out.println("Nombre del archivo nulo.");
            return;
        }
        
        try {
            fileService.deleteFile(fileName);
            System.out.println("Archivo eliminado exitosamente: " + fileName);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error al eliminar el archivo: " + fileName, e);
            System.err.println("Error al eliminar el archivo: " + fileName);
            e.printStackTrace();
        }
    }
}
