import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.io.*;
import javax.swing.*;

public class Main_menu extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main_menu frame = new Main_menu();
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
	public Main_menu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 708, 687);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEnterYourName = new JLabel("Enter Your Name :");
		lblEnterYourName.setFont(new Font("Tahoma", Font.ITALIC, 18));
		lblEnterYourName.setBounds(139, 106, 155, 43);
		contentPane.add(lblEnterYourName);
		
		JLabel lblEnterYourSurname = new JLabel("Enter Your Surname :");
		lblEnterYourSurname.setFont(new Font("Tahoma", Font.ITALIC, 18));
		lblEnterYourSurname.setBounds(139, 188, 178, 29);
		contentPane.add(lblEnterYourSurname);
		
		JLabel lblEnterUsername = new JLabel("Enter Username :");
		lblEnterUsername.setFont(new Font("Tahoma", Font.ITALIC, 18));
		lblEnterUsername.setBounds(139, 257, 166, 29);
		contentPane.add(lblEnterUsername);
		
		JLabel lblEnterPassword = new JLabel("Enter Password :");
		lblEnterPassword.setFont(new Font("Tahoma", Font.ITALIC, 18));
		lblEnterPassword.setBounds(139, 326, 155, 29);
		contentPane.add(lblEnterPassword);
		
		textField = new JTextField();
		textField.setBounds(336, 114, 207, 35);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(336, 188, 207, 35);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(336, 256, 207, 35);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(336, 325, 207, 35);
		contentPane.add(passwordField);
		
		JRadioButton rdbtnMale = new JRadioButton("MALE");
		rdbtnMale.setBounds(336, 410, 127, 43);
		contentPane.add(rdbtnMale);
		
		JRadioButton rdbtnFemale = new JRadioButton("FEMALE");
		rdbtnFemale.setBounds(461, 410, 127, 43);
		contentPane.add(rdbtnFemale);
		
		JLabel lblGender = new JLabel("GENDER :");
		lblGender.setFont(new Font("Tahoma", Font.ITALIC, 18));
		lblGender.setBounds(192, 419, 102, 21);
		contentPane.add(lblGender);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Class.forName("com.mysql.jdbc.Driver").newInstance();
					Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/users","root","123");
					String query = "insert into information (name, surname, username,password,gender) values (?,?,?,?,?)";
					PreparedStatement pst = cn.prepareStatement(query);
					pst.setString(1, textField.getText());
					pst.setString(2, textField_1.getText());
					pst.setString(3, textField_2.getText());
					pst.setString(4, passwordField.getText());
					
					String gen="";
					if(rdbtnMale.isSelected())
						gen="Male";
					else
						gen="Female";
					
					pst.setString(5, gen);
					pst.execute();
					
					JOptionPane.showMessageDialog(null, "Data subitted");
					
					pst.close();
					
				}catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		});
		btnSubmit.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnSubmit.setBounds(379, 495, 127, 43);
		contentPane.add(btnSubmit);
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		btnBack.setBounds(12, 13, 97, 25);
		contentPane.add(btnBack);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\hp-p\\Downloads\\8cb0a6d21316bcc257054491c1de35c8.jpg"));
		lblNewLabel.setBounds(0, 0, 690, 640);
		contentPane.add(lblNewLabel);
	}
}
