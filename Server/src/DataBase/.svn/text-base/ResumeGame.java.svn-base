package DataBase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Messages.MessageGameProgress;
import Messages.MessageGameProgress.MessageGameAction;

// TODO: Auto-generated Javadoc
/**
 * The Class ResumeGame.
 */
public class ResumeGame {

	/** The room_id. */
	private int room_id;
	
	/** The msg gp. */
	private MessageGameProgress msgGP;
	
	/**
	 * Instantiates a new resume game.
	 *
	 * @param room_id the room_id
	 */
	public ResumeGame(int room_id) {
		
		this.room_id=room_id;
	}
	
	/**
	 * Resume game.
	 *
	 * @return the message game progress
	 */
	public MessageGameProgress resumeGame() {
try {
		msgGP = new MessageGameProgress(MessageGameAction.RESUME_GAME);
		
		SQLConnection sc = SQLConnection.SQLConnectionSingleton();
		
	
			PreparedStatement getProgress = sc.getConnection().prepareStatement("SELECT game_progress FROM rooms WHERE room_id=?");
			
			getProgress.setInt(1, room_id);
			
			ResultSet rsProgress = (ResultSet) getProgress.executeQuery();
			
			while(rsProgress.next())
			{
				String rawProgress = rsProgress.getString(1);
				
				String[] progress = rawProgress.split(" ");
				
				String sender = progress[0];
				String senderScore = progress[1];
				String receiver = progress[2];
				String receiverScore = progress[3];
				String qnumber = progress[4];
				String whosTurn = progress[5];
				
				msgGP.setSenderUsername(sender);
				msgGP.setCurrentScore(Integer.parseInt(senderScore));
				msgGP.setReceiverUsername(receiver);
				msgGP.setRivalScore(Integer.parseInt(receiverScore));
				msgGP.setQnumber(Integer.parseInt(qnumber));
				msgGP.setWhosTurn(whosTurn);
			
				//Setting the room being resumed to 'Resumed' game status
				PreparedStatement setResumed = sc.getConnection().prepareStatement("UPDATE rooms SET game_status='Resumed' WHERE room_id=?");
				setResumed.setInt(1, room_id);
			
				setResumed.executeUpdate();
	
			}
				
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return msgGP;
		
	}
	
}
