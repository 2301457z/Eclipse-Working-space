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
	public static void main(String[] args) {
		boolean writeGameLogsToFile = false; // Should we write game logs to file?
	//	if (args[0].equalsIgnoreCase("true")) writeGameLogsToFile=true; // Command line selection
		
		// State
		boolean userWantsToQuit = false; // flag to check whether the user wants to quit the application
		
		// Loop until the user wants to exit the game
		while (!userWantsToQuit) {

			// ----------------------------------------------------
			// Add your game logic here based on the requirements
			// ----------------------------------------------------
		
			GameRound game=new GameRound();
//			if(writeGameLogsToFile==true) {
//			game.setWriteGameLogsToFile(true);}
			game.gamePlay();
			System.out.println("1.Another Game  2.Exit");
			Scanner input=new Scanner(System.in);
			int choice=input.nextInt();
			if(choice==1) {
				continue;
			}
			else {
				userWantsToQuit=true; // use this when the user wants to exit the game	
				System.exit(0);
			}
			
		


	}

	}}
