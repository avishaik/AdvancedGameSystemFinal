
package DataBase;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import com.mysql.jdbc.ResultSet;

import DataObjects.Room;
import DataObjects.Player;
import Messages.MessageLoginReply.ErrMsg;
import Messages.MessageLoginReply.LoginStatus;




// TODO: Auto-generated Javadoc
/**
 * The Class LoginCheck.
 */
public class LoginCheck {
	
	
	/** The user name. */
	private String userName;
	
	/** The password. */
	private String password;
	
	/** The player. */
	private Player player;
	
	/** The user ip. */
	private String userIP;


	/** The status. */
	private LoginStatus status;
	
	/** The error. */
	private ErrMsg error;

	
	
	
	/** The ret status. */
	int[] retStatus;

	/** The sc. */
	SQLConnection sc;
	
	/**
	 * Instantiates a new login check.
	 *
	 * @param user Username
	 * @param pass Password
	 * @param ip the ip
	 */
	public LoginCheck(String user,String pass,String ip)
	{
		this.userName=user;
		this.password=pass;
		this.userIP=ip;
	}

	/**
	 * This method checks if the user details are correct and give back all it's details.
	 */
	public void Check() 
	{
		try
		{
		
			String user;
			String pass;
			
		sc=SQLConnection.SQLConnectionSingleton();
		
																							//login_status = 0 - Logged out
																							//login_status = 1 - Logged in
																							
		PreparedStatement checkLogin = sc.getConnection().prepareStatement("SELECT username,password,login_status FROM users WHERE username=? and Password=?;");
		PreparedStatement updateLoginStatus = sc.getConnection().prepareStatement("UPDATE users SET login_status=1, ip=? WHERE username=?");
		
		//set userName and password in query
		checkLogin.setString(1, userName);
		checkLogin.setString(2, password);
		ResultSet rsUN=(ResultSet) checkLogin.executeQuery();
		System.out.println(rsUN);
		
		if (rsUN.next())
		{
			user=rsUN.getString(1);
			pass=rsUN.getString(2);
		}
		else
		{
			user="";
			pass="";
		}
		
					
		if((userName.equals(user))&&(password.equals(pass)) )
		{
			
			
			 if(rsUN.getInt(3)==2)
			{
				String per="NOT_RELEVANT";
				this.status=LoginStatus.LOGIN_FAIL;
				this.error=ErrMsg.FROZEN;
			}
			 
			else if((rsUN.getInt(3)==0))		//if userName and password is correct and the user is not logged in then Login the user
			{
				/**login_status update disabled for development*/ 
				updateLoginStatus.setString(2, userName);
				updateLoginStatus.setString(1,userIP );
				updateLoginStatus.executeUpdate();
				
				
				
				this.error=ErrMsg.SUCCESS;
				
				this.status=LoginStatus.LOGGED_IN;
				
			}


				
		
			else if(rsUN.getInt(3)==1)			//User is already logged in to the system
			{
				String per="NOT_RELEVANT";
				this.status=LoginStatus.LOGIN_FAIL;
				this.error=ErrMsg.ALREADY_LOGIN;

			}
		}
		
		else		//userName or password are incorrect 
		{
			String per="NOT_RELEVANT";
			this.status=LoginStatus.LOGIN_FAIL;
			this.error=ErrMsg.INCORRECT;;
		}
		
		
	
	updateLoginStatus.close();
	updateLoginStatus.close();
	rsUN.close();
	}
	 catch (SQLException ex) 
 	{/* handle any errors*/
	System.out.println("SQLException: " + ex.getMessage());
	System.out.println("SQLState: " + ex.getSQLState());
	System.out.println("VendorError: " + ex.getErrorCode());
	
}
	
	}
	

	/**
	 * Gets the login status.
	 *
	 * @return Login Status
	 */
	public LoginStatus GetLoginStatus()
	{
		return status;
	}
	
	
	/**
	 * Gets the error message.
	 *
	 * @return Error Message
	 */
	public ErrMsg GetErrorMessage()
	{
		return error;
	}
	
	/**
	 * Gets the username.
	 *
	 * @return Username
	 */
	public String getUsername()
	{
		return this.userName;
	}

	/**
	 * Gets the passwrod.
	 *
	 * @return Password
	 */
	public String getPasswrod()
	{
		return this.password;
	}


	/**
	 * Gets the games.
	 *
	 * @return the games
	 */
	public ArrayList<Room> getGames() {
		ArrayList<Room> rooms=new ArrayList<Room>();
		return rooms;
	}




	/**
	 * Gets the players.
	 *
	 * @return the players
	 */
	public ArrayList<Player> getPlayers() {
		ArrayList<Player> players=new ArrayList<Player>();
		return players;
	}


	

	
}


