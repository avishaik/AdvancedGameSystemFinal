package Messages;

import Messages.MessageGameProgress.MessageGameAction;

public class MessageSpecExitGame extends MessageGameProgress {

	private String specUsername;
	
	public MessageSpecExitGame(String specUsername) {
		super(MessageGameAction.SPEC_EXIT_GAME);
		this.specUsername=specUsername;
	}

	public void setSpecUsername(String specUsername) {
		this.specUsername = specUsername;
	}

	public String getSpecUsername() {
		return specUsername;
	}

}
