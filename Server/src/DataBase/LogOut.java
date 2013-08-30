package DataBase;

import java.sql.PreparedStatement;

import java.sql.SQLException;

import Messages.MessageLogoutReply.ErrMsg;
import Messages.MessageLogoutReply.LogoutStatus;


// TODO: Auto-generated Javadoc
/**
 * The Class LogOut.
 */
public class LogOut {

	/** The sc. */
	SQLConnection sc;
	
	/** The user name. */
	private String userName;
	
	/** The status. */
	private LogoutStatus status;
	
	/** The error. */
	private ErrMsg error;
	
	/** The num. */
	private int num;
	
	/**
	 * Instantiates a new log out.
	 *
	 * @param user the user
	 */
	public LogOut(String user){
		
		this.userName = user;

	}
	
	/**
	 * Make log out.
	 *
	 * @throws SQLException the sQL exception
	 */
	public void makeLogOut() throws SQLException{
		
			
		sc = SQLConnection.SQLConnectionSingleton();
		PreparedStatement ps1;
		
		try {
			
				ps1 = sc.getConnection().prepareStatement("UPDATE users SET login_status=0, game_status='offline' WHERE username=?;");
				ps1.setString(1, userName);
				ps1.executeUpdate();
			
		} catch (SQLException e) {

			status=LogoutStatus.LOGOUT_FAIL;
			error=ErrMsg.NO_CONNECTION;
			e.printStackTrace();
		}
		
		status=LogoutStatus.LOGGED_OUT;
		
	}
	
	/**
	 * Gets the user name.
	 *
	 * @return the user name
	 */
	public String getUserName(){
		return userName;
		
	}
	
	/**
	 * Gets the logout status.
	 *
	 * @return the logout status
	 */
	public LogoutStatus getLogoutStatus() {
		return status;
	}
	
	/**
	 * Gets the error msg.
	 *
	 * @return the error msg
	 */
	public ErrMsg getErrorMsg() {
		return error;
	}
}