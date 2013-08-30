package games;

import java.awt.Color;
import java.awt.Dimension;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;

import DataObjects.Role;
import DataObjects.Room;
import GUI.GUI_DefaultPanel;
import GUI.GUI_ImagePanel;
import GUI.GUI_MainScreen;
import Messages.EnvStatus;
import Messages.Message;
import Messages.MessageEndGame;
import Messages.MessageExitGame;
import Messages.MessageGameProgress;
import Messages.MessageGameTrivia;
import Messages.MessageOpenRoom;
import Messages.MessageOutOfTime;
import Messages.MessageSpecExitGame;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Rectangle;
import javax.swing.JPanel;

import ocsf.server.ConnectionToClient;

import java.awt.GridBagLayout;
import java.awt.Font;

/**
 * The Class GUI_GamePlatform.
 */
public abstract class GUI_GamePlatform extends GUI_DefaultPanel {
	
	/** The Main Screen */
	protected  GUI_MainScreen ms;


	/** The local user's username. */
	protected String myUsername = null;
	
	/** The rival username. */
	protected String rivalUsername;
	
	/** The spectator username. */
	protected String specUsername;
	
	/** The host username. */
	protected String hostUsername;
	
	/** The guest username. */
	protected String guestUsername;
	
	/** The host score. */
	protected int hostScore = 0;
	
	/** The guest score. */
	protected int guestScore = 0;
	
	/** The local username's score. */
	protected int myScore = 0;
	
	/** The rival score. */
	protected int rivalScore = 0;
	
	/** The background. */
	protected GUI_ImagePanel background;
	
	/** The game panel. */
	protected JPanel gamePanel = null;
	
	/** The label user turn. */
	protected JLabel labelUserTurn = null;
	
	/** The button exit game. */
	protected JButton buttonExitGame = null;
	
	/** The label wait for guest. */
	protected JLabel labelWaitForGuest = null;
	
	/** The timer. */
	protected Timer timer;
	
	/** The room_id. */
	protected int room_id;
	
	
	/**
	 * Instantiates a new GUI_GamePlatform
	 */
	public GUI_GamePlatform()
	{
		timer = new Timer();
		ms = GUI_MainScreen.getInstance();
		this.myUsername=ms.myData.getPlayer().getUsername();
		room_id=ms.roomPlat.getRoom().getRoomID();
		
		init();
	}
	

/**
 * Inits the GUI elements.
 */
private void init() {
		
		//*** Initializing GUI elements
	
		
		background = new GUI_ImagePanel(new ImageIcon("images/background.jpg").getImage());
		background.setSize(new Dimension(1000, 800));
		
		labelUserTurn = new JLabel();
		labelUserTurn.setBounds(new Rectangle(749, 700, 206, 24));
		labelUserTurn.setForeground(Color.LIGHT_GRAY);
		labelUserTurn.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
 
		this.setLayout(null);
        this.setSize(new Dimension(1000, 800));
        this.add(labelUserTurn, null);
        this.add(getButtonExitGame(), null);
       
  
		
}

//Design conflict: want to make all inheritors implement waitforGuest() on one hand,
//But on the other hand the implementation is constant for all . . .
/**
 * Wait for guest to join game.
 */
protected void waitForGuest() {
	
	labelWaitForGuest = new JLabel();
	labelWaitForGuest.setForeground(Color.LIGHT_GRAY);
	labelWaitForGuest.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 35));
	labelWaitForGuest.setBounds(new Rectangle(20,20,500,500));
	labelWaitForGuest.setText("Waiting for rival . . .");
	
	add(labelWaitForGuest);
	this.add(background);
}

/**
 * End game.
 *
 * @param winner the winner of the game
 */
protected void endGame(String winner) {
	
	ms.myData.getPlayer().setEnvironment(EnvStatus.LOBBY);
	ms.myData.setEnvironment(EnvStatus.LOBBY);
	
	if(ms.roomPlat.role.equals(Role.HOST))
	{
		Room oldRoom = ms.roomPlat.getRoom();
		
		int room_id = oldRoom.getRoomID();
		String hostUsername = oldRoom.getHostName();
		String host_ip = oldRoom.getHostIP();
		String game_name = oldRoom.getGameName();
		String specUsername = oldRoom.getSpecName();
		
		
	
		Room bufferRoom = new Room();
		bufferRoom.setRoomID(room_id);
		
		if(ms.roomPlat.role.equals(Role.CLIENT))
			bufferRoom.setGuestName(myUsername);
		else bufferRoom.setGuestName(rivalUsername);
		
		
		bufferRoom.setHostIP(host_ip);
		bufferRoom.setGameName(game_name);
		bufferRoom.setHostName(hostUsername);
		
		if(winner.equals("TIE"))
			bufferRoom.setTieFlag(true);
		else
		{
			bufferRoom.setTieFlag(false);
			bufferRoom.setWinner(winner);
		}
		
		if(specUsername!=null)
			bufferRoom.setSpecName(specUsername);
		
		
		MessageEndGame msgEG = new MessageEndGame(bufferRoom);
		
		gameCommunication(msgEG);
	
	}
	
	closeConnection();
	
}


/**
 * Force exit game.
 *
 * @throws InterruptedException the interrupted exception
 */
public void forceExitGame() throws InterruptedException
{
	if(ms.roomPlat.role==Role.SPECTATOR)
		specExitGame();
	else 
		exitGame();
}

/**
 * Invitation denied.
 *
 * @param username the username
 * @param gamename the gamename
 * @throws InterruptedException the interrupted exception
 */
public void invitationDenied(String username,String gamename) throws InterruptedException
{
	ms.myData.getPlayer().setEnvironment(EnvStatus.LOBBY);
	ms.myData.setEnvironment(EnvStatus.LOBBY);
	gameTimerStop();
	JOptionPane.showMessageDialog(null, username+ " refused to start a game with you.\nReturning to lobby");
	
	closeConnection();
}



/**
 * Inits the exit game.
 *
 * @throws InterruptedException the interrupted exception
 */
protected void initExitGame() throws InterruptedException {
		
		ms.myData.getPlayer().setEnvironment(EnvStatus.LOBBY);
		ms.myData.setEnvironment(EnvStatus.LOBBY);
		
		if(ms.roomPlat.role!=Role.SPECTATOR)
		{	
			Room oldRoom = ms.roomPlat.getRoom();
			
			int room_id = oldRoom.getRoomID();
			String hostUsername = oldRoom.getHostName();
			String host_ip = oldRoom.getHostIP();
			String game_name = oldRoom.getGameName();
			String specUsername = oldRoom.getSpecName();
			
			
	
			Room bufferRoom = new Room();
			bufferRoom.setRoomID(room_id);
			
			if(ms.roomPlat.role.equals(Role.CLIENT))
				bufferRoom.setGuestName(myUsername);
			else bufferRoom.setGuestName(rivalUsername);
			
			
			bufferRoom.setHostIP(host_ip);
			bufferRoom.setGameName(game_name);
			bufferRoom.setHostName(hostUsername);
			
			if(specUsername!=null)
				bufferRoom.setSpecName(specUsername);
			
			MessageExitGame msgEG = new MessageExitGame(bufferRoom);
		
			
			gameCommunication(msgEG);
			
			gameTimerStop();
			
			JOptionPane.showMessageDialog(null, "Game aborted.\nReturning to lobby");
		}
		
		else
		{
			specExitGame();
		}
		
		closeConnection();
		
		
	
}

/**
 * Spectator exit game.
 */
protected void specExitGame() {
	
	ms.myData.getPlayer().setEnvironment(EnvStatus.LOBBY);
	ms.myData.setEnvironment(EnvStatus.LOBBY);
	
	MessageSpecExitGame msgSEG = new MessageSpecExitGame(ms.myData.getPlayer().getUsername());
	
	gameCommunication(msgSEG);
	
	JOptionPane.showMessageDialog(null, "Exiting to lobby");
}

/**
 * Exit game.
 *
 * @throws InterruptedException the interrupted exception
 */
protected void exitGame() throws InterruptedException {
	
	ms.myData.getPlayer().setEnvironment(EnvStatus.LOBBY);
	ms.myData.setEnvironment(EnvStatus.LOBBY);
	gameTimerStop();
	JOptionPane.showMessageDialog(null, "Rival has exited.\nReturning to lobby");
	
	closeConnection();
	
}




/**
 * Send message from host.
 *
 * @param msg the game message
 */
protected void sendMessageFromHost(Object msg)
	{
		//Sending message to all guests
		ms.roomPlat.p2pServer.sendToAllClients(msg);

		if(msg instanceof MessageExitGame)
			sendToServer(msg);
	}
	
	/**
	 * Send message from guest.
	 *
	 * @param msg the game message
	 */
	protected void sendMessageFromGuest(Object msg)
	{
		//Sending message to host
		try {
			ms.roomPlat.p2pClient.sendToServer(msg);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
		if(msg instanceof MessageExitGame)
			sendToServer(msg);
	}
	
	/**
	 * Send message from spectator.
	 *
	 * @param msg the spectating initialization message
	 */
	protected void sendMessageFromSpec(Object msg)
	{
		try {
			if(msg instanceof MessageSpecExitGame)
				ms.client.sendToServer(msg);
			
			else ms.roomPlat.p2pClient.sendToServer(msg);
				
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	/**
	 * Send to server.
	 *
	 * @param msg the game message
	 */
	protected void sendToServer(Object msg)
	{
		
		
		try {
			ms.client.sendToServer(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	/**
	 * Game communication.
	 *
	 * @param msg the game message
	 */
	public  void gameCommunication(Object msg)
	{
		Message baseMessage = (Message) msg;
		MessageGameProgress msgGProg = (MessageGameProgress) baseMessage;
		
			switch (ms.roomPlat.role){
			
				case HOST :
				{	
					if(msgGProg instanceof MessageEndGame)
						sendToServer(msgGProg);
					else
						sendMessageFromHost(msgGProg);
				}
					break;
				
				case CLIENT:
				{
					if(!msgGProg.isEndGame())
						sendMessageFromGuest(msgGProg);
				}
					break;
				
				case SPECTATOR:
				{
					sendMessageFromSpec(msg);
				}
					break;	
			}	
	}
	
	/**
	 * Close connection.
	 */
	public void closeConnection()
	{
		switch (ms.roomPlat.role){
		
		case HOST :
		{	

			try {
				ms.roomPlat.p2pServer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			break;
		
		case CLIENT:
		{
			try {
				ms.roomPlat.p2pClient.closeConnection();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			break;
		
		case SPECTATOR:
		{
			try {
				ms.roomPlat.p2pClient.closeConnection();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			break;	
	}	
	}
	
	/**
	 * Game get message.
	 *
	 * @param msgGProg the game message
	 */
	public void gameGetMessage(MessageGameProgress msgGProg)
	{
		switch (ms.roomPlat.role){
		
			case HOST:
			{
				updateSpec(msgGProg);	//updating the spectators, if there are any...
				gameMessage(msgGProg);
			}
			break;
			
			case CLIENT:
			{
				
				if(!msgGProg.getRole().equals(Role.SPECTATOR))
					gameMessage(msgGProg);	
			}
			break;
			
			case SPECTATOR:
			{
				gameMessageSpectator(msgGProg);
			}
			break;
		}
	}
	
	//Abstract methods declarations - inheritors must implement
	
	/**
	 * Game process. Processes game actions and send the game message.
	 *
	 * @param msgGProg the game message
	 * @return true, if game ends
	 */
	protected abstract boolean gameProcess(MessageGameProgress msgGProg);	
	
	/**
	 * Game message. Receives and processes the game message.
	 *
	 * @param msgGProg the game message
	 */
	protected abstract void gameMessage(MessageGameProgress msgGProg);
	
	/**
	 * Start game host. Starts the game for host side.
	 *
	 * @param rivalUsername the rival username
	 */
	protected abstract void startGameHost(String rivalUsername);
	
	/**
	 * Start game guest. Starts the game for guest side
	 *
	 * @param rivalUsername the rival username
	 */
	protected abstract void startGameGuest(String rivalUsername);
	
	/**
	 * Start game spectator starts the game for spectator side.
	 *
	 * @param hostUsername the host username
	 * @param guestUsername the guest username
	 */
	protected abstract void startGameSpec(String hostUsername, String guestUsername);
	
	/**
	 * Game message spectator. Receives and displays the game progress.
	 *
	 * @param msgGProg the game message
	 */
	protected abstract void gameMessageSpectator(MessageGameProgress msgGProg);
	
	/**
	 * Reply to spectator. The host replies to initial spectator message.
	 *
	 * @param client the connection to spectator 
	 */
	protected abstract void replyToSpectator(ConnectionToClient client);
	
	/**
	 * My turn. Initializes GUI elements when it is the local player's turn.
	 */
	protected abstract void myTurn();
	
	/**
	 * Rival turn. Initializes GUI elements when it is the rival player's turn.
	 */
	protected abstract void rivalTurn();
	
	/**
	 * Spec view. Initializes GUI elements for spectator view.
	 */
	protected abstract void specView();
	
	
	/**
	 * Update spec. Host updating spectators about the game progress.
	 *
	 * @param msgGProg the game message
	 */
	protected void updateSpec(MessageGameProgress msgGProg)
	{
		msgGProg.setRole(Role.SPECTATOR);
		gameCommunication(msgGProg);
	}
	
	
	
	/**
	 * Game timer start. Starting the timer counting backwards for lack of player reaction.
	 */
	public void gameTimerStart()
	{
		TimerTask task = new TimerTask() {

			public void run() {
				
				
				//Informing rival that player's time was over, declaring current player as loser
				MessageOutOfTime msgOOT = new MessageOutOfTime(myUsername);
				
				gameCommunication(msgOOT);
	
				endGame(rivalUsername);
				
				JOptionPane.showMessageDialog(null, "Time's up! You lost the game!");
				
			}
			
			
		};
		
		timer = new Timer();
		timer.schedule(task, 30000);	//30000 - 30 seconds (in milliseconds)
		
	}
	
	/**
	 * Game timer stop. Stops the game timer.
	 */
	public void gameTimerStop() {
		
		timer.cancel();
		
	}
	

	/**
	 * This method initializes gamePanel.
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getGamePanel() {
		if (gamePanel == null) {
			gamePanel = new JPanel();
			gamePanel.setLayout(new GridBagLayout());
			gamePanel.setBounds(new Rectangle(40, 16, 900, 650));
		}
		return gamePanel;
	}

	/**
	 * This method initializes buttonExitGame.
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getButtonExitGame() {
		if (buttonExitGame == null) {
			buttonExitGame = new JButton();
			buttonExitGame.setBounds(new Rectangle(46, 700, 141, 34));
			buttonExitGame.setText("Exit Game");
			buttonExitGame.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
			buttonExitGame.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
				
					
					try {
						
						initExitGame();
					} catch (InterruptedException e1) {
					
						e1.printStackTrace();
					}
					
				}
				
			});
			
			
			
			
		}
		return buttonExitGame;
	}





} 
