package DataObjects;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class Room extends LobbyData implements Serializable {

	private int roomID;
	private String gameName;
	private String status;
	private String hostName;
	private String guestName;
	private String specName;
	private String hostIP;
	private String clientIP;
	private String TimeStamp;
	private String winner;
	private boolean tieFlag;
	private String check;
	private ArrayList<Player> specList;
	private boolean invite = false;

	
	
	public Room(String status,String gameName) {
		
		this.status=status;

		this.gameName=gameName;
		
		this.specList = new ArrayList<Player>();
		
	}
	
	public Room(String status,String gameName,String ip) {
		
		this.status=status;
		this.gameName=gameName;
		this.hostIP=ip;
		this.specList = new ArrayList<Player>();
	}

	
	public Room() {
		this.specList = new ArrayList<Player>();
	}

	public int getRoomID()
	{
		return roomID;
	}
	
	public void setRoomID(int ID)
	{
		this.roomID=ID;
	}
	
	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}
	
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	public String getHostIP()
	{
		return hostIP;
	}
	
	public void setHostIP(String ip)
	{
		this.hostIP=ip;
	}
	
	public String getTimeStamp()
	{
		return TimeStamp;
	}
	
	public void setTimeStamp(String timestamp)
	{
		this.TimeStamp=timestamp;
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


	public void setClientIP(String clientIP) {
		this.clientIP = clientIP;
	}

	public String getClientIP() {
		return clientIP;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getHostName() {
		return hostName;
	}

	public void setGuestName(String guestName) {
		this.guestName = new String();
		this.guestName = guestName;
	}

	public String getGuestName() {
		return guestName;
	}

	public void setSpecName(String specName) {
		this.specName = specName;
	}

	public String getSpecName() {
		return specName;
	}

	public void setCheck(String check) {
		this.check = check;
	}

	public String getCheck() {
		return check;
	}

	public void setWinner(String winner) {
		this.winner = winner;
	}

	public String getWinner() {
		return winner;
	}

	public void setTieFlag(boolean tieFlag) {
		this.tieFlag = tieFlag;
	}

	public boolean isTieFlag() {
		return tieFlag;
	}

	public void setSpecList(ArrayList<Player> specList) {
		this.specList = specList;
	}

	public ArrayList<Player> getSpecList() {
		return specList;
	}
	
	public void addSpecToList(Player spectator)
	{
		specList.add(spectator);
	}

	public void setInvite(boolean invite) {
		this.invite = invite;
	}

	public boolean isInvite() {
		return invite;
	}
	
}


