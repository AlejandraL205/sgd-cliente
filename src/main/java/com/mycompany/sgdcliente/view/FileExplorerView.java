// src/main/java/com/mycompany/sgdcliente/view/FileExplorerView.java
package com.mycompany.sgdcliente.view;

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

    public FileExplorerView() {
        setTitle("Explorador de Archivos");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear el árbol de carpetas
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Documentos");
        DefaultMutableTreeNode contratos = new DefaultMutableTreeNode("Contratos");
        DefaultMutableTreeNode facturas = new DefaultMutableTreeNode("Facturas");
        DefaultMutableTreeNode informes = new DefaultMutableTreeNode("Informes");
        root.add(contratos);
        root.add(facturas);
        root.add(informes);

        folderTree = new JTree(root);
        folderTree.setRootVisible(true);

        // Lista de archivos
        fileListModel = new DefaultListModel<>();
        fileList = new JList<>(fileListModel);
        fileList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Panel de botones
        JPanel buttonPanel = new JPanel();
        downloadButton = new JButton("Descargar");
        renameButton = new JButton("Renombrar");
        propertiesButton = new JButton("Propiedades");
        buttonPanel.add(downloadButton);
        buttonPanel.add(renameButton);
        buttonPanel.add(propertiesButton);

        // Campo de búsqueda
        searchField = new JTextField(20);
        searchField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filterFiles();
            }
        });

        // Layout
        JPanel searchPanel = new JPanel();
        searchPanel.add(new JLabel("Buscar:"));
        searchPanel.add(searchField);

        add(new JScrollPane(folderTree), BorderLayout.WEST);
        add(new JScrollPane(fileList), BorderLayout.CENTER);
        add(searchPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void filterFiles() {
        // Implementar la lógica de búsqueda
        String query = searchField.getText().toLowerCase();
        DefaultListModel<String> filteredModel = new DefaultListModel<>();
        for (int i = 0; i < fileListModel.size(); i++) {
            String file = fileListModel.getElementAt(i);
            if (file.toLowerCase().contains(query)) {
                filteredModel.addElement(file);
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

    public void setFileListData(String[] files) {
        fileListModel.clear();
        for (String file : files) {
            fileListModel.addElement(file);
        }
    }
}
