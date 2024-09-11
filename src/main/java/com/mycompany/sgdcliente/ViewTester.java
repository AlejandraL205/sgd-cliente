package com.mycompany.sgdcliente;

import com.mycompany.sgdcliente.view.FileExplorerView;
import com.mycompany.sgdcliente.view.LoginView;
import com.mycompany.sgdcliente.view.MainView;
import com.mycompany.sgdcliente.view.NotificationView;
import javax.swing.SwingUtilities;

public class ViewTester {
    public static void main(String[] args) {
        // Mostrar LoginView
        SwingUtilities.invokeLater(() -> {
            LoginView loginView = new LoginView();
            loginView.setVisible(true);
        });

        // Mostrar FileExplorerView
        SwingUtilities.invokeLater(() -> {
            FileExplorerView fileExplorerView = new FileExplorerView();
            fileExplorerView.setVisible(true);
        });

        // Mostrar MainView
        SwingUtilities.invokeLater(() -> {
            MainView mainView = new MainView();
            mainView.setVisible(true);
        });

        // Mostrar NotificationView
        SwingUtilities.invokeLater(() -> {
            NotificationView notificationView = new NotificationView();
            notificationView.setVisible(true);
        });
    }
}
