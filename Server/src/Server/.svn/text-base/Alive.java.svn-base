package Server;

import java.io.IOException;
import java.util.ArrayList;

import ocsf.server.ConnectionToClient;

import DataBase.DropRoom;
import DataBase.UpdateUser;
import DataObjects.Player;
import DataObjects.Players;
import DataObjects.Role;
import DataObjects.Room;
import DataObjects.Rooms;
import Messages.MessageConnectionLost;

// TODO: Auto-generated Javadoc
/**
 * The Class Alive. - This is actually a 'Watch Dog' class. It detects users that disconnected from the server
 * and mark them as offline, if the user that got disconnected was in a room, the class also closes the room and informs 
 * the other user who played with the disconnected player and all the spectators.
 */
public class Alive {
	
	/** The server. */
	private EchoServer server;
	
	/** The active players. */
	private Players activePlayers;
	
	/** The active rooms. */
	private Rooms activeRooms;
	
	/** The old players list. */
	private Players oldPlayersList;
	
	/** The mark offline list. */
	private ArrayList<Player> markOfflineList;
	
	/** The room to close. */
	private ArrayList<Room> roomToClose;
	
	/** The thr arr. */
	private Thread[] thrArr=null;
	
	/** The client. */
	private ConnectionToClient client;
	
	
	
	/**
	 * Instantiates a new alive.
	 *
	 * @param server the server
	 */
	public Alive(EchoServer server)
	{
		this.server = server;
		thrArr = server.getClientConnections();
		activePlayers = Players.getPlayersInstance();
		activeRooms = Rooms.getRoomsInstance();
		markOfflineList = new ArrayList<Player>();
		roomToClose = new ArrayList<Room>();
	}
	
	/**
	 * Dead players.
	 * Checks for players who got disconnected, put them in an ArrayList and send them
	 * to MarkOffline class to mark them as offline in the DB.
	 *
	 * @return the array list
	 */
	public ArrayList<Player> deadPlayers()
	{
		
		ArrayList<Player> players = activePlayers.getOnlinePlayers();
		
		boolean connected=false;
		if(isEmpty()&& players.isEmpty())		//if there is no users connected, do nothing
			System.out.println("no clients are connected at the moment");
		else
		{
			for(Player player: players)		//check the players, see if anyone lost connection
			{
				for(int i=0;i<thrArr.length;i++)
				{
					//Looking for player in thread list
					if((thrArr[i].getId()==player.getThreadNum()))
					{
						//player found in thread list
						connected=true;
						
						//checking for possibility it is a "ghost" player
						ConnectionToClient ctc = (ConnectionToClient) thrArr[i];
						
						String rawInetAddress = ctc.getInetAddress().toString();
						
						String[] convertInetAddress = rawInetAddress.split("/");
						
						String InetAddress = convertInetAddress[1];
						
						//Player IP incorrect - connection dropped
						if(!InetAddress.equals(player.getUserIP()))
						{
							markOfflineList.add(player);
						}
					}
				}
				//Player not found  - connection dropped
				if(!connected) {
					
					markOfflineList.add(player);
					
				}
				
				connected=false;
					

			}
			
			//Loop is responsible for cleaning dropped player from players list
			for(Player player: markOfflineList)
			{
				players.remove(player);
			}
			
			
			
				//Player p = activePlayers.getPlayerByIP(thrArr[i].toString());

		}
		
		if(markOfflineList.isEmpty())
			return null;
		else
			return markOfflineList;
			
	}
	
	/**
	 * Close rooms.
	 * If the user who got disconnected was in a room, then close the room and inform the other
	 * users that was in the room (rival and spectators).
	 */
	public void closeRooms()
	{
		ArrayList<Room> rooms = activeRooms.getOnlineRooms();
		ArrayList<Room> roomsToDrop = new ArrayList<Room>();
		
		boolean isRoomDropped=false;
		for(Player player: markOfflineList)
		{
			for(Room room: rooms)
			{
				if(player.getUsername().equals(room.getHostName()))	//if host disconnected
				{	
					isRoomDropped=true;
					
					roomsToDrop.add(room);
					
					//Dropping room in DB
					//DropRoom dropRoom = new DropRoom(room.getRoomID());
					
					if(room.getStatus().equals("Playing"))	//if the room has 2 players that are playing
					{
						sendForceCloseRoomToClient(room.getGuestName());	//inform guest to leave room
					}

				}
				else if(player.getUsername().equals(room.getGuestName())) //if guest disconnected
				{
					isRoomDropped=true;
					
					roomsToDrop.add(room);
					
					sendForceCloseRoomToClient(room.getHostName());	//inform host to leave room
			
				}
			}
		}
		
		if(isRoomDropped)
		{
			for(Room roomToDrop: roomsToDrop)
			{
				//Dropping room in DB
				DropRoom droproom = new DropRoom(roomToDrop.getRoomID());
				rooms.remove(roomToDrop);
				
				//informing all spectators to leave room
				ArrayList<Player> specs = roomToDrop.getSpecList();
				if(specs!=null) {
					for(Player spec: specs)
					{
						sendForceCloseRoomToClient(spec.getUsername());
					}
				}
			}
			
		}
		
		
	}
	
	/**
	 * Checks if is empty.
	 * 
	 *
	 * @return true, if is empty
	 */
	private boolean isEmpty()
	{
		if(server.getNumberOfClients()==0)
			return true;
		else
			return false;
	}
	
	/**
	 * Send force close room to client.
	 *
	 * @param username the username
	 */
	private void sendForceCloseRoomToClient(String username)
	{
		Player p = activePlayers.getPlayerByUN(username);
		
		//update the user's status to 'in_lobby'
		UpdateUser update = new UpdateUser(username,"in_lobby");
		update.update_user();
		
		if(p!=null)
		{
			
			MessageConnectionLost msgCL = new MessageConnectionLost();
			try {
				//inform the client that his buddy lost the connection
				
				for(int i=0;i<thrArr.length;i++)
				{
					int id = (int) thrArr[i].getId();
					
					
					if(id==p.getThreadNum()) {
						
						ConnectionToClient ctc = (ConnectionToClient)thrArr[i];
						ctc.sendToClient(msgCL);
						
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}

	}

}
