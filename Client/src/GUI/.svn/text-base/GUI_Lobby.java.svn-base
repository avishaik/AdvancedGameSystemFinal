package GUI;

//import games.GamePlatform;

import games.GUI_Game3Boom;
import games.GUI_GamePlatform;
import games.GUI_GameTrivia;
import games.RoomPlatform;
import DataObjects.Role;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import DataObjects.LobbyData;
import DataObjects.MyData;
import DataObjects.Players;
import DataObjects.Room;
import DataObjects.Player;
import Messages.EnvStatus;
import Messages.MessageCheckResume;
import Messages.MessageCheckResumeReply;
import Messages.MessageGameProgress;
import Messages.MessageGetRankReply;
import Messages.MessageInvitationDenied;
import Messages.MessageInvitePlayer;
import Messages.MessageLogout;
import Messages.MessageLogoutReply;
import Messages.MessageOpenRoom;
import Messages.MessageRefreshLobby;
import Messages.MessageGetResumeInfo;
import Messages.MessageResumeGame;
import Messages.MessageShowHistory;
import Messages.MessageShowHistoryReply;
import Messages.RoomStatus;
import Messages.MessageLogoutReply.LogoutStatus;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 * This constructor get order list and initialize button
 * 
 * @param MainMenuType type, GUI_MainScreen ms
 */


public class GUI_Lobby extends GUI_DefaultPanel {
	
	
	
	public JButton logOut = null;
	protected JButton about = null;
	

	
	public GUI_MainScreen ms;
	
	protected GUI_ImagePanel background;
	private GUI_DefaultTable gamesTable;
	private GUI_DefaultTable playersTable;
	private String[] gamesColumnNames = {"Room ID", "Status","Player1", "Player2","Game Name","Host IP"};
	private String[] playersColumnNames ={"Username", "Rank", "Status"};
	private String[] gamesTypes = {"Game Type","3Boom", "Trivia"};
	private String gameType=null;  //  @jve:decl-index=0:
	protected JPanel jInboxPanel = null;
	private JButton buttonOpenGame = null;
	private JButton buttonJoinView = null;
	private JButton gameHistory;
	private JComboBox comboBoxGameType = null;
	protected JLabel mainMenu = null;
	private JLabel labelPlayerList = null;
	private JLabel labelRoomList = null;
	private JLabel labelWelcome = null;
	private ArrayList<Room> rooms;
	private ArrayList<Player> players;
	


	public GUI_Lobby(GUI_MainScreen ms, ArrayList<Room> rooms, ArrayList<Player> players) {
		
		this.ms = GUI_MainScreen.getInstance();
		this.rooms = rooms;
		this.players = players;
		initialize();
		
	}
	

	public GUI_Lobby(GUI_MainScreen ms, ArrayList<Player> players) {
		
		this.ms = GUI_MainScreen.getInstance();
		this.players = players;
		initialize();
		
		
	}
	
	public GUI_Lobby(GUI_MainScreen ms) {
		
		this.ms=GUI_MainScreen.getInstance();
		initialize();
	}
	

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	protected void initialize() {
		
		//*** Initializing GUI elements
		
		
		background = new GUI_ImagePanel(new ImageIcon("images/background.jpg").getImage());
		background.setSize(new Dimension(1000, 800));

		labelPlayerList = new JLabel();
		labelPlayerList.setBounds(new Rectangle(140, 181, 153, 27));
		labelPlayerList.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
		labelPlayerList.setForeground(Color.LIGHT_GRAY);
		labelPlayerList.setText("Player List");
		
		labelRoomList = new JLabel();
		labelRoomList.setBounds(new Rectangle(595,181,153,27));
		labelRoomList.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
		labelRoomList.setForeground(Color.LIGHT_GRAY);
		labelRoomList.setText("Room List");
		
		labelWelcome = new JLabel();
		labelWelcome.setBounds(new Rectangle(43, 130, 160, 27));
		labelWelcome.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
		labelWelcome.setForeground(Color.LIGHT_GRAY);
		labelWelcome.setText("Welcome "+ms.myData.getPlayer().getUsername()+"!");
		
		/*gameHistory = new JButton();
		gameHistory.setBounds(new Rectangle(220, 130, 200, 27));
		gameHistory.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
		gameHistory.setForeground(Color.LIGHT_GRAY);
		gameHistory.setText("Game History");*/

		mainMenu = new JLabel();
		mainMenu.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 50));
		mainMenu.setBounds(new Rectangle(420, 55, 170, 54));
		mainMenu.setText("Lobby");
		mainMenu.setForeground(Color.LIGHT_GRAY);
		
	
		initTables(rooms,players);
		
		//this.add(gameHistory,null);
		this.add(labelRoomList,null);
		this.add(labelWelcome, null);
		this.add(getLogout(), null);
		this.add(mainMenu, null);
		this.add(getAbout(), null);
		this.add(getGameHistory(), null);
		this.add(getButtonOpenGame(), null);
		this.add(getButtonJoinView(), null);
		this.add(getGameType(), null);
		this.add(labelPlayerList, null);
		this.add(labelWelcome, null);
		this.add(background, null);	

	}
	

	public void initTables(ArrayList<Room> rooms, ArrayList<Player> players) {
		
		if(rooms!=null)
			initGamesTable(rooms);
		if(players!=null)
			initPlayersTable(players);
		
		
	}
	
	protected void initPlayersTable(ArrayList<Player> players) {
		
		int length = players.size();
		Object[][] data = new Object[length][3];
		int i=0;
		
		for(Player player: players)
		{
			data[i][0] = player.getUsername();
			data[i][1] = player.getRank();
			data[i][2] = player.getStatus();
			
			i++;
			
		}
		
		playersTable = new GUI_DefaultTable(data, playersColumnNames);
		playersTable.setBounds(43, 225, 300, 441);

		playersTable.setVisible(true);
		
		this.add(playersTable);
		
	}

	protected void initGamesTable(ArrayList<Room> rooms) {
		
		int length = rooms.size();
		Object[][] data = new Object[length][6];
		int i=0;
		
		for(Room room: rooms)
		{
			data[i][0] = room.getRoomID();
			data[i][1] = room.getStatus();
			ArrayList<String> players = new ArrayList<String>();
			
			String hostPlayer = room.getHostName();
			
			players.add(hostPlayer);
			
			String guestPlayer = room.getGuestName();
			
			if(guestPlayer!=null)
				players.add(guestPlayer);
			
			String specPlayer = room.getSpecName();
			
			if(specPlayer!=null)
				players.add(specPlayer);

			length = players.size();

			int j=0;
			for(j=0;j<length;j++)
			{
				data[i][j+2] = players.get(j);
			}
			
			if(data[i][j+2]==null)
				data[i][j+2]="";
				
			
			data[i][4] = room.getGameName();
			data[i][5] = room.getHostIP();
	
			i++;
			
		}
		
		gamesTable = new GUI_DefaultTable(data,gamesColumnNames);
		gamesTable.setBounds(new Rectangle(370, 225, 550, 441));
		gamesTable.setVisible(true);
	
		gameType = "Game Type";
		
		this.add(gamesTable,null);
	}
	

	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	protected JButton getLogout() {
			logOut = new JButton();
			logOut.setText("Log Out");
			logOut.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
			logOut.setBounds(new Rectangle(43, 701, 146, 34));
			logOut.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					
					ms.myData.setEnvironment(EnvStatus.LOGGED_OUT);
						
					MessageLogout msgLog = new MessageLogout(ms.lg.getCurrentUsername());
					
					try {
						//Sending the login message to server
						ms.client.sendToServer(msgLog);			
						

					} catch (IOException e1) {

						e1.printStackTrace();
					} 
					
					//Getting reply to login from client
					MessageLogoutReply msglogrep = (MessageLogoutReply) ms.client.GetMessage();
					
					if(msglogrep.getLogoutStatus()==LogoutStatus.LOGGED_OUT)
					{
						//ms.lg=new GUI_Login(ms);
						//ms.setContentPane(ms.lg);
						JOptionPane.showMessageDialog(null,"Thank you for using GameStation ©");
						System.exit(ABORT);
						
					}
					
					else if(msglogrep.getLogoutStatus()==LogoutStatus.LOGOUT_FAIL)
					{
						JOptionPane.showMessageDialog(null,msglogrep.GetErrorMessage(1));
					}
				}
			});
		
		return logOut;
	}
	

	/**
	 * This method initializes gameHistory	
	 * 	
	 * @return javax.swing.JButton	
	 */
	protected JButton getGameHistory() {
		if (gameHistory == null) {
			gameHistory = new JButton();
			gameHistory.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 17));
			gameHistory.setBounds(new Rectangle(210, 120, 152, 34));
			gameHistory.setText("Game History");
			gameHistory.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					
					MessageShowHistory msgSH = new MessageShowHistory(ms.myData);
					
					try {
						ms.client.sendToServer(msgSH);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
					MessageShowHistoryReply msgSHR = (MessageShowHistoryReply) ms.client.GetMessage();
					
					ms.myData.setEnvironment(EnvStatus.IN_GAME);
					
					ms.gameHistory = new GUI_GameHistory(ms,msgSHR.getRoomsHistory());
					ms.setContentPane(ms.gameHistory);
				}
			});
		
		}
		return gameHistory;
	}	


	/**
	 * This method initializes about	
	 * 	
	 * @return javax.swing.JButton	
	 */
	protected JButton getAbout() {
		if (about == null) {
			about = new JButton();
			about.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 15));
			about.setBounds(new Rectangle(896, 732, 82, 26));
			about.setText("About");
			about.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					
					JOptionPane.showMessageDialog(null, "GameStation\n All rights reserved ©");
				}
			});
		
		}
		return about;
	}


	/**
	 * This method initializes buttonOpenGame	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getButtonOpenGame() {
		if (buttonOpenGame == null) {
			buttonOpenGame = new JButton();
			buttonOpenGame.setBounds(new Rectangle(371, 701, 152, 34));
			buttonOpenGame.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
			buttonOpenGame.setText("Open Game");
			buttonOpenGame.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					
						if(!gameType.equals("Game Type")) {

							Room myRoom = new Room();
							
							Player myPlayer = ms.myData.getPlayer();
	
							myRoom.setHostName(myPlayer.getUsername());

							myRoom.setGameName(gameType);
							
							myRoom.setStatus("Waiting");
							
							myRoom.setHostIP(ms.myData.getPlayer().getUserIP());
												
							ms.roomPlat = new RoomPlatform(myRoom,Role.HOST);
							
							int roomID = ms.roomPlat.getRoomID();
							
							RoomStatus serverStatus = ms.roomPlat.getServerStatus();
							
							switch(serverStatus)
							{
								case ROOM_OPEN:
								{
									ms.myData.setEnvironment(EnvStatus.IN_GAME);
									
									if(gameType.equals("Trivia"))
									{
										int row = playersTable.table.getSelectedRow();
										
										//If a player was selected to start a game with
										if(row!=-1)
										{
											//Retrieving player's username
											String playerUN = playersTable.table.getValueAt(row, 0).toString();
											
											//Retrieving player's status
											String playerStatus = playersTable.table.getValueAt(row, 2).toString();
											
											//Retrieving my username
											String myUsername = ms.myData.getPlayer().getUsername();
											
											if(playerStatus.equals("playing") || playerStatus.equals("viewing")) 
												JOptionPane.showMessageDialog(null, "Selected player is currently unavailable for play");
											else if(myUsername.equals(playerUN)) 
											{
												JOptionPane.showMessageDialog(null, "Cannot invite yourself to a game, silly");												
											}
											else
											{
												
												
												String myIP = ms.myData.getPlayer().getUserIP();
																								
												MessageInvitePlayer msgIP = new MessageInvitePlayer(myUsername,playerUN,myIP,gameType,roomID);
												
												try {
													ms.client.sendToServer(msgIP);
												} catch (IOException e1) {
													
													e1.printStackTrace();
												}
												
												if(gameType.equals("Trivia"))
												{
													ms.gameTrivia = new GUI_GameTrivia(Role.HOST);
													ms.roomPlat.gamePlat = (GUI_GamePlatform) ms.gameTrivia;
													ms.setContentPane(ms.gameTrivia);
												}
												
											
											
											}
											
										
											
										}
										
										//Opening room without inviting a player to play
										else 
										{
											ms.gameTrivia = new GUI_GameTrivia(Role.HOST);
											ms.roomPlat.gamePlat = (GUI_GamePlatform) ms.gameTrivia;
											ms.setContentPane(ms.gameTrivia);
										}
											
									
									
									}
									
									if(gameType.equals("3Boom"))
									{
										int row = playersTable.table.getSelectedRow();
										
										//If a player was selected to start a game with
										if(row!=-1)
										{
											//Retrieving player's username
											String playerUN = playersTable.table.getValueAt(row, 0).toString();
											
											//Retrieving player's status
											String playerStatus = playersTable.table.getValueAt(row, 2).toString();
											
											//Retrieving my username
											String myUsername = ms.myData.getPlayer().getUsername();
											
											if(playerStatus.equals("playing") || playerStatus.equals("viewing")) 
												JOptionPane.showMessageDialog(null, "Selected player is currently unavailable for play");
											else if(myUsername.equals(playerUN)) 
											{
												JOptionPane.showMessageDialog(null, "Cannot invite yourself to a game, silly");												
											}
											else
											{
												
												
												String myIP = ms.myData.getPlayer().getUserIP();
																								
												MessageInvitePlayer msgIP = new MessageInvitePlayer(myUsername,playerUN,myIP,gameType,roomID);
												
												try {
													ms.client.sendToServer(msgIP);
												} catch (IOException e1) {
													
													e1.printStackTrace();
												}
												
												if(gameType.equals("3Boom"))
												{
													ms.game3Boom = new GUI_Game3Boom(Role.HOST);
													ms.roomPlat.gamePlat = (GUI_GamePlatform) ms.game3Boom;
													ms.setContentPane(ms.game3Boom);
												}
												
											
											
											}
											
										
											
										}
										
										//Opening room without inviting a player to play
										else 
										{
											ms.game3Boom = new GUI_Game3Boom(Role.HOST);
											ms.roomPlat.gamePlat = (GUI_GamePlatform) ms.game3Boom;
											ms.setContentPane(ms.game3Boom);
										}
											
									
									
									}

					
									
									/*if(gameType.equals("3Boom"))
									{
										
										ms.game3Boom = new GUI_Game3Boom(Role.HOST);
										ms.roomPlat.gamePlat = (GUI_GamePlatform) ms.game3Boom;
										ms.setContentPane(ms.game3Boom);
										
									}*/
									
								}
									break;
								
								case OPEN_FAILED:
								{
									JOptionPane.showMessageDialog(null,"Opening game failed! Check connection");
									
									
								}
								
									break;
							
							
							}
						
						}
						
						else {
							
								JOptionPane.showMessageDialog(null,"Please choose game type");
								comboBoxGameType.setForeground(Color.RED);
						}		
					}
				});
		}
		return buttonOpenGame;
	}


	/**
	 * This method initializes buttonJoinView	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getButtonJoinView() {
		if (buttonJoinView == null) {
			buttonJoinView = new JButton();
			buttonJoinView.setBounds(new Rectangle(540, 700, 146, 35));
			buttonJoinView.setText("Join / View");
			buttonJoinView.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));

			buttonJoinView.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					
					ms.myData.setEnvironment(EnvStatus.IN_GAME);
					ms.myData.getPlayer().setEnvironment(EnvStatus.IN_GAME);
					
					int row = gamesTable.table.getSelectedRow();
					
					if(row!=-1)
					{
						Room room = new Room();
						
						Integer room_id = (Integer) gamesTable.table.getValueAt(row, 0);
						
						String status = gamesTable.table.getValueAt(row, 1).toString(); 
						
						String hostPlayer = gamesTable.table.getValueAt(row, 2).toString();
						
						String guestPlayer = gamesTable.table.getValueAt(row, 3).toString();
						
						String gameName = gamesTable.table.getValueAt(row, 4).toString();
						
						String hostIP = gamesTable.table.getValueAt(row, 5).toString();
						
						System.out.println("Joining to: "+room_id+" "+" "+status+" "+hostPlayer+" "+gameName+" "+hostIP);
						
						
						
						room.setHostIP(hostIP);
						
						room.setGameName(gameName);
						
						if(guestPlayer!="")
							room.setGuestName(guestPlayer);
						
						room.setHostName(hostPlayer);
						
						room.setStatus(status);
						
						room.setRoomID(room_id);
						
						//check if room is in playing status
					    if(room.getStatus().equals("Playing"))  
					         ms.roomPlat = new RoomPlatform(room,Role.SPECTATOR);
					    else
					         ms.roomPlat = new RoomPlatform(room,Role.CLIENT);
						
						RoomStatus serverStatus = ms.roomPlat.getServerStatus();
						
						RoomStatus hostStatus = ms.roomPlat.getHostStatus();
						
						if(serverStatus.equals(RoomStatus.ROOM_JOINED)&& hostStatus.equals(RoomStatus.ROOM_JOINED))
						{
						
									if(gameName.equals("Trivia"))
									{
										
										if(room.getStatus().equals("Playing"))
								        {
								           ms.gameTrivia = new GUI_GameTrivia(Role.SPECTATOR);
								           ms.roomPlat.gamePlat = (GUI_GamePlatform) ms.gameTrivia;
								           ms.gameTrivia.startGameSpec(room.getHostName(),room.getGuestName());
								        }
										else
										{
										   room.setStatus("Playing");	
										   ms.gameTrivia = new GUI_GameTrivia(Role.CLIENT);
										   ms.roomPlat.gamePlat = (GUI_GamePlatform) ms.gameTrivia;
										   ms.gameTrivia.startGameGuest(room.getHostName());	
										}
	
										ms.setContentPane(ms.gameTrivia);
									}
									
									
									else if(gameName.equals("3Boom"))
									{
										
										if(room.getStatus().equals("Playing"))
										{
											ms.game3Boom = new GUI_Game3Boom(Role.SPECTATOR);
									        ms.roomPlat.gamePlat = (GUI_GamePlatform) ms.game3Boom;
									        ms.game3Boom.startGameSpec(room.getHostName(),room.getGuestName());
											
										}
										
									    else
									    {
									    	room.setStatus("Playing");	
									    	ms.game3Boom = new GUI_Game3Boom(Role.CLIENT);
									        ms.roomPlat.gamePlat = (GUI_GamePlatform) ms.game3Boom;
									        ms.game3Boom.startGameGuest(room.getHostName());
									    }

											ms.setContentPane(ms.game3Boom);
										
										
									}
	
									//Add more games here
						}	
						else {
								ms.myData.setEnvironment(EnvStatus.LOBBY);
								ms.myData.getPlayer().setEnvironment(EnvStatus.LOBBY);
								
							
								switch(serverStatus)
								{
									case JOIN_FAILED:
									{
										JOptionPane.showMessageDialog(null,"Joining game failed! Unable to connect to server!");

									}
										break;
								
								}
								
								switch(hostStatus)
								{
									case JOIN_FAILED:
									{
										JOptionPane.showMessageDialog(null,"Joining game failed! Unable to connect to host player!");
	
									}
										break;
								
								
								}
										

							}
						

					

						
					}
					
					
					else { JOptionPane.showMessageDialog(null, "Please select a game to join"); }
				}
			});

			/*
			buttonJoinView.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					

					GamePlatform gameplat=new GamePlatform(ms,false);
					ms.setContentPane(gameplat);
				}
			});
			 */
		}
		
		return buttonJoinView;
	}


	/**
	 * This method initializes GameType	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JComboBox getGameType() {
		if (comboBoxGameType == null) {
			comboBoxGameType = new JComboBox(gamesTypes);
			comboBoxGameType.setBounds(new Rectangle(703, 700, 160, 35));
			comboBoxGameType.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
			comboBoxGameType.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					
					gameType = comboBoxGameType.getSelectedItem().toString();

				}
			});
		}
		
		return comboBoxGameType;
	}
	
	public void getInvite(String inviterUN, String inviterIP, String offeredgameName, int roomID)
	{
		if(!inviterUN.equals(ms.lg.getCurrentUsername()))	
		{
		

			Room room = new Room();
			
			room.setRoomID(roomID);
			
			room.setHostIP(inviterIP);
			
			room.setGuestName(ms.myData.getPlayer().getUsername());
			
			room.setHostName(inviterUN);
			
			room.setStatus("Playing");
			
			room.setInvite(true);
			
			int answer = JOptionPane.showConfirmDialog(null, inviterUN+" invites you to play\nDo you confirm?", "Invitation to play", JOptionPane.YES_NO_OPTION);
					
			//0 is yes for some reason 
			if(answer==0)
			{
				
				//gamesTable.table.setRowSelectionInterval(0, 0);
				
				//buttonJoinView.doClick();


	
				ms.myData.setEnvironment(EnvStatus.IN_GAME);
				ms.myData.getPlayer().setEnvironment(EnvStatus.IN_GAME);
				
				ms.roomPlat = new RoomPlatform(room,Role.CLIENT);
	
				if(offeredgameName.equals("Trivia"))
				{
		
					ms.gameTrivia = new GUI_GameTrivia(Role.CLIENT);
					ms.roomPlat.gamePlat = (GUI_GamePlatform) ms.gameTrivia;
					ms.gameTrivia.startGameGuest(room.getHostName());
					
					ms.setContentPane(ms.gameTrivia);
				}
				if(offeredgameName.equals("3Boom"))
				{
		
					ms.game3Boom = new GUI_Game3Boom(Role.CLIENT);
					ms.roomPlat.gamePlat = (GUI_GamePlatform) ms.game3Boom;
					ms.game3Boom.startGameGuest(room.getHostName());
					
					ms.setContentPane(ms.game3Boom);
				}
	
					
			}
			else
			{
				MessageInvitationDenied msgID = new MessageInvitationDenied(room);
				
				try {
					ms.client.sendToServer(msgID);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		
			
		}
	}



	



} 
