import java.awt.EventQueue;

import javax.swing.*;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Menu {

	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu window = new Menu();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
						
						

					
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Menu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1081, 652);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblChatApplication = new JLabel("WELCOME  TO  CHAT  APPLICATION");
		lblChatApplication.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 18));
		lblChatApplication.setBounds(336, 31, 503, 77);
		frame.getContentPane().add(lblChatApplication);
		
		textField = new JTextField();
		textField.setBounds(522, 173, 268, 46);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(522, 260, 268, 46);
		frame.getContentPane().add(passwordField);
		
		JLabel lblUsername = new JLabel("USERNAME :");
		lblUsername.setFont(new Font("Tw Cen MT", Font.ITALIC, 22));
		lblUsername.setBounds(336, 179, 131, 31);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("PASSWORD :");
		lblPassword.setFont(new Font("Tw Cen MT", Font.ITALIC, 22));
		lblPassword.setBounds(332, 273, 159, 16);
		frame.getContentPane().add(lblPassword);
		
		JButton btnLogin = new JButton("Log In");
		btnLogin.setBackground(new Color(0, 0, 0));
		btnLogin.setForeground(new Color(75, 0, 130));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.jdbc.Driver").newInstance();
					Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/users","root","123");
					String query = "select * from information where username=? and password=? ";
					PreparedStatement pst = cn.prepareStatement(query);
					 pst.setString(1, textField.getText());
	            	pst.setString(2, passwordField.getText());
	            	String s1 = textField.getText();
	            	String s2 = passwordField.getText();
	            	
	            	String query1 = "select name from information where username = '"+s1+"' and password = '"+s2+"'";
	            	Statement smt = cn.createStatement();
	            	ResultSet rs1 = smt.executeQuery(query1);
	            	
	            	ResultSet rs =pst.executeQuery();
	            	
	            	int a=0;
	            	while(rs.next())
	            	{
	            		a++;
	            	}
	            	if(a==1)
	            	{
	            		  if(rs1.next())
	           	       { do{
	           	    	 log_name= rs1.getString(1);
	           	       }while(rs1.next());
	           	    	   
	           	    	   
	           	       }
	           	    	 
	            		
	            		            	}
	            	else if(a>1)
	            	{
	            		JOptionPane.showMessageDialog(null, "Duplicate username and password");
	            	}
	            	else
	            	{
	            		JOptionPane.showMessageDialog(null, "Invalid username and password");
	            	}
	      
					
					pst.execute();
		     		pst.close();
		     		rs.close();
					
				}catch(Exception e)
				{
					e.printStackTrace();
				}
				
				
				 		
					
			}
		});
		btnLogin.setFont(new Font("Segoe Print", Font.PLAIN, 17));
		btnLogin.setBounds(595, 358, 108, 38);
		frame.getContentPane().add(btnLogin);
		
		JLabel lblNewUser = new JLabel("New User ? ");
		lblNewUser.setForeground(new Color(25, 25, 112));
		lblNewUser.setBackground(new Color(72, 61, 139));
		lblNewUser.setFont(new Font("Tw Cen MT", Font.BOLD | Font.ITALIC, 20));
		lblNewUser.setBounds(810, 99, 97, 38);
		frame.getContentPane().add(lblNewUser);
		
		JButton btnSignIn = new JButton("Sign In");
		//frame.setContentPane(btnSignIn);
		//frame.setVisible(true);
		btnSignIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			     signin  in = new signin();
			     in.setVisible(true);
			}
		});
		btnSignIn.setFont(new Font("Segoe Script", Font.PLAIN, 17));
		btnSignIn.setBounds(914, 108, 97, 25);
		frame.getContentPane().add(btnSignIn);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setFont(new Font("Segoe Print", Font.PLAIN, 17));
		lblNewLabel.setForeground(new Color(72, 61, 139));
		lblNewLabel.setIcon(new ImageIcon("F:\\chat-dienste-t.jpg"));
		lblNewLabel.setBounds(-53, -49, 1450, 671);
		frame.getContentPane().add(lblNewLabel);
	}
}
