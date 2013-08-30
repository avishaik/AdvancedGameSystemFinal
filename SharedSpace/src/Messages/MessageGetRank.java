package Messages;

import DataObjects.MyData;


public class MessageGetRank extends Message {

	private String username;
	private MyData myData;
	
	public MessageGetRank(MyData data) {
		super(MessageType.MESSAGE_GET_RANK);
		this.myData=data;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public MyData getData()
	{
		return myData;
	}
	
	public void setData(MyData data)
	{
		this.myData=data;
	}

}
