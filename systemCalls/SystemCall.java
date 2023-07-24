package systemCalls;

import java.awt.*;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import systemCalls.processManagement.ProcessList;

import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SystemCall extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SystemCall frame = new SystemCall();
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
	public SystemCall() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 794, 504);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		String fileManagementLogoPath = "E:\\System call files\\file-manager-10.png";
		ImageIcon fileManagementBack = new ImageIcon(fileManagementLogoPath);
		Image img1 = fileManagementBack.getImage();
		Image temp1 = img1.getScaledInstance(149,134, Image.SCALE_SMOOTH);
		fileManagementBack = new ImageIcon(temp1);
		
		JButton fileManagementBtn = new JButton("FILE");
		fileManagementBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new FileManage().setVisible(true);
				dispose();
			}
		});
		
		String ProcessLogoPath = "E:\\System call files\\logo2.png";
		ImageIcon processManagementIcon = new ImageIcon(ProcessLogoPath);
		Image PL1 = processManagementIcon.getImage();
		Image temp2 = PL1.getScaledInstance(149, 134, Image.SCALE_SMOOTH);
		processManagementIcon = new ImageIcon(temp2);
		
		JButton processBtn = new JButton("PROCESS");
		processBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new ProcessList().setVisible(true);
					dispose();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		processBtn.setForeground(new Color(255, 255, 255));
		processBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		processBtn.setBackground(new Color(0, 64, 64));
		processBtn.setBounds(337, 242, 117, 23);
		contentPane.add(processBtn);
		
		
		JLabel ProcessLogo = new JLabel(processManagementIcon);
		ProcessLogo.setBounds(319, 118, 149, 134);
		contentPane.add(ProcessLogo);
		
		fileManagementBtn.setBackground(new Color(0, 64, 64));
		fileManagementBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		fileManagementBtn.setForeground(new Color(255, 255, 255));
		fileManagementBtn.setBounds(98, 242, 89, 23);
		contentPane.add(fileManagementBtn);
		
		
		JLabel fileManagementLogo = new JLabel(fileManagementBack);
		fileManagementLogo.setBounds(66, 118, 149, 134);
		contentPane.add(fileManagementLogo);
		

		
		JLabel mainHeading = new JLabel("SYSTEM CALLS");
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
		
		contentPane.add(backgroundLbl);
	}
}
