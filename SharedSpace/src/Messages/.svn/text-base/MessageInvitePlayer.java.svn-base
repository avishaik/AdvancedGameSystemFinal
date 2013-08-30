package Messages;

import Messages.Message.MessageType;

public class MessageInvitePlayer extends MessageGameProgress {

	private String playerUN;
	private String inviterUN;
	private String inviterIP;
	private String gameType;
	private int roomID;
	
	public MessageInvitePlayer(String inviterUN, String playerUN, String inviterIP,String gameType, int roomID) {
		super(MessageGameAction.MESSAGE_INVITE_PLAYER);
		this.setPlayerUN(playerUN);
		this.setInviterUN(inviterUN);
		this.setInviterIP(inviterIP);
		this.setGameType(gameType);
		this.setRoomID(roomID);
	}

	public void setPlayerUN(String playerUN) {
		this.playerUN = playerUN;
	}

	public String getPlayerUN() {
		return playerUN;
	}

	public void setInviterUN(String inviterUN) {
		this.inviterUN = inviterUN;
	}

	public String getInviterUN() {
		return inviterUN;
	}

	public void setInviterIP(String inviterIP) {
		this.inviterIP = inviterIP;
	}

	public String getInviterIP() {
		return inviterIP;
	}

	public void setGameType(String gameType) {
		this.gameType = gameType;
	}

	public String getGameType() {
		return gameType;
	}

	public void setRoomID(int roomID) {
		this.roomID = roomID;
	}

	public int getRoomID() {
		return roomID;
	}

}
