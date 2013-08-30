package DataBase;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



// TODO: Auto-generated Javadoc
/**
 * The Class SQLConnection.
 */
public class SQLConnection 
{
	
	/** The connection. */
	private Connection connection;
	
	/** The SQL conn ins. */
	private static SQLConnection SQLConnIns;
	
	/** The password. */
	private static String password = "";
	
	/** The username. */
	private static String username = "root";
	
	
	/**
	 * Open a connection to the db.
	 *
	 * @throws SQLException the sQL exception
	 */
	private SQLConnection() throws SQLException 
	{
		
		try 
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception ex) {/* handle the error*/}
    
	
			connection = DriverManager.getConnection("jdbc:mysql://localhost/gamestation","root","");
	
	}
	
	/**
	 * SQL connection singleton
	 *
	 * @return the sQL connection
	 */
	public static SQLConnection SQLConnectionSingleton()
	{
		if(SQLConnIns==null){
			try {
				SQLConnIns=new SQLConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return SQLConnIns;
		
	}

	/**
	 * Gets the connection.
	 *
	 * @return the Connection
	 */
	public Connection getConnection() {
		return connection;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public static String getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password the password to set
	 */
	public static void setPassword(String password) 
	{
		SQLConnection.password = password;
	}

	/**
	 * Gets the username.
	 *
	 * @return the username
	 */
	public static String getUsername() 
	{
		return username;
	}

	/**
	 * Sets the username.
	 *
	 * @param username the username to set
	 */
	public static void setUsername(String username) 
	{
		SQLConnection.username = username;
	}
	
}