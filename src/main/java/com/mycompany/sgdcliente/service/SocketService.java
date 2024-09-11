// src\main\java\com\mycompany\sgdcliente\service\SocketService.java
package com.mycompany.sgdcliente.service;

import java.io.File;
import java.io.IOException;
import com.mycompany.sgdcliente.network.ClientConnection;

public class SocketService {
    private final ClientConnection clientConnection;

    public SocketService() {
        this.clientConnection = new ClientConnection("localhost", 8080);
    }

    /**
     * Intenta conectar al servidor utilizando ClientConnection.
     */
    public void connect() {
        try {
            clientConnection.connect();
            System.out.println("Conexión establecida exitosamente.");
        } catch (IOException e) {
            System.err.println("Error al conectar con el servidor.");
            e.printStackTrace();
        }
    }

    /**
     * Desconecta del servidor.
     */
    public void disconnect() {
        try {
            clientConnection.disconnect();
            System.out.println("Desconexión exitosa.");
        } catch (IOException e) {
            System.err.println("Error al desconectar del servidor.");
            e.printStackTrace();
        }
    }

    /**
     * Envía un mensaje al servidor.
     * 
     * @param message El mensaje a enviar.
     */
    public void sendMessage(String message) {
        try {
            clientConnection.sendMessage(message);
            System.out.println("Mensaje enviado: " + message);
        } catch (IOException e) {
            System.err.println("Error al enviar el mensaje: " + message);
            e.printStackTrace();
        }
    }

    /**
     * Recibe un mensaje del servidor.
     * 
     * @return El mensaje recibido.
     */
    public String receiveMessage() {
        try {
            String message = clientConnection.receiveMessage();
            System.out.println("Mensaje recibido: " + message);
            return message;
        } catch (IOException e) {
            System.err.println("Error al recibir el mensaje.");
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Envía un archivo al servidor.
     * 
     * @param file El archivo a enviar.
     */
    public void sendFile(File file) {
        try {
            clientConnection.sendFile(file);
            System.out.println("Archivo enviado: " + file.getName());
        } catch (IOException e) {
            System.err.println("Error al enviar el archivo: " + file.getName());
            e.printStackTrace();
        }
    }

    /**
     * Recibe un archivo del servidor.
     * 
     * @param outputFile El archivo donde se guardará el archivo recibido.
     */
    public void receiveFile(File outputFile) {
        try {
            clientConnection.receiveFile(outputFile);
            System.out.println("Archivo recibido y guardado como: " + outputFile.getName());
        } catch (IOException e) {
            System.err.println("Error al recibir el archivo: " + outputFile.getName());
            e.printStackTrace();
        }
    }
}
