// src\main\java\com\mycompany\sgdcliente\view\NotificationView.java
package com.mycompany.sgdcliente.view;

import javax.swing.*;
import java.awt.*;

public class NotificationView extends JFrame {
    private JTextArea notificationArea;
    private JButton closeButton;

    public NotificationView() {
        setTitle("Notificaciones");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        notificationArea = new JTextArea();
        notificationArea.setEditable(false);
        closeButton = new JButton("Cerrar");

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JScrollPane(notificationArea), BorderLayout.CENTER);
        panel.add(closeButton, BorderLayout.SOUTH);

        add(panel, BorderLayout.CENTER);

        closeButton.addActionListener(e -> dispose());
    }

    public void show() {
        this.setVisible(true);
    }

    public void displayNotification(String message) {
        try {
            notificationArea.append(message + "\n");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error al mostrar la notificación: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}
