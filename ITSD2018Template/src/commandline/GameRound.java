package commandline;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
/**
 * GameRound class is about the game logic,includes:
 * 1.start the game(initialise the card deck and allocate cards)
 * 2.create the players
 * 3.compare the cards value
 * 4.show the result of a round
 * 5.write to the log file(optional up to the command arguments)
 * 6.insert the related data into the database
 * 
 * @author 2301457z
 *
 */
public class GameRound {
	private CardDeck cardDeck;
	private List<Player> players;
	private DrawPile drawPile;
	private boolean isdraw;  //represents whether the round is draw or not
	private Player winner; //represents the winner of one round
	private Player activePlayer;
	private Player humanPlayer;
	private Player ai1;
	private Player ai2;
	private Player ai3;
	private Player ai4;
	private int choice;
	private static final String LINE="------------------------------------------";
	private TextClass writeLog;
	private boolean writeGameLogsToFile;
	
	private int roundNum; // the round number of the game
	private int drawNum; //the draw number of the game
	private Player finWinner=null; //the final Winner of the game
	private int gameId; // the id the each game
	private DbUtil db;

	/*
	 * constructor
	 */
	public GameRound(DbUtil db) {
		this.db=db;
		cardDeck=new CardDeck();
		players=new ArrayList<Player>();
		drawPile=new DrawPile();
	}
	
	/*
	 * method to get the shuffled cards
	 */
	public List<Card> shuffleCards() {
		cardDeck.readText();
		if(writeGameLogsToFile==true) {  // write the initial card information to the log file
			writeLog=new TextClass();
			writeLog.getPw().println("Description Size Speed Range Firepower Cargo");
		for(Card card:cardDeck.getInicardlist()) {
			String line=card.getCardName()+" "+card.getAttribute1()+" "+card.getAttribute2()+" "+card.getAttribute3()+" "+card.getAttribute4()+" "+card.getAttribute5();
			writeLog.getPw().println(line);
		}	
		writeLog.getPw().println(LINE);
		writeLog.getPw().flush();
		}
		if(writeGameLogsToFile==true) {  //write the shuffled cards information to the log file
			writeLog.getPw().println("Description Size Speed Range Firepower Cargo");
			for(Card card:cardDeck.shuffleCards(cardDeck.getInicardlist())) {
				String line=card.getCardName()+" "+card.getAttribute1()+" "+card.getAttribute2()+" "+card.getAttribute3()+" "+card.getAttribute4()+" "+card.getAttribute5();
				writeLog.getPw().println(line);
			}	
			writeLog.getPw().println(LINE);
			writeLog.getPw().flush();
			}
		return cardDeck.shuffleCards(cardDeck.getInicardlist());
	}
	
	/*
	 * method to create the players(in the command line mode,we assume that the game involves a human player four AI players)
	 */
	public void crePlayers() {
		humanPlayer=new Player("HumanPlayer");
		ai1=new Player("AI1");
		ai2=new Player("AI2");
		ai3=new Player("AI3");
		ai4=new Player("AI4");
		Player[] playerArray= {humanPlayer,ai1,ai2,ai3,ai4};
		players.addAll(Arrays.asList(playerArray));
	}
	
	/*
	 * method to allocate the cards to players
	 */
	public void alloCards() {				
		List<Card> shuffled=shuffleCards();
		for(int i=0;i<8;i++) {
			humanPlayer.getCardInhand().add(shuffled.get(i));
			ai1.getCardInhand().add(shuffled.get(i+8));
			ai2.getCardInhand().add(shuffled.get(i+16));
			ai3.getCardInhand().add(shuffled.get(i+24));
			ai4.getCardInhand().add(shuffled.get(i+32));
		}
		if(writeGameLogsToFile==true) {
		for(int i=0;i<players.size();i++){
			writeLog.getPw().println(players.get(i).getPlayerName());
			writeLog.getPw().println("Description Size Speed Range Firepower Cargo");			
		for(Card card:players.get(i).getCardInhand()) {
			String line=card.getCardName()+" "+card.getAttribute1()+" "+card.getAttribute2()+" "+card.getAttribute3()+" "+card.getAttribute4()+" "+card.getAttribute5();
			writeLog.getPw().println(line);
		}
		writeLog.getPw().println(LINE);
		}	
		writeLog.getPw().flush();
		}	
	}
	
	/**
	 * method to choose the active player of the game for the first round
	 */
	public void randomPlayer() {   
		Random random = new Random();
		int randplayer=random.nextInt(5);
		activePlayer=players.get(randplayer);
	}
	
	
	/**
	 * method to know that the active player is human or computer
	 * @param player
	 * @return
	 */
	public void playerType(Player activeplayer) {
		if(activeplayer==humanPlayer) {
			System.out.println("You are the active player!");
			if(writeGameLogsToFile==true) {
				writeLog.getPw().println("Human player is the active player!");
				writeLog.getPw().flush();
			}
			choAttribute(humanPlayer);
		}else {
			System.out.println("The activate player is "+activePlayer.getPlayerName());
			System.out.println("The player selected: "+activeplayer.getTopCard().getAttributeString(choiceOfPC(activeplayer)));  //the attribute with the biggest value
			if(writeGameLogsToFile==true) {
				writeLog.getPw().println("The activate player is "+activePlayer.getPlayerName());
				writeLog.getPw().println("The player selected: "+activeplayer.getTopCard().getAttributeString(choiceOfPC(activeplayer)));
				writeLog.getPw().flush();
			}
		}
	}
	
	/**
	 * method for human user to choose the attribute
	 * @param player
	 * @return the choice of human player
	 */
	public int choAttribute(Player player) {
		boolean choiceRight=false;
		while(!choiceRight) {
		System.out.println("Please choose the attribute: 1.Size 2.Speed 3.Range 4.Firepower 5.Cargo");
		Scanner input=new Scanner(System.in);
		choice=input.nextInt();
		if(choice>=1&&choice<=5) {
			choiceRight=true;
			System.out.print("Your choice: ");
			System.out.println(player.getTopCard().getAttributeString(choice));
			if(writeGameLogsToFile==true) {
				writeLog.getPw().println("Choice: "+player.getTopCard().getAttributeString(choice));
				writeLog.getPw().flush();
			}
			return choice;
		}else {
			System.out.println("Please enter the correct number!");
			continue;
		}
		}
		return 0;
	}
	/**
	 * method to print the player's card information
	 */
	public void printCard(Player player) {
		Card playerCard=player.getTopCard();
		System.out.println(LINE);
		System.out.println("---------"+player.getPlayerName()+"---------");
		System.out.println("Card name: "+playerCard.getCardName());
		System.out.println("Size: "+playerCard.getAttribute1());
		System.out.println("Speed: "+playerCard.getAttribute2());
		System.out.println("Range: "+playerCard.getAttribute3());
		System.out.println("Firepower: "+playerCard.getAttribute4());
		System.out.println("Cargo: "+playerCard.getAttribute5());
		System.out.println(LINE);
		if(writeGameLogsToFile==true) {
			writeLog.getPw().println(LINE);
			writeLog.getPw().println("---------"+player.getPlayerName()+"---------");
			writeLog.getPw().println("Card name: "+playerCard.getCardName());
			writeLog.getPw().println("Size: "+playerCard.getAttribute1());
			writeLog.getPw().println("Speed: "+playerCard.getAttribute2());
			writeLog.getPw().println("Range: "+playerCard.getAttribute3());
			writeLog.getPw().println("Firepower: "+playerCard.getAttribute4());
			writeLog.getPw().println("Cargo: "+playerCard.getAttribute5());
			writeLog.getPw().println(LINE);
			writeLog.getPw().flush();
		}
	}
	
	/**
	 * method to return the winner of the round
	 */
	public Player getWinner(List<Player> players,int choice) {
		winner=activePlayer;
		Iterator<Player> plaIter = players.iterator();  
		while(plaIter.hasNext()) {
			Player player=plaIter.next();
			if(winner.getTopCard().getAttribute(choice)<player.getTopCard().getAttribute(choice)) {
				winner=player;		
			}
		}
		ifDraw();
		if(isdraw==false) {
			activePlayer=winner;
			int temp=winner.getRoundWin();
			temp++;
			winner.setRoundWin(temp);
		}
		return activePlayer;	
	}
	
	/**
	 * method to judge whether the round is draw			
	 */
	public boolean ifDraw() {
		int count=0;
		Iterator<Player> plaIter = players.iterator();  
		while(plaIter.hasNext()) {
			Player player=plaIter.next();
			if(winner.getTopCard().getAttribute(choice)==player.getTopCard().getAttribute(choice))
					count=count+1;
		}
		if(count>1) {
			isdraw=true;
			drawNum=drawNum+1;
		}else 
			isdraw=false;
		return isdraw;	
	}
	
	/**
	 * method to collect the cards to the draw pile if the round is draw
	 */
	public void pileCollect() {
		if(isdraw==true) {
			Iterator<Player> plaIter = players.iterator();  
			while(plaIter.hasNext()) {
				Player player=plaIter.next();
				drawPile.addCard(player.getTopCard());
				player.getCardInhand().remove(player.getTopCard());
			}
		}
	}
	
	/**
	 * method to get the cards in the draw pile to the winner
	 */
	public void pileDistribute() {
		winner.getCardInhand().addAll(drawPile.getDrawCards());
		drawPile.removeCard(drawPile.getDrawCards());
		
	}
	
	/**
	 * method to return the choice of computer(the attribute with the biggest value)
	 * @param player
	 * @return the choice of the computer(choose the biggest value automatically)
	 */
	public int choiceOfPC(Player player) {
		Card topCard=player.getTopCard();
		choice=topCard.getIndex();
		return choice;	
	}
	
	/**
	 * method to show the result of the round£ºwho won the round
	 * @param args
	 */
	public void showResult() {
		if(isdraw==true) {
			System.out.println("The round is a draw!");
			if(writeGameLogsToFile==true) {
				writeLog.getPw().println("The round is a draw!");
				writeLog.getPw().println(LINE);
				writeLog.getPw().flush();
			}
		}else if(winner==humanPlayer) {
			System.out.println("You win the round!");
			if(writeGameLogsToFile==true) {
				writeLog.getPw().println("Human player win the round!");
				writeLog.getPw().flush();
			}
		}else {
			if(writeGameLogsToFile==true) {
				writeLog.getPw().println(winner.getPlayerName()+" win the round!");
				writeLog.getPw().flush();
			}
			System.out.println("You lose the round!");
			System.out.println(winner.getPlayerName()+" win the round!");}
	}

	/**
	 * method to collect losers' cards for the winner(remove the losers' top cards in hand)
	 * @param args
	 */
	public void collectCards(List<Player> players) {
		Iterator<Player> plaIter = players.iterator();  
		while(plaIter.hasNext()) {
			Player player=plaIter.next();
			winner.getCardInhand().add(player.getTopCard());
			player.getCardInhand().remove(0);
			}
		}
			
	/**
	 * method to check the player with no cards in the game(remove player from the players list
	 * @param args
	 */
	public void checkList(List<Player> players) {
		Iterator<Player> plaIter = players.iterator();  
		while(plaIter.hasNext()) {
			Player player=plaIter.next();
			if(player.statusJudge()==false) {
				if(player==humanPlayer) {
					System.out.println("You have no cards now. Game over!");
				}
				plaIter.remove();
			}
		}
			if(players.size()==1) {
				while(plaIter.hasNext()) {
					Player player1=plaIter.next();
				finWinner=player1;
				db.addNewGameState(getGameId(),player1.getPlayerName(), drawNum, roundNum);  
				System.out.println("The final winner is "+finWinner.getPlayerName());
				System.out.println(LINE);
				if(writeGameLogsToFile==true) {
					writeLog.getPw().println("The final winner is "+player1.getPlayerName());
					writeLog.getPw().flush();
				}
			}	
		}	
	}
	

	/**
	 * method for the game loop
	 * @param args
	 */
	public void gameLoop() {
		roundNum=roundNum+1;
		System.out.println("Round "+roundNum);
		if(writeGameLogsToFile==true) {
			writeLog.getPw().println("Round "+roundNum);
			writeLog.getPw().flush();
		}
		if(humanPlayer.statusJudge()!=false) {
			printCard(humanPlayer);
		}
		playerType(activePlayer);

		getWinner(players, choice);
		Iterator<Player> plaIter = players.iterator();  
		while(plaIter.hasNext()) {
			Player player=plaIter.next();
				if(player!=humanPlayer)
					printCard(player);
		}
		showResult();
		if(isdraw==true) { 
			pileCollect();
			checkList(players);
			Iterator<Player> plaIter1 = players.iterator();  
			while(plaIter1.hasNext()) {
				Player player=plaIter1.next();
					System.out.println(player.getPlayerName()+": "+player.getCardNumber());}
		}else {
			pileDistribute();
			collectCards(players);
			Iterator<Player> plaIter2 = players.iterator();  
			while(plaIter2.hasNext()) {
				Player player=plaIter2.next();
					System.out.println(player.getPlayerName()+": "+player.getCardNumber());}
		}
		checkComPile();
		iteraDeck();
		checkList(players);	
	}	
	
	/**
	 * method for the game loop(if the user is eliminated, the computers will finish the game automatically
	 * @param args
	 */
	public void remainLoop() {
		roundNum=roundNum+1;
		choiceOfPC(activePlayer);
		if(writeGameLogsToFile==true) {
			writeLog.getPw().println("Round "+roundNum);
			writeLog.getPw().println("The activate player is "+activePlayer.getPlayerName());
			writeLog.getPw().println("The player selected: "+activePlayer.getTopCard().getAttributeString(choiceOfPC(activePlayer)));		
		}	
		getWinner(players, choice);
		if(writeGameLogsToFile==true) {
			writeLog.getPw().println(winner.getPlayerName()+" wins the round!");
			writeLog.getPw().println(LINE);
			writeLog.getPw().flush();
		}
		if(isdraw==true) {  
			pileCollect();
			Iterator<Player> plaIter = players.iterator();  
			while(plaIter.hasNext()) {
				Player player=plaIter.next();
				if(player.statusJudge()==false) {
					plaIter.remove();
				}
			}
		}else {
			pileDistribute();
			collectCards(players);
			Iterator<Player> plaIter = players.iterator();  
			while(plaIter.hasNext()) {
				Player player=plaIter.next();
				if(player.statusJudge()==false) {
					plaIter.remove();
				}
			}
		}
		if(players.size()==1) {
		Iterator<Player> plaIter = players.iterator();  
		while(plaIter.hasNext()) {
			Player player=plaIter.next();
			db.addNewGameState(getGameId(),player.getPlayerName(), drawNum, roundNum);
			System.out.println("The final winner is "+player.getPlayerName());
			System.out.println(LINE);
			if(writeGameLogsToFile==true) {
				writeLog.getPw().println("The final winner is "+player.getPlayerName());
				writeLog.getPw().flush();
			}
		}
		}
		iteraDeck();
		checkComPile();
	}
	
	/**
	 * method to deal with the game loop( human player is still in game)
	 */
	public void methodLoop() {
		System.out.println(LINE);
		System.out.println("Please input any character to continue. ");
		Scanner input = new Scanner(System.in);
		String str = input.next();
		while(str!=null) {
			gameLoop();		
			str=null;
		}
		if(players.contains(humanPlayer)) {
			methodLoop();	
		}else {		
			while(players.size()>1) {
				remainLoop();
			}
		}
	}
	
	/**
	 * method to iterate the players' cards deck(write to the log file)
	 */
	public void iteraDeck() {
		if(writeGameLogsToFile==true) {
			Iterator<Player> plaIter = players.iterator();  
			while(plaIter.hasNext()) {
				Player player=plaIter.next();
				writeLog.getPw().println(player.getPlayerName());
				writeLog.getPw().println("Description Size Speed Range Firepower Cargo");
			for(Card card:player.getCardInhand()) {
				String line=card.getCardName()+" "+card.getAttribute1()+" "+card.getAttribute2()+" "+card.getAttribute3()+" "+card.getAttribute4()+" "+card.getAttribute5();
				writeLog.getPw().println(line);
				}
			writeLog.getPw().println(LINE);
			}
			writeLog.getPw().flush();
		}
	}
	
	/**
	 * method to check the communal pile (write to the log file)
	 */
	public void checkComPile() {
		if(writeGameLogsToFile==true) {
			if(drawPile.getDrawCards().size()==0) {
				writeLog.getPw().println("The communal pile has no cards now!");
				writeLog.getPw().println(LINE);
			}else {
				writeLog.getPw().println("The communal pile has cards:");
				for(Card card:drawPile.getDrawCards()) {
					String line=card.getCardName()+" "+card.getAttribute1()+" "+card.getAttribute2()+" "+card.getAttribute3()+" "+card.getAttribute4()+" "+card.getAttribute5();
					writeLog.getPw().println(line);
					
				}
				writeLog.getPw().println(LINE);
			}
			writeLog.getPw().flush();			
		}
	}
	/**
	 * method to play a game round
	 */
	public void gamePlay() {
		System.out.println("Please input any character to continue. ");
		Scanner input = new Scanner(System.in);
		String str = input.next();
		while(str!=null) {
			crePlayers();
			alloCards();
			randomPlayer();
			str=null;
		}
			gameLoop();
			methodLoop();
			db.insertPlayerStates(getGameId(), humanPlayer.getPlayerName(), humanPlayer.getRoundWin());
			db.insertPlayerStates(getGameId(), ai1.getPlayerName(), ai1.getRoundWin());
			db.insertPlayerStates(getGameId(), ai2.getPlayerName(), ai2.getRoundWin());
			db.insertPlayerStates(getGameId(), ai3.getPlayerName(), ai3.getRoundWin());
			db.insertPlayerStates(getGameId(), ai4.getPlayerName(), ai4.getRoundWin());
	}
	
	/**
	 * getters and setters methods
	 */
	public int getGameId() {
		return gameId;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	public Player getFinWinner() {
		return finWinner;
	}

	public void setFinWinner(Player finWinner) {
		this.finWinner = finWinner;
	}

	public boolean isWriteGameLogsToFile() {
		return writeGameLogsToFile;
	}

	public void setWriteGameLogsToFile(boolean writeGameLogsToFile) {
		this.writeGameLogsToFile = writeGameLogsToFile;
	}
}

