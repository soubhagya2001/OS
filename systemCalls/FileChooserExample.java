package systemCalls;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class FileChooserExample extends JFrame {
    private JTextField fileTextField;

    public FileChooserExample() {
        super("File Chooser Example");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 150);
        setLayout(new FlowLayout());

        JButton chooseFileButton = new JButton("Choose File");
        fileTextField = new JTextField(25);
        JButton moveButton = new JButton("Move");

        chooseFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    fileTextField.setText(selectedFile.getAbsolutePath());
                }
            }
        });

        moveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showSaveDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    // Here, you can implement the code to move the file to the selected location
                    // For simplicity, let's just show a message dialog with the selected file's path
                    JOptionPane.showMessageDialog(null, "File saved to: " + selectedFile.getAbsolutePath());
                }
            }
        });

        add(chooseFileButton);
        add(fileTextField);
        add(moveButton);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new FileChooserExample().setVisible(true);
            }
        });
    }
}

