// src\main\java\com\mycompany\sgdcliente\service\FileService.java
package com.mycompany.sgdcliente.service;

import com.mycompany.sgdcliente.model.FileModel;
import com.mycompany.sgdcliente.network.ClientConnection;

import java.io.File;
import java.io.IOException;

public class FileService {
    private final ClientConnection clientConnection;

    public FileService(String host, int port) {
        this.clientConnection = new ClientConnection(host, port);
    }

    public FileModel getFileDetails(String fileName) throws IOException {
        clientConnection.connect();
        try {
            clientConnection.sendMessage("GET_DETAILS " + fileName);
            String response = clientConnection.receiveMessage();
            return parseFileModel(response);
        } catch (IOException e) {
            throw e;
        } finally {
            clientConnection.disconnect();
        }
    }

    public void downloadFile(String fileName, File outputFile) throws IOException {
        clientConnection.connect();
        try {
            clientConnection.sendMessage("DOWNLOAD " + fileName);
            clientConnection.receiveFile(outputFile);
        } catch (IOException e) {
            throw e;
        } finally {
            clientConnection.disconnect();
        }
    }

    public void renameFile(String oldName, String newName) throws IOException {
        clientConnection.connect();
        try {
            clientConnection.sendMessage("RENAME " + oldName + " " + newName);
            String response = clientConnection.receiveMessage();
            if (!response.equals("SUCCESS")) {
                throw new IOException("Error al renombrar el archivo: " + response);
            }
        } catch (IOException e) {
            throw e;
        } finally {
            clientConnection.disconnect();
        }
    }

    public void deleteFile(String fileName) throws IOException {
        clientConnection.connect();
        try {
            clientConnection.sendMessage("DELETE " + fileName);
            String response = clientConnection.receiveMessage();
            if (!response.equals("SUCCESS")) {
                throw new IOException("Error al eliminar el archivo: " + response);
            }
        } catch (IOException e) {
            throw e;
        } finally {
            clientConnection.disconnect();
        }
    }

    public FileModel[] getAllFiles() throws IOException {
        clientConnection.connect();
        try {
            clientConnection.sendMessage("GET_ALL_FILES");
            String response = clientConnection.receiveMessage();
            return parseFileModels(response);
        } catch (IOException e) {
            throw e;
        } finally {
            clientConnection.disconnect();
        }
    }

    private FileModel parseFileModel(String response) {
        FileModel fileModel = new FileModel();
        // Implementar la deserialización según el formato de respuesta del servidor.
        return fileModel;
    }

    private FileModel[] parseFileModels(String response) {
        // Implementar la deserialización según el formato de respuesta del servidor.
        // Supongamos que la respuesta es una lista de archivos en formato JSON.
        return new FileModel[0]; 
    }
}
