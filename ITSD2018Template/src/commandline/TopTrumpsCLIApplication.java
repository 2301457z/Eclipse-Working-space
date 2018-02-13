package commandline;

import java.util.Scanner;

/**
 * Top Trumps command line application
 */
public class TopTrumpsCLIApplication {
	/**
	 * This main method is called by TopTrumps.java when the user specifies that they want to run in
	 * command line mode. The contents of args[0] is whether we should write game logs to a file.
 	 * @param args
 	 */
	static DbUtil db=new DbUtil();

	public static void main(String[] args) {
		boolean writeGameLogsToFile = false; // Should we write game logs to file?
		if (args[0].equalsIgnoreCase("true")) writeGameLogsToFile=true; // Command line selection
		db.getConnection();
		int count=db.getMaxGameId();
		System.out.println("Game start!");
		System.out.println("Do you want to see statistics of past games or play a new game?");
		System.out.println("1. See statistics of past games   2.Play a new game");
		Scanner input=new Scanner(System.in);
		int choice=input.nextInt();
		if(choice==1) {
			if(db.getMaxGameId()==0) {
				System.out.println("No records in the database.No player played the game before!");
				keepPlay(count, writeGameLogsToFile);
			}else {
				System.out.println("The number of the game played overall: "+db.getMaxGameId());
				System.out.println("The number of the game computer has won: "+db.getCWTotalTimes());
				System.out.println("The number of the game human has won: "+db.getHWTotalTimes());
				System.out.println("The average number of draws: "+db.getAvgNumberOfDraw());
				System.out.println("The largest number of rounds playered in a single game: "+db.getMaxRoundTimes());
				keepPlay(count, writeGameLogsToFile);
			}
		}else {
			keepPlay(count, writeGameLogsToFile);
		}
	}
	/**
	 * method to keep the game going until the human player choose to exit the game
	 * @param count
	 * @param writeGameLogsToFile
	 */
	public static void keepPlay(int count,boolean writeGameLogsToFile ) {
		boolean userWantsToQuit = false; // flag to check whether the user wants to quit the application
		while (!userWantsToQuit) {
			System.out.println("1.Game Start  2.Exit");
			Scanner input1=new Scanner(System.in);
			int choice1=input1.nextInt();
			if(choice1==1) {
				count++;
				GameRound game=new GameRound(db);
				game.setGameId(count);
			if(writeGameLogsToFile==true) {
				game.setWriteGameLogsToFile(true);}
			game.gamePlay();
			}else {
				userWantsToQuit=true; // use this when the user wants to exit the game	
				db.closeConnection();
				System.exit(0);
			}	
		}
	}
}