package DataBase;

import java.sql.PreparedStatement;
import java.sql.SQLException;

// TODO: Auto-generated Javadoc
/**
 * The Class DropRoom - Responsible to mark the room as crashed when a user get disconnected from the system in middle of a game
 */
public class DropRoom {

	/** The sc. */
	SQLConnection sc;
	
	/**
	 * Instantiates a new drop room.
	 *
	 * @param room_id the room_id
	 */
	public DropRoom(int room_id) {
		
		sc = SQLConnection.SQLConnectionSingleton();
		
		//Updating the game_status field in the room to 'Crashed'
		
		PreparedStatement chgRoomStat;
		try {
			chgRoomStat = sc.getConnection().prepareStatement("UPDATE rooms SET game_status='Crashed' WHERE room_id=?");

		
		chgRoomStat.setInt(1, room_id);
		
		chgRoomStat.executeUpdate();
		chgRoomStat.close();
		
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
