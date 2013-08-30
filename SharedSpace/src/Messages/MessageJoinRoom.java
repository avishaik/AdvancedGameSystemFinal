package Messages;

import DataObjects.Room;

public class MessageJoinRoom extends Message {

	private Room room;
	
	private static final long serialVersionUID = 1L;
	
	public MessageJoinRoom(Room room) {
		super(Message.MessageType.MESSAGE_JOIN_ROOM);
		// TODO Auto-generated constructor stub
		this.room=room;
	}
	
	public MessageJoinRoom() {
		super(Message.MessageType.MESSAGE_JOIN_ROOM);
	}
	
	public Room getRoom()
	{
		return room;
	}
	
	public void setRoom(Room room)
	{
		this.room=room;
	}
	
}
