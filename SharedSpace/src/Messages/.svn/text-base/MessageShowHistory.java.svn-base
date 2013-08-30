package Messages;

import DataObjects.MyData;
import Messages.Message.MessageType;

public class MessageShowHistory extends Message {
	
	private MyData myData;
	
	public MessageShowHistory(MyData data) {
		super(MessageType.MESSAGE_SHOW_HISTORY);
		this.setMyData(data);
	}

	public void setMyData(MyData myData) {
		this.myData = myData;
	}

	public MyData getMyData() {
		return myData;
	}

}
