package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import DataObjects.Player;
import DataObjects.Room;
import Messages.EnvStatus;
import Messages.MessageInviteRefresh;

import javax.swing.JButton;

public class GUI_GameHistory extends GUI_DefaultPanel {

	public GUI_MainScreen ms;
	
	private GUI_ImagePanel background;
	private GUI_DefaultTable detailsTable;
	private String[] columnNames = {"Game", "Rival", "Date & Time"};
	private JLabel detailsLabel;
	private ArrayList<Room> roomsHistory;
	private JButton backBtn = null;

	private JLabel rankLbl = null;

	private JLabel rankTitleLbl = null;

	public GUI_GameHistory(GUI_MainScreen ms, ArrayList<Room> roomsHistory) {
		
		super();
		this.ms = ms;
		this.roomsHistory = roomsHistory;
		initialize();
		
		
	}
	
	
	protected void initHistoryTable() {
		
		int length = roomsHistory.size();
		Object[][] data = new Object[length][3];
		int i=0;
		
		for(Room room: roomsHistory)
		{
			data[i][0] = room.getGameName();
			data[i][1] = room.getGuestName();
			data[i][2] = room.getTimeStamp();
			
			i++;
			
		}
		
		detailsTable = new GUI_DefaultTable(data, columnNames);
		detailsTable.setBounds(230, 225, 550, 441);

		detailsTable.setVisible(true);
		
		this.add(detailsTable);
		
	}
	
	public void initialize() {
		
		rankTitleLbl = new JLabel();
		rankTitleLbl.setBounds(new Rectangle(55, 150, 97, 23));
		rankTitleLbl.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
		rankTitleLbl.setText("My Rank:");
		rankTitleLbl.setForeground(Color.LIGHT_GRAY);
		
		rankLbl = new JLabel();
		rankLbl.setBounds(new Rectangle(150, 152, 105, 16));
		rankLbl.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
		rankLbl.setText(Integer.toString(ms.myData.getPlayer().getRank()));
		rankLbl.setForeground(Color.LIGHT_GRAY);
		
		background = new GUI_ImagePanel(new ImageIcon("images/background.jpg").getImage());
		background.setSize(new Dimension(1000, 800));
	
		Object[][] sampleData = new Object[10][3];
		
		detailsTable = new GUI_DefaultTable(sampleData,columnNames);
		
		detailsTable.setBounds(new Rectangle(230, 225, 550, 441));
		detailsTable.setVisible(true);
		
		detailsLabel = new JLabel();
		detailsLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 50));
		detailsLabel.setBounds(new Rectangle(320, 55, 500, 54));
		detailsLabel.setText("Game History");
		detailsLabel.setForeground(Color.LIGHT_GRAY);

		
		if(roomsHistory!=null)
			initHistoryTable();
		
		
		this.setLayout(null);
		this.add(detailsTable);
		this.add(detailsLabel);
		this.add(getBackBtn());
		this.add(rankLbl);
		this.add(rankTitleLbl);
		this.add(background);
	}

	/**
	 * This method initializes backBtn	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBackBtn() {
		if (backBtn == null) {
			backBtn = new JButton();
			backBtn.setBounds(new Rectangle(36, 710, 100, 34));
			backBtn.setText("Back");
			backBtn.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
			backBtn.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {

					ms.myData.getPlayer().setEnvironment(EnvStatus.LOBBY);
					ms.myData.setEnvironment(EnvStatus.LOBBY);
					
					MessageInviteRefresh msgIR = new MessageInviteRefresh();
					
					msgIR.setMyData(ms.myData);
					
					try {
						ms.client.sendToServer(msgIR);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
					
				}
			});
		}
		return backBtn;
	}
	
	
	/*public static void main(String[] args) {
		
		JFrame frame = new JFrame();
		GUI_GameHistory detailsTest = new GUI_GameHistory();
		frame.add(detailsTest);
		frame.setPreferredSize(new Dimension(1000, 800));
		frame.setResizable(false);
		frame.setVisible(true);
		frame.pack();
		
		
	}*/
	
	
}
