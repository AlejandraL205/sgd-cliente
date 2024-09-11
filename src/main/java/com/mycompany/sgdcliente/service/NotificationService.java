// src\main\java\com\mycompany\sgdcliente\service\NotificationService.java
package com.mycompany.sgdcliente.service;

import com.mycompany.sgdcliente.view.NotificationView;

public class NotificationService {
    private final NotificationView notificationView;

    public NotificationService() {
        this.notificationView = new NotificationView();
    }

    /**
     * Notifica sobre un cambio en un archivo.
     * 
     * @param fileName El nombre del archivo que ha cambiado.
     */
    public void notifyFileChange(String fileName) {
        String message = "El archivo " + fileName + " ha cambiado.";
        try {
            notificationView.displayNotification(message);
            notificationView.show();
        } catch (Exception e) {
            System.err.println("Error al mostrar la notificación.");
            e.printStackTrace();
        }
    }
}
