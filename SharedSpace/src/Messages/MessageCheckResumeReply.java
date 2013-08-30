package Messages;

import Messages.Message.MessageType;

public class MessageCheckResumeReply extends Message {

	private boolean isResumable;
	private int resRoomID;
	
	public MessageCheckResumeReply(boolean isResumable) {
		super(MessageType.MESSAGE_CHECK_RESUME_REPLY);
		this.isResumable=isResumable;
	}

	public MessageCheckResumeReply(boolean isResumable, int resRoomID) {
		super(MessageType.MESSAGE_CHECK_RESUME_REPLY);
		this.isResumable=isResumable;
		this.resRoomID=resRoomID;
		
	}

	public void setResumable(boolean isResumable) {
		this.isResumable = isResumable;
	}

	public boolean isResumable() {
		return isResumable;
	}

	public void setResRoomID(int resRoomID) {
		this.resRoomID = resRoomID;
	}

	public int getResRoomID() {
		return resRoomID;
	}

}
