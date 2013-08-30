package client;

import javax.swing.UIManager;


import GUI.GUI_MainScreen;
import Messages.EnvStatus;
import de.javasoft.plaf.synthetica.SyntheticaBlackEyeLookAndFeel;

/**
 * The Class ClientInitialize.
 */
public class ClientInitialize {

/**
 * This is our main, this class launches our project.
 *
 * @param args the arguments
 */
	
	public static void main( String args[] )
	   {     
		
	    try 
	    {
	      UIManager.setLookAndFeel(new SyntheticaBlackEyeLookAndFeel());
	    } 
	    catch (Exception e) 
	    {
	      e.printStackTrace();
	    }
		
		
		  GUI_MainScreen MainFrame = GUI_MainScreen.getInstance();
		  MainFrame.setDefaultCloseOperation(MainFrame.DO_NOTHING_ON_CLOSE);
		  MainFrame.myData.setEnvironment(EnvStatus.LOGGED_OUT);
		  
	  } // end main
}
