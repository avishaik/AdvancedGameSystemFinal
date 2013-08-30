package GUI;



import games.GUI_Game3Boom;
import games.GUI_GamePlatform;
import games.GUI_GameTrivia;
import games.RoomPlatform;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import DataObjects.MyData;
import DataObjects.Player;
import Messages.EnvStatus;

import client.ChatClient;


/**
 * The Class GUI_MainScreen.
 */
public class GUI_MainScreen extends JFrame {

	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	//Singleton instance
	/** The ms instance. */
	static GUI_MainScreen msInstance;
	
	//GUI attributes
	/** The lg. */
	public GUI_Login lg;
	
	/** The lobby. */
	public GUI_Lobby lobby;
	
	/** The Game History */
	public GUI_GameHistory gameHistory;
	
	
	/** The room plat. */
	public RoomPlatform roomPlat;
	
	/** The game trivia. */
	public GUI_GameTrivia gameTrivia;
	
	/** The game3 boom. */
	public GUI_Game3Boom game3Boom;
	
	/** The my data. */
	public MyData myData;

	

	
	/** The background. */
	private GUI_ImagePanel background;
	
	/** The Port text field. */
	private JTextField PortTextField;
	
	/** The I ptextfield. */
	private JTextField IPtextfield;
	
	/** The First panel. */
	private JPanel FirstPanel = null;
	
	/** The Exit. */
	private JButton Exit = null;
	
	/** The Connectbt. */
	private JButton Connectbt = null;
	
	/** The Port. */
	private JLabel Port = null;
	
	/** The IP. */
	private JLabel IP = null;
	
	/** The j label. */
	private JLabel jLabel = null;
	
	
	//Client-Server attributes
	/** The client ip. */
	public String clientIP="localhost";
	
	/** The client port. */
	public int clientPort=5555;
	
	/** The client. */
	public ChatClient client;

	
	/**
	 * This method initializes Connectbt button
	 * which connects the client to server.
	 *
	 * @return the connectbt
	 */
	private JButton getConnectbt() {
		if (Connectbt == null) {
			Connectbt = new JButton();
			Connectbt.setText("Connect");
			Connectbt.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
			Connectbt.setBounds(new Rectangle(515, 482, 146, 34));
			Connectbt.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					
					try {	
						
						connect();
			
						
					} catch (IOException e1) {
						
						JOptionPane.showMessageDialog(FirstPanel, "Unable to connect! \n Please check connection", "ERROR", JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					} catch (Exception e2) {
						
						JOptionPane.showMessageDialog(FirstPanel, "Unable to connect! \n Please insert legal server IP and PORT format", "ERROR", JOptionPane.ERROR_MESSAGE);

						e2.printStackTrace();
					}
					
					
					setContentPane(lg);
							
				}
			});
		}
		return Connectbt;
	}
	
	
	/**
	 * Connect.
	 *
	 * @throws Exception the exception
	 */
	public void connect() throws Exception {
		
		if(IPtextfield.getText().equals("") || PortTextField.equals(""))
		{	
			Exception e = new Exception();
			throw(e);
		}
			
			clientIP=IPtextfield.getText();
			clientPort=Integer.parseInt(PortTextField.getText());
			
		
		
		
			client = new ChatClient(clientIP,clientPort);
			activateLogin();
	}

	
	/**
	 * This constructor ensures protected instantiation.
	 */
	protected GUI_MainScreen() {
		
		super();
		initialize();
		
	}

	/**
	 * This method initializes the Main screen . 
	 */
	private void initialize() {
		
	
		this.setSize(1000, 800);
		this.setContentPane(getFirstPanel());
		this.setResizable(true);
		this.setVisible(true);
		this.setTitle("GameStation");
		
		myData = new MyData();
	
	}
	
	/**
	 * Gets the single instance of GUI_MainScreen.
	 *
	 * @return single instance of GUI_MainScreen
	 */
	public static GUI_MainScreen getInstance() {
		
		if(msInstance==null)
		{
			msInstance = new GUI_MainScreen();
		}
		return msInstance;
	}

	/**
	 * This method sends the client details.
	 */
	public void activateLogin()
	{
		lg=new GUI_Login(this);
	}
	
	
	/**
	 * This method initializes FirstPanel.
	 *
	 * @return the first panel
	 */
	public JPanel getFirstPanel() {
		if (FirstPanel == null) {
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(266, 173, 495, 57));
			jLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 35));
			jLabel.setForeground(Color.LIGHT_GRAY);
			jLabel.setText("Welcome To GameStation");
			
			IP = new JLabel();
			IP.setSize(new Dimension(141, 31));
			IP.setLocation(new Point(306, 318));
			IP.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 25));
			
			IP.setForeground(Color.LIGHT_GRAY);
			IP.setText("IP Address:");
			
			Port = new JLabel();
			Port.setSize(new Dimension(136, 37));
			Port.setLocation(new Point(309, 258));
			Port.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 25));
			Port.setForeground(Color.LIGHT_GRAY);
			Port.setText("Port:");
			
			FirstPanel = new JPanel();
			FirstPanel.setLayout(null);
			FirstPanel.add(getConnectbt(), null);
			FirstPanel.add(getPortTextField(), null);
			FirstPanel.add(getIPtextfield(), null);
			FirstPanel.add(Port, null);
			FirstPanel.add(IP, null);
			FirstPanel.add(jLabel, null);
			FirstPanel.add(getExit());
		
			
			this.background = new GUI_ImagePanel(new ImageIcon("images/background.jpg").getImage());
			FirstPanel.add(background,null);
		}
		return FirstPanel; 
	}
	


	/**
	 * This method initializes PortTextField.
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getPortTextField() {
		if (PortTextField == null) {
			PortTextField = new JTextField();
			PortTextField.setVisible(true);
			PortTextField.setText("5555");
			PortTextField.setFont(new Font("Arial", Font.BOLD, 25));
			PortTextField.setSize(new Dimension(200, 38));
			PortTextField.setLocation(new Point(465, 257));
		}
		return PortTextField;
	}


	/**
	 * This method initializes IPtextfield.
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getIPtextfield() {
		if (IPtextfield == null) {
			IPtextfield = new JTextField();
			IPtextfield.setVisible(true);
			IPtextfield.setText("localhost");
			IPtextfield.setFont(new Font("Arial", Font.BOLD, 25));
			IPtextfield.setSize(new Dimension(200, 35));
			IPtextfield.setLocation(new Point(465, 315));
		}
		return IPtextfield;
	}
	
	
	/**
	 * This method initializes Get client data
	 * which shows the currently connected clients to 
	 * the server. 
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public void getclientData()
	{
		this.clientIP=IPtextfield.getText();
		this.clientPort=Integer.parseInt(PortTextField.getText());
	}
	
	/**
	 * Gets the exit.
	 *
	 * @return the exit
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
					JOptionPane.showMessageDialog(null,"Thanks For Using GameStation ©");
					System.exit(ABORT);
				}
			});
		}
		return Exit;
	}
	
	

}  
