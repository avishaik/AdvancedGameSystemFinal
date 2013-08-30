package client;

//import games.GamePlatform;

import games.GUI_GamePlatform;
import games.GUI_GameTrivia;
import games.RoomPlatform;

import java.util.ArrayList;

import DataObjects.Player;
import DataObjects.Role;
import DataObjects.Room;
import GUI.GUI_ConnectionLost;
import GUI.GUI_Lobby;
import GUI.GUI_MainScreen;
import Messages.EnvStatus;
import Messages.Message;
import Messages.MessageGameProgress;
import Messages.MessageGameTrivia;
import Messages.MessageInvitationDeniedReply;
import Messages.MessageInvitePlayer;
import Messages.MessageRefreshLobby;
import Messages.MessageRefreshLobby;
import Messages.MessageUnsolicited;

/**
 * The Class UserTaskAssignment.
 */
public class UserTaskAssignment {

	
	/**
	 * Instantiates a new user task assignment.
	 *
	 * @param baseMessage the base message
	 */
	public UserTaskAssignment(Message baseMessage) {
		
		
		MessageUnsolicited baseUnsolicitedMessage = (MessageUnsolicited) baseMessage;
		GUI_MainScreen ms = GUI_MainScreen.getInstance();
	
		switch (baseUnsolicitedMessage.getUnsolicitedMessageType()){
		
		//This message is sent by the server every time a global change is made
		//Users use the message's content to refresh their lobby
		case UNSOLICITED_REFRESH_LOBBY:
		{					
							MessageRefreshLobby msgRLrep = (MessageRefreshLobby) baseMessage;
							
							switch(ms.myData.getEnvironment()) 
							{
							
								case LOBBY:
								{			
									ArrayList<Player> playerList = msgRLrep.getPlayers();
									ArrayList<Room> roomList = msgRLrep.getRooms();
									
									ms.lobby = new GUI_Lobby(ms,roomList,playerList);
									ms.setContentPane(ms.lobby);
									
									System.out.println("Refresh from "+ms.myData.getPlayer().getUsername());
									System.out.println("Players: "+playerList.toString());
									System.out.println("Rooms: "+roomList.toString());
									
								}
									break;		
								
								default:	//Do not update in case user is not on lobby screen
							}
							
		}	
		
			break;
		
		//This message relates to all game correspondences
		case UNSOLICITED_GAME_PROGRESS:
		{
				MessageGameProgress baseGameMessage = (MessageGameProgress) baseUnsolicitedMessage;
				
				if(baseGameMessage instanceof MessageInvitePlayer)
				{
					MessageInvitePlayer msgIP = (MessageInvitePlayer) baseUnsolicitedMessage;
					
					String inviterUN = msgIP.getInviterUN();
					String inviterIP = msgIP.getInviterIP();
					String gameType = msgIP.getGameType();
					int roomID = msgIP.getRoomID();
					
					ms.lobby.getInvite(inviterUN, inviterIP,gameType,roomID);
				}
				else 
					ms.roomPlat.gamePlat.gameGetMessage(baseGameMessage);

		}
			break;
			
		//This message arrives in case a user disconnects abruptly during game
		case MESSAGE_CONNECTION_LOST:
		{
			
			if(ms.myData.getEnvironment()==EnvStatus.IN_GAME)
				try {
							ms.roomPlat.gamePlat.forceExitGame();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			
			
		}
			break;
			
		//This message arrives in case a user denies an invitation to play
		case MESSAGE_INIVTATION_DENIED_REPLY:
		{
			
			MessageInvitationDeniedReply msgIDR = (MessageInvitationDeniedReply) baseUnsolicitedMessage;
			Room room = msgIDR.getRoom();
			
			if(ms.myData.getEnvironment()==EnvStatus.IN_GAME)
				try {
							ms.roomPlat.gamePlat.invitationDenied(room.getGuestName(),room.getGameName());
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			
			
		}
			break;
			



	}
	
}
	

	
}
