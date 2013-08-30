package DataBase;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import DataObjects.Player;

// TODO: Auto-generated Javadoc
/**
 * The Class UpdateSpectator.
 */
public class UpdateSpectator {
	
	/** The sc. */
	SQLConnection sc;
	
	/** The player. */
	Player player;
	
	/**
	 * Instantiates a new update spectator.
	 *
	 * @param player the player
	 */
	public UpdateSpectator(Player player)
	{
		this.player=player;
	}
	
	/**
	 * Update_user.
	 */
	public void update_user()
	{
		try {
			sc = SQLConnection.SQLConnectionSingleton();
			
			//Retrieve lobby information
			
			PreparedStatement updateUser = sc.getConnection().prepareStatement("UPDATE users SET game_status='viewing' WHERE username=?");
			
			updateUser.setString(1, player.getUsername());
			updateUser.executeUpdate();
			
			updateUser.close();
		}
		
		catch (SQLException e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

}
