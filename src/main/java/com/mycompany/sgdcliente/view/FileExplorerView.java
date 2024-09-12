// src/main/java/com/mycompany/sgdcliente/view/FileExplorerView.java
package com.mycompany.sgdcliente.view;

import com.mycompany.sgdcliente.controller.FileController;
import com.mycompany.sgdcliente.model.FileModel;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FileExplorerView extends JFrame {
    private JTree folderTree;
    private JList<String> fileList;
    private JButton downloadButton, renameButton, propertiesButton;
    private JTextField searchField;
    private DefaultListModel<String> fileListModel;
    private FileController fileController;

    public FileExplorerView(FileController fileController) {
        this.fileController = fileController;
        setTitle("Explorador de Archivos");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Documentos");
        DefaultMutableTreeNode contratos = new DefaultMutableTreeNode("Contratos");
        DefaultMutableTreeNode facturas = new DefaultMutableTreeNode("Facturas");
        DefaultMutableTreeNode informes = new DefaultMutableTreeNode("Informes");
        root.add(contratos);
        root.add(facturas);
        root.add(informes);

        folderTree = new JTree(root);
        folderTree.setRootVisible(true);

        fileListModel = new DefaultListModel<>();
        fileList = new JList<>(fileListModel);
        fileList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JPanel buttonPanel = new JPanel();
        downloadButton = new JButton("Descargar");
        renameButton = new JButton("Renombrar");
        propertiesButton = new JButton("Propiedades");
        buttonPanel.add(downloadButton);
        buttonPanel.add(renameButton);
        buttonPanel.add(propertiesButton);

        searchField = new JTextField(20);
        searchField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filterFiles();
            }
        });

        JPanel searchPanel = new JPanel();
        searchPanel.add(new JLabel("Buscar:"));
        searchPanel.add(searchField);

        add(new JScrollPane(folderTree), BorderLayout.WEST);
        add(new JScrollPane(fileList), BorderLayout.CENTER);
        add(searchPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void filterFiles() {
        String query = searchField.getText().toLowerCase();
        DefaultListModel<String> filteredModel = new DefaultListModel<>();

        FileModel[] allFiles = fileController.getAllFiles();
        for (FileModel file : allFiles) {
            boolean matches = file.getName().toLowerCase().contains(query) ||
                    file.getType().toLowerCase().contains(query) ||
                    (file.getLastModified() != null && file.getLastModified().toString().contains(query));

            if (matches) {
                filteredModel.addElement(file.getName());
            }
        }
        fileList.setModel(filteredModel);
    }

    public JTree getFolderTree() {
        return folderTree;
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

    public JTextField getSearchField() {
        return searchField;
    }
}
