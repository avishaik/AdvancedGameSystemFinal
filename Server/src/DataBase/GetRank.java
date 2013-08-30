package DataBase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// TODO: Auto-generated Javadoc
/**
 * The Class GetRank.
 */
public class GetRank {
	
	/** The rank. */
	private int rank=0;
	
	/** The username. */
	private String username;

	/**
	 * Instantiates a new gets the rank.
	 */
	public GetRank() {}
	
	/**
	 * Gets the rank.
	 *
	 * @param username the username
	 * @return the rank
	 */
	public int getRank(String username) {
		
		this.username=username;
		
		SQLConnection sc = SQLConnection.SQLConnectionSingleton();
		
		try {
			PreparedStatement getRank = sc.getConnection().prepareStatement("SELECT rank FROM users WHERE username=?");
			
			getRank.setString(1, username);
			
			ResultSet rsRank = (ResultSet) getRank.executeQuery();
			
			if(rsRank.next())
				rank=rsRank.getInt(1);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return rank;
		
	
	}
	
}
