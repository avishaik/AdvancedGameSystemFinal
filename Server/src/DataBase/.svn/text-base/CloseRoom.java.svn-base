package DataBase;

import java.sql.PreparedStatement;
import java.sql.SQLException;


/**
 * The Class CloseRoom - Responsible to mark the room as Canceled when a user refuses an invitation to play a game
 */
public class CloseRoom {
	



		/** The sc. */
		SQLConnection sc;
		
		/**
		 * Instantiates a new close room.
		 *
		 * @param room_id the room_id
		 */
		public CloseRoom(int room_id) {
			
			sc = SQLConnection.SQLConnectionSingleton();
			
			//Updating the game_status field in the room to 'Canceled'
			
			PreparedStatement chgRoomStat;
			try {
				chgRoomStat = sc.getConnection().prepareStatement("UPDATE rooms SET game_status='Canceled' WHERE room_id=?");

			
			chgRoomStat.setInt(1, room_id);
			
			chgRoomStat.executeUpdate();
			chgRoomStat.close();
			
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}

}
