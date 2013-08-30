package DataObjects;

import java.io.IOException;
import java.io.Serializable;

import Messages.EnvStatus;

public class Player extends LobbyData implements Serializable {

	private String username;
	private int rank;
	private int score;
	private String status;
	private EnvStatus environmentStat;
	private String userIP;
	private int threadNum;
	
	public Player(String username, int rank, String status) {
		
		this.username=username;
		this.rank=rank;
		this.status=status;
		
	}


	public Player() {
		
	}


public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
	this.username = username;
}


public void setRank(int rank) {
	this.rank = rank;
}

public void incRank()
{
	this.rank++;
}

public int getRank() {
	return rank;
}


public void setStatus(String status) {
	this.status = status;
}

public String getStatus() {
	return status;
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


public void setUserIP(String userIP) {
	this.userIP = userIP;
}


public String getUserIP() {
	return userIP;
}


public void setScore(int score) {
	this.score = score;
}


public int getScore() {
	return score;
}


public void setThreadNum(int threadNum) {
	this.threadNum = threadNum;
}


public int getThreadNum() {
	return threadNum;
}

}
