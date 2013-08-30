package Messages;

import Messages.Message.MessageType;

public class MessageCheckResume extends Message {

	private String gameName;
	private String player1;
	private String player2;
	
	public MessageCheckResume(String gameName, String player1, String player2) {
		super(MessageType.MESSAGE_CHECK_RESUME);
		
		this.setGameName(gameName);
		this.setPlayer1(player1);
		this.setPlayer2(player2);
		
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public String getGameName() {
		return gameName;
	}

	public void setPlayer1(String player1) {
		this.player1 = player1;
	}

	public String getPlayer1() {
		return player1;
	}

	public void setPlayer2(String player2) {
		this.player2 = player2;
	}

	public String getPlayer2() {
		return player2;
	}

}
