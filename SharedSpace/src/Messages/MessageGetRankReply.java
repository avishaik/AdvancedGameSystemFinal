package Messages;

import Messages.Message.MessageType;

public class MessageGetRankReply extends Message {

	private int rank=0;
	


	public MessageGetRankReply(int rank) {
		super(MessageType.MESSAGE_GET_RANK_REPLY);
		this.rank = rank;
	}
	
	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

}
