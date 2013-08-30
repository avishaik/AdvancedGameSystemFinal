package DataBase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// TODO: Auto-generated Javadoc
/**
 * The Class GetPlayerIP.
 */
public class GetPlayerIP {

	/** The sc. */
	private SQLConnection sc;
	
	/** The player ip. */
	private String playerIP;
	
	/**
	 * Instantiates a new gets the player ip.
	 */
	public GetPlayerIP() {
		
		sc = SQLConnection.SQLConnectionSingleton();
		
	}
	
	/**
	 * Gets the player ip.
	 *
	 * @param playerUN the player un
	 * @return the player ip
	 */
	public String getPlayerIP(String playerUN)
	{
		PreparedStatement getPlayerIP;
		try {
			
			getPlayerIP = sc.getConnection().prepareStatement("SELECT ip FROM users WHERE username=?");
	
			getPlayerIP.setString(1, playerUN);
			
			ResultSet rsPlayerIP = (ResultSet) getPlayerIP.executeQuery();
			
			
			if(rsPlayerIP.next())
				playerIP=rsPlayerIP.getString(1);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return playerIP;
	}
	
}
