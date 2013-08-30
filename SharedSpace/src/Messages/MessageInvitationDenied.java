package Messages;

import DataObjects.Room;
import Messages.Message.MessageType;

public class MessageInvitationDenied extends Message {
	
	private Room room;
	
	public MessageInvitationDenied(Room room) {
		super(MessageType.MESSAGE_INVITATION_DENIED);
		this.room = room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public Room getRoom() {
		return room;
	}

}
