package Messages;

import DataObjects.Role;
import DataObjects.Room;
import Messages.MessageGameProgress.MessageGameAction;
import Messages.MessageUnsolicited.UnsolicitedType;

public class MessageInvitationDeniedReply extends MessageUnsolicited {
	
	private Room room;
	
	public MessageInvitationDeniedReply(Room room) {
		super(UnsolicitedType.MESSAGE_INIVTATION_DENIED_REPLY);
		this.room = room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public Room getRoom() {
		return room;
	}

}
