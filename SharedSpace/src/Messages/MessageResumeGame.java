package Messages;

import Messages.Message.MessageType;

public class MessageResumeGame extends Message {

	private MessageGameProgress gameProgress;
	
	public MessageResumeGame(MessageGameProgress msgGP) {
		super(MessageType.MESSAGE_RESUME_GAME);
		this.setGameProgress(msgGP);
		
	}

	public void setGameProgress(MessageGameProgress gameProgress) {
		this.gameProgress = gameProgress;
	}

	public MessageGameProgress getGameProgress() {
		return gameProgress;
	}

}
