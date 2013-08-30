package Messages;

import DataObjects.Room;
import Messages.MessageGameProgress.MessageGameAction;
import Messages.MessageUnsolicited.UnsolicitedType;

public class MessageExitGame extends MessageGameProgress {

	private Room room;
	
	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public MessageExitGame(Room room) {
		super(MessageGameAction.EXIT_GAME);
		this.room=room;
	}


	


}
