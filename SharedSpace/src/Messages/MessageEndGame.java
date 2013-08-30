package Messages;

import DataObjects.Room;
import Messages.MessageGameProgress.MessageGameAction;

public class MessageEndGame extends MessageGameProgress {

	private Room room;
	
	public MessageEndGame(Room room) {
		super(MessageGameAction.END_GAME);
		this.room=room;
		
	}
	
	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	
}
