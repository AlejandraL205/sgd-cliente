//src\main\java\com\mycompany\sgdcliente\view\FileExplorerView.java
package com.mycompany.sgdcliente.view;

import javax.swing.*;
import java.awt.*;

public class FileExplorerView extends JFrame {
    private JList<String> fileList;
    private JButton downloadButton, renameButton, propertiesButton;

    public FileExplorerView() {
        setTitle("File Explorer");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        fileList = new JList<>(new String[]{"File1.txt", "File2.doc", "File3.pdf"});
        downloadButton = new JButton("Download");
        renameButton = new JButton("Rename");
        propertiesButton = new JButton("Properties");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(downloadButton);
        buttonPanel.add(renameButton);
        buttonPanel.add(propertiesButton);

        add(new JScrollPane(fileList), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    // Getters for buttons and list

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
