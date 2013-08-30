package Messages;

import Messages.Message.MessageType;

public class MessageGetResumeInfo extends Message {

	private int room_id;
	
	public MessageGetResumeInfo(int room_id) {
		super(MessageType.MESSAGE_GET_RESUME_INFO);
		this.setRoom_id(room_id);
		
	}

	public void setRoom_id(int room_id) {
		this.room_id = room_id;
	}

	public int getRoom_id() {
		return room_id;
	}

}
