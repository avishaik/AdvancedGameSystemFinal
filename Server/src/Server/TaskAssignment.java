package Server;


import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import ocsf.server.ConnectionToClient;
import DataBase.*;
import DataObjects.MyData;
import DataObjects.Player;
import DataObjects.Players;
import DataObjects.Room;
import DataObjects.Rooms;
import Messages.*;


// TODO: Auto-generated Javadoc
/**
 * The Class TaskAssignment.
 */
public class TaskAssignment {
	
	/** The client. */
	ConnectionToClient client;
	
	/** The server. */
	EchoServer server;
	
	/** The arr obj. */
	ArrayList<Object> arrObj = null;
	
	/** The active rooms. */
	private Rooms activeRooms;
	
	/** The active players. */
	private Players activePlayers;
	
	/**
	 * Instantiates a new task assignment.
	 *
	 * @param msg - The message to be dealt with
	 * @param client - The client who sent the message
	 * @param server the server
	 * @throws IllegalArgumentException the illegal argument exception
	 * @throws IllegalAccessException the illegal access exception
	 * @throws SQLException the sQL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	
	public TaskAssignment(Object msg,ConnectionToClient client, EchoServer server) throws IllegalArgumentException, IllegalAccessException, SQLException, IOException
	{
		
		this.client=client;
		this.server=server;	
		activePlayers = Players.getPlayersInstance();
		activeRooms = Rooms.getRoomsInstance();
		Message baseMessage = (Message) msg;
		switch (baseMessage.getMessageType()){
		
		case MESSAGE_LOGIN :
		{
			// check if username and pass are correct in database
			   //and send back corresponding message
			   MessageLogin MsgLog=(MessageLogin) baseMessage;
			   
		   try {
			   
			    LoginCheck logC=new LoginCheck(MsgLog.getUsername(),MsgLog.getPassword(),client.getInetAddress().toString());
			    
			    logC.Check();
			    
			    MessageLoginReply reply = new MessageLoginReply(logC.GetLoginStatus(),logC.GetErrorMessage(),logC.getUsername(),logC.getPasswrod(),client.getInetAddress().getHostAddress());
			    
			    client.sendToClient(reply);
			    
			    
			    
			   } catch (IOException e) {
			 
			    e.printStackTrace();
			   }
			   
		 } 
			break;
			
		case MESSAGE_OPEN_ROOM :
		{
			//Handling MESSAGE_OPEN_ROOM - when a user opens a new room
			MessageOpenRoom msgOR = (MessageOpenRoom) baseMessage;

			Room room = msgOR.getRoom();
			
			StartRoom startRoom = new StartRoom(room);
			room = startRoom.create_room();
			
			int roomID = room.getRoomID();
			
			MessageOpenRoomReply reply = new MessageOpenRoomReply(RoomStatus.ROOM_OPEN,roomID);
			
			client.sendToClient(reply);
		
			//Handling refresh lobby
			MyData data = new MyData();
			
			data.setRoom(room);
			
			data.setEnvironment(EnvStatus.OPEN_GAME);
			
			refreshLobby(data);
			
			activeRooms.setRoom(room);	//add new room to the rooms list
			
		}
		
			break;
			
		case MESSAGE_JOIN_ROOM :
		{
			//Handling message MESSAGE_JOIN_ROOM - when a user join a room
			MessageJoinRoom MsgjoinRoom=(MessageJoinRoom) baseMessage;
			
			Room room = MsgjoinRoom.getRoom();
			
			JoinRoom jr=new JoinRoom(room);
			
			jr.join_room();
			
			//Rooms online_rooms=Rooms.getRoomsInstance();
			
			//Room room=online_rooms.getRoom(MsgjoinRoom.getRoom().getRoomID());
			
			
			MessageJoinRoomReply reply = new MessageJoinRoomReply(RoomStatus.ROOM_JOINED);
			
			if(!room.isInvite())
				client.sendToClient(reply);
		
			//Handling refresh lobby
			MyData data = new MyData();
			
			data.setRoom(room);
			
			data.setEnvironment(EnvStatus.OPEN_GAME);
			
			refreshLobby(data);
			
			int index = activeRooms.getRoomIndex(room.getRoomID());//get the room index to update room
			
			room.setStatus("Playing");
			
			activeRooms.updateRoom(index, room);	//update room in the rooms list
			
			//room.setPlayer(1, player)
			//JoinRoom joinRoom=new JoinRoom(room);
			
		}
			break;
		
		case MESSAGE_LOGOUT:
		{
			MessageLogout msgLogOut=(MessageLogout) baseMessage;
			
			LogOut logout = new LogOut(msgLogOut.getUsername());
			
			logout.makeLogOut();	//updating user's status in the DB
			
			MessageLogoutReply reply = new MessageLogoutReply(logout.getLogoutStatus(), logout.getErrorMsg());
			
			client.sendToClient(reply);
			
			MyData data = new MyData();
			
			Player player = new Player();
			
			player.setUsername(msgLogOut.getUsername());
			
			data.setPlayer(player);
			
			data.setEnvironment(EnvStatus.LOGGED_OUT);
			
			refreshLobby(data);
			
			activePlayers.removePlayerFromList(data.getPlayer().getUsername());	 //remove the player from the players list
				

		}
			break;
		
			
		case MESSAGE_GET_RANK:
		{
			
			//Handling MESSAGE_GET_RANK - After the user successfully logs in, he gets his current rank
			MessageGetRank msgGR = (MessageGetRank) baseMessage;
			
			MyData data = msgGR.getData();
			
			GetRank getRank = new GetRank();
			
			int rank = getRank.getRank(data.getPlayer().getUsername());
			
			MessageGetRankReply reply = new MessageGetRankReply(rank);
			
			
			client.sendToClient(reply);
			
			//Handling refresh lobby
			data.getPlayer().setRank(rank);
			
			data.setEnvironment(EnvStatus.LOBBY);

			refreshLobby(data);
			
			data.getPlayer().setThreadNum((int)client.getId());	//here we save the thread id to check if the user is still alive
			
			activePlayers.setPlayer(data.getPlayer());		//add new player to the players list
			
		}
			break;
		
		case MESSAGE_UNSOLICITED:
		{
			//Handling MESSAGE_UNSOLICITED - handles messages from a game, if user exit a game and so.
			MessageGameProgress msgGP = (MessageGameProgress) baseMessage;
			
			HandleGameProgress handleGP = new HandleGameProgress(msgGP,server);
			
			MyData data = new MyData();
			
			data.setEnvironment(handleGP.getStatus());
			
			refreshLobby(data);

		}
		
			break;
		
		  case MESSAGE_SPEC_ROOM:
		  {
		   //Handling MESSAGE_SPEC_ROOM - when a user joins a room that has 2 members, he enters the game as a spectator
		   MessageSpecRoom msgSR = (MessageSpecRoom) baseMessage;
		   
		   UpdateSpectator updateSpec = new UpdateSpectator(msgSR.getPlayer());
		   updateSpec.update_user();
		   
		   MessageSpecRoomReply reply = new MessageSpecRoomReply(RoomStatus.ROOM_JOINED);
		   
		   client.sendToClient(reply);
		   
		   
		   MyData data = new MyData();
		   data.setPlayer(msgSR.getPlayer());
		   
		   data.setEnvironment(EnvStatus.OPEN_GAME);
		   
		   refreshLobby(data);
		   
			Room room = msgSR.getRoom();
		   
			
			room.addSpecToList(msgSR.getPlayer());
			
			int index = activeRooms.getRoomIndex(room.getRoomID());//get the room index to update room
			
			activeRooms.updateRoom(index, room);	//update room in the rooms list
		   
		   
		  }
		  	break;
		 
			case MESSAGE_INVITATION_DENIED:
			{
				//Handling MESSAGE_INVITATION_DENIED - if a user refuses a game offer, the user that sent the invitation get a cancelation message
				MessageInvitationDenied msgID = (MessageInvitationDenied) baseMessage;
				
				Room room = msgID.getRoom();
				
				Player host = activePlayers.getPlayerByUN(room.getHostName());
				
				//This is a cancelation message
				MessageInvitationDeniedReply msgIDR = new MessageInvitationDeniedReply(room);
				
				Thread[] thrArr = server.getClientConnections();
				
				//send to the inviting player to cancel the room
				try {
					 
					for(int i=0;i<thrArr.length;i++)
					{
						int id = (int) thrArr[i].getId();
						
						if(id==host.getThreadNum()) {
							
							ConnectionToClient ctc = (ConnectionToClient)thrArr[i];
							ctc.sendToClient(msgIDR);
							
						}
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
				
					CloseRoom closeroom = new CloseRoom(room.getRoomID());
					UpdateUser updateUser = new UpdateUser(room.getHostName(),"in_lobby");
					updateUser.update_user();
				
				   MyData data = new MyData();
				   data.setPlayer(host);
				   
				   data.setEnvironment(EnvStatus.LOBBY);
				   
				   refreshLobby(data);
				   
				   activeRooms.removeRoomFromList(room);

			}
			
				break;
				
			case MESSAGE_SHOW_HISTORY:
			{

				MessageShowHistory MsgShowHistory=(MessageShowHistory) baseMessage;
				   
				   
				GameHistory gh = new GameHistory(MsgShowHistory.getMyData().getPlayer().getUsername());
				   
				    
				 ArrayList<Room> roomHistory = gh.getGamesHistory();
				 
				 MessageShowHistoryReply msgShowHistoryReply = new MessageShowHistoryReply();
				 msgShowHistoryReply.setRoomsHistory(roomHistory);
				 
				 client.sendToClient(msgShowHistoryReply);
	
				   
			 } 
				break;
				
			case MESSAGE_INVITE_REFRESH:
			{

				MessageInviteRefresh MsgIR =(MessageInviteRefresh) baseMessage;
				   
				MyData data = MsgIR.getMyData();
				
				//data.setEnvironment(EnvStatus.LOBBY);
				
				refreshLobby(data);
	
				   
			 } 
				break;
		  	
	}
		

}
	
	/**
	 * Refresh lobby.
	 *
	 * @param myData the my data
	 */
	private void refreshLobby(MyData myData)
	{
		MessageRefreshLobby msgRefresh = new MessageRefreshLobby();
		
		RefreshLobby refLobby = new RefreshLobby();
		
		refLobby.refresh(myData.getEnvironment(), myData);
		
		ArrayList<Player> players = refLobby.getPlayers();
		
		ArrayList<Room> rooms = refLobby.getRooms();
		
		
		if((players!=null)&&(rooms!=null))
		{
			msgRefresh.setPlayers(refLobby.getPlayers());
			msgRefresh.setRooms(refLobby.getRooms());
			
			server.sendToAllClients(msgRefresh);
		}
			
	}	


}


