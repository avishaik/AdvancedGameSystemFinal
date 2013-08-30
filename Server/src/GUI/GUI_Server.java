package GUI;




import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.UIManager;


import java.awt.Rectangle;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import javax.swing.JList;

import de.javasoft.plaf.synthetica.SyntheticaBlackEyeLookAndFeel;

import DataBase.MarkOffline;
import DataBase.RefreshLobby;
import DataBase.SQLConnection;
import DataObjects.MyData;
import DataObjects.Player;
import DataObjects.Players;
import DataObjects.Room;
import DataObjects.Rooms;
import Messages.EnvStatus;
import Messages.MessageRefreshLobby;
import Server.Alive;
import Server.EchoServer;
import de.javasoft.plaf.synthetica.SyntheticaBlackEyeLookAndFeel;


public class GUI_Server extends JFrame {


	private static final long serialVersionUID = 1L;

	private JPanel FirstPanel = null;
	private EchoServer server=null; 
	
	

	private JButton closeBtn = null;

	private DefaultListModel clientConnectionModelList=null;

	private JLabel jLabel = null;

	private SQLConnection sc;
	
	private Rooms rooms;
	
	private Players players;  
	
	protected GUI_ImagePanel background;
	

	/**
	 * This is the default constructor
	 */
	public GUI_Server() {
		
		initialize();
		
	}

	/**
	 * This method initializes this
	 * This is actually the CORE of the server, here the server start listening to a connection and operates the 'Watch Dog'
	 * 
	 * @return void
	 */
	private void initialize() {
		
		background = new GUI_ImagePanel(new ImageIcon("images/background.jpg").getImage());
		background.setSize(new Dimension(800, 600));
		
		this.setSize(900, 700);
		this.setContentPane(getFirstPanel());
		this.setTitle("GameStation Server");
		this.add(background);
		this.sc = SQLConnection.SQLConnectionSingleton();	//create the SQLConnection object
		
		rooms = Rooms.getRoomsInstance();		//create the rooms data object that will contain all the online rooms
		players = Players.getPlayersInstance();  	//create the players data object that will contain all online players
		
		server=new EchoServer(5555);
		 try 
		    {
			 server.listen(); //Start listening for connections
			 Timer watchDogTimer = new Timer();
			 
			 //This is actually the 'Watch Dog' in operation. Every 2 seconds check if some user crashed
			 TimerTask ttask = new TimerTask() {

				@Override
				public void run() {
					
					Alive alive = new Alive(server);
					
					//Find the crashed players
					ArrayList<Player> deadPlayers = alive.deadPlayers();
					
					//Change the status of the crashed players in the DB.
					MarkOffline mf = new MarkOffline(deadPlayers);
					
					mf.mark_offline_players();
					
					//Some players died - need to refresh lobby
					if(deadPlayers!=null)
					{
						//The players were in rooms - need to close rooms
						if((players!=null)&&(rooms!=null))
						{
							alive.closeRooms();	
						}
						
						//Handling refresh lobby
						RefreshLobby refreshLobby = new RefreshLobby();
						MyData data = new MyData();
						refreshLobby.refresh(EnvStatus.EXIT_GAME, data);
						
						MessageRefreshLobby msgRefresh = new MessageRefreshLobby();

						ArrayList<Player> players = refreshLobby.getPlayers();
						
						ArrayList<Room> rooms = refreshLobby.getRooms();
						
						msgRefresh.setPlayers(refreshLobby.getPlayers());
						msgRefresh.setRooms(refreshLobby.getRooms());

						server.sendToAllClients(msgRefresh);
						
					}
					
					
					
					
					
				}
				 
			 };
			 watchDogTimer.scheduleAtFixedRate( ttask, 0, 2000);
		    }
		    catch (Exception ex) 
		    {
		      System.out.println("ERROR - Could not listen for clients!");
		    }
		
		
	}
	

/**
	 * gets the main panel
	 * 
	 * @return JPanel with the elements of the screen
	 */
	public JPanel getFirstPanel() {
		if (FirstPanel == null) {
			
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(214, 99, 396, 41));
			jLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 40));
			jLabel.setText("GameStation Server");
			jLabel.setForeground(Color.LIGHT_GRAY);
		
			FirstPanel = new JPanel();
			FirstPanel.setLayout(null);
			FirstPanel.add(getCloseBtn(), null);
			FirstPanel.add(jLabel, null);
			
			
		}
		return FirstPanel; 
	}



	/**
	 * This method initializes closeBtn	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getCloseBtn() {
		if (closeBtn == null) {
			closeBtn = new JButton();
			closeBtn.setText("Close Connection");
			closeBtn.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 18));
			closeBtn.setSize(new Dimension(200, 30));
			closeBtn.setLocation(new Point(323, 248));
			closeBtn.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					server.stopListening();
					try {
						server.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					JOptionPane.showMessageDialog(null,"Server is shutting down");
					System.exit(ABORT);
				
				}
			});
		}
		return closeBtn;
	}

	/**
	 * gets the clients connected to the server into a list model
	 * 
	 * @return DefaultListModel of clients connected to the server
	 */
	private DefaultListModel getConnectedClients()
	{
		clientConnectionModelList=new DefaultListModel();
		if(server.getNumberOfClients()==0)
		{
			clientConnectionModelList.addElement("No Connections at the moments");
		}
		else
		{
		Thread[] thrArr=null;
		clientConnectionModelList=new DefaultListModel();
		thrArr=server.getClientConnections();
		for(int i=0;i<server.getNumberOfClients();i++)
			clientConnectionModelList.addElement(thrArr[i]);
		}
			
		
		return clientConnectionModelList;
	}

	
	public static void main(String[] args) {
		
	    try 
	    {
	      UIManager.setLookAndFeel(new SyntheticaBlackEyeLookAndFeel());
	    } 
	    catch (Exception e) 
	    {
	      e.printStackTrace();
	    }
		
		GUI_Server server = new GUI_Server();
		
		server.setVisible(true);
		server.setSize(800,600);
		server.setResizable(false);
		server.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	


}  
