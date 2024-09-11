// src/main/java/com/mycompany/sgdcliente/view/FileExplorerView.java
package com.mycompany.sgdcliente.view;

import javax.swing.*;
import java.awt.*;

public class FileExplorerView extends JFrame {
    private JList<String> fileList;
    private JButton downloadButton, renameButton, propertiesButton;

    public FileExplorerView() {
        setTitle("Explorador de Archivos");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        fileList = new JList<>(new String[] { "Archivo1.txt", "Archivo2.doc", "Archivo3.pdf" });
        downloadButton = new JButton("Descargar");
        renameButton = new JButton("Renombrar");
        propertiesButton = new JButton("Propiedades");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(downloadButton);
        buttonPanel.add(renameButton);
        buttonPanel.add(propertiesButton);

        add(new JScrollPane(fileList), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

    }

    public JList<String> getFileList() {
        return fileList;
    }

    public JButton getDownloadButton() {
        return downloadButton;
    }

    public JButton getRenameButton() {
        return renameButton;
    }

    public JButton getPropertiesButton() {
        return propertiesButton;
    }
}
