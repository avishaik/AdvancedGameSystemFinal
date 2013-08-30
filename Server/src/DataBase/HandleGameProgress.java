package DataBase;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ocsf.server.ConnectionToClient;

import DataObjects.Player;
import DataObjects.Players;
import DataObjects.Room;
import DataObjects.Rooms;
import Messages.EnvStatus;
import Messages.MessageEndGame;
import Messages.MessageExitGame;
import Messages.MessageGameProgress;
import Messages.MessageInvitePlayer;
import Messages.MessageSpecExitGame;
import Server.EchoServer;

// TODO: Auto-generated Javadoc
/**
 * The Class HandleGameProgress - Handles various situations of a game as: exit game, end game, invite player.
 */
public class HandleGameProgress {

		/** The sc. */
		SQLConnection sc;
		
		/** The chg users stat. */
		private PreparedStatement chgUsersStat;
		
		/** The chg room stat. */
		private PreparedStatement chgRoomStat;
		
		/** The inc rank. */
		private PreparedStatement incRank;
		
		/** The room. */
		private Room room;
		
		/** The players. */
		private ArrayList<String> players;
		
		/** The room_id. */
		private int room_id;
		
		/** The status. */
		private EnvStatus status;
		
		/** The active rooms. */
		private Rooms activeRooms;

		
		
		/**
		 * Instantiates a new handle game progress.
		 *
		 * @param msgGP the msg gp
		 * @param server the server
		 */
		public HandleGameProgress(MessageGameProgress msgGP, EchoServer server) {
				
		try {
			activeRooms = Rooms.getRoomsInstance();
			sc = SQLConnection.SQLConnectionSingleton();
			
			switch(msgGP.getMessageGameAction())
			{
				//what happens when a user presses the exit game button
				case EXIT_GAME:
				{
					status = EnvStatus.EXIT_GAME;
					players = new ArrayList<String>();
					
					MessageExitGame msgEG = (MessageExitGame) msgGP;
					room = msgEG.getRoom();

					//Updating the game_status field in the room to 'Paused'
					room_id = room.getRoomID();
					
					chgRoomStat = sc.getConnection().prepareStatement("UPDATE rooms SET game_status='Paused' WHERE room_id=?");
					
					chgRoomStat.setInt(1, room_id);
					
					chgRoomStat.executeUpdate();
					chgRoomStat.close();

					
					//Updating the game_status field of players to 'in_lobby'
					String hostName = room.getHostName();
					String guestName = room.getGuestName();
					String specName = room.getSpecName();
					
					players.add(hostName);
					players.add(guestName);
					if(specName!=null)
						players.add(specName);
					
					for(String player:players)
					{
						
						chgUsersStat = sc.getConnection().prepareStatement("UPDATE users SET game_status='in_lobby' WHERE username=?");
							
						chgUsersStat.setString(1, player);
						
						chgUsersStat.executeUpdate();

						chgUsersStat.close();	
						
					
					}
					
					activeRooms.removeRoomFromList(room);	//remove room from the activeRooms

				}
					break;
				
				case SPEC_EXIT_GAME:
				{
					status = EnvStatus.EXIT_GAME; /** SPEC_EXIT_GAME  is the same as EXIT_GAME for refresh lobby which uses this status */
					
					MessageSpecExitGame msgSEG = (MessageSpecExitGame) msgGP;
					String specUN = msgSEG.getSpecUsername();
					
					PreparedStatement chgSpecStat = sc.getConnection().prepareStatement("UPDATE users SET game_status='in_lobby' WHERE username=?");
					
					chgSpecStat.setString(1, specUN);
					
					chgSpecStat.executeUpdate();
					
					chgSpecStat.close();
					
				}
					break;
				
				case END_GAME:		//when a game ends
				{
					status = EnvStatus.EXIT_GAME; /** END_GAME is the same as EXIT_GAME for refresh lobby which uses this status */
				
					players = new ArrayList<String>();
					
					MessageEndGame msgEG = (MessageEndGame) msgGP;
					
					room = msgEG.getRoom();
					
					//Updating the game_status field in the room to 'Paused'
					room_id = room.getRoomID();
					
					chgRoomStat = sc.getConnection().prepareStatement("UPDATE rooms SET game_status='Ended' WHERE room_id=?");
					
					chgRoomStat.setInt(1, room_id);
					
					chgRoomStat.executeUpdate();
					chgRoomStat.close();
					
					
					//Updating the game_status field of players to 'in_lobby'
					String hostName = room.getHostName();
					String guestName = room.getGuestName();
					String specName = room.getSpecName();
					
					players.add(hostName);
					players.add(guestName);
					if(specName!=null)
						players.add(specName);
					
					for(String player:players)
					{
						
						chgUsersStat = sc.getConnection().prepareStatement("UPDATE users SET game_status='in_lobby' WHERE username=?");
							
						chgUsersStat.setString(1, player);
						
						chgUsersStat.executeUpdate();

						chgUsersStat.close();	
					}
					
					incRank = sc.getConnection().prepareStatement("UPDATE users SET rank=rank+1 WHERE username=?");
					String winner = room.getWinner();
					
					if(!room.isTieFlag()) 
					{
						incRank.setString(1, winner);
						incRank.executeUpdate();
						
					}
						
					activeRooms.removeRoomFromList(room);
				}
				
					break;
					
				  case MESSAGE_INVITE_PLAYER:		
				  {
					  status = EnvStatus.EXIT_GAME; /** EXIT_GAME fits all */

					  MessageInvitePlayer msgIP = (MessageInvitePlayer) msgGP;
					  
					  
					  Players players = Players.getPlayersInstance();
					  
					  Player rival = players.getPlayerByUN(msgIP.getPlayerUN());
					  
					  Thread[] thrArr= server.getClientConnections();
					  
						try {
							//send an invitation to play a game to the chosen player
							
							for(int i=0;i<thrArr.length;i++)
							{
								int id = (int) thrArr[i].getId();
								
								if(id==rival.getThreadNum()) {
									
									ConnectionToClient ctc = (ConnectionToClient)thrArr[i];
									ctc.sendToClient(msgIP);
									
								}
							}
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					  
					  /*GetPlayerIP getPlayerIP = new GetPlayerIP();
					  
					  String playerIP = getPlayerIP.getPlayerIP(msgIP.getPlayerUN());
					  
					  InvitePlayer invitePlayer = new InvitePlayer(server);
					  
					  invitePlayer.invitePlayer(playerIP, msgIP);*/
					  
				  }
				  
				  	break;
					
				
					//ADD other game process cases here
					
				default: 
				{
					status = EnvStatus.IN_GAME;
				}
			}
			
			
				
					
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();		
				
			
			
			}/* catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
			
			
		}


		/**
		 * Sets the status.
		 *
		 * @param status the new status
		 */
		public void setStatus(EnvStatus status) {
			this.status = status;
		}


		/**
		 * Gets the status.
		 *
		 * @return the status
		 */
		public EnvStatus getStatus() {
			return status;
		}
}
