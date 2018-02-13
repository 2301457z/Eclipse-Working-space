package commandline;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;

/**
 * DbUtil class is used to deal with the issues about database, includes:
 * 1.connect the database
 * 2.disconnect the database
 * 3.related query sentences
 * @author 2284668y
 *
 */
public class DbUtil {
	public static final String URL = "jdbc:postgresql://yacata.dcs.gla.ac.uk:5432/"; // Database URL
	public static final String DATABASE = "m_17_2284668y"; // Database name
	public static final String USER = "m_17_2284668y"; // User name
	public static final String PASSWORD = "2284668y"; // User password
	private Connection connection = null;

	/**
	 * Gets the connection of the database
	 */
	public void getConnection() {
		try {
			connection = DriverManager.getConnection(URL + DATABASE, USER, PASSWORD);
		} catch (SQLException e) {
			System.err.println("Connection Failed!"); // Connection failed reminding
			e.printStackTrace();
			return;
		}
		if (connection != null)
			System.out.println("Connection successful!"); // Connection succeed reminding
		else
			System.err.println("Failed to make connection!"); // Connection failed reminding
	}

	/**
	 * Closes the connection of database
	 */
	public void closeConnection() {
		try {
			connection.close();
			System.out.println("Connection closed!"); // Closing succeed reminding
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Connection could not be closed ¨C SQL exception!"); // Closing failed reminding
		}
	}

	/**
	 * Obtains the maximum value of gameid
	 * 
	 * @return int
	 */
	public int getMaxGameId() {
		String SQL = "SELECT MAX(gameid) FROM gamestate";
		int maxGameId = 0;
		maxGameId = getDataFromDatabase(SQL); // When maxGameId equals to 0, it means the table games is null
		return maxGameId;
	}
	
	/**
	 * Deletes the initialised row in gamestate where gameid equals to 0
	 */
	public void deleteRow() {
		String SQL = "DELETE FROM gamestate WHERE gameid = 0";		
		PreparedStatement preStatement = null;
		try {
			try {
				preStatement = connection.prepareStatement(SQL);
				preStatement.executeUpdate();
			} finally {
				if (preStatement != null) preStatement.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Failed to delete the row whose gameid equals to 0!");
		}
	}
	
	/**
	 * Adds a new game sate when the game once finished
	 * 
	 * @param gameId, winPlayerName, drawTimes, roundTimes
	 */
	public void addNewGameState(int gameId, String winPlayerName, int drawTimes, int roundTimes) {
		String SQL = "INSERT INTO gamestate (gameid, winplayername, drawtimes, roundtimes) VALUES (?, ?, ?, ?)";
		PreparedStatement preStatement = null;
		try {
			try {
				preStatement = connection.prepareStatement(SQL);
				preStatement.setInt(1, gameId);
				preStatement.setString(2, winPlayerName);
				preStatement.setInt(3, drawTimes);
				preStatement.setInt(4, roundTimes);
				preStatement.executeUpdate();
			} finally {
				if (preStatement != null) preStatement.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Failed to add the new game state!");
		}
	}
	
	/**
	 * Inserts numbers of new player states
	 * 
	 * @param gameId, playerName, winRoundTimes
	 */
	public void insertPlayerStates(int gameId, String playerName, int winRoundTimes) {
		String SQL = "INSERT INTO playerstate (gameid, playername, winroundtimes) VALUES (?, ?, ?)";
		
		PreparedStatement preStatement = null;
		try {
			try {
				preStatement = connection.prepareStatement(SQL);
				preStatement.setInt(1, gameId);
				preStatement.setString(2, playerName);
				preStatement.setInt(3, winRoundTimes);
				preStatement.executeUpdate();
			} finally {
				if (preStatement != null)
					preStatement.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Failed to insert numbers of player states!");
		}
	}
	
	/**
	 * Obtains the total times of computer win
	 * 
	 * @return total times that computers have won
	 */
	public int getCWTotalTimes() {
		String SQL = "SELECT COUNT(*) FROM gamestate WHERE winplayername <> ?";
		
		int totalTimesCW;
		totalTimesCW = getDataFromDatabase(SQL, "HumanPlayer");
		return totalTimesCW;
	}
	
	/**
	 * Obtains the total times of human win
	 * 
	 * @return total times that human has won
	 */
	public int getHWTotalTimes() {
		String SQL = "SELECT COUNT(*) FROM gamestate WHERE winplayername = ?";
		
		int totalTimesHW;
		totalTimesHW = getDataFromDatabase(SQL, "HumanPlayer");
		return totalTimesHW;
	}
	
	/**
	 * Obtains the average number of draw times
	 * 
	 * @return averge number of draw times
	 */
	public double getAvgNumberOfDraw() {
		String SQL = "SELECT SUM(drawtimes) FROM gamestate";
		
		double avgDrawNumber = 0;
		int totalDrawTimes = 0;
		int totalGameTimes = 0;
		totalDrawTimes = getDataFromDatabase(SQL);
		totalGameTimes = getMaxGameId();
		DecimalFormat decimalFormat = new DecimalFormat("#.##");
		avgDrawNumber = Double.parseDouble(decimalFormat.format((float) totalDrawTimes / totalGameTimes));
		return avgDrawNumber;
	}
	
	/**
	 * Obtains the max value of roundtimes
	 * 
	 * @return int
	 */
	public int getMaxRoundTimes() {
		String SQL = "SELECT MAX(roundtimes) FROM gamestate";
		
		int maxRoundTimes = 0;
		maxRoundTimes = getDataFromDatabase(SQL);
		return maxRoundTimes;
	}
	
	/**
	 * Gets integral data according to the specific SQL
	 * 
	 * @param SQL
	 * @return int
	 */
	public int getDataFromDatabase(String SQL) {
		int value = 0;
		PreparedStatement preStatement = null;
		ResultSet resultSet = null;
		try {
			try {
				preStatement = connection.prepareStatement(SQL);
				resultSet = preStatement.executeQuery();
				while (resultSet.next())
					value = resultSet.getInt(1);
			} finally {
				if (resultSet != null) resultSet.close();
				if (preStatement != null) preStatement.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Failed to get the value from the database!");
		}
		return value;
	}
	
	/**
	 * Gets integral data according to the specific SQL and the attribute name
	 * 
	 * @param SQL, attributeName
	 * @return int
	 */
	public int getDataFromDatabase(String SQL, String attributeName) {
		int value = 0;
		PreparedStatement preStatement = null;
		ResultSet resultSet = null;
		try {
			try {
				preStatement = connection.prepareStatement(SQL);
				preStatement.setString(1, attributeName);
				resultSet = preStatement.executeQuery();
				while (resultSet.next())
					value = resultSet.getInt(1);
			} finally {
				if (resultSet != null) resultSet.close();
				if (preStatement != null) preStatement.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Failed to get the value of " + attributeName + "!");
		}
		return value;
	}
	
}
