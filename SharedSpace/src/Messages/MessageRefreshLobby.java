package Messages;

import java.util.ArrayList;

import DataObjects.Player;
import DataObjects.Players;
import DataObjects.Room;
import DataObjects.Rooms;
import Messages.Message.MessageType;

public class MessageRefreshLobby extends MessageUnsolicited {

	private ArrayList<Player> players;
	private ArrayList<Room> rooms;
	
	
	public MessageRefreshLobby() {
		super(UnsolicitedType.UNSOLICITED_REFRESH_LOBBY);
		
	}


	public ArrayList<Player> getPlayers() {
		return players;
	}


	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}


	public ArrayList<Room> getRooms() {
		return rooms;
	}


	public void setRooms(ArrayList<Room> rooms) {
		this.rooms = rooms;
	}

}
