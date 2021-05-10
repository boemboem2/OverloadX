package server.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import server.Server;
//import server.util.DatabaseFunctions;
//public class DatabaseFunctions {

	//public DatabaseConnection getDatabase() {
		//if (Server.database != null)
			//return Server.database;
		//return null;
	//}
	//public boolean checkVotes(String playerName) {
		//try {
			//ResultSet results = getDatabase().getQuery("SELECT * FROM `votes` WHERE `playerName` = '" + playerName + "'");
			//while(results.next()) {
				//int recieved = results.getInt("recieved");
					//getDatabase().updateQuery("UPDATE `votes` SET `recieved` = '1' WHERE `playerName` = '" + playerName + "'");
					//return true;
				//}
			//}
		//} catch(SQLException e) {
			//e.printStackTrace();
		//}
		//return false;
	//}
	
//}