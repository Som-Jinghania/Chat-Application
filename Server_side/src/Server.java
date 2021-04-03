import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Server extends JFrame{
	
  private JTextField usertext;
  private JTextArea  chatwindow;
  private ObjectOutputStream output;
  private ObjectInputStream input;
  private ServerSocket server;
  private Socket connection;
  
  //constructor
  public Server()
  {
	  super("Instant Messenger");
	  
	  usertext = new JTextField();
	  usertext.setEditable(false);
	  usertext.addActionListener(
			  
			  new ActionListener()
			  {
				  public void actionPerformed(ActionEvent Event)
				  {
					  sendMessage(Event.getActionCommand());
					  usertext.setText("");
				  }
			  }
			  );
	  add(usertext, BorderLayout.NORTH);
	  chatwindow = new JTextArea();
	  add(new JScrollPane(chatwindow));
	  setSize(300,150);
	  setVisible(true);
	  
	
	  
	
  }
    //setup and run the server
  
    public void startRunning()
    {
    	try {
    		server = new ServerSocket(6789,100);
    		while(true)
    		{
    			try {
    				waitForConnection();
    				setupStreams();
    				whileChatting();
    				
    			}catch(EOFException eofException)
    			{
    				showMessage("\n server ended the connection!");
    			}finally {
    				closeCrap();
    			}
    		}
    		
    	}catch(IOException ioException)
    	{
    		ioException.printStackTrace();
    	}
    }
    // wait for connection then display connection information
    public void waitForConnection() throws IOException
    {
    	showMessage("Waiting for someone to connect..");
    	connection = server.accept();
    	showMessage("now connected to"+connection.getInetAddress().getHostName());
    }
    //get stream to send and receive data
    private void setupStreams() throws IOException
   {
    	 output = new ObjectOutputStream(connection.getOutputStream());
    	 output.flush();
    	 input = new ObjectInputStream(connection.getInputStream());
    	 showMessage("\n Streams are now setup \n");
    	 
   }
    
    // during the chat conversation
 public void whileChatting() throws IOException
 {
	 String message = "you are now connected";
	 sendMessage(message);
	 ableToType(true);
	 do {
		 
		 try {
		 message = (String) input.readObject();
		 showMessage("\n" + message);
		 }catch(ClassNotFoundException classnotfoundException)
		 {
			 showMessage("\n idk wtf that user sent");
		 }
	 }while(!message.equals("CLIENT _ END"));
 }
 public void closeCrap()
 {
	 showMessage("closing the conneciton");
	 ableToType(false);
	 try {
		 output.close();
		 input.close();
		 connection.close();
	 }catch(IOException ioException )
	 {
		 ioException.printStackTrace();
	 }
 }
 public void sendMessage(String message)
 {
	 try {
		 output.writeObject("SERVER - " + message);
		 output.flush();
		 showMessage("\nSERVER - "+ message);
	 }catch(IOException ioException)
	 {
		 chatwindow.append("\n ERROR: DUDE I CANT SEND THAT MESSAGE");
	 }
 }
 //updates chatwindow
 public void showMessage(final String text)
 {
	 SwingUtilities.invokeLater(
			 new Runnable()
			 {
				 public void run()
				 {
					 chatwindow.append(text);
				 }
			 }
			 );
 }
 public void ableToType(final boolean tof)
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
 
  public static void main(String args[])
  {
	  Server sally  = new Server();
	  sally.setDefaultCloseOperation(EXIT_ON_CLOSE);
	  sally.startRunning();
  }


}
