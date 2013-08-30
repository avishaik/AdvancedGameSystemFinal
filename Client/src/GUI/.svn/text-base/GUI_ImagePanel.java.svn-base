package GUI;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * The Class GUI_ImagePanel.
 */
public class GUI_ImagePanel extends JPanel {

	  /** The img. */
  	private Image img;

	  /**
  	 * Instantiates a new gU i_ image panel.
  	 *
  	 * @param img the img
  	 */
  	public GUI_ImagePanel(String img) {
	    this(new ImageIcon(img).getImage());
	  }

	  /**
  	 * Instantiates a new gU i_ image panel.
  	 *
  	 * @param img the img
  	 */
  	public GUI_ImagePanel(Image img) {
	    this.img = img;
	    Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
	    setPreferredSize(size);
	    setMinimumSize(size);
	    setMaximumSize(size);
	    setSize(size);
	    setLayout(null);
	  }

	  /* (non-Javadoc)
  	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
  	 */
  	public void paintComponent(Graphics g) {
	    g.drawImage(img, 0, 0, null);
	  }

	}