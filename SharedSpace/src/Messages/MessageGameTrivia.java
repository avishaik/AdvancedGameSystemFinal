package Messages;

import java.util.ArrayList;

import DataObjects.Player;

public class MessageGameTrivia extends MessageGameProgress {


	
	private ArrayList<Player> players;
	private String answer;
	private boolean isCorrect;

	
	
	public MessageGameTrivia()
	{
		super(MessageGameAction.TURN_TRIVIA);
	}
	

	public void setPlayer(Player player) {
		this.players.add(player);
	}
	public ArrayList<Player> getPlayers() {
		return players;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getAnswer() {
		return answer;
	}
	public void setIsCorrect(boolean isCorrect) {
		this.isCorrect = isCorrect;
	}
	public boolean getIsCorrect() {
		return isCorrect;
	}


	
}
