// src\main\java\com\mycompany\sgdcliente\network\ClientConnection.java
package com.mycompany.sgdcliente.network;

import java.io.*;
import java.net.Socket;

public class ClientConnection {
    private Socket socket;
    private String host;
    private int port;
    private DataOutputStream out;
    private DataInputStream in;

    public ClientConnection(String host, int port) {
        this.host = host;
        this.port = port;
    }

    /**
     * Conecta al servidor especificado.
     * 
     * @throws IOException Si ocurre un error al conectar o al obtener los streams.
     */
    public void connect() throws IOException {
        try {
            socket = new Socket(host, port);
            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream());
            System.out.println("Conectado al servidor en " + host + ":" + port);
        } catch (IOException e) {
            System.err.println("Error al conectar al servidor en " + host + ":" + port);
            e.printStackTrace();
            closeResources();
            throw e;
        }
    }

    /**
     * Desconecta del servidor y cierra los streams.
     * 
     * @throws IOException Si ocurre un error al cerrar los recursos.
     */
    public void disconnect() throws IOException {
        try {
            if (socket != null && !socket.isClosed()) {
                closeResources();
                System.out.println("Desconectado del servidor.");
            }
        } catch (IOException e) {
            System.err.println("Error al desconectar del servidor.");
            e.printStackTrace();
            throw e; 
        }
    }

    /**
     * Envía un mensaje al servidor.
     * 
     * @param message El mensaje a enviar.
     * @throws IOException Si ocurre un error al enviar el mensaje.
     */
    public void sendMessage(String message) throws IOException {
        if (socket == null || socket.isClosed()) {
            throw new IOException("La conexión no está establecida.");
        }
        try {
            out.writeUTF(message);
            out.flush();
            System.out.println("Mensaje enviado: " + message);
        } catch (IOException e) {
            System.err.println("Error al enviar el mensaje: " + message);
            e.printStackTrace();
            throw e; // Re-lanzar para que el llamador también pueda manejar el error
        }
    }

    /**
     * Recibe un mensaje del servidor.
     * 
     * @return El mensaje recibido.
     * @throws IOException Si ocurre un error al recibir el mensaje.
     */
    public String receiveMessage() throws IOException {
        if (socket == null || socket.isClosed()) {
            throw new IOException("La conexión no está establecida.");
        }
        try {
            String message = in.readUTF();
            System.out.println("Mensaje recibido: " + message);
            return message;
        } catch (IOException e) {
            System.err.println("Error al recibir el mensaje.");
            e.printStackTrace();
            throw e; // Re-lanzar para que el llamador también pueda manejar el error
        }
    }

    /**
     * Envía un archivo al servidor.
     * 
     * @param file El archivo a enviar.
     * @throws IOException Si ocurre un error al enviar el archivo.
     */
    public void sendFile(File file) throws IOException {
        if (socket == null || socket.isClosed()) {
            throw new IOException("La conexión no está establecida.");
        }
        try (FileInputStream fileIn = new FileInputStream(file)) {
            long fileSize = file.length();
            out.writeLong(fileSize);
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = fileIn.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
            out.flush();
            System.out.println("Archivo enviado: " + file.getName());
        } catch (IOException e) {
            System.err.println("Error al enviar el archivo: " + file.getName());
            e.printStackTrace();
            throw e; // Re-lanzar para que el llamador también pueda manejar el error
        }
    }

    /**
     * Recibe un archivo del servidor.
     * 
     * @param outputFile El archivo donde se guardará el archivo recibido.
     * @throws IOException Si ocurre un error al recibir el archivo.
     */
    public void receiveFile(File outputFile) throws IOException {
        if (socket == null || socket.isClosed()) {
            throw new IOException("La conexión no está establecida.");
        }
        try (FileOutputStream fileOut = new FileOutputStream(outputFile)) {
            long fileSize = in.readLong();
            byte[] buffer = new byte[4096];
            int bytesRead;
            long totalBytesRead = 0;
            while (totalBytesRead < fileSize && (bytesRead = in.read(buffer)) != -1) {
                fileOut.write(buffer, 0, bytesRead);
                totalBytesRead += bytesRead;
            }
            System.out.println("Archivo recibido y guardado como: " + outputFile.getName());
        } catch (IOException e) {
            System.err.println("Error al recibir el archivo: " + outputFile.getName());
            e.printStackTrace();
            throw e; // Re-lanzar para que el llamador también pueda manejar el error
        }
    }

    /**
     * Cierra los streams y el socket.
     * 
     * @throws IOException Si ocurre un error al cerrar los recursos.
     */
    private void closeResources() throws IOException {
        if (out != null) {
            out.close();
        }
        if (in != null) {
            in.close();
        }
        if (socket != null) {
            socket.close();
        }
    }
}
