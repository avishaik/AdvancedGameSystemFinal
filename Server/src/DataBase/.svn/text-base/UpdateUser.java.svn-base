package DataBase;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import DataObjects.Player;

public class UpdateUser {
	
	/** The sc. */
	SQLConnection sc;
	
	String username;
	String status;
	
	/**
	 * Instantiates a new update spectator.
	 *
	 * @param player the player
	 */
	public UpdateUser(String username,String status)
	{
		this.username = username;
		this.status = status;
	}
	
	
	
	/**
	 * Update_user.
	 */
	public void update_user()
	{
		try {
			sc = SQLConnection.SQLConnectionSingleton();
			
			//Retrieve lobby information
			
			PreparedStatement updateUser = sc.getConnection().prepareStatement("UPDATE users SET game_status=? WHERE username=?");
			
			updateUser.setString(1, status);
			updateUser.setString(2, username);
			updateUser.executeUpdate();
			
			updateUser.close();
		}
		
		catch (SQLException e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

}
