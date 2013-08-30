package Messages;

import Messages.Message.MessageType;

public class MessageJoinRoomReply extends Message {

	private RoomStatus status;
	
	public MessageJoinRoomReply(RoomStatus status) {
		super(MessageType.MESSAGE_JOIN_ROOM_REPLY);
		this.status=status;
	}


	public RoomStatus getStatus() {
		return status;
	}

}
