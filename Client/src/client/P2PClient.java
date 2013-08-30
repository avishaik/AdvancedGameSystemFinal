package client;

// This file contains material supporting section 3.7 of the textbook:
//"Object Oriented Software Engineering" and is issued under the open-source
//license found at www.lloseng.com 


import ocsf.client.*;
import java.io.*;
import java.util.ArrayList;

import Messages.Message;
import Messages.MessageConnectionLost;
import Messages.MessageUnsolicited;


/**
* This class overrides some of the methods defined in the abstract
* superclass in order to give more functionality to the client.
*
* @author Dr Timothy C. Lethbridge
* @author Dr Robert Lagani&egrave;
* @author Fran&ccedil;ois B&eacute;langer
* @version July 2000
*/
public class P2PClient extends AbstractClient
{
//Instance variables **********************************************
	

/**
* The interface type variable.  It allows the implementation of 
* the display method in the client.
*/
// ClientConsole clientUI; 
Object message;


//Constructors ****************************************************

/**
 * Constructs an instance of the chat client.
 *
 * @param host The server to connect to.
 * @param port The port number to connect on.
 * @throws IOException Signals that an I/O exception has occurred.
 */

public P2PClient(String host, int port) 
 throws IOException 
{
 super(host, port); 
 openConnection();
}


//Instance methods ************************************************
 
/**
* This method handles all data that comes in from the server.
*
* @param msg The message from the server.
*/
@SuppressWarnings("unchecked")
public synchronized void handleMessageFromServer(Object msg)
{
	  while (msg==null)
			try {
				wait();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			Message baseMessage = (Message) msg;
			
			
			switch(baseMessage.getMessageType())
			{
				
				case MESSAGE_UNSOLICITED:
				{
				
							UserTaskAssignment userTA = new UserTaskAssignment(baseMessage);
							notifyAll();
				}
						break;
					
		
		
	
				default: 
				{	
						this.message=msg;
						notifyAll();
				}
			
			}
			
			
		
 
}

/**
 * This method handles all data coming from the UI.
 *
 */

public void connectionClosed() {
	  
	  
	 
	  MessageConnectionLost msgCL = new MessageConnectionLost();
	  
	  //UserTaskAssignment userTA = new UserTaskAssignment(msgCL);

}


/**
 * Handle message from client ui.
 *
 * @param message the message
 */
public void handleMessageFromClientUI(String[] message)
{
 try
 {
 	sendToServer(message);
 }
 catch(IOException e)
 {
   //clientUI.display("Could not send message to server.  Terminating client.");
   quit();
 }
}

/**
* This method terminates the client.
*/
public void quit()
{
 try
 {
   closeConnection();
 }
 catch(IOException e) {}
 System.exit(0);
}


/**
 * Gets the message.
 *
 * @return the object
 */
public synchronized Object GetMessage() {
	
	try {
			wait();
	}
	catch (InterruptedException e) {
		e.printStackTrace();
	}
	
	Object tmpMsg = new Object(); 
	tmpMsg = message;
	
	message = null;
	
	return tmpMsg;
}
}
//End of ChatClient class
