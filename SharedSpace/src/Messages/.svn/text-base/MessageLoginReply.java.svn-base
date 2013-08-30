package Messages;

import java.util.ArrayList;

import DataObjects.Room;
import DataObjects.Player;


public class MessageLoginReply extends Message {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public enum LoginStatus {
	    LOGGED_IN,
	    LOGIN_FAIL
	}

	
	public enum ErrMsg{
		SUCCESS,
		INCORRECT,
		ALREADY_LOGIN,
		UNKNOWN,
		FROZEN, DELETED,
	}
	
	private LoginStatus status;
	private ErrMsg error;
	private String username;
	private String password;
	private String name;
	private String userIP;

	
	
	public String[] ErrMsg={"Success","Username Or Password are incorrect","You are already logged in to the system","Your account has been deleted by Admin","Your account is frozen"};
	

	/**
	 * 
	 * @param stat
	 * @param Err 	Error Message (only if the user didn't log on)
	 * @param user
	 * @param pass
	 * @param players 
	 * @param games 
	 */
	public MessageLoginReply(LoginStatus stat ,ErrMsg Err,String user,String pass,String ip) {
		super(Message.MessageType.MESSAGE_LOGIN_REPLY);
		status = stat;
		error=Err;
		username=user;
		password=pass;
		this.userIP=ip;

	}
	
	
	
	/**
	 * 
	 * @return status
	 */
	public LoginStatus getLoginStatus (){
		return status;
	}
	
	
	/**
	 * 
	 * @param n index to get an Error message
	 * @return Error Message (String)
	 */
	public String GetErrorMessage(int n)
	{
		switch (error){
		case SUCCESS :
		{
			return ErrMsg[n];
		}	
		
		case INCORRECT:
		{
			return ErrMsg[n];
		}
		
		case ALREADY_LOGIN:
		{
			return ErrMsg[n];
		}
		case FROZEN:
		{
			return ErrMsg[n];
		}
		default:
			return ErrMsg[n];
		}
	}
	
	/**
	 * 
	 * @param un Username
	 */
	public void setUser(String un)
	{
		this.username=un;
	}
	
	/**
	 * 
	 * @param pass 
	 */
	public void setPassword(String pass)
	{
		this.password=pass;
	}
	
	/**
	 * 
	 * @return Username
	 */
	public String getUser()
	{
		return this.username;
	}
	
	/**
	 * 
	 * @return password
	 */
	public String getPassword()
	{
		return this.password;
	}
	
	/**
	 * 
	 * @return User name
	 */
	public String getName()
	{
		return this.name;
	}
	
	/**
	 * 
	 * @return Error message
	 */
	public ErrMsg getError()
	{
		return error;
	}



	public void setUserIP(String userIP) {
		this.userIP = userIP;
	}



	public String getUserIP() {
		return userIP;
	}
}