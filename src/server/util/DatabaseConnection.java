package server.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import server.util.Misc;
//import server.util.DatabaseFunctions;
import server.Server;

public class DatabaseConnection {

	/**
	 * Define MySQL connection info.
	 */
	static final String host = "";
	static final String db = "";//Database name
	static final String user = "";
	static final String pass = "";;
	static final String port = "3306";

	/**
	 * The database connection in use
	 */
	private Connection con;
	/**
	 * A statement for running queries on
	 */
	private Statement statement;
	/**
	 * The last query being executed
	 */
	private String lastQuery;

	static {
		testForDriver();
	}
	public DatabaseConnection getDatabase() {
		//if (Server.database != null)
			//return Server.database;
		return null;
	}
	public boolean checkVotes(String playerName) {
		try {
			ResultSet results = getDatabase().getQuery("SELECT * FROM `votes` WHERE `playerName` = '" + playerName + "'");
			while(results.next()) {
				int recieved = results.getInt("recieved");
				if(recieved == 0) {
					getDatabase().updateQuery("UPDATE `votes` SET `recieved` = '1' WHERE `playerName` = '" + playerName + "'");
					return true;
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * Tests we have a mysql Driver
	 */
	private static void testForDriver() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException cnfe) {
			Misc.println("Class not found exception");
		}
	}

	/**
	 * Instantiates a new database connection
	 */
	public DatabaseConnection() {
		if (!createConnection()) {
			Misc.println("Unable to connect to MySQL");
			System.exit(1);
		}
	}

	public boolean createConnection() {
		try {
			con = DriverManager.getConnection("jdbc:mysql://" + host + ":"
					+ port + "/" + db, user, pass);
			statement = con.createStatement();
			statement.setEscapeProcessing(true);
			return isConnected();
		} catch (SQLException e) {
			Misc.println(e.getMessage());
			return false;
		}
	}

	public boolean isConnected() {
		try {
			statement.executeQuery("SELECT CURRENT_DATE");
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	/**
	 * Runs a select query on the current database connection
	 * 
	 * @param s
	 *            The query to be ran
	 */
	public ResultSet getQuery(String q) throws SQLException {
		try {
			lastQuery = q;
			return statement.executeQuery(q);
		} catch (SQLException e) {
			if (!isConnected() && createConnection()) {
				return getQuery(q);
			}
			throw new SQLException(e.getMessage() + ": '" + lastQuery + "'", e
					.getSQLState(), e.getErrorCode());
		}
	}

	/**
	 * Runs a update/insert/replace query on the current database connection
	 * 
	 * @param s
	 *            The query to be ran
	 */
	public int updateQuery(String q) throws SQLException {
		try {
			lastQuery = q;
			return statement.executeUpdate(q);
		} catch (SQLException e) {
			if (!isConnected() && createConnection()) {
				return updateQuery(q);
			}
			throw new SQLException(e.getMessage() + ": '" + lastQuery + "'", e.getSQLState(), e.getErrorCode());
		}
	}


	public int newQuery(String q) throws SQLException {
		try {
			Statement tempStatement = con.createStatement();
			lastQuery = q;
			return tempStatement.executeUpdate(q);
		} catch (SQLException e) {
			if (!isConnected() && createConnection()) {
				return updateQuery(q);
			}
			throw new SQLException(e.getMessage() + ": '" + lastQuery + "'", e
					.getSQLState(), e.getErrorCode());
		}
	}

	/**
	 * Closes the database conection.
	 * 
	 * @throws SQLException
	 *             if there was an error when closing the connection
	 */
	public void close() throws SQLException {
		con.close();
		con = null;
	}

}