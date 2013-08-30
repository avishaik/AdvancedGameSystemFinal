package Messages;

import DataObjects.Player;
import DataObjects.Room;
import Messages.Message.MessageType;

public class MessageSpecRoom extends Message {

	private Room room;
	private Player player;
	
	public MessageSpecRoom(Player player,Room room) {
		super(MessageType.MESSAGE_SPEC_ROOM);
		this.player=player;
		this.room=room;
	}

	public Player getPlayer()
	{
		return player;
	}
	
	public Room getRoom(){
		return room;
	}

}
