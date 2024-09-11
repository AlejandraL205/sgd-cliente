// src\main\java\com\mycompany\sgdcliente\controller\FileController.java
package com.mycompany.sgdcliente.controller;

import com.mycompany.sgdcliente.model.FileModel;
import com.mycompany.sgdcliente.service.FileService;

import java.io.File;
import java.io.IOException;

public class FileController {
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
            System.err.println("Error al obtener detalles del archivo: " + e.getMessage());
            return null; // O manejar el error según sea necesario
        }
    }

    /**
     * Descarga un archivo.
     * @param fileName Nombre del archivo a descargar.
     * @param outputFile Archivo local donde se guardará el archivo descargado.
     */
    public void downloadFile(String fileName, File outputFile) {
        try {
            fileService.downloadFile(fileName, outputFile);
        } catch (IOException e) {
            System.err.println("Error al descargar el archivo: " + e.getMessage());
            // Manejar el error según sea necesario
        }
    }

    /**
     * Renombra un archivo en el servidor.
     * @param oldName Nombre antiguo del archivo.
     * @param newName Nuevo nombre del archivo.
     */
    public void renameFile(String oldName, String newName) {
        try {
            fileService.renameFile(oldName, newName);
        } catch (IOException e) {
            System.err.println("Error al renombrar el archivo: " + e.getMessage());
            // Manejar el error según sea necesario
        }
    }

    /**
     * Elimina un archivo del servidor.
     * @param fileName Nombre del archivo a eliminar.
     */
    public void deleteFile(String fileName) {
        try {
            fileService.deleteFile(fileName);
        } catch (IOException e) {
            System.err.println("Error al eliminar el archivo: " + e.getMessage());
            // Manejar el error según sea necesario
        }
    }
}
