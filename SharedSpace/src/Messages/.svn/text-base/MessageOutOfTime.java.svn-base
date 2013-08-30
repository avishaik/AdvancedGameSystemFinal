package Messages;

import Messages.MessageGameProgress.MessageGameAction;

public class MessageOutOfTime extends MessageGameProgress {

	private String loserUsername;
	
	public MessageOutOfTime(String loserUsername) {
		super(MessageGameAction.TIME_OVER);
		this.setLoserUsername(loserUsername);
		
	}

	public void setLoserUsername(String loserUsername) {
		this.loserUsername = loserUsername;
	}

	public String getLoserUsername() {
		return loserUsername;
	}

}
