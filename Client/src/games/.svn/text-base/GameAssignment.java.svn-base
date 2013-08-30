package games;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import ocsf.server.ConnectionToClient;
import DataObjects.Role;
import GUI.GUI_MainScreen;
import Messages.Message;
import Messages.MessageGameProgress;
import Messages.MessageJoinRoom;
import Messages.MessageJoinRoomReply;
import Messages.MessageResumeGame;
import Messages.RoomStatus;

/**
 * The Class GameAssignment.
 */
public class GameAssignment {
	
	/** The arr obj. */
	ArrayList<Object> arrObj = null;
	
	/** The client. */
	ConnectionToClient client;
	
	/**
	 * Instantiates a new game assignment.
	 *
	 * @param msg the msg
	 * @param client the client
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public GameAssignment(Object msg,ConnectionToClient client) throws IOException 
	{
		Message baseMessage = (Message) msg;
		this.client = client;
		GUI_MainScreen ms = GUI_MainScreen.getInstance();
		
		switch(baseMessage.getMessageType())
		{
			case MESSAGE_JOIN_ROOM:
			{
				//Initializing host game
	
				MessageJoinRoom msgJR = (MessageJoinRoom) baseMessage;
				
				String rivalUsername = msgJR.getRoom().getGuestName();
				ms.roomPlat.gamePlat.startGameHost(rivalUsername);
				
				
				MessageJoinRoomReply msgJRrep = new MessageJoinRoomReply(RoomStatus.ROOM_JOINED);
				
				try {
					client.sendToClient(msgJRrep);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
				break;
			
			case MESSAGE_UNSOLICITED:
			{
				MessageGameProgress msgGP = (MessageGameProgress) baseMessage;

				if(msgGP.getRole().equals(Role.SPECTATOR))
					ms.roomPlat.gamePlat.replyToSpectator(client);
				else
					ms.roomPlat.gamePlat.gameGetMessage(msgGP);
				
				
			}
				break;
		
		
		}
		
	}

}
