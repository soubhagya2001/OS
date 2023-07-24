package systemCalls.fileManagement;
import javax.swing.*;

import systemCalls.FileManage;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.*;

public class FileMoverApp extends JFrame {

    private JTextField sourceField;
    private JTextField destinationField;
    private JButton chooseFileButton;
    private JButton moveButton;

    public FileMoverApp() {
        setTitle("File Mover");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 150);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        sourceField = new JTextField(20);
        destinationField = new JTextField(20);
        chooseFileButton = new JButton("Choose File");
        moveButton = new JButton("Move File");

        chooseFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(FileMoverApp.this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    sourceField.setText(selectedFile.getAbsolutePath());
                }
            }
        });

        moveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sourcePath = sourceField.getText();
                String destinationPath = destinationField.getText();

                File sourceFile = new File(sourcePath);
                File destinationFile = new File(destinationPath);

                try {
//                    if (sourceFile.exists() && !destinationFile.exists()) {
//                        boolean success = sourceFile.renameTo(destinationFile);
//                        if (success) {
//                            JOptionPane.showMessageDialog(FileMoverApp.this, "File moved successfully!");
//                        } else {
//                            JOptionPane.showMessageDialog(FileMoverApp.this, "Failed to move the file.", "Error", JOptionPane.ERROR_MESSAGE);
//                        }
//                    } else {
//                        JOptionPane.showMessageDialog(FileMoverApp.this, "Source file does not exist or destination file already exists.", "Error", JOptionPane.ERROR_MESSAGE);
//                    }
                	
                	//Files.move(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING );
                	if(sourceFile.renameTo(destinationFile)) {
                		JOptionPane.showMessageDialog(FileMoverApp.this, "File moved successfully!");
                		
                		dispose();
                	}else {
                		 JOptionPane.showMessageDialog(FileMoverApp.this, "Failed to move the file.", "Error", JOptionPane.ERROR_MESSAGE);                   
                	}
                		
                	
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        add(new JLabel("Source File:"));
        add(sourceField);
        add(chooseFileButton);
        add(new JLabel("Destination File:"));
        add(destinationField);
        add(moveButton);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                FileMoverApp fileMoverApp = new FileMoverApp();
                fileMoverApp.setVisible(true);
            }
        });
    }
}
