// src\main\java\com\mycompany\sgdcliente\SGDcliente.java
package com.mycompany.sgdcliente;

/**
 * Sistema de Gestión Documental (SGD)
 * 
 * Este es el punto de entrada de la aplicación SGD.
 * Al ejecutar el programa, se mostrará la vista de inicio de sesión.
 * 
 * Desarrolladores:
 * - Alejandra Lozano
 * - Francisco Mora
 * - Kevin Becerra
 */
import com.mycompany.sgdcliente.view.LoginView;
import com.mycompany.sgdcliente.service.RealTimeNotificationClient;

public class SGDcliente {
    public static void main(String[] args) {
        try {
            // Crear e iniciar la vista de inicio de sesión
            LoginView loginView = new LoginView();
            loginView.show();

            // Configura el cliente de notificaciones en tiempo real
            RealTimeNotificationClient realTimeClient = new RealTimeNotificationClient("localhost", 12345);
            realTimeClient.start();

        } catch (Exception e) {
            // Manejar cualquier excepción inesperada
            System.err.println("Ocurrió un error al iniciar la aplicación.");
            e.printStackTrace();
        }
    }
}

