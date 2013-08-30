package DataBase;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import DataObjects.Player;
import DataObjects.Room;

// TODO: Auto-generated Javadoc
/**
 * The Class MarkOffline.
 */
public class MarkOffline {
	
	/** The players list. */
	private ArrayList<Player> playersList;
	
	/** The rooms list. */
	private ArrayList<Room> roomsList;
	
	/** The sc. */
	private SQLConnection sc;
	
	/**
	 * Instantiates a new mark offline.
	 *
	 * @param markPlayersList the mark players list
	 */
	public MarkOffline(ArrayList<Player> markPlayersList) 
	{
		sc = SQLConnection.SQLConnectionSingleton();
		this.playersList = markPlayersList;
	}
	
	/**
	 * Sets the room list.
	 *
	 * @param markRoomsList the new room list
	 */
	public void setRoomList(ArrayList<Room> markRoomsList)
	{
		this.roomsList = markRoomsList;
	}

	
	/**
	 * Mark_offline_players.
	 */
	public void mark_offline_players()
	{
		try {
			
			
			//Retrieve lobby information
			
			
			
			
			if(playersList!=null)
			{
				PreparedStatement updateUser = sc.getConnection().prepareStatement("UPDATE users SET game_status='offline',login_status=0 WHERE username=?");
				for(Player player: playersList)
				{
					
					updateUser.setString(1, player.getUsername());
					updateUser.executeUpdate();
				}
				updateUser.close();
			}
	
			
			
			
		}
		
		
		catch (SQLException e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	/**
	 * Mark_offline_rooms.
	 */
	public void mark_offline_rooms()
	{
		try {
			PreparedStatement updateRoom = sc.getConnection().prepareStatement("UPDATE rooms SET game_status=? WHERE room_id=?");
			
			
			for(Room room: roomsList)
			{
				if(room.getStatus().equals("waiting"))
				{
					updateRoom.setString(1, "closed");
				}
				if(room.getStatus().equals("playing"))
				{
					updateRoom.setString(1, "crashed");
				}
				
				updateRoom.setInt(2, room.getRoomID());
				updateRoom.close();

			}

			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
