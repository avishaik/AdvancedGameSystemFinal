
package games;

import java.io.IOException;

import DataObjects.Role;
import DataObjects.Room;
import GUI.GUI_MainScreen;
import Messages.MessageExitGame;
import Messages.MessageGameProgress;
import Messages.MessageGameTrivia;
import Messages.MessageJoinRoom;
import Messages.MessageJoinRoomReply;
import Messages.MessageOpenRoom;
import Messages.MessageOpenRoomReply;
import Messages.MessageSpecRoom;
import Messages.MessageSpecRoomReply;
import Messages.RoomStatus;
import client.P2PClient;
import client.P2PServer;


/**
 * The Class RoomPlatform.
 */
public class RoomPlatform  {

	/** The p2p server. */
	public P2PServer p2pServer=null; 
	
	/** The p2p client. */
	public P2PClient p2pClient=null;  
	
	/** The role. */
	public Role role;
	
	/** The room. */
	private Room room;
	
	/** The game plat. */
	public GUI_GamePlatform gamePlat;
	
	/**
	 * Gets the room.
	 *
	 * @return the room
	 */
	public Room getRoom() {
		return room;
	}



	/**
	 * Sets the room.
	 *
	 * @param room the new room
	 */
	public void setRoom(Room room) {
		this.room = room;
	}

	/** The server status. */
	private RoomStatus serverStatus;
	
	/** The host status. */
	private RoomStatus hostStatus;
	
	/** The host ip. */
	public String hostIP;  
	
	/** The client ip. */
	public String clientIP;
	
	/** The room id. */
	private int roomID;
	
	/** The client port. */
	public int clientPort=5556;
	
	/** The ms. */
	private GUI_MainScreen ms;
	

	/**
	 * Instantiates a new room platform.
	 *
	 * @param room the room
	 * @param role the role
	 */
	public RoomPlatform(Room room,Role role)
	{
		super();

		this.role=role;
		this.room=room;
		ms = GUI_MainScreen.getInstance();
		
		switch(role)
		{
			case HOST:
			{
				hostConnection();
			
			}
				break;
				
			case CLIENT:
			{
				if(room.isInvite())
					geustInvitedConnection();
				else
					guestConnection();	
			}
				break;
				
			case SPECTATOR:
			{
				
				specConnection();
				
			}
				break;
		}
	
	}
	
	
	
	/**
	 * Host connection.
	 */
	private void hostConnection()
	{
		
		
		MessageOpenRoom msgOR = new MessageOpenRoom(room);
		
			p2pServer = new P2PServer(5556);			
	
		try 
	    	{
				p2pServer.listen(); //Start listening for connections, this is the host of the game
	    	} 
	    	catch (Exception ex) 
	    	{
	    		System.out.println("ERROR - Could not listen for clients!");
	    	}
	    	
	    	try {
				ms.client.sendToServer(msgOR);		//sending message to server to open a room
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			MessageOpenRoomReply msgORrep = (MessageOpenRoomReply) ms.client.GetMessage();
			
			roomID = msgORrep.getRoomID();
			
			this.room.setRoomID(roomID);
			
			setServerStatus(msgORrep.getRoomStatus());

	}
	/**guestConnection() - creating a connection when the user is joining a game. The user plays the role of a client*/	
	private void guestConnection()
	{

		
		MessageJoinRoom msgJR = new MessageJoinRoom();
		room.setGuestName(ms.lg.getCurrentUsername());
		
		msgJR.setRoom(room);
		
		
	try {	
			ms.client.sendToServer(msgJR);		//updating the server that the room has 2 players, now in status 'playing'

		} catch (IOException e) {
					e.printStackTrace(); }

			MessageJoinRoomReply msgJRrep = (MessageJoinRoomReply) ms.client.GetMessage();
		
			setServerStatus(msgJRrep.getStatus());

			
		try {
			p2pClient = new P2PClient(room.getHostIP(),5556);
			
			p2pClient.sendToServer(msgJR);

			msgJRrep = (MessageJoinRoomReply) p2pClient.GetMessage();
			
			setHostStatus(msgJRrep.getStatus());
			
			
		} catch (IOException e) {
		
			e.printStackTrace();
		}	
	}
	
	
	/**
	 * Geust invited connection.
	 */
	private void geustInvitedConnection()
	{
		MessageJoinRoom msgJR = new MessageJoinRoom();
		room.setGuestName(ms.lg.getCurrentUsername());
		
		msgJR.setRoom(room);
		
		
	try {	
			ms.client.sendToServer(msgJR);		//updating the server that the room has 2 players, now in status 'playing'

		} catch (IOException e) {
					e.printStackTrace(); }

			//MessageJoinRoomReply msgJRrep = (MessageJoinRoomReply) ms.client.GetMessage();
		
			MessageJoinRoomReply msgJRrep = new MessageJoinRoomReply(RoomStatus.ROOM_JOINED);
		
			setServerStatus(msgJRrep.getStatus());

			
		try {
			p2pClient = new P2PClient(room.getHostIP(),5556);
			
			p2pClient.sendToServer(msgJR);

			msgJRrep = (MessageJoinRoomReply) p2pClient.GetMessage();
			
			setHostStatus(msgJRrep.getStatus());
			
			
		} catch (IOException e) {
		
			e.printStackTrace();
		}	
	}
	
	
		

	
	
	
	 /**
 	 * specConnection() -.
 	 */
	 private void specConnection() {
	  
	  room.setSpecName(ms.lg.getCurrentUsername());
	  MessageSpecRoom msgSR = new MessageSpecRoom(ms.myData.getPlayer(),room);
	  
	  try {
	   p2pClient = new P2PClient(room.getHostIP(),5556);  //connecting to the host to watch the game
	  } catch (IOException e) {
	   // TODO Auto-generated catch block
	   e.printStackTrace();
	  }

	  try {
	   ms.client.sendToServer(msgSR);  //updating the server that the room has 2 players, now in status 'playing'
	  } catch (IOException e) {
	   // TODO Auto-generated catch block
	   e.printStackTrace();
	  }
	  
	  MessageSpecRoomReply msgSRrep = (MessageSpecRoomReply) ms.client.GetMessage();
	  
	  setServerStatus(msgSRrep.getStatus());
	  
	  setHostStatus(msgSRrep.getStatus());
	  

	  
	 }
	


	/**
	 * Sets the server status.
	 *
	 * @param serverStatus the new server status
	 */
	public void setServerStatus(RoomStatus serverStatus) {
		this.serverStatus = serverStatus;
	}



	/**
	 * Gets the server status.
	 *
	 * @return the server status
	 */
	public RoomStatus getServerStatus() {
		return serverStatus;
	}



	/**
	 * Sets the host status.
	 *
	 * @param hostStatus the new host status
	 */
	public void setHostStatus(RoomStatus hostStatus) {
		this.hostStatus = hostStatus;
	}



	/**
	 * Gets the host status.
	 *
	 * @return the host status
	 */
	public RoomStatus getHostStatus() {
		return hostStatus;
	}



	/**
	 * Sets the room id.
	 *
	 * @param roomID the new room id
	 */
	public void setRoomID(int roomID) {
		this.roomID = roomID;
	}



	/**
	 * Gets the room id.
	 *
	 * @return the room id
	 */
	public int getRoomID() {
		return roomID;
	}
	



}
	





