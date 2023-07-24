package systemCalls.processManagement;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import systemCalls.FileManage;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JEditorPane;

public class ProcessList extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProcessList frame = new ProcessList();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public ProcessList() throws IOException {
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 632, 401);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
//		JLabel List = new JLabel("");
//		List.setForeground(new Color(255, 255, 255));
//		List.setBackground(new Color(240, 240, 240));
//		List.setBounds(180, 62, 428, 291);
//		contentPane.add(List);
		
		JEditorPane List = new JEditorPane();
		List.setBounds(191, 62, 417, 291);
		contentPane.add(List);
		
		JButton PListBtn = new JButton("processes");
		PListBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String line;
			    Process process;
				try {
					process = Runtime.getRuntime().exec
						    (System.getenv("windir") +"\\system32\\"+"tasklist.exe");
					process.getOutputStream().close();
				    BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
				    while ((line = input.readLine()) != null) {
				       // System.out.println(line); //<-- Parse data here.
				    	List.setText(line);
				    }
				    input.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			    
			}
		});
		
		
		
		
		PListBtn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		PListBtn.setBounds(46, 205, 89, 23);
		contentPane.add(PListBtn);
		
		JPanel PList = new JPanel();
		PList.setBounds(36, 88, 119, 106);
		contentPane.add(PList);
		PList.setLayout(null);
		
		
		JLabel mainHeading = new JLabel("PROCESS MANAGEMENT");
		mainHeading.setForeground(new Color(255, 255, 255));
		mainHeading.setHorizontalAlignment(SwingConstants.CENTER);
		mainHeading.setBounds(68, 11, 468, 40);
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
