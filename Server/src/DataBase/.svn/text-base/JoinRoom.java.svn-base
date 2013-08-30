package DataBase;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import DataObjects.Room;

// TODO: Auto-generated Javadoc
/**
 * The Class JoinRoom - Responsible to update the database with the guest name that entered the room and changing the room status to 'playing'
 */
public class JoinRoom {
	
	/** The room. */
	Room room;
	
	/** The sc. */
	SQLConnection sc;
	
	/**
	 * Instantiates a new join room.
	 *
	 * @param room the room
	 */
	public JoinRoom(Room room)
	{
		this.room=room;
	}
	
/**
 * Join_room.
 *
 * @return the room
 */
public Room join_room() {
		
		try {
			sc = SQLConnection.SQLConnectionSingleton();
			
			//Retrieve lobby information
			
			PreparedStatement updateUser = sc.getConnection().prepareStatement("UPDATE users SET game_status=? WHERE username=?");
			PreparedStatement updateRoom = sc.getConnection().prepareStatement("UPDATE rooms SET game_status='Playing' WHERE room_id=?");
			PreparedStatement joinRoom = sc.getConnection().prepareStatement("INSERT INTO users_in_room (room_id,username,role) VALUES(?,?,?);");
			
			//Setting players in room
			joinRoom.setInt(1, room.getRoomID());
			joinRoom.setString(2, room.getGuestName());
			joinRoom.setString(3, "Guest");
			joinRoom.executeUpdate();
			
			
			String specName = room.getSpecName();
			
			if(specName!=null)
			{
				joinRoom.setString(2,room.getSpecName());
				joinRoom.setString(3, "Spec");
				joinRoom.executeUpdate();
				
				updateUser.setString(1, "spectating");
				updateUser.setString(2, specName);
				updateUser.executeUpdate();
					}
			
			
			updateUser.setString(1, "playing");
			updateUser.setString(2, room.getHostName());
			updateUser.executeUpdate();
			
			updateUser.setString(2, room.getGuestName());
			updateUser.executeUpdate();
			
			updateRoom.setInt(1, room.getRoomID());
			updateRoom.executeUpdate();
			
			updateUser.close();
			updateRoom.close();
			joinRoom.close();
		}
		
		
		catch (SQLException e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return room;


	}

}
