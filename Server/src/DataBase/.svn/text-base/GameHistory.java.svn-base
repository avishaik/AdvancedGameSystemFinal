package DataBase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DataObjects.Room;

public class GameHistory {
	
	private String username;
	private ArrayList<Room> roomsList;
	
	public GameHistory(String username)
	{
		this.username = username;
		roomsList = new ArrayList<Room>();
	}
	
	public ArrayList<Room> getGamesHistory()
	{
		SQLConnection sc = SQLConnection.SQLConnectionSingleton();
		
		try {
			PreparedStatement getRooms = sc.getConnection().prepareStatement("SELECT room_id FROM users_in_room WHERE username=?");
			PreparedStatement getRoomsDetails = sc.getConnection().prepareStatement("SELECT game_name,time_stamp,username FROM gamestation.rooms,gamestation.users_in_room WHERE  rooms.room_id=? AND users_in_room.room_id=? AND users_in_room.username<>?");
			
			getRooms.setString(1, username);
			
			ResultSet rsRooms = (ResultSet) getRooms.executeQuery();
			
			ResultSet rsRoomsDetails;
			ResultSet rsOpponent;
			
			
			while(rsRooms.next())
			{
				//get the room details: game name, date
				getRoomsDetails.setInt(1, rsRooms.getInt(1));
				getRoomsDetails.setInt(2, rsRooms.getInt(1));
				getRoomsDetails.setString(3, username);
				rsRoomsDetails = getRoomsDetails.executeQuery();
				
				
				if(rsRoomsDetails.next())
				{
					if(!rsRoomsDetails.getString(1).isEmpty())
					{
						Room room = new  Room();
						room.setGameName(rsRoomsDetails.getString(1));
						room.setTimeStamp(rsRoomsDetails.getString(2));
						room.setGuestName(rsRoomsDetails.getString(3));
						roomsList.add(room);
					}
				}
				
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return roomsList;
	}

	public void setRoomsList(ArrayList<Room> roomsList) {
		this.roomsList = roomsList;
	}

	public ArrayList<Room> getRoomsList() {
		return roomsList;
	}

}
