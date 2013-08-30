package Messages;

import Messages.MessageGameProgress.MessageGameAction;

public class MessageUnsolicited extends Message {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	UnsolicitedType unsolicited;
	
	public MessageUnsolicited(UnsolicitedType unsolicited)
	{
		super(MessageType.MESSAGE_UNSOLICITED);
		this.unsolicited=unsolicited;
	}
	
	public UnsolicitedType getUnsolicitedMessageType()
	{
		return unsolicited;
	}
	
	public enum UnsolicitedType {
			  UNSOLICITED_REFRESH_LOBBY,
			  UNSOLICITED_GAME_PROGRESS, 
			  UNSOLICITED_EXIT_GAME,
			  MESSAGE_CONNECTION_LOST, 
			  MESSAGE_INVITE_PLAYER,
			  MESSAGE_INIVTATION_DENIED_REPLY,
		}
	


	

}
