package Messages;

import Messages.Message.MessageType;

public class MessageOpenRoomReply extends Message {

	private RoomStatus status;
	private int roomID;
	
	public MessageOpenRoomReply(RoomStatus status, int roomID) {
		super(MessageType.MESSAGE_OPEN_ROOM_REPLY);
		this.status=status;
		this.setRoomID(roomID);
		
	}
	
	public RoomStatus getRoomStatus() {
		
		return status;
	}

	public void setRoomID(int roomID) {
		this.roomID = roomID;
	}

	public int getRoomID() {
		return roomID;
	}

}
