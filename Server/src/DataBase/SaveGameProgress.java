package DataBase;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import Messages.MessageGameProgress;


// TODO: Auto-generated Javadoc
/**
 * The Class SaveGameProgress.
 */
public class SaveGameProgress {
	
	/** The sc. */
	private SQLConnection sc;
	
	/** The save game progress. */
	private PreparedStatement saveGameProgress;
	
	
	/**
	 * Instantiates a new save game progress.
	 *
	 * @param msgGP the msg gp
	 */
	public SaveGameProgress(MessageGameProgress msgGP) {
		

		
		sc = SQLConnection.SQLConnectionSingleton();
		
		try {
				String senderUN = msgGP.getSenderUsername();
				int senderScore = msgGP.getCurrentScore();
				String receiverUN = msgGP.getReceiverUsername();
				int receiverScore = msgGP.getRivalScore();
				int qnumber = msgGP.getQnumber()+1;
				String whosTurn = msgGP.getWhosTurn();
			
			
				String backup = senderUN+" "+senderScore+" "+receiverUN+" "+receiverScore+" "+qnumber+" "+whosTurn;
			
				saveGameProgress = sc.getConnection().prepareStatement("UPDATE rooms SET game_progress='"+backup+"' WHERE room_id=?");
	
				int room_id = msgGP.getRoom_id();
				
				saveGameProgress.setInt(1, room_id);
	
				saveGameProgress.executeUpdate();
				
				saveGameProgress.close();
			
	
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

}
	
}
