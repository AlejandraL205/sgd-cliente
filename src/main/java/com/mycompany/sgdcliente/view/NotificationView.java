// src\main\java\com\mycompany\sgdcliente\view\NotificationView.java
package com.mycompany.sgdcliente.view;

import javax.swing.*;
import java.awt.*;

public class NotificationView extends JFrame {
    private JTextArea notificationArea;
    private JButton closeButton;

    public NotificationView() {
        setTitle("Notifications");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        notificationArea = new JTextArea();
        notificationArea.setEditable(false);
        closeButton = new JButton("Close");

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JScrollPane(notificationArea), BorderLayout.CENTER);
        panel.add(closeButton, BorderLayout.SOUTH);

        add(panel, BorderLayout.CENTER);

        closeButton.addActionListener(e -> dispose());
    }

    public void show() {
        setVisible(true);
    }

    public void displayNotification(String message) {
        notificationArea.append(message + "\n");
    }
}
