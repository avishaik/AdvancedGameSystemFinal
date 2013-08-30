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
import Messages.MessageGameProgress;
import Messages.MessageGameTrivia;
import Messages.MessageOutOfTime;

import javax.swing.JTabbedPane;

import ocsf.server.ConnectionToClient;


/**
 * The Class GUI_GameTrivia.
 */
public class GUI_GameTrivia extends GUI_GamePlatform {
	
	/**
	 * The Class TriviaData.
	 */
	private class TriviaData {
		
		/** The question. */
		private String question;
		
		/** The true answer. */
		private String trueAnswer;
		
		/** The false answers. */
		private ArrayList<String> falseAnswers;
		
		
		/**
		 * Instantiates a new trivia data.
		 */
		public TriviaData() {
			
			falseAnswers = new ArrayList<String>();
		}
		
		
		/**
		 * Sets the question.
		 *
		 * @param question the new question
		 */
		public void setQuestion(String question) {
			this.question = question;
		}
		
		/**
		 * Gets the question.
		 *
		 * @return the question
		 */
		public String getQuestion() {
			return question;
		}
		
		/**
		 * Sets the true answer.
		 *
		 * @param answer the new true answer
		 */
		public void setTrueAnswer(String answer) {
			this.trueAnswer = answer;
		}
		
		/**
		 * Gets the true answer.
		 *
		 * @return the true answer
		 */
		public String getTrueAnswer() {
			return trueAnswer;
		}
		
		/**
		 * Adds the false answer.
		 *
		 * @param falseAnswer the false answer
		 */
		public void addFalseAnswer(String falseAnswer) {
			this.falseAnswers.add(falseAnswer);
		}
		
		/**
		 * Gets the false answers.
		 *
		 * @return the false answers
		 */
		public ArrayList<String> getFalseAnswers() {
			return falseAnswers;
		}

		
	}
	
	/** The triv data. */
	private ArrayList<TriviaData> trivData;  //  @jve:decl-index=0:
	
	/** The label title. */
	private JLabel labelTitle = null;  //  @jve:decl-index=0:visual-constraint="10,1081"
	
	/** The label question. */
	private JLabel labelQuestion = null;
	
	/** The radio button a1. */
	private JRadioButton radioButtonA1 = null;
	
	/** The radio button a2. */
	private JRadioButton radioButtonA2 = null;
	
	/** The radio button a3. */
	private JRadioButton radioButtonA3 = null;
	
	/** The radio button a4. */
	private JRadioButton radioButtonA4 = null;
	
	/** The bg group. */
	private ButtonGroup bgGroup = null;  //  @jve:decl-index=0:
	
	/** The false answers. */
	private ArrayList<String> falseAnswers;
	
	/** The qnumber. */
	private int qnumber = 0;
	
	/** The button submit a. */
	private JButton buttonSubmitA = null;
	
	/** The my user. */
	private GUI_UserBox myUser;
	
	/** The rival user. */
	private GUI_UserBox rivalUser;
	
	
	/**
	 * Instantiates a new gU i_ game trivia.
	 *
	 * @param role the role
	 */
	public GUI_GameTrivia(Role role)
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
		
		initTrivData();
		initialize();
		
		buttonSubmitA.setEnabled(false);
		radioButtonA1.setFocusable(false);
		radioButtonA2.setFocusable(false);
		radioButtonA3.setFocusable(false);
		radioButtonA4.setFocusable(false);
		
	
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
		
		initTrivData();
		initialize();
		
	}
	
	/* (non-Javadoc)
	 * @see games.GUI_GamePlatform#startGameSpec(java.lang.String, java.lang.String)
	 */
	@Override
	public void startGameSpec(String hostUsername, String guestUsername) {
		
		  //myUsername = new String();
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
		  
		  MessageGameTrivia msgGT = new MessageGameTrivia();
		  msgGT.setRole(Role.SPECTATOR);
		  
		  gameCommunication(msgGT);

		  initTrivData();
		  initialize();
		  
		  buttonSubmitA.setEnabled(false);
		  radioButtonA1.setFocusable(false);
		  radioButtonA2.setFocusable(false);
		  radioButtonA3.setFocusable(false);
		  radioButtonA4.setFocusable(false);
		
		
	}

	
	/**
	 * Inits the triv data.
	 */
	public void initTrivData() {
		
		
		trivData = new ArrayList<TriviaData>();
		
		falseAnswers = new ArrayList<String>();
		
		String[] q={"What did the french man say in Monty Python?","Who's bad?","What's up?"};
		
		int length = q.length;
		
		String[][] answers = new String[length][4];
		
		//Question 1 answers
		answers[0][0] = "I fart in your general direction";	//True answer
		answers[0][1] = "I ask for your general instruction";
		answers[0][2] = "I kill in your general rotation";
		answers[0][3] = "I crush in your general speculation";
		
		//Question 2 answers
		answers[1][0] = "Michael Jackson";	//True answer
		answers[1][1] = "Robert E. Junior";
		answers[1][2] = "Michael J. Fox";
		answers[1][3] = "John Doe";
		
		//Question 3 answers
		answers[2][0] = "1";	//True answer
		answers[2][1] = "2";
		answers[2][2] = "3";
		answers[2][3] = "4";

		for(int i=0;i<q.length;i++)
		{
			String question=q[i];
			
			TriviaData data=new TriviaData();
			data.setQuestion(question);
		
			
			for(int j=0;j<4;j++)
			{		
				if(j==0)
					data.setTrueAnswer(answers[i][j]);
				else
					data.addFalseAnswer(answers[i][j]);
			}
			trivData.add(data);
		}
		
		falseAnswers = trivData.get(0).getFalseAnswers();
	}
		



	/**
	 * This method initializes this.
	 */
	private void initialize() {
		
		labelQuestion = new JLabel();
		labelQuestion.setBounds(new Rectangle(49, 154, 905, 59));
		labelQuestion.setForeground(Color.WHITE);
		labelQuestion.setText("Question #"+(qnumber+1)+": "+trivData.get(qnumber).getQuestion());
		labelQuestion.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 30));
		
		labelTitle = new JLabel();
		labelTitle.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 50));
		labelTitle.setText("Trivia Game");
		labelTitle.setBounds(new Rectangle(328, 44, 315, 71));
		labelTitle.setForeground(Color.WHITE);
		
		
		bgGroup = new ButtonGroup();
		
		bgGroup.add(getRadioButtonA1());
		bgGroup.add(getRadioButtonA2());
		bgGroup.add(getRadioButtonA3());
		bgGroup.add(getRadioButtonA4());
		
		falseAnswers = trivData.get(qnumber).getFalseAnswers();
		

		
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
		this.gamePanel.add(getButtonSubmitA(), null);
		this.gamePanel.add(labelTitle, null);
		this.gamePanel.add(labelQuestion, null);
		this.gamePanel.add(getRadioButtonA1() , null);
		this.gamePanel.add(getRadioButtonA2() , null);
		this.gamePanel.add(getRadioButtonA3() , null);
		this.gamePanel.add(getRadioButtonA4() , null);
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
		
		labelQuestion.setText("Question #"+(qnumber+1)+": "+trivData.get(qnumber).getQuestion());	//changing to next question
		
		labelUserTurn.setText("User's Turn: "+rivalUsername);	//changing to rival's turn
		
		myUser.setStatusLabel("Waiting");
		rivalUser.setStatusLabel("Playing");
		
		//Getting new answers
		radioButtonA1.setText(trivData.get(qnumber).getTrueAnswer());	
		radioButtonA2.setText(trivData.get(qnumber).falseAnswers.get(0));
		radioButtonA3.setText(trivData.get(qnumber).falseAnswers.get(1));
		radioButtonA4.setText(trivData.get(qnumber).falseAnswers.get(2));
		
		//Setting buttons to be inactive
		radioButtonA1.setFocusable(false);
		radioButtonA2.setFocusable(false);
		radioButtonA3.setFocusable(false);
		radioButtonA4.setFocusable(false);
		
		buttonSubmitA.setEnabled(false);
	
		
	}
	
	/* (non-Javadoc)
	 * @see games.GUI_GamePlatform#myTurn()
	 */
	protected void myTurn()
	{

		myUser.setStatusLabel("Playing");
		rivalUser.setStatusLabel("Waiting");
		
		qnumber++;					//Moving forward to my question
		
		labelQuestion.setText("Question #"+(qnumber+1)+": "+trivData.get(qnumber).getQuestion());	//changing to next question
		
		labelUserTurn.setText("User's Turn: "+myUsername);	//changing to rival's turn
		
		bgGroup.clearSelection();
		
		//Getting new answers
		radioButtonA1.setText(trivData.get(qnumber).getTrueAnswer());	
		radioButtonA1.setActionCommand(trivData.get(qnumber).getTrueAnswer());
		
		radioButtonA2.setText(trivData.get(qnumber).falseAnswers.get(0));
		radioButtonA2.setActionCommand(trivData.get(qnumber).falseAnswers.get(0));
		
		radioButtonA3.setText(trivData.get(qnumber).falseAnswers.get(1));
		radioButtonA3.setActionCommand(trivData.get(qnumber).falseAnswers.get(1));
		
		
		radioButtonA4.setText(trivData.get(qnumber).falseAnswers.get(2));
		radioButtonA4.setActionCommand(trivData.get(qnumber).falseAnswers.get(2));
		
		//Setting buttons to be inactive
		radioButtonA1.setFocusable(true);
		radioButtonA2.setFocusable(true);
		radioButtonA3.setFocusable(true);
		radioButtonA4.setFocusable(true);
		
		buttonSubmitA.setEnabled(true);
				
	}



	/* (non-Javadoc)
	 * @see games.GUI_GamePlatform#gameProcess(Messages.MessageGameProgress)
	 */
	@Override
	protected boolean gameProcess(MessageGameProgress msgGProg) {
		
		MessageGameTrivia msgGT = (MessageGameTrivia) msgGProg;

		gameTimerStop();
		
		//checking if the answer is right
		if(msgGT.getAnswer().equals(trivData.get(qnumber).getTrueAnswer()))	
		{
			msgGT.setIsCorrect(true);
			msgGT.setCurrentScore(++myScore);
			myUser.setScoreLabel(myScore);
			JOptionPane.showMessageDialog(null, "Correct Answer!", "", JOptionPane.INFORMATION_MESSAGE);

		}
		else
		{
			msgGT.setIsCorrect(false);
			msgGT.setCurrentScore(myScore);
			JOptionPane.showMessageDialog(null, "Wrong Answer!", "", JOptionPane.INFORMATION_MESSAGE);

		}
		
		boolean isEndGame = false;
		
		String winner = null;
		if((qnumber + 1) == trivData.size())	//if this is the last question
		{
			msgGT.setEndGame(true);
			
			isEndGame = true;
			
			
			if(myScore>rivalScore)		//if i win
			{
				winner = ms.myData.getPlayer().getUsername();
				
				msgGT.setWinner(winner);
				
				ms.myData.getPlayer().incRank();
				myUser.setStatusLabel("WINS");
				rivalUser.setStatusLabel("LOST");
				JOptionPane.showMessageDialog(null, "Game ended! You win!\nYour score: "+myScore+"\nRival score: "+rivalScore);
			
			}
			
			else if(myScore==rivalScore) //if it's a tie 
			{
				winner = "TIE";
				
				msgGT.setTieFlag(true);
				myUser.setStatusLabel("TIE");
				rivalUser.setStatusLabel("TIE");
				JOptionPane.showMessageDialog(null, "Game ended! It's a tie!\nYour score: "+myScore+"\nRival score: "+rivalScore);
				
			}
			else		//if rival wins
			{	
				winner = ms.roomPlat.getRoom().getGuestName();
				
				msgGT.setWinner(winner);
	
				myUser.setStatusLabel("LOST");
				rivalUser.setStatusLabel("WINS");
				JOptionPane.showMessageDialog(null, "Game ended! You lose!\nYour score: "+myScore+"\nRival score: "+rivalScore);
			}
						
				
		}
		
		msgGT.setWhosTurn(rivalUsername);	//updating the user's turn
		msgGT.setRivalScore(rivalScore);	//updating rival's score (for server backup)
		
		gameCommunication(msgGT);
		
		if(isEndGame)
		{
			endGame(winner);
			closeConnection();	//method from GUI_GamePlatform to close the connection
		}
			
		
		return isEndGame;
		
	}



	/**
	 * This method initializes radioButtonA1.
	 *
	 * @return javax.swing.JRadioButton
	 */
	private JRadioButton getRadioButtonA1() {
		if (radioButtonA1 == null) {
			radioButtonA1 = new JRadioButton();
			radioButtonA1.setBounds(new Rectangle(48, 325, 605, 30));
			radioButtonA1.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 25));
			radioButtonA1.setForeground(Color.WHITE);
			radioButtonA1.setText(trivData.get(0).getTrueAnswer());
			radioButtonA1.setActionCommand(trivData.get(0).getTrueAnswer());
		}
		return radioButtonA1;
	}




	/**
	 * This method initializes radioButtonA2.
	 *
	 * @return javax.swing.JRadioButton
	 */
	private JRadioButton getRadioButtonA2() {
		if (radioButtonA2 == null) {
			radioButtonA2 = new JRadioButton();
			radioButtonA2.setBounds(new Rectangle(48, 386, 610, 30));
			radioButtonA2.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 25));
			radioButtonA2.setForeground(Color.WHITE);
			radioButtonA2.setText(falseAnswers.get(0));
			radioButtonA2.setActionCommand(falseAnswers.get(0));
		}
		return radioButtonA2;
	}




	/**
	 * This method initializes radioButtonA3.
	 *
	 * @return javax.swing.JRadioButton
	 */
	private JRadioButton getRadioButtonA3() {
		if (radioButtonA3 == null) {
			radioButtonA3 = new JRadioButton();
			radioButtonA3.setBounds(new Rectangle(48, 450, 612, 30));
			radioButtonA3.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 25));
			radioButtonA3.setForeground(Color.WHITE);
			radioButtonA3.setText(falseAnswers.get(1));
			radioButtonA3.setActionCommand(falseAnswers.get(1));
		}
		return radioButtonA3;
	}




	/**
	 * This method initializes radioButtonA4.
	 *
	 * @return javax.swing.JRadioButton
	 */
	private JRadioButton getRadioButtonA4() {
		if (radioButtonA4 == null) {
			radioButtonA4 = new JRadioButton();
			radioButtonA4.setBounds(new Rectangle(48, 520, 610, 30));
			radioButtonA4.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 25));
			radioButtonA4.setForeground(Color.WHITE);
			radioButtonA4.setText(falseAnswers.get(2));
			radioButtonA4.setActionCommand(falseAnswers.get(2));
		}
		return radioButtonA4;
	}


/**
 * This method initializes buttonSubmitA.
 *
 * @return javax.swing.JButton
 */
	private JButton getButtonSubmitA() {
		if (buttonSubmitA == null) {
			buttonSubmitA = new JButton();
			buttonSubmitA.setBounds(new Rectangle(740, 610, 111, 34));
			buttonSubmitA.setText("Submit");
			buttonSubmitA.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
			buttonSubmitA.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
				
					MessageGameTrivia msgGT = new MessageGameTrivia(); 


					String selectedAnswer = bgGroup.getSelection().getActionCommand();	//Getting the answer selected by the user
					msgGT.setAnswer(selectedAnswer);	
					msgGT.setQnumber(qnumber);		//Setting the question number
					
					//Setting information for server backup
					msgGT.setSenderUsername(myUsername);
					msgGT.setReceiverUsername(rivalUsername);
					msgGT.setRoom_id(room_id);
					
					boolean isEndGame = gameProcess(msgGT);
				
					if(!isEndGame)
						rivalTurn();
				}
			});
		}
		return buttonSubmitA;
	}


/* (non-Javadoc)
 * @see games.GUI_GamePlatform#gameMessage(Messages.MessageGameProgress)
 */
@Override
protected void gameMessage(MessageGameProgress msgGProg) {
	
	switch(msgGProg.getMessageGameAction())
	{
		case TURN_TRIVIA:
		{
						
						MessageGameTrivia msgGT = (MessageGameTrivia) msgGProg;
						
						boolean isEndGame = msgGT.isEndGame();
						
						//if's location must be here first for synchronization
						if(isEndGame)
						{
							ms.myData.getPlayer().setEnvironment(EnvStatus.LOBBY);
							ms.myData.setEnvironment(EnvStatus.LOBBY);				
						}
							
						//checking if the rival's answer is right
						if(msgGT.getAnswer().equals(trivData.get(qnumber).getTrueAnswer()))	
						{
							
							rivalScore++;
							rivalUser.setScoreLabel(rivalScore);
							JOptionPane.showMessageDialog(null, "Rival Correct Answer!", "", JOptionPane.INFORMATION_MESSAGE);
							
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Rival Wrong Answer!", "", JOptionPane.INFORMATION_MESSAGE);
						}
						
						
						
						
						if(isEndGame)	//if game  has ended
						{

							
							String winner;
							
							winner = msgGT.getWinner();
							
							//check if user is the winner
							if(msgGT.getWinner().equals(ms.myData.getPlayer().getUsername()))
							{
							
								myUser.setStatusLabel("WINS");
								rivalUser.setStatusLabel("LOST");
								JOptionPane.showMessageDialog(null, "Game ended! You win!\nYour score: "+myScore+"\nRival score: "+rivalScore);
								ms.myData.getPlayer().incRank();
		
							}
							else if(msgGT.isTieFlag()) //if it's a tie 
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
								//gameCommunication(msgGT);
							
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
			//Rival's time is over ending and declaring me as winner,
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
		case TURN_TRIVIA:
		{
		
			 MessageGameTrivia msgGT = (MessageGameTrivia) msgGProg;
			 	 
			 //not game initialization
			 if(!msgGT.isInitGame())
			 {
				 //not game ending
				 if(!msgGT.isEndGame())
				 {
						
					 	String sender = msgGT.getSenderUsername();
					 
						//checking if the player's answer is right
						if(msgGT.getAnswer().equals(trivData.get(qnumber).getTrueAnswer()))	
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
						if(msgGT.getWinner().equals(hostUsername))
						{
							myUser.setStatusLabel("WINS");
							rivalUser.setStatusLabel("LOST");
							JOptionPane.showMessageDialog(null, "Game ended! "+hostUsername+" WINS!"+"\n"+hostUsername+"'s score: "+hostScore+"\n"+guestUsername+"'s score: "+guestScore);
			
						}
						else if(msgGT.isTieFlag()) //if it's a tie 
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
				 qnumber = msgGT.getQnumber();
				 hostScore = msgGT.getCurrentScore();
				 guestScore = msgGT.getRivalScore();
				 
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

 
 labelQuestion.setText("Question #"+(qnumber+1)+": "+trivData.get(qnumber).getQuestion()); //changing to next question
 
 
 //Getting new answers
 radioButtonA1.setText(trivData.get(qnumber).getTrueAnswer()); 
 radioButtonA2.setText(trivData.get(qnumber).falseAnswers.get(0));
 radioButtonA3.setText(trivData.get(qnumber).falseAnswers.get(1));
 radioButtonA4.setText(trivData.get(qnumber).falseAnswers.get(2));
 

 //ADD more spec view features here
}

/* (non-Javadoc)
 * @see games.GUI_GamePlatform#replyToSpectator(ocsf.server.ConnectionToClient)
 */
@Override
protected void replyToSpectator(ConnectionToClient client) {
	// TODO Auto-generated method stub
	
	
	MessageGameTrivia msgGT = new MessageGameTrivia();
	
	msgGT.setInitGame(true);
	msgGT.setQnumber(qnumber);
	msgGT.setCurrentScore(myScore);
	msgGT.setRivalScore(rivalScore);
	
	try {
		client.sendToClient(msgGT);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}









}

