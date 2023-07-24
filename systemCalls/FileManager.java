package systemCalls;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class FileManager extends JFrame implements ActionListener {
    private JButton copyButton;
    private JButton pasteButton;
    private JButton renameButton;
    private JTree fileTree;

    public FileManager() {
        setTitle("File Manager");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create buttons
        copyButton = new JButton("Copy");
        pasteButton = new JButton("Paste");
        renameButton = new JButton("Rename");

        // Add action listeners to buttons
        copyButton.addActionListener(this);
        pasteButton.addActionListener(this);
        renameButton.addActionListener(this);

        // Create file tree
        File root = new File(System.getProperty("user.home")+ File.separator +"Desktop");
        //File root = new File(System.getProperty("user.home"));
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(root.getName());
        DefaultTreeModel treeModel = new DefaultTreeModel(rootNode);
        createTree(root, rootNode);

        fileTree = new JTree(treeModel);

        // Create panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(copyButton);
        buttonPanel.add(pasteButton);
        buttonPanel.add(renameButton);

        // Set layout
        setLayout(new BorderLayout());
        add(new JScrollPane(fileTree), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void createTree(File file, DefaultMutableTreeNode parentNode) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null) {
                for (File child : files) {
                    DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(child.getName());
                    parentNode.add(childNode);
                    createTree(child, childNode);
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == copyButton) {
            // Copy logic
            // Implement your copy logic here
        } else if (e.getSource() == pasteButton) {
            // Paste logic
            // Implement your paste logic here
        } else if (e.getSource() == renameButton) {
            // Rename logic
            // Implement your rename logic here
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FileManager fileManager = new FileManager();
            fileManager.setVisible(true);
        });
    }
}
