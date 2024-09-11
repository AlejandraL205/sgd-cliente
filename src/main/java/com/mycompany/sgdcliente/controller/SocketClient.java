// src\main\java\com\mycompany\sgdcliente\controller\SocketClient.java
package com.mycompany.sgdcliente.controller;

import com.mycompany.sgdcliente.service.SocketService;

import java.util.logging.Level;
import java.util.logging.Logger;

public class SocketClient {
    private static final Logger LOGGER = Logger.getLogger(SocketClient.class.getName());
    private final SocketService socketService;

    public SocketClient() {
        this.socketService = new SocketService();
    }

    /**
     * Intenta conectar al servidor usando el servicio de socket.
     */
    public void connectToServer() {
        try {
            socketService.connect();
            System.out.println("Conexión al servidor establecida exitosamente.");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error al conectar con el servidor.", e);
            System.err.println("Error al conectar con el servidor.");
            e.printStackTrace();
        }
    }
}
