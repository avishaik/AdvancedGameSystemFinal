package DataBase;

import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import DataObjects.LobbyData;
import DataObjects.MyData;
import DataObjects.Players;
import DataObjects.Room;
import DataObjects.Player;
import DataObjects.Rooms;
import Messages.EnvStatus;



// TODO: Auto-generated Javadoc
/**
 * The Class RefreshLobby.
 */
public class RefreshLobby {

	//del? private Rooms rooms;
	//del? private Players players;
	/** The update player list. */
	private PreparedStatement updatePlayerList=null;
	
	/** The get player list. */
	private PreparedStatement getPlayerList = null;
	
	/** The get room list. */
	private PreparedStatement getRoomList = null;
	
	/** The get player list when returning to lobby. */
	private PreparedStatement getPlayerInLobby = null;
	
	/** The players. */
	private ArrayList<Player> players = null;
	
	/** The rooms. */
	private ArrayList<Room> rooms = null;

	/** The sc. */
	SQLConnection sc;
	
	/**
	 * Instantiates a new refresh lobby.
	 */
	public RefreshLobby() {
		
		sc = SQLConnection.SQLConnectionSingleton();
		try {
			getPlayerList = sc.getConnection().prepareStatement("SELECT username,rank,game_status FROM users WHERE game_status<>'offline'");
			getRoomList = sc.getConnection().prepareStatement("SELECT room_id,game_name,game_status,host_ip FROM rooms WHERE game_status='Playing' OR game_status='Waiting'");
			getPlayerInLobby = sc.getConnection().prepareStatement("SELECT username,rank,game_status FROM users WHERE game_status<>'offline' AND username<>?");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	
	/**
	 * Refresh.
	 *
	 * @param status the status
	 * @param data the data
	 */
	public void refresh(EnvStatus status, MyData data) {
		
		try {
				
				
				rooms = new ArrayList<Room>();
				players = new ArrayList<Player>();
				
				switch(status)
				{
				
					case LOBBY:
					{
						Player newPlayer = (Player) data.getPlayer();
						
						String username = newPlayer.getUsername();

						//Retrieving information for new player
						PreparedStatement getPlayerRank = sc.getConnection().prepareStatement("SELECT rank FROM users WHERE username=?");
						
						getPlayerRank.setString(1, username);
						
						ResultSet rsPlayerRank = (ResultSet) getPlayerRank.executeQuery();
						
						int rank=0;
						if(rsPlayerRank.next())
							rank = rsPlayerRank.getInt(1);
						
						newPlayer.setRank(rank);
						newPlayer.setStatus("in_lobby");
						
						//Retrieving active player list information	
						getPlayerInLobby.setString(1, username);
						ResultSet rsPlayerList = (ResultSet) getPlayerInLobby.executeQuery();
						//ResultSet rsPlayerList = (ResultSet) getPlayerList.executeQuery();
						
						while(rsPlayerList.next())
						{
							Player tempPlayer = new Player();
								
							String userName = rsPlayerList.getString(1);
							rank = rsPlayerList.getInt(2);
							String game_status = rsPlayerList.getString(3);
							tempPlayer.setUsername(userName);
							tempPlayer.setRank(rank);
							tempPlayer.setStatus(game_status);
							
							players.add(tempPlayer);
							
						}
						
						
						//Adding new player to player list
						players.add(newPlayer);
						
						//Retrieving active rooms list information
						PreparedStatement getPlayersInRoom = sc.getConnection().prepareStatement("SELECT username,role FROM users_in_room WHERE room_id=?");

						
						ResultSet rsRoomList = (ResultSet) getRoomList.executeQuery();
						
						while(rsRoomList.next())
						{
							//Retrieving rooms information
							Room tempRoom = new Room();
							
							int room_id = rsRoomList.getInt(1);
							String game_name = rsRoomList.getString(2);
							String game_status = rsRoomList.getString(3);
							String hostIP = rsRoomList.getString(4);
							
							tempRoom.setRoomID(room_id);
							tempRoom.setGameName(game_name);
							tempRoom.setStatus(game_status);
							tempRoom.setHostIP(hostIP);
							
							//Retrieving players in room information
							getPlayersInRoom.setInt(1, room_id);
							
							ResultSet rsPlayersInRoom = (ResultSet) getPlayersInRoom.executeQuery();
							
							String role;
							while(rsPlayersInRoom.next())
							{
								username = rsPlayersInRoom.getString(1);
								role = rsPlayersInRoom.getString(2);
								
								if(role.equals("Host"))
									tempRoom.setHostName(username);
								else if (role.equals("Guest"))
									tempRoom.setGuestName(username);
								else if (role.equals("Spec"))
									tempRoom.setSpecName(username);
								
							}
							
							rooms.add(tempRoom);
	
						}
						
						//Updating user status
						updatePlayerList = sc.getConnection().prepareStatement("UPDATE users SET game_status='in_lobby' WHERE username=?");

						username = newPlayer.getUsername();
						
						updatePlayerList.setString(1, username);
						
						updatePlayerList.executeUpdate();
						
						//del? players.setPlayer(player);
							

					}
						break;
					
					case OPEN_GAME:
					{
						//Retrieving active player list information	
						ResultSet rsPlayerList = (ResultSet) getPlayerList.executeQuery();
						
						while(rsPlayerList.next())
						{
							Player tempPlayer = new Player();
								
							String userName = rsPlayerList.getString(1);
							int rank = rsPlayerList.getInt(2);
							String game_status = rsPlayerList.getString(3);
							tempPlayer.setUsername(userName);
							tempPlayer.setRank(rank);
							tempPlayer.setStatus(game_status);
							
							players.add(tempPlayer);
							
						}
						
						//Retrieving active rooms list information
						PreparedStatement getPlayersInRoom = sc.getConnection().prepareStatement("SELECT username,role FROM users_in_room WHERE room_id=?");

						
						ResultSet rsRoomList = (ResultSet) getRoomList.executeQuery();
						
						while(rsRoomList.next())
						{
							//Retrieving rooms information
							Room tempRoom = new Room();
							
							int room_id = rsRoomList.getInt(1);
							String game_name = rsRoomList.getString(2);
							String game_status = rsRoomList.getString(3);
							String hostIP = rsRoomList.getString(4);
							
							tempRoom.setRoomID(room_id);
							tempRoom.setGameName(game_name);
							tempRoom.setStatus(game_status);
							tempRoom.setHostIP(hostIP);
							
							//Retrieving players in room information
							getPlayersInRoom.setInt(1, room_id);
							
							ResultSet rsPlayersInRoom = (ResultSet) getPlayersInRoom.executeQuery();
							
							String role;
							String username;
							while(rsPlayersInRoom.next())
							{
								username = rsPlayersInRoom.getString(1);
								role = rsPlayersInRoom.getString(2);
								
								if(role.equals("Host"))
									tempRoom.setHostName(username);
								else if (role.equals("Guest"))
									tempRoom.setGuestName(username);
								else if (role.equals("Spec"))
									tempRoom.setSpecName(username);
								
							}
							
							rooms.add(tempRoom);
	
						}
						
					}
						break;
					
					case EXIT_GAME:
					{
						//Fetching active room list
						ResultSet rsRoomList = (ResultSet) getRoomList.executeQuery();
						
						//For each room
						while(rsRoomList.next())
						{

							Room tempRoom = new Room();
							
							//Fetching room information
							int room_id = rsRoomList.getInt(1);
							String game_name = rsRoomList.getString(2);
							String game_status = rsRoomList.getString(3);
							String host_ip = rsRoomList.getString(4);
							
							//Setting room information
							tempRoom.setRoomID(room_id);
							tempRoom.setGameName(game_name);
							tempRoom.setStatus(game_status);
							tempRoom.setHostIP(host_ip);
							
							//Fetching users in room information
							PreparedStatement getPlayersInRoom = sc.getConnection().prepareStatement("SELECT username,role FROM users_in_room WHERE room_id=?");
							getPlayersInRoom.setInt(1, room_id);
							
							ResultSet rsPlayersInRoom = (ResultSet) getPlayersInRoom.executeQuery();
							
							//Add each player to room
							String role;
							String username;
							while(rsPlayersInRoom.next())
							{
								username = rsPlayersInRoom.getString(1);
								role = rsPlayersInRoom.getString(2);
								
								if(role.equals("Host"))
									tempRoom.setHostName(username);
								else if (role.equals("Guest"))
									tempRoom.setGuestName(username);
								else if (role.equals("Spec"))
									tempRoom.setSpecName(username);
								
							}
							getPlayersInRoom.close();
							rsPlayersInRoom.close();
							
							//Add room to the refresh room list
							rooms.add(tempRoom);
			
						}
						getRoomList.close();
						rsRoomList.close();
						
						//Fetching active player list
						ResultSet rsPlayerList = (ResultSet) getPlayerList.executeQuery();
				
						while(rsPlayerList.next())
						{
							Player player = new Player();
							
							//Fetching player information
							String username = rsPlayerList.getString(1);
							int rank = rsPlayerList.getInt(2);
							String game_status = rsPlayerList.getString(3);
							
							//Setting player information
							player.setUsername(username);
							player.setRank(rank);
							player.setStatus(game_status);
							
							players.add(player);
							
						}
						rsPlayerList.close();
							
					}
						break;
						
					case LOGGED_OUT:
					{
						
						//Retrieving active player list information
						PreparedStatement getPlayerList = sc.getConnection().prepareStatement("SELECT username,rank,game_status FROM users WHERE game_status<>'offline'");
						
						ResultSet rsPlayerList = (ResultSet) getPlayerList.executeQuery();
						
						while(rsPlayerList.next())
						{
							Player tempPlayer = new Player();
								
							String userName = rsPlayerList.getString(1);
							int rank = rsPlayerList.getInt(2);
							String game_status = rsPlayerList.getString(3);
							tempPlayer.setUsername(userName);
							tempPlayer.setRank(rank);
							tempPlayer.setStatus(game_status);
							
							players.add(tempPlayer);
							
						}
						
						//Retrieving active rooms list information
						PreparedStatement getRoomList = sc.getConnection().prepareStatement("SELECT room_id,game_name,game_status FROM rooms WHERE game_status='Playing' OR game_status='Waiting'");
						PreparedStatement getPlayersInRoom = sc.getConnection().prepareStatement("SELECT username,role FROM users_in_room WHERE room_id=?");
						
						ResultSet rsRoomList = (ResultSet) getRoomList.executeQuery();
	
						while(rsRoomList.next())
						{
							//Retrieving rooms information
							Room tempRoom = new Room();
							
							int room_id = rsRoomList.getInt(1);
							String game_name = rsRoomList.getString(2);
							String game_status = rsRoomList.getString(3);
							
							tempRoom.setRoomID(room_id);
							tempRoom.setGameName(game_name);
							tempRoom.setStatus(game_status);
							
							//Retrieving players in room information
							getPlayersInRoom.setInt(1, room_id);
							
							ResultSet rsPlayersInRoom = (ResultSet) getPlayersInRoom.executeQuery();
							
							String role;
							String username;
							while(rsPlayersInRoom.next())
							{
								username = rsPlayersInRoom.getString(1);
								role = rsPlayersInRoom.getString(2);
								
								if(role.equals("Host"))
									tempRoom.setHostName(username);
								else if (role.equals("Guest"))
									tempRoom.setGuestName(username);
								else if (role.equals("Spec"))
									tempRoom.setSpecName(username);
								
							}
							
							rooms.add(tempRoom);
	
						}
						
						
					}
				
						break;
						
					case IN_GAME: 
					{
						players = null;
						rooms = null;		
					}
						break;
				
				
				}
				
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Gets the rooms.
	 *
	 * @return the rooms
	 */
	public ArrayList<Room> getRooms() {
		return rooms;
	}


	/**
	 * Gets the players.
	 *
	 * @return the players
	 */
	public ArrayList<Player> getPlayers() {
		return players;

	
	}
}
