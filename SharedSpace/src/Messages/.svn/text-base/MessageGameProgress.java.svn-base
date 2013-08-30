package Messages;



import DataObjects.Role;

import DataObjects.MyData;
import DataObjects.Room;
import Messages.Message.MessageType;

public class MessageGameProgress extends MessageUnsolicited {

	private int qNumber;
	private Role role;
	private int room_id;
	private String whosTurn;
	private int currentScore;
	private boolean isEndGame = false;
	private String winner;
	private int rivalScore;
	private boolean tieFlag;
	private String senderUsername;
	private String receiverUsername;
	private boolean initGame = false;
	
	private MessageGameAction messageGameAction;
	
	
	public MessageGameProgress(MessageGameAction gameType) {
		super(UnsolicitedType.UNSOLICITED_GAME_PROGRESS);
		this.messageGameAction=gameType;
		this.role = Role.INCOGNITO;
		
		
	}
	
	
	public enum MessageGameAction {
		 EXIT_GAME,
		 SPEC_EXIT_GAME,
		 END_GAME,
		 GAME_TURN,
		TURN_TRIVIA,
	   TURN_3BOOM, 
	   SPECTATE, 
	   UPDATE_SPEC,
	   TIME_OVER, 
	   RESUME_GAME, 
	   MESSAGE_INVITE_PLAYER, 
	 
	}
	
	
	public Role getRole()
	{
		return this.role;
	}
	
	public void setRole(Role role)
	{
		this.role=role;
	}

	public void setQnumber(int qnumber) {
		this.qNumber = qnumber;
	}
	public int getQnumber() {
		return qNumber;
	}
	

	public void setWhosTurn(String whosTurn) {
		this.whosTurn = whosTurn;
	}


	public String getWhosTurn() {
		return whosTurn;
	}


	public void setCurrentScore(int currentScore) {
		this.currentScore = currentScore;
	}


	public int getCurrentScore() {
		return currentScore;
	}

	public void setMessageGameAction(MessageGameAction messageGameType) {
		this.messageGameAction = messageGameType;
	}

	public MessageGameAction getMessageGameAction() {
		return messageGameAction;
	}
	
	public void setEndGame(boolean isEndGame) {
		this.isEndGame = isEndGame;
	}
	public boolean isEndGame() {
		return isEndGame;
	}

	public void setWinner(String winner) {
		this.winner = winner;
	}

	public String getWinner() {
		if(winner!=null)
			return winner;
		else
			return "";
	}

	public void setTieFlag(boolean tieFlag) {
		this.tieFlag = tieFlag;
	}

	public boolean isTieFlag() {
		return tieFlag;
	}

	public void setRivalScore(int rivalScore) {
		this.rivalScore = rivalScore;
	}

	public int getRivalScore() {
		return rivalScore;
	}

	public void setSenderUsername(String senderUsername) {
		this.senderUsername = senderUsername;
	}

	public String getSenderUsername() {
		return senderUsername;
	}

	public void setReceiverUsername(String receiverUsername) {
		this.receiverUsername = receiverUsername;
	}

	public String getReceiverUsername() {
		return receiverUsername;
	}

	public void setRoom_id(int room_id) {
		this.room_id = room_id;
	}

	public int getRoom_id() {
		return room_id;
	}

	public void setInitGame(boolean initGame) {
		this.initGame = initGame;
	}

	public boolean isInitGame() {
		return initGame;
	}
	
}
