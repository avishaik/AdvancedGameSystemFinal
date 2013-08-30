package GUI;



import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


import DataObjects.LobbyData;
import DataObjects.MyData;
import DataObjects.Player;
import Messages.EnvStatus;
import Messages.MessageGetRank;
import Messages.MessageGetRankReply;
import Messages.MessageLogin;
import Messages.MessageLoginReply;
import Messages.MessageRefreshLobby;
import Messages.MessageRefreshLobby;
import Messages.MessageLoginReply.ErrMsg;
import Messages.MessageLoginReply.LoginStatus;



/**
 * The Class GUI_Login.
 */
public class GUI_Login extends GUI_DefaultPanel {

	/** The curr username. */
	private String currUsername;
	
	/** The curr password. */
	private String currPassword;
	
	/** The empty u nflag. */
	boolean emptyUNflag=false;
	
	/** The empty p wflag. */
	boolean emptyPWflag=false;
	
	/** The background. */
	private GUI_ImagePanel background;

	/** The Close. */
	public JButton Close = null;
	
	/** The Login. */
	private JButton Login = null;
	
	/** The Username. */
	private JTextField Username = null;
	
	/** The User. */
	private JLabel User = null;
	
	/** The Pass. */
	private JLabel Pass = null;
	
	/** The Exit. */
	private JButton Exit = null;
	
	/** The j password field. */
	private JPasswordField jPasswordField = null;
	
	/** The j label. */
	private JLabel jLabel = null;
	
	/** The ms. */
	private GUI_MainScreen ms;
	
	/** The check. */
	public int check=0;
	
	/**
	 * This is the default constructor.
	 *
	 * @param ms the ms
	 */
	public GUI_Login(GUI_MainScreen ms) {
		super();
		this.ms = ms;
		initialize();
	}

	/**
	 * This method initializes the Login screen.
	 */
	private void initialize() {
		
		jLabel = new JLabel();
		jLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 40));
		jLabel.setBounds(new Rectangle(430, 173, 495, 57));
		jLabel.setForeground(Color.LIGHT_GRAY);
		jLabel.setText("Login");

		Pass = new JLabel();
		Pass.setSize(new Dimension(141, 31));
		Pass.setLocation(new Point(306, 318));
		Pass.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 25));
		Pass.setForeground(Color.LIGHT_GRAY);
		Pass.setText("Password:");
		
		User = new JLabel();
		User.setSize(new Dimension(136, 37));
		User.setLocation(new Point(309, 258));
		User.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 25));
		User.setForeground(Color.LIGHT_GRAY);
		User.setText("Username:");
		

		this.add(getLogin(), null);
		this.add(getUsername(), null);
		this.add(User, null);
		this.add(Pass, null);
		this.add(getExit(), null);
		this.add(getJPasswordField(), null);
		this.add(jLabel, null);
		background = new GUI_ImagePanel(new ImageIcon("images/background.jpg").getImage());
		this.add(background);
	

	}
	
	/**
	 * This method initializes Username text field.
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getUsername() {
		if (Username == null) {
			Username = new JTextField();
			Username.setPreferredSize(new Dimension(5, 25));
			Username.setSize(new Dimension(200, 38));
			Username.setFont(new Font("Arial", Font.BOLD, 25));
			Username.setLocation(new Point(465, 257));
		}
		return Username;
	}

	

	/**
	 * This method initializes Login	
	 * 	sends MessageLogin and receives MessageLoginReply messages .
	 * @return javax.swing.JButton	
	 */
	private JButton getLogin() {
		if (Login == null) {
			
			//Set Graphics
			Login = new JButton();
			Login.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
			Login.setBounds(new Rectangle(515, 482, 146, 34));
			Login.setText("Login");
			
			//Button "Login" pressed
			Login.addActionListener(new java.awt.event.ActionListener() {
				
				public void actionPerformed(java.awt.event.ActionEvent e) {
					
					
					//Creating new login message and inserting login information to it
					currUsername = Username.getText();
					currPassword = jPasswordField.getText();
					emptyUNflag=false;
					
					if(currUsername.equals(""))
					{
						JOptionPane.showMessageDialog(null, "Please insert username", "NOTICE", JOptionPane.INFORMATION_MESSAGE);
						User.setForeground(Color.YELLOW);
						emptyUNflag=true;
					}
					
					else { User.setForeground(Color.WHITE); }
					
					emptyPWflag=false;
					
					if(currPassword.equals(""))
					{
						
						JOptionPane.showMessageDialog(null, "Please insert password", "NOTICE", JOptionPane.INFORMATION_MESSAGE);
						Pass.setForeground(Color.YELLOW);
						emptyPWflag=true;
					}
					
					else { Pass.setForeground(Color.WHITE); }
				
					
					if(!emptyPWflag && !emptyUNflag) 
					{
					
						MessageLogin msgLog=new MessageLogin(Username.getText(),jPasswordField.getText());
									
						try {
							//Sending the login message to server
							ms.client.sendToServer(msgLog);			
							
	
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} 
						
						//Getting reply to login from client
						MessageLoginReply msglogrep = (MessageLoginReply) ms.client.GetMessage();
					
	                    
	                    //Login successful scenarios
	                    if(msglogrep.getLoginStatus()==LoginStatus.LOGGED_IN) 
	                    {
	                    	
           	
	                    	
	                    
	                    	Player player=new Player();
	                    	
	                    	player.setUsername(currUsername);
	                    	player.setStatus("in_lobby");
	                    	player.setUserIP(msglogrep.getUserIP());
	                		
	                    	
	                    	
	                    	//ms.myPlayer.setUsername(currUsername);
	                    	//ms.myPlayer.setStatus("in_lobby");
	                    	ms.myData.setPlayer(player);
	                    	MessageGetRank msgGR = new MessageGetRank(ms.myData);
	                    	
	                    	try {
								ms.client.sendToServer(msgGR);
							} catch (IOException e2) {
						
								e2.printStackTrace();
							}
							
							MessageGetRankReply msgGRrep = (MessageGetRankReply) ms.client.GetMessage();
							
							player.setRank(msgGRrep.getRank());
							
							//setting the player's data in myData, user considered to be in lobby
							ms.myData.setPlayer(player);
							ms.myData.setEnvironment(EnvStatus.LOBBY);
							ms.lobby = new GUI_Lobby(ms);
							
							//ms.myPlayer.setRank(msgGRrep.getRank());
	                    	
	                  /*  	ArrayList<LobbyData> data = new ArrayList<LobbyData>();
	                    	
	                    	data.add(ms.myPlayer);
	                    
	                    	ms.status=EnvStatus.LOBBY;*/
	                    	
	                    	              	

	                   } //end if logged in
	                    
	                    //Login failed scenarios
	                  if(msglogrep.getLoginStatus()==LoginStatus.LOGIN_FAIL)
	                  {
	                   	if(msglogrep.getError()==ErrMsg.ALREADY_LOGIN)
	                   		JOptionPane.showMessageDialog(null,msglogrep.GetErrorMessage(2));
	                   	else if(msglogrep.getError()==ErrMsg.INCORRECT)
	                   		JOptionPane.showMessageDialog(null,msglogrep.GetErrorMessage(1));
	                   	else if(msglogrep.getError()==ErrMsg.FROZEN)
	                   		JOptionPane.showMessageDialog(null,msglogrep.GetErrorMessage(4));
	                   	else
	                   		JOptionPane.showMessageDialog(null,msglogrep.GetErrorMessage(3));
	                  }
          
					}
				}
			});
		}
		return Login;
	}

	/**
	 * This method initializes Exit.
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getExit() {
		if (Exit == null) {
			Exit = new JButton();
			Exit.setText("Exit");
			Exit.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
			Exit.setBounds(new Rectangle(305, 482, 146, 34));
			Exit.setActionCommand("Exit");
			Exit.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					JOptionPane.showMessageDialog(null,"Thank you for using GameStation ©");
					System.exit(ABORT);
				}
			});
		}
		return Exit;
	}

	/**
	 * This method initializes Password.
	 *
	 * @return javax.swing.JPasswordField
	 */
	private JPasswordField getJPasswordField() {
		if (jPasswordField == null) {
			jPasswordField = new JPasswordField();
			jPasswordField.setLocation(new Point(465, 315));
			jPasswordField.setFont(new Font("Arial", Font.BOLD, 14));
			jPasswordField.setSize(new Dimension(200, 35));
		}
		return jPasswordField;
	}
	
	/**
	 * Gets the current username.
	 *
	 * @return the current username
	 */
	public String getCurrentUsername(){
		return currUsername;
	}
	
}  
