package Messages;

import java.util.ArrayList;

import DataObjects.MyData;
import DataObjects.Room;
import Messages.Message.MessageType;

public class MessageShowHistoryReply extends Message {
	
	private ArrayList<Room> roomsHistory;
	
	public MessageShowHistoryReply() {
		super(MessageType.MESSAGE_SHOW_HISTORY_REPLY);
	}

	public void setRoomsHistory(ArrayList<Room> roomsHistory) {
		this.roomsHistory = roomsHistory;
	}

	public ArrayList<Room> getRoomsHistory() {
		return roomsHistory;
	}

}
