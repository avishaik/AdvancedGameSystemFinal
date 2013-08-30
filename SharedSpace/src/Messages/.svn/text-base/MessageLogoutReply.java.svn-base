package Messages;

public class MessageLogoutReply extends Message {
	
	public enum LogoutStatus {
	    LOGGED_OUT,
	    LOGOUT_FAIL
	}
	
	public enum ErrMsg	{
		
		NO_CONNECTION
	}
	
	private LogoutStatus status;
	private ErrMsg error;
	
	public String[] ErrMsg={"Logout failed! Check connection to server"};
	
	public MessageLogoutReply(LogoutStatus stat, ErrMsg err){
		
		super(Message.MessageType.MESSAGE_LOGOUT_REPLY);
		this.status=stat;
		this.error=err;
	}
	
	public LogoutStatus getLogoutStatus()
	{
		return status;
	}
	
	public ErrMsg getError()
	{
		return error;
	}
	
	public String GetErrorMessage(int n)
	{
		switch (error){
		case NO_CONNECTION :
		{
			return ErrMsg[n];
		}
	
		default:
			return ErrMsg[n];
		}
	}

}
