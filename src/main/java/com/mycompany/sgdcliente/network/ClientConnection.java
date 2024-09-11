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

    public void connect() throws IOException {
        socket = new Socket(host, port);
        out = new DataOutputStream(socket.getOutputStream());
        in = new DataInputStream(socket.getInputStream());
        System.out.println("Conectado al servidor en " + host + ":" + port);
    }

    public void disconnect() throws IOException {
        if (socket != null && !socket.isClosed()) {
            out.close();
            in.close();
            socket.close();
            System.out.println("Desconectado del servidor.");
        }
    }

    public void sendMessage(String message) throws IOException {
        if (socket == null || socket.isClosed()) {
            throw new IOException("La conexión no está establecida.");
        }
        out.writeUTF(message);
        out.flush();
    }

    public String receiveMessage() throws IOException {
        if (socket == null || socket.isClosed()) {
            throw new IOException("La conexión no está establecida.");
        }
        return in.readUTF();
    }

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
        }
    }

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
        }
    }
}
