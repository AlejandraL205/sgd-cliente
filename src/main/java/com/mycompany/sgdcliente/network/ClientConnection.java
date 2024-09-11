//src\main\java\com\mycompany\sgdcliente\network\ClientConnection.java
package com.mycompany.sgdcliente.network;

import java.io.IOException;
import java.net.Socket;

public class ClientConnection {
    private Socket socket;
    private String host;
    private int port;

    public ClientConnection(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void connect() throws IOException {
        socket = new Socket(host, port);
        // Implement connection handling logic here
    }

    public void disconnect() throws IOException {
        if (socket != null) {
            socket.close();
        }
    }
}
