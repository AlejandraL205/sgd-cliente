// src/main/java/com/mycompany/sgdcliente/service/RealTimeNotificationClient.java
package com.mycompany.sgdcliente.service;

import com.mycompany.sgdcliente.network.ClientConnection;
import com.mycompany.sgdcliente.view.NotificationView;

import java.io.IOException;

public class RealTimeNotificationClient {
    private final ClientConnection clientConnection;
    private final NotificationView notificationView;

    public RealTimeNotificationClient(String host, int port) {
        this.clientConnection = new ClientConnection(host, port);
        this.notificationView = new NotificationView();
    }

    public void start() {
        new Thread(() -> {
            try {
                clientConnection.connect();
                System.out.println("Conectado al servidor. Esperando notificaciones...");

                while (true) {
                    String message = clientConnection.receiveMessage();
                    if (message != null && message.startsWith("FILE_CHANGED")) {
                        String fileName = message.split(" ", 2)[1];
                        notificationView.displayNotification("El archivo " + fileName + " ha cambiado.");
                        notificationView.showView();
                    }
                }
            } catch (IOException e) {
                System.err.println("Error al recibir mensaje del servidor.");
                e.printStackTrace();
            } finally {
                try {
                    clientConnection.disconnect();
                } catch (IOException e) {
                    System.err.println("Error al desconectar del servidor.");
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
