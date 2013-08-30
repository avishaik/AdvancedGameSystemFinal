package Messages;

import java.io.Serializable;


/**
 * This is our Message system we use to send messages between the client to the server.
 * all messages inherits from this class. every message has a unique purpose and it gets it 
 * type from the enum to the server or the client can identify what the message meaning is.
 *
 */
public class Message implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;




	public enum MessageType {
	    MESSAGE_LOGIN,
	    MESSAGE_LOGIN_REPLY,
	    MESSAGE_LOGOUT,
	    MESSAGE_LOGOUT_REPLY, 
	    MESSAGE_REFRESH_LOBBY,
	    MESSAGE_REFRESH_LOBBY_REPLY, 
	    MESSAGE_OPEN_ROOM, 
	    MESSAGE_OPEN_ROOM_REPLY,
	    MESSAGE_JOIN_ROOM,
	    MESSAGE_JOIN_ROOM_REPLY,
	    MESSAGE_SPEC_ROOM,
	    MESSAGE_SPEC_ROOM_REPLY,   
	    MESSAGE_GET_RANK,
	    MESSAGE_GET_RANK_REPLY, 
	    MESSAGE_GAME_PROGRESS,
	    MESSAGE_UNSOLICITED, 
	    MESSAGE_CONNECTION_LOST, 
	    MESSAGE_CHECK_RESUME, 
	    MESSAGE_CHECK_RESUME_REPLY, 
	    MESSAGE_RESUME_GAME, 
	    MESSAGE_GET_RESUME_INFO, 
	    MESSAGE_INVITE_PLAYER,
	    MESSAGE_INVITATION_DENIED,
	    MESSAGE_SHOW_HISTORY,
	    MESSAGE_SHOW_HISTORY_REPLY,
	    MESSAGE_INVITE_REFRESH,
	}
	
	/**
	 * 
	 * @param type Message type
	 */
	public Message(MessageType type){
		messageType = type;	
	}

	
	/**
	 * 
	 * @return Message type
	 */
	public MessageType getMessageType(){
		return messageType;	
	}
	

	
	
	private MessageType messageType;
}

