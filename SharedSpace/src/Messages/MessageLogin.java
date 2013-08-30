package Messages;

/**
 * This message purpose is to give the user's data to the server to check if he can login
 * 
 *
 */
public class MessageLogin extends Message {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
/**
 * 
 * @param user
 * @param pass
 */
	public MessageLogin(String user , String pass) {
		super(Message.MessageType.MESSAGE_LOGIN);
		username = user;
		password = pass;

		
	}
	
	/**
	 * 
	 * @return Username
	 */
	public String getUsername(){
		return username;
	}
	
	/**
	 * 
	 * @return Password
	 */
	public String getPassword(){
		return password;
	}
	
	
	
	private String username;
	private String password;

	

}
