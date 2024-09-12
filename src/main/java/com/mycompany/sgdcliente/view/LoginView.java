// src/main/java/com/mycompany/sgdcliente/view/LoginView.java

package com.mycompany.sgdcliente.view;

import com.mycompany.sgdcliente.controller.FileController;
import com.mycompany.sgdcliente.network.ClientConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class LoginView extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton cancelButton;

    private ClientConnection clientConnection;

    public LoginView() {
        setTitle("Login");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        usernameField = new JTextField(15);
        passwordField = new JPasswordField(15);
        loginButton = new JButton("Login");
        cancelButton = new JButton("Cancel");

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.add(new JLabel("Nombre de usuario:"));
        panel.add(usernameField);
        panel.add(new JLabel("Contraseña:"));
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(cancelButton);

        add(panel, BorderLayout.CENTER);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleLogin();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Botón de cancelar clickeado");
                System.exit(0);
            }
        });

        // Inicializar la conexión con el servidor
        clientConnection = new ClientConnection("localhost", 12345);
    }

    private void handleLogin() {
        String username = usernameField.getText();
        char[] password = passwordField.getPassword();

        if (username.isEmpty() || password.length == 0) {
            JOptionPane.showMessageDialog(this,
                    "Por favor, ingrese nombre de usuario y contraseña.",
                    "Campos Vacíos",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            clientConnection.connect(); // Conectar al servidor

            // Enviar credenciales al servidor
            clientConnection.sendMessage(username);
            clientConnection.sendMessage(new String(password));

            // Leer respuesta del servidor
            String response = clientConnection.receiveMessage();
            if ("SUCCESS".equals(response)) {
                SwingUtilities.invokeLater(() -> {
                    LoginView.this.dispose(); // Cierra la ventana de login

                    // Crear un FileController con la misma configuración que antes
                    FileController fileController = new FileController("localhost", 12345);

                    // Pasar el FileController al FileExplorerView
                    FileExplorerView fileExplorerView = new FileExplorerView(fileController);
                    fileExplorerView.setVisible(true);
                });
            } else {
                JOptionPane.showMessageDialog(this,
                        "Credenciales inválidas. Inténtalo de nuevo.",
                        "Error de Autenticación",
                        JOptionPane.ERROR_MESSAGE);
            }

            clientConnection.disconnect(); // Desconectar del servidor
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this,
                    "Error de conexión con el servidor: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    public void showView() {
        this.setVisible(true);
    }
}
