//src\main\java\com\mycompany\sgdcliente\service\NotificationService.java
package com.mycompany.sgdcliente.service;

import com.mycompany.sgdcliente.view.NotificationView;

public class NotificationService {
    private final NotificationView notificationView;

    public NotificationService() {
        this.notificationView = new NotificationView();
    }

    public void notifyFileChange(String fileName) {
        String message = "El archivo " + fileName + " ha cambiado.";
        notificationView.displayNotification(message);
        notificationView.show();
    }
}
