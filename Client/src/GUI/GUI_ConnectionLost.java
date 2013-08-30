package GUI;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;


/**
 * The Class GUI_ConnectionLost.
 */
public class GUI_ConnectionLost extends GUI_DefaultPanel {

	/** The background. */
	GUI_ImagePanel background;
	
	/** The ms. */
	GUI_MainScreen ms;
	
	/**
	 * Instantiates a new gU i_ connection lost.
	 */
	public GUI_ConnectionLost() {
		
		ms = GUI_MainScreen.getInstance();
		
		background = new GUI_ImagePanel(new ImageIcon("images\background.jpg").getImage());
		this.add(background);
		JOptionPane.showMessageDialog(null, "Connection to server lost!\n We apologize for the inconvenience");
		
		ms.lobby.logOut.doClick();
	}
}
