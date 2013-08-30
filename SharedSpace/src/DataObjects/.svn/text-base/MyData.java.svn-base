package DataObjects;

import java.io.IOException;
import java.io.Serializable;

import Messages.EnvStatus;

public class MyData implements Serializable {
	

	
	private Player player;
	private Room room;
	private EnvStatus environmentStat;
	
	//constructors
	public MyData()
	{
		
	}
	
	public MyData(Player player)
	{
		this.player=player;
	}
	
	public MyData(Room room)
	{
		this.room=room;
	}
	
	
	//methods 
	public void setPlayer(Player player)
	{
		this.player=player;
	}
	
	public Player getPlayer()
	{
		return player;
	}
	
	public void setRoom(Room room)
	{
		this.room=room;
	}
	
	public Room getRoom()
	{
		return room;
	}
	
	public void setEnvironment(EnvStatus environment)
	{
		this.environmentStat=environment;
	}
	
	public EnvStatus getEnvironment()
	{
		return environmentStat;
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
