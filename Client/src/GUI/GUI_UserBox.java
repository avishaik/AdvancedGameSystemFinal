package GUI;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Rectangle;

/**
 * The Class GUI_UserBox.
 */
public class GUI_UserBox extends JPanel {
	
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The label username. */
	private JLabel labelUsername = null;
	
	/** The label status. */
	private JLabel labelStatus = null;
	
	/** The label score. */
	private JLabel labelScore = null;
	
	/** The status label. */
	private String statusLabel="";
	
	/** The score label. */
	private Integer scoreLabel=0;
	
	/** The username label. */
	private String usernameLabel="";
	
	
	/**
	 * Instantiates a new gU i_ user box.
	 */
	public GUI_UserBox() {
		
		initialize();
	}
	
	/**
	 * Gets the status label.
	 *
	 * @return the status label
	 */
	public String getStatusLabel() {
		return statusLabel;
	}

	/**
	 * Sets the status label.
	 *
	 * @param status the new status label
	 */
	public void setStatusLabel(String status) {
		this.statusLabel = status;
		labelStatus.setText(status);
	}

	/**
	 * Gets the score label.
	 *
	 * @return the score label
	 */
	public Integer getScoreLabel() {
		return scoreLabel;
	}

	/**
	 * Sets the score label.
	 *
	 * @param score the new score label
	 */
	public void setScoreLabel(Integer score) {
		this.scoreLabel = score;
		labelScore.setText(score.toString());
	}

	/**
	 * Gets the username label.
	 *
	 * @return the username label
	 */
	public String getUsernameLabel() {
		return usernameLabel;
	}

	/**
	 * Sets the username label.
	 *
	 * @param usernameLabel the new username label
	 */
	public void setUsernameLabel(String usernameLabel) {
		this.usernameLabel = usernameLabel;
		labelUsername.setText(usernameLabel);
	}



	/**
	 * Initialize.
	 */
	void initialize() {
		
		labelScore = new JLabel();
		labelScore.setBounds(new Rectangle(18, 35, 38, 17));

		
		labelStatus = new JLabel();
		labelStatus.setBounds(new Rectangle(44, 35, 38, 17));
		
		labelUsername = new JLabel();
		labelUsername.setBounds(new Rectangle(20, 7, 70, 17));
	
		add(labelUsername, null);
		add(labelScore, null);
		add(labelStatus, null);

	
		setSize(100, 56);
		setLayout(new BorderLayout());
		setVisible(true);

		
		
	}
	
	/**
	 * Inc score.
	 */
	void incScore() {
		
		scoreLabel++;
		labelScore.setText(scoreLabel.toString());
	}
}