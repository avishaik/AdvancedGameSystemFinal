package DataObjects;

import java.io.IOException;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;


public class Rooms implements Serializable {
	
	
	static Rooms RoomsInstance=null;
	private ArrayList<Room> online_rooms;
	
	protected Rooms()
	{
		online_rooms=new ArrayList<Room>();
	}
	
	public static Rooms getRoomsInstance()
	{
		if(RoomsInstance==null)
		{	
			RoomsInstance=new Rooms();
		}
		return RoomsInstance;
	}
	
	public Room getRoom(Integer room_id)
	{
		return online_rooms.get(room_id);
	}
	
	
	public void setRoom(Room room)
	{
		online_rooms.add(room);
	}
	
	public ArrayList<Room> getOnlineRooms() {
		
		return online_rooms;
	}
	
	public int getRoomIndex(int room_id)
	{
		Room r = null;
		for(Room room: online_rooms)
		{
			if(room.getRoomID()==room_id)
				r=room;
		}
		return online_rooms.indexOf(r);	
	}
	
	public Room getRoomByID(int room_id)
	{
		Room r = null;
		for(Room room: online_rooms)
		{
			if(room.getRoomID()==room_id)
				r=room;
		}
		return r;
	}
	
	public void updateRoom(int i,Room room)
	{
		online_rooms.set(i, room);
	}
	
	public void removeRoomFromList(Room room)
	{
		online_rooms.remove(room);
	}




/**
 * 
 * @param out
 * @throws IOException
 */
private void writeObject(java.io.ObjectOutputStream out)throws IOException
 {
     out.defaultWriteObject();
 }
    
/**
 * 
 * @param in
 * @throws IOException
 * @throws ClassNotFoundException
 */
private void readObject(java.io.ObjectInputStream in)throws IOException, ClassNotFoundException
 {
     in.defaultReadObject();
     
 }

}


