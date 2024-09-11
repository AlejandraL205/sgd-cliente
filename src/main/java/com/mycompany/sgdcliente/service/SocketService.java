//src\main\java\com\mycompany\sgdcliente\service\SocketService.java
package com.mycompany.sgdcliente.service;
import java.io.IOException;
import com.mycompany.sgdcliente.network.ClientConnection;

public class SocketService {
    private final ClientConnection clientConnection;

    public SocketService() {
        this.clientConnection = new ClientConnection("localhost", 8080);
    }

    public void connect() {
        try {
            clientConnection.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
