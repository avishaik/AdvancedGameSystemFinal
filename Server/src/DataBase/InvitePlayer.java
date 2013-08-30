package DataBase;

import java.io.IOException;

import ocsf.server.ConnectionToClient;
import Messages.MessageInvitePlayer;
import Server.EchoServer;

// TODO: Auto-generated Javadoc
/**
 * The Class InvitePlayer. NOT IN USE
 */
public class InvitePlayer {

	/** The server. */
	private EchoServer server;
	
	/**
	 * Instantiates a new invite player.
	 *
	 * @param server the server
	 */
	public InvitePlayer(EchoServer server) {
		
		this.server=server;
	}
	
	/**
	 * Invite player.
	 *
	 * @param rawPlayerIP the raw player ip
	 * @param msgIP the msg ip
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void invitePlayer(String rawPlayerIP, MessageInvitePlayer msgIP) throws IOException {
		
		Thread[] thrArr = server.getClientConnections();
		
		for(int i=0;i<thrArr.length;i++)
		{
			ConnectionToClient ctc = (ConnectionToClient) thrArr[i];
			
			String rawInetAddress = ctc.getInetAddress().toString();
			
			String[] convertInetAddress = rawInetAddress.split("/");
			
			String ip = convertInetAddress[1];
			
			convertInetAddress = rawPlayerIP.split("/");
			
			String playerIP = convertInetAddress[1];
			
			if(ip.equals(playerIP))
			{
				ctc.sendToClient(msgIP);
			}
			
		}
		
	}

}

