// src\main\java\com\mycompany\sgdcliente\service\FileService.java
package com.mycompany.sgdcliente.service;

import com.mycompany.sgdcliente.model.FileModel;
import com.mycompany.sgdcliente.network.ClientConnection;

import java.io.File;
import java.io.IOException;

public class FileService {
    private final ClientConnection clientConnection;

    /**
     * Constructor para inicializar el servicio de archivos con la conexión del
     * cliente.
     * 
     * @param host Dirección IP o nombre del host del servidor.
     * @param port Puerto del servidor.
     */
    public FileService(String host, int port) {
        this.clientConnection = new ClientConnection(host, port);
    }

    /**
     * Obtiene los detalles de un archivo desde el servidor.
     * 
     * @param fileName Nombre del archivo para obtener detalles.
     * @return Un objeto FileModel que contiene los detalles del archivo.
     * @throws IOException Si ocurre un error de entrada/salida al comunicarse con
     *                     el servidor.
     */
    public FileModel getFileDetails(String fileName) throws IOException {
        clientConnection.connect();
        try {
            clientConnection.sendMessage("GET_DETAILS " + fileName);
            String response = clientConnection.receiveMessage();
            // Asumimos que el servidor responde con los detalles en formato JSON o similar
            // Deserializar la respuesta en un FileModel (implementación pendiente)
            return parseFileModel(response);
        } finally {
            clientConnection.disconnect();
        }
    }

    /**
     * Descarga un archivo desde el servidor.
     * 
     * @param fileName   Nombre del archivo a descargar.
     * @param outputFile Archivo local donde se guardará el archivo descargado.
     * @throws IOException Si ocurre un error de entrada/salida al comunicarse con
     *                     el servidor o al guardar el archivo.
     */
    public void downloadFile(String fileName, File outputFile) throws IOException {
        clientConnection.connect();
        try {
            clientConnection.sendMessage("DOWNLOAD " + fileName);
            clientConnection.receiveFile(outputFile);
        } finally {
            clientConnection.disconnect();
        }
    }

    /**
     * Renombra un archivo en el servidor.
     * 
     * @param oldName Nombre antiguo del archivo.
     * @param newName Nuevo nombre del archivo.
     * @throws IOException Si ocurre un error de entrada/salida al comunicarse con
     *                     el servidor.
     */
    public void renameFile(String oldName, String newName) throws IOException {
        clientConnection.connect();
        try {
            clientConnection.sendMessage("RENAME " + oldName + " " + newName);
            // Asumimos que el servidor envía una confirmación de éxito o error
            String response = clientConnection.receiveMessage();
            if (!response.equals("SUCCESS")) {
                throw new IOException("Error al renombrar el archivo: " + response);
            }
        } finally {
            clientConnection.disconnect();
        }
    }

    /**
     * Elimina un archivo del servidor.
     * 
     * @param fileName Nombre del archivo a eliminar.
     * @throws IOException Si ocurre un error de entrada/salida al comunicarse con
     *                     el servidor.
     */
    public void deleteFile(String fileName) throws IOException {
        clientConnection.connect();
        try {
            clientConnection.sendMessage("DELETE " + fileName);
            // Asumimos que el servidor envía una confirmación de éxito o error
            String response = clientConnection.receiveMessage();
            if (!response.equals("SUCCESS")) {
                throw new IOException("Error al eliminar el archivo: " + response);
            }
        } finally {
            clientConnection.disconnect();
        }
    }

    /**
     * Analiza la respuesta del servidor para crear un objeto FileModel.
     * 
     * @param response Respuesta del servidor en formato JSON o similar.
     * @return Un objeto FileModel con la información del archivo.
     */
    private FileModel parseFileModel(String response) {
        // Implementar la deserialización según el formato de respuesta del servidor.
        // Este es un ejemplo simplificado, ajusta según el formato real.
        FileModel fileModel = new FileModel();
        // Parsear el contenido de response para llenar el fileModel
        return fileModel;
    }
}
