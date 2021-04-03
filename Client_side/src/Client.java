import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Client extends JFrame {
	
	private JTextField usertext;
	  private JTextArea  chatwindow;
	  private ObjectOutputStream output;
	  private ObjectInputStream input;
	  private String message="";
	  private String serverIP;
	  private Socket connection;
	  
	  //constructor
	  
	  public Client(String host)
	  {
		  super("Client Side");
		  serverIP = host;
		  usertext = new JTextField();
		  usertext.setEditable(false);
		  usertext.addActionListener(
		   new ActionListener()
		   {
			   public void actionPerformed(ActionEvent event)
			   {
				   sendMessage(event.getActionCommand());
				   usertext.setText("");
			   }
		   }
				  
	    );
		  add(usertext, BorderLayout.NORTH);
		  chatwindow = new JTextArea();
		  add(new JScrollPane(chatwindow),BorderLayout.CENTER);
		  setSize(300,150);
		  setVisible(true);
		    
	  }

	  public void startRunning()
	  {
		  try {
			  connectToServer();
			  setupStreams();
			  whileChatting();			  
		  }catch(EOFException eofException)
		  {
			  showMessage("\n Client terminate connection");
		  }catch(IOException ioException)
		  {
			  ioException.printStackTrace();
		  }finally {
			  closeCrap();
		  }
	  }
	  
	  //connect to server
	  
	  private void connectToServer() throws IOException
	  {
		  showMessage("Attempting connection..\n");
		  connection = new Socket(InetAddress.getByName(serverIP),6789);
		  showMessage("connected to: "+ connection.getInetAddress().getHostName());
	  }
	  
	  //setup streams to send and receive message 
	  private void setupStreams() throws IOException
	  {
		  output = new ObjectOutputStream(connection.getOutputStream());
		  output.flush();
		  input = new ObjectInputStream(connection.getInputStream());
		  showMessage("\n dude your streams are now good to go");
	  }

	  // while chatting with server
	  
	  private void whileChatting() throws IOException
	  {
		  ableToType(true);
		  do { 
			  try {
				  message = (String) input.readObject();
				  showMessage("\n" + message);
				  
			  }catch(ClassNotFoundException classnotfoundException)
			  {
				  showMessage("\n i dont know that object type");
			  }
			  
		  }while(!message.equals("SERVER - END"));
	  }
	  
	  // close the streams and the sockets 
	  
	  private void closeCrap()
	  {
		  showMessage("\n closing crap down...");
		  ableToType(false);
		  try {
			  output.close();
			  input.close();
			  connection.close();
			  
		  }catch(IOException ioException)
		  {
			  ioException.printStackTrace();
		  }
	  }
	  
	  // send message to server
	  
	  private void sendMessage(String message)
	  {
		  try {
			  output.writeObject("CLIENT - "+ message);
			  output.flush();
			  showMessage("\nCLIENT -"+ message);
		  }catch(IOException ioException)
		  {
			  chatwindow.append("\n something messed up sending message");
		  }
	  }
	  
	  // change or update chat window
	  private void showMessage(final String m)
	  {
		  SwingUtilities.invokeLater(
				  new Runnable()
				  {
					  public void run()
					  {
						  chatwindow.append(m);
					  }
				  }
		  );
	  }
	  
	  //gives user permission to type crap into the text box
	  
	  private void ableToType(final boolean tof)
	  {
		  SwingUtilities.invokeLater(
				  new Runnable()
				  {
					  public void run()
					  {
						 usertext.setEditable(tof);
					  }
				  }
		  );
	  }
	  public static void main(String[] args) {
			Client charlie = new Client("127.0.0.1");
			charlie.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	         charlie.startRunning();
		}
}
