//src\main\java\com\mycompany\sgdcliente\controller\SocketClient.java
package com.mycompany.sgdcliente.controller;

import com.mycompany.sgdcliente.service.SocketService;

public class SocketClient {
    private final SocketService socketService;

    public SocketClient() {
        this.socketService = new SocketService();
    }

    public void connectToServer() {
        socketService.connect();
    }
}
