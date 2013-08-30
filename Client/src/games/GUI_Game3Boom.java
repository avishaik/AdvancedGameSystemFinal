package games;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.ArrayList;


import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

import DataObjects.Role;
import DataObjects.Room;
import GUI.GUI_ImagePanel;
import GUI.GUI_UserBox;
import Messages.EnvStatus;
import Messages.Message3Boom;
import Messages.MessageGameProgress;
import Messages.MessageGameTrivia;
import Messages.MessageOutOfTime;
import Messages.MessageGameProgress.MessageGameAction;

import javax.swing.JTabbedPane;

import ocsf.server.ConnectionToClient;


/**
 * The Class GUI_Game3Boom.
 */
public class GUI_Game3Boom extends GUI_GamePlatform {
	
	
	/** The numbers. */
	private ArrayList<String> numbers;  //  @jve:decl-index=0:
	
	/** The label title. */
	private JLabel labelTitle = null;  //  @jve:decl-index=0:visual-constraint="10,1081"
	
	/** The label number. */
	private JLabel labelNumber = null;
	
	/** The qnumber. */
	private int qnumber = 0;
	
	/** The button boom. */
	private JButton buttonBoom = null;
	
	/** The button next. */
	private JButton buttonNext = null;
	
	/** The my user. */
	private GUI_UserBox myUser;
	
	/** The rival user. */
	private GUI_UserBox rivalUser;
	
	/** The is boom. */
	private boolean isBoom=false;
	
	/** The is next. */
	private boolean isNext=false;
	
	/** The number of questions. */
	private final int numberOfQuestions=3;
	
	
	/**
	 * Instantiates a new gU i_ game3 boom.
	 *
	 * @param role the role
	 */
	public GUI_Game3Boom(Role role)
	{

		if(role.equals(Role.HOST))
			waitForGuest();

	}
	
	/* (non-Javadoc)
	 * @see games.GUI_GamePlatform#startGameGuest(java.lang.String)
	 */
	public void startGameGuest(String rivalUsername) {
		
		myUsername = new String();
		this.rivalUsername = new String();
		
		myUsername = ms.myData.getPlayer().getUsername();
		this.rivalUsername = rivalUsername; 
		
		//Updating it is host's turn
		labelUserTurn.setText("User's Turn: "+rivalUsername);
		
		myUser = new GUI_UserBox();
		myUser.setUsernameLabel("YOU: "+myUsername);
		myUser.setStatusLabel("Waiting");
		Integer zero = new Integer(0);
		myUser.setScoreLabel(zero);
		myUser.setBounds(new Rectangle(740,300,100,56));
		
		rivalUser = new GUI_UserBox();
		rivalUser.setUsernameLabel("Rival: "+rivalUsername);
		rivalUser.setStatusLabel("Playing");
		rivalUser.setScoreLabel(zero);
		rivalUser.setBounds(new Rectangle(740,400,100,56));
		
		init3BoomData();
		initialize();
		
		
		buttonBoom.setEnabled(false);
		buttonNext.setEnabled(false);
	
	}
	
	/* (non-Javadoc)
	 * @see games.GUI_GamePlatform#startGameHost(java.lang.String)
	 */
	public void startGameHost(String rivalUsername) {
	
		myUsername = new String();
		this.rivalUsername = new String();
		
		//Updating it's now current user's turn
		myUsername = ms.myData.getPlayer().getUsername();
		this.rivalUsername = rivalUsername; 	
		
		ms.roomPlat.getRoom().setGuestName(rivalUsername);
		
	
		labelUserTurn.setText("User's Turn: "+myUsername);
		
		myUser = new GUI_UserBox();
		myUser.setUsernameLabel("YOU: "+myUsername);
		myUser.setStatusLabel("Playing");
		Integer zero = new Integer(0);
		myUser.setScoreLabel(zero);
		myUser.setBounds(new Rectangle(740,300,100,56));
		
		rivalUser = new GUI_UserBox();
		rivalUser.setUsernameLabel("Rival: "+rivalUsername);
		rivalUser.setStatusLabel("Waiting");
		rivalUser.setScoreLabel(zero);
		rivalUser.setBounds(new Rectangle(740,400,100,56));
		
		this.remove(labelWaitForGuest);
		
		gameTimerStart();
		
		init3BoomData();
		initialize();
		
	}
	
	/* (non-Javadoc)
	 * @see games.GUI_GamePlatform#startGameSpec(java.lang.String, java.lang.String)
	 */
	@Override
	public void startGameSpec(String hostUsername, String guestUsername) {
		
		  this.specUsername = ms.myData.getPlayer().getUsername();
		  
		  this.hostUsername=hostUsername;
		  this.guestUsername=guestUsername;
		  
		  labelUserTurn.setVisible(false);
		  
		  myUser = new GUI_UserBox();
		  myUser.setUsernameLabel("HOST: "+hostUsername);
		  myUser.setStatusLabel("");
		  Integer zero = new Integer(0);
		  myUser.setScoreLabel(zero);
		  myUser.setBounds(new Rectangle(740,300,100,56));
		  
		  rivalUser = new GUI_UserBox();
		  rivalUser.setUsernameLabel("GUEST: "+guestUsername);
		  rivalUser.setStatusLabel("");
		  rivalUser.setScoreLabel(zero);
		  rivalUser.setBounds(new Rectangle(740,400,100,56));
		  
		  Message3Boom msg3B = new Message3Boom();
		  msg3B.setRole(Role.SPECTATOR);

		  gameCommunication(msg3B);
	 
		  init3BoomData();
		  initialize();
		  
		  buttonBoom.setEnabled(false);
		  buttonNext.setEnabled(false);
		
	}

	
	/**
	 * Init3 boom data.
	 */
	public void init3BoomData() {
		
		
		numbers = new ArrayList<String>();
		
		for(Integer i=0;i<numberOfQuestions;i++)
		{		
			numbers.add(i,i.toString());	
		}
		
	}
		



	/**
	 * This method initializes this.
	 */
	private void initialize() {
		
		labelNumber = new JLabel();
		labelNumber.setBounds(new Rectangle(250, 300, 905, 59));
		labelNumber.setForeground(Color.WHITE);
		labelNumber.setText("Current #:"+"("+(Integer.parseInt(numbers.get(0))+1)+")");
		labelNumber.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 30));
		
		labelTitle = new JLabel();
		labelTitle.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 50));
		labelTitle.setText("3-Boom");
		labelTitle.setBounds(new Rectangle(328, 44, 315, 71));
		labelTitle.setForeground(Color.WHITE);
		
	
		this.setLayout(null);
		this.setSize(new Dimension(1000, 800));
		
		GUI_ImagePanel gamePanelBG = new GUI_ImagePanel(new ImageIcon("images/gamePanel-background.jpg").getImage());
		gamePanelBG.setBounds(new Rectangle(0, 0, 900, 650));
		
		this.remove(background);

		this.gamePanel = new JPanel();
		gamePanel.setLayout(new GridBagLayout());
		gamePanel.setBounds(new Rectangle(40, 16, 900, 650));
		
		this.gamePanel.add(myUser);
		this.gamePanel.add(rivalUser);
		this.gamePanel.add(getButtonBoom(), null);
		this.gamePanel.add(getButtonNext() , null);
		this.gamePanel.add(labelTitle, null);
		this.gamePanel.add(labelNumber, null);

		this.gamePanel.setLayout(null);
		this.gamePanel.add(gamePanelBG);
		this.gamePanel.setVisible(true);
		this.add(gamePanel);
		this.add(background);
		
	}
	
	/* (non-Javadoc)
	 * @see games.GUI_GamePlatform#rivalTurn()
	 */
	protected void rivalTurn() {
		
		gameTimerStop();			//Stopping the timer counting backwards for lack of reaction
		
		qnumber++;					//Moving forward to next (rival's) question
		
		labelNumber.setText("Current #:"+"("+(Integer.parseInt(numbers.get(qnumber))+1)+")");
		
		labelUserTurn.setText("User's Turn: "+rivalUsername);	//changing to rival's turn
		
		myUser.setStatusLabel("Waiting");
		rivalUser.setStatusLabel("Playing");
		
		buttonBoom.setEnabled(false);
		buttonNext.setEnabled(false);
	
		
	}
	
	/* (non-Javadoc)
	 * @see games.GUI_GamePlatform#myTurn()
	 */
	protected void myTurn()
	{

		myUser.setStatusLabel("Playing");
		rivalUser.setStatusLabel("Waiting");
		
		qnumber++;					//Moving forward to my question
		
		labelNumber.setText("Current #:"+"("+(Integer.parseInt(numbers.get(qnumber))+1)+")");
		
		labelUserTurn.setText("User's Turn: "+myUsername);	//changing to rival's turn
			
		buttonBoom.setEnabled(true);
		buttonNext.setEnabled(true);
				
	}



	/* (non-Javadoc)
	 * @see games.GUI_GamePlatform#gameProcess(Messages.MessageGameProgress)
	 */
	@Override
	protected boolean gameProcess(MessageGameProgress msgGProg) {
		
		Message3Boom msg3B = (Message3Boom) msgGProg;

		gameTimerStop();
		
		//checking if the answer is right
		boolean boom = msg3B.isBoom();
		
		if(boom)
		{
			float num = msg3B.getNumber();
			
			if(num%3==0)	//boom is correct
			{
				msg3B.setIsCorrect(true);
				msg3B.setCurrentScore(++myScore);
				myUser.setScoreLabel(myScore);
				JOptionPane.showMessageDialog(null, "Correct Answer!", "", JOptionPane.INFORMATION_MESSAGE);

			}
			
			else
			{
				msg3B.setIsCorrect(false);
				msg3B.setCurrentScore(myScore);
				JOptionPane.showMessageDialog(null, "Wrong Answer!", "", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		
		
		boolean isEndGame = false;
		
		String winner = null;
		if((qnumber + 1) == numbers.size())	//if this is the last question
		{
			msg3B.setEndGame(true);
			
			isEndGame = true;
			
			
			if(myScore>rivalScore)		//if i win
			{
				winner = ms.myData.getPlayer().getUsername();
				
				msg3B.setWinner(winner);
				
				ms.myData.getPlayer().incRank();
				myUser.setStatusLabel("WINS");
				rivalUser.setStatusLabel("LOST");
				JOptionPane.showMessageDialog(null, "Game ended! You win!\nYour score: "+myScore+"\nRival score: "+rivalScore);
			
			}
			
			else if(myScore==rivalScore) //if it's a tie 
			{
				winner = "TIE";
				
				msg3B.setTieFlag(true);
				myUser.setStatusLabel("TIE");
				rivalUser.setStatusLabel("TIE");
				JOptionPane.showMessageDialog(null, "Game ended! It's a tie!\nYour score: "+myScore+"\nRival score: "+rivalScore);
				
			}
			else		//if rival wins
			{	
				winner = ms.roomPlat.getRoom().getGuestName();
				
				msg3B.setWinner(winner);
	
				myUser.setStatusLabel("LOST");
				rivalUser.setStatusLabel("WINS");
				JOptionPane.showMessageDialog(null, "Game ended! You lose!\nYour score: "+myScore+"\nRival score: "+rivalScore);
			}
						
				
		}
		
		msg3B.setWhosTurn(rivalUsername);	//updating the user's turn
		
		gameCommunication(msg3B);
		
		if(isEndGame)
		{
			endGame(winner);
			closeConnection();	//method from GUI_GamePlatform to close the connection
		}
			
		
		return isEndGame;
		
	}

	

/**
	 * This method initializes buttonSubmitA.
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getButtonBoom() {
		if (buttonBoom == null) {
			buttonBoom = new JButton();
			buttonBoom.setBounds(new Rectangle(400, 500, 111, 34));
			buttonBoom.setText("Boom");
			buttonBoom.setForeground(Color.RED);
			buttonBoom.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
			buttonBoom.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
				
					isBoom = true;
					isNext = false;
					
					Message3Boom msg3B = new Message3Boom(isBoom,isNext,(float)qnumber+1); 
					
					//Setting information for server backup
					msg3B.setSenderUsername(myUsername);
					msg3B.setReceiverUsername(rivalUsername);
					msg3B.setRoom_id(room_id);
					msg3B.setQnumber(qnumber);
					
					boolean isEndGame = gameProcess(msg3B);
				
					if(!isEndGame)
						rivalTurn();
				}
			});
		}
		return buttonBoom;
	}
	
	/**
	 * Gets the button next.
	 *
	 * @return the button next
	 */
	private JButton getButtonNext() {
		
		if(buttonNext == null) {
			
			buttonNext = new JButton();
			buttonNext.setBounds(new Rectangle(200,500,111,34));
			buttonNext.setText("Next");
			buttonNext.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
			buttonNext.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
				
					isBoom = false;
					isNext = true;
					
					
					Message3Boom msg3B = new Message3Boom(isBoom,isNext, qnumber+1); 
					
					boolean isEndGame = gameProcess(msg3B);
				
					if(!isEndGame)
						rivalTurn();
				}
			});
		}
		return buttonNext;
			
				
	}
		

/* (non-Javadoc)
 * @see games.GUI_GamePlatform#gameMessage(Messages.MessageGameProgress)
 */
@Override
protected void gameMessage(MessageGameProgress msgGProg) {
	
	switch(msgGProg.getMessageGameAction())
	{
		case TURN_3BOOM:
		{
						
						Message3Boom msg3B = (Message3Boom) msgGProg;
						
						boolean isEndGame = msg3B.isEndGame();
						
						//if's location must be here first for synchronization
						if(isEndGame)
						{
							ms.myData.getPlayer().setEnvironment(EnvStatus.LOBBY);
							ms.myData.setEnvironment(EnvStatus.LOBBY);				
						}
						
						if(msg3B.isBoom())
						{

							//checking if the answer is right
							if(msg3B.isCorrect())
							{
								rivalScore++;
								rivalUser.setScoreLabel(rivalScore);
								JOptionPane.showMessageDialog(null, "Rival Correct Answer!", "", JOptionPane.INFORMATION_MESSAGE);
								
							}
							else
							{
								JOptionPane.showMessageDialog(null, "Rival Wrong Answer!", "", JOptionPane.INFORMATION_MESSAGE);
							}
		
						}
						
						else { JOptionPane.showMessageDialog(null, "Your turn", "", JOptionPane.INFORMATION_MESSAGE); }
						
						if(isEndGame)	//if game  has ended
						{

							
							String winner;
							
							winner = msg3B.getWinner();
							
							//check if user is the winner
							if(msg3B.getWinner().equals(ms.myData.getPlayer().getUsername()))
							{
							
								myUser.setStatusLabel("WINS");
								rivalUser.setStatusLabel("LOST");
								JOptionPane.showMessageDialog(null, "Game ended! You win!\nYour score: "+myScore+"\nRival score: "+rivalScore);
								ms.myData.getPlayer().incRank();
		
							}
							else if(msg3B.isTieFlag()) //if it's a tie 
							{
								winner = "TIE";
								
								myUser.setStatusLabel("TIE");
								rivalUser.setStatusLabel("TIE");
								JOptionPane.showMessageDialog(null, "Game ended! It's a tie!\nYour score: "+myScore+"\nRival score: "+rivalScore);
								
							}
							else
							{
								myUser.setStatusLabel("LOST");
								rivalUser.setStatusLabel("WINS");
								JOptionPane.showMessageDialog(null, "Game ended! You lose!\nYour score: "+myScore+"\nRival score: "+rivalScore);	
							
							}
								endGame(winner);
								
							
						}
						
			
						//Game continues
						if(!isEndGame) {
							
							gameTimerStart();	//Starting timer counting backwards for lack of reaction again
							
							myTurn();
						}
					
						
						
		}
		
			break;
		
			
		case EXIT_GAME:
		{
			try {
				//If host - inform all spectators about exit
				if(ms.roomPlat.role.equals(Role.HOST))
					gameCommunication(msgGProg);
				
				exitGame();
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
			
		}
			break;
			
		case TIME_OVER:
		{
			endGame(myUsername);
			JOptionPane.showMessageDialog(null, "Rival's time is over! You win!","", JOptionPane.INFORMATION_MESSAGE);

		}
			break;
	
	}
}


/* (non-Javadoc)
 * @see games.GUI_GamePlatform#gameMessageSpectator(Messages.MessageGameProgress)
 */
@Override
protected void gameMessageSpectator(MessageGameProgress msgGProg) {

	switch(msgGProg.getMessageGameAction())
	{
		case TURN_3BOOM:
		{
						
		 Message3Boom msg3B = (Message3Boom) msgGProg;
		 	 
		 //not game initialization
		 if(!msg3B.isInitGame())
		 {
			 //not game ending
			 if(!msg3B.isEndGame())
			 {
					
				 	String sender = msg3B.getSenderUsername();
				 
					//checking if the player's answer is right
					if(msg3B.isCorrect())	
					{
						JOptionPane.showMessageDialog(null, sender+" Answered Correctly!", "", JOptionPane.INFORMATION_MESSAGE);
						
						if(sender.equals(hostUsername))
						{
							hostScore++;
							myUser.setScoreLabel(hostScore);
						}
						else
						{
							guestScore++;
							rivalUser.setScoreLabel(guestScore);
						}
					}
					else if(msg3B.isNext())
					{
						JOptionPane.showMessageDialog(null, sender+" passed the turn!", "", JOptionPane.INFORMATION_MESSAGE);
	
					}
					else
					{
						JOptionPane.showMessageDialog(null, sender+" Answered Incorrectly!", "", JOptionPane.INFORMATION_MESSAGE);
					}
					
		
				 qnumber++;
				 specView();
			 }
			 //game ending
			 else
			 {
					//check if host player is the winner
					if(msg3B.getWinner().equals(hostUsername))
					{
						myUser.setStatusLabel("WINS");
						rivalUser.setStatusLabel("LOST");
						JOptionPane.showMessageDialog(null, "Game ended! "+hostUsername+" WINS!"+"\n"+hostUsername+"'s score: "+hostScore+"\n"+guestUsername+"'s score: "+guestScore);
		
					}
					else if(msg3B.isTieFlag()) //if it's a tie 
					{
						myUser.setStatusLabel("TIE");
						rivalUser.setStatusLabel("TIE");
						JOptionPane.showMessageDialog(null, "Game ended! It's a tie!"+"\n"+hostUsername+"'s score: "+hostScore+"\n"+guestUsername+"'s score: "+guestScore);
						
					}
					else	//check if guest player is the winner
					{
						myUser.setStatusLabel("LOST");
						rivalUser.setStatusLabel("WINS");
						JOptionPane.showMessageDialog(null, "Game ended! "+guestUsername+" WINS!"+"\n"+hostUsername+"'s score: "+hostScore+"\n"+guestUsername+"'s score: "+guestScore);
					
					}
						
					try {
						initExitGame();
					} catch (InterruptedException e) {
						
						e.printStackTrace();
					}
				 
			 }
			 
		 }
		 
		 //Game initialization
		 else
		 {
			 qnumber = msg3B.getQnumber();
			 hostScore = msg3B.getCurrentScore();
			 guestScore = msg3B.getRivalScore();
			 
			 myUser.setScoreLabel(hostScore);
			 rivalUser.setScoreLabel(guestScore);
			 
			 specView();
			 	 
		 }
		 
		}
			break;
			
		case EXIT_GAME:
		{
			specExitGame();
		}
			break;
			
		case TIME_OVER:
		{
			MessageOutOfTime msgOOT = (MessageOutOfTime) msgGProg;
			
			String loserUN = msgOOT.getLoserUsername();
			
			String winnerUN;
			
			//Host was out of time and he is the loser
			if(loserUN.equals(hostUsername))
				winnerUN=guestUsername;
			//Guest was out of time and he is the loser
			else
				winnerUN=hostUsername;
			
			endGame(winnerUN);
			JOptionPane.showMessageDialog(null, loserUN+"'s time is over! "+winnerUN+" wins!","", JOptionPane.INFORMATION_MESSAGE);
			specExitGame();
		}
			break;
		
		
	
		
	}
}
	 

/* (non-Javadoc)
 * @see games.GUI_GamePlatform#specView()
 */
public void specView()
{

 //Changing to next number
 labelNumber.setText("Current #:"+"("+(Integer.parseInt(numbers.get(qnumber))+1)+")"); 
 

 //ADD more spec view features here
}



/* (non-Javadoc)
 * @see games.GUI_GamePlatform#replyToSpectator(ocsf.server.ConnectionToClient)
 */
@Override
protected void replyToSpectator(ConnectionToClient client) {
	// TODO Auto-generated method stub
	
	
	Message3Boom msg3B = new Message3Boom();
	
	msg3B.setInitGame(true);
	msg3B.setQnumber(qnumber);
	msg3B.setCurrentScore(myScore);
	msg3B.setRivalScore(rivalScore);
	
	try {
		client.sendToClient(msg3B);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}










}

