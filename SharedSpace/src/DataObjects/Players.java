package DataObjects;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class Players implements Serializable {

	static Players PlayersInstance=null;
	private ArrayList<Player> online_players;
	
	protected Players()
	{
		online_players=new ArrayList<Player>();
	}
	
	public static Players getPlayersInstance()
	{
		if(PlayersInstance==null)
		{
			PlayersInstance=new Players();
		}
		return PlayersInstance;
	}
	
	public Player getPlayer(Integer player_id)
	{
		return online_players.get(player_id);
	}
	
	public Player getPlayerByUN(String username) {
		
		for(Player player: online_players)
		{
			String playerUN = player.getUsername();
			if(playerUN.equals(username))
				return player;
			
		}
		return null;
	}
	
	public Player getPlayerByIP(String ipNumber) {
		
		for(Player player: online_players)
		{
			String playerIP = player.getUserIP();
			if(playerIP.equals(ipNumber))
				return player;
			
		}
		return null;
	}
	
	public int getPlayerIndex(String username)
	{
		Player p = null;
		for(Player player: online_players)
		{
			if(player.getUsername().contains(username))
				p=player;
		}
		return online_players.indexOf(p);
	}
	
	public void removePlayerFromList(Player player)
	{

		online_players.remove(player);

	}
	
	public void removePlayerFromList(String username)
	{
		Player p = null;
		for(Player player: online_players)
		{
			if(player.getUsername().contains(username))
				p = player;
		}
		online_players.remove(p);
	}
	
	
	public void setPlayer(Player player)
	{
		online_players.add(player);
	}
	
	public ArrayList<Player> getOnlinePlayers() {
		
		return online_players;
	}
	
	public void updatePlayer(int i,Player player)
	{
		online_players.set(i, player);
	}
	
	
	
	
/**
 * 
 * @param out
 * @throws IOException
 */
private void writeObject(java.io.ObjectOutputStream out)throws IOException
 {
     out.defaultWriteObject();
 }
    
/**
 * 
 * @param in
 * @throws IOException
 * @throws ClassNotFoundException
 */
private void readObject(java.io.ObjectInputStream in)throws IOException, ClassNotFoundException
 {
     in.defaultReadObject();
     
 }

}


