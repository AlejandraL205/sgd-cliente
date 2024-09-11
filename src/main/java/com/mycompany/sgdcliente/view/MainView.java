//src\main\java\com\mycompany\sgdcliente\view\MainView.java
package com.mycompany.sgdcliente.view;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame {
    private JButton connectButton, openExplorerButton;

    public MainView() {
        setTitle("SGD Client");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        connectButton = new JButton("Connect");
        openExplorerButton = new JButton("Open File Explorer");

        JPanel panel = new JPanel(new GridLayout(2, 1, 10, 10));
        panel.add(connectButton);
        panel.add(openExplorerButton);

        add(panel, BorderLayout.CENTER);
    }

    // Getters for buttons

    public JButton getConnectButton() {
        return connectButton;
    }

    public JButton getOpenExplorerButton() {
        return openExplorerButton;
    }
}
