package DataBase;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.mysql.jdbc.ResultSet;

import DataObjects.Room;

// TODO: Auto-generated Javadoc
/**
 * The Class StartRoom.
 */
public class StartRoom {
	

	/** The room. */
	Room room;
	
	/** The sc. */
	SQLConnection sc;
	
	/**
	 * Instantiates a new start room.
	 *
	 * @param room the room
	 */
	public StartRoom(Room room) {
		
		this.room=room;
	}
	
/**
 * Create_room.
 *
 * @return the room
 */
public Room create_room() {
		
		try {
			sc = SQLConnection.SQLConnectionSingleton();
			
			//Retrieve lobby information
			PreparedStatement startRoom = sc.getConnection().prepareStatement("INSERT INTO rooms (game_name,game_status,time_stamp,host_ip) VALUES(?,?,now(),?);");
			PreparedStatement getroomID = sc.getConnection().prepareStatement("SELECT  LAST_INSERT_ID();");
			PreparedStatement updateUsersInRoom = sc.getConnection().prepareStatement("INSERT INTO users_in_room (room_id,username,role) VALUES(?,?,?);");
			PreparedStatement updateUserStatus = sc.getConnection().prepareStatement("UPDATE users set game_status='waiting' WHERE username=?");
			
			//Updating room opener game_status to 'waiting'
			String username = room.getHostName();
			updateUserStatus.setString(1, username);
			
			updateUserStatus.executeUpdate();
			updateUserStatus.close();
			
			//updating new room is being opened
			startRoom.setString(1, room.getGameName());
			startRoom.setString(2, room.getStatus());
			startRoom.setString(3, room.getHostIP());
			
			startRoom.executeUpdate();
			
			//take the the room id, it is an incremental number
			ResultSet rsRoomID = (ResultSet) getroomID.executeQuery();	//get the last inserted room	
			
			if(rsRoomID.next())			//get the ID of the room being played
				room.setRoomID(rsRoomID.getInt(1));
			
			
			//updating user (host) in room
			updateUsersInRoom.setInt(1,room.getRoomID());
			updateUsersInRoom.setString(2, room.getHostName());
			updateUsersInRoom.setString(3, "Host");
			
			updateUsersInRoom.executeUpdate();
			
			startRoom.close();
			getroomID.close();
			updateUsersInRoom.close();

		}
 
		catch (SQLException e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return room;

	}
}
