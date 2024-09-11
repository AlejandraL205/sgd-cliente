//src\main\java\com\mycompany\sgdcliente\service\SocketService.java
package com.mycompany.sgdcliente.service;

import com.mycompany.sgdcliente.network.ClientConnection;

import java.io.IOException;

public class SocketService {
    private final ClientConnection clientConnection;

    public SocketService() {
        this.clientConnection = new ClientConnection("localhost", 8080);
    }

    public void connect() {
        try {
            clientConnection.connect();
            // Implement socket connection logic
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
