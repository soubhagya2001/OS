package systemCalls;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import javax.swing.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import systemCalls.fileManagement.FileMoverApp;

import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.JTextField;
import java.io.*;

public class FileManage extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FileManage frame = new FileManage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FileManage() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 794, 504);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
//		ImageIcon CreateImage = new ImageIcon(getClass().getResource("E:\\System call files\\background.jpg"));
		JButton CREATE = new JButton("CREATE");
		CREATE.setForeground(SystemColor.text);
		CREATE.setBackground(new Color(0, 0, 64));
		
		JButton MoveBtn = new JButton("MOVE");
		
		MoveBtn.setToolTipText("Move a File");
		MoveBtn.setForeground(Color.WHITE);
		MoveBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		MoveBtn.setBackground(new Color(0, 0, 64));
		MoveBtn.setBounds(607, 403, 123, 53);
		contentPane.add(MoveBtn);
		
		JButton DELETE = new JButton("DELETE");
		DELETE.setToolTipText("Delete a file");
		DELETE.setForeground(Color.WHITE);
		DELETE.setFont(new Font("Tahoma", Font.PLAIN, 18));
		DELETE.setBackground(new Color(0, 0, 64));
		DELETE.setBounds(413, 403, 123, 53);
		contentPane.add(DELETE);
		
		JButton btnOpen = new JButton("OPEN");
		btnOpen.setToolTipText("Open a file");
		btnOpen.setForeground(Color.WHITE);
		btnOpen.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnOpen.setBackground(new Color(0, 0, 64));
		btnOpen.setBounds(222, 403, 123, 53);
		contentPane.add(btnOpen);
		
		JLabel Fname = new JLabel(" ");
		Fname.setForeground(Color.WHITE);
		Fname.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Fname.setBounds(601, 81, 169, 22);
		contentPane.add(Fname);
		CREATE.setFont(new Font("Tahoma", Font.PLAIN, 18));
		CREATE.setToolTipText("Create a new file");
		CREATE.setBounds(33, 403, 123, 53);
		contentPane.add(CREATE);
		
		JLabel FileLocation = new JLabel("FILE LOCATION  : ");
		FileLocation.setForeground(Color.WHITE);
		FileLocation.setFont(new Font("Tahoma", Font.PLAIN, 18));
		FileLocation.setBounds(497, 146, 164, 22);
		contentPane.add(FileLocation);
		
		JLabel FileSize = new JLabel("FILE SIZE : ");
		FileSize.setForeground(Color.WHITE);
		FileSize.setFont(new Font("Tahoma", Font.PLAIN, 18));
		FileSize.setBounds(497, 113, 110, 22);
		contentPane.add(FileSize);
		
		JLabel FileName = new JLabel("FILE NAME : ");
		FileName.setForeground(new Color(255, 255, 255));
		FileName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		FileName.setBounds(497, 80, 110, 22);
		contentPane.add(FileName);
		
		JTextArea textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 17));
		textArea.setBounds(33, 75, 454, 317);
		contentPane.add(textArea);
		
		JLabel Flocation = new JLabel("");
		Flocation.setForeground(Color.WHITE);
		Flocation.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Flocation.setBounds(497, 179, 273, 22);
		contentPane.add(Flocation);

		JLabel mainHeading = new JLabel("FILE MANAGEMENT");
		mainHeading.setForeground(new Color(255, 255, 255));
		mainHeading.setHorizontalAlignment(SwingConstants.CENTER);
		mainHeading.setBounds(159, 11, 468, 40);
		mainHeading.setFont(new Font("Times New Roman", Font.BOLD, 34));
		mainHeading.setAutoscrolls(true);
		mainHeading.setAlignmentX(Component.CENTER_ALIGNMENT);
		contentPane.add(mainHeading);
		
		String path = "E:\\System call files\\background.jpg";
		ImageIcon background = new ImageIcon(path);
		Image img = background.getImage();
		Image temp = img.getScaledInstance(780, 467, Image.SCALE_SMOOTH);
		background = new ImageIcon(temp);
		JLabel backgroundLbl = new JLabel(background);
		backgroundLbl.setBounds(0, 0, 780, 467);
		
		JLabel Fsize = new JLabel("");
		Fsize.setForeground(Color.WHITE);
		Fsize.setFont(new Font("Tahoma", Font.PLAIN, 18));
		Fsize.setBounds(601, 113, 110, 22);
		contentPane.add(Fsize);
		
		
		CREATE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s = e.getActionCommand();
				
				String FileText = textArea.getText();
				
				JFileChooser j = new JFileChooser("C:\\Users\\soubh\\Desktop\\");				
			
				int r = j.showSaveDialog(null);
				 
	            if (r == JFileChooser.APPROVE_OPTION) {
	 
	                // Set the label to the path of the selected directory
	                File fi = new File(j.getSelectedFile().getAbsolutePath());
	 
	                try {
	                    // Create a file writer
	                    FileWriter wr = new FileWriter(fi, false);
	 
	                    // Create buffered writer to write
	                    BufferedWriter w = new BufferedWriter(wr);
	 
	                    // Write
	                    w.write(textArea.getText());
	 
	                    w.flush();
	                    w.close();
	                    textArea.setText("");
	                    JOptionPane.showMessageDialog(CREATE, "File created successfully .");
	                }
	                catch (Exception evt) {
	                    JOptionPane.showMessageDialog(CREATE ,evt.getMessage());
	                }
	            }
	            // If the user cancelled the operation
	            else
	                {JOptionPane.showMessageDialog(CREATE , "the user cancelled the operation");}
			
			}
		});
		
		
		MoveBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				new FileMoverApp().setVisible(true);

			}
		});
		
		contentPane.add(backgroundLbl);
		
		
		
		
	}
}
