import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

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
	
	private int roundNum; // the round number of the game
	private int drawNum; //the draw number of the game
	private Player finWinner; //the final Winner of the game
	
	/*
	 * constructor
	 */
	public GameRound() {
		cardDeck=new CardDeck();
		players=new ArrayList<Player>();
		drawPile=new DrawPile();
	}
	
	/*
	 * method to get the shuffled cards
	 */
	public List<Card> shuffleCards() {
		/**
		 * write the initial card deck to the log file
		 */
		cardDeck.readText();
		for(Card card:cardDeck.getInicardlist()) {
			String line=card.getCardName()+" "+card.getAttribute1()+" "+card.getAttribute2()+" "+card.getAttribute3()+" "+card.getAttribute4()+" "+card.getAttribute5();
		}	
		return cardDeck.shuffleCards(cardDeck.getInicardlist());
	}
	
	/*
	 * method to create the players
	 */
	public void crePlayers(int i) {  //the argument i represents the number of the players
		humanPlayer=new Player("HumanPlayer");	
		if (i==1){
			ai1=new Player("AI1");
			Player[] playerArray= {humanPlayer,ai1};
			players.addAll(Arrays.asList(playerArray));
		}else if(i==2) {	
			ai1=new Player("AI1");
			ai2=new Player("AI2");
			Player[] playerArray= {humanPlayer,ai1,ai2};
			players.addAll(Arrays.asList(playerArray));
		}else if(i==3) {
			ai1=new Player("AI1");
			ai2=new Player("AI2");
			ai3=new Player("AI3");
			Player[] playerArray= {humanPlayer,ai1,ai2,ai3};
			players.addAll(Arrays.asList(playerArray));
		}else if(i==4) {
			ai1=new Player("AI1");
			ai2=new Player("AI2");
			ai3=new Player("AI3");
			ai4=new Player("AI4");	
			Player[] playerArray= {humanPlayer,ai1,ai2,ai3,ai4};
			players.addAll(Arrays.asList(playerArray));
		}
	}
	
	/*
	 * method to allocate the cards to players
	 */
	public void alloCards(int number) {		
		List<Card> shuffled=shuffleCards();
		if(number==1) {
			for(int i=0;i<40;i=i+2) {
				humanPlayer.getCardInhand().add(shuffled.get(i));
				ai1.getCardInhand().add(shuffled.get(i+1));
			}
		}else if(number==2) {
			for(int i=0;i<39;i=i+3) {
				humanPlayer.getCardInhand().add(shuffled.get(i));
				ai1.getCardInhand().add(shuffled.get(i+1));
				ai2.getCardInhand().add(shuffled.get(i+2));
			}
			ai2.getCardInhand().add(shuffled.get(39));
		}else if(number==3) {
			for(int i=0;i<40;i=i+4) {
			humanPlayer.getCardInhand().add(shuffled.get(i));
			ai1.getCardInhand().add(shuffled.get(i+1));
			ai2.getCardInhand().add(shuffled.get(i+2));
			ai3.getCardInhand().add(shuffled.get(i+3));}
		}else if(number==4) {
			for(int i=0;i<8;i++) {
				humanPlayer.getCardInhand().add(shuffled.get(i));
				ai1.getCardInhand().add(shuffled.get(i+8));
				ai2.getCardInhand().add(shuffled.get(i+16));
				ai3.getCardInhand().add(shuffled.get(i+24));
				ai4.getCardInhand().add(shuffled.get(i+32));
			}
		}
		
		for(int i=0;i<players.size();i++){		
			for(Card card:players.get(i).getCardInhand()) {
			String line=card.getCardName()+" "+card.getAttribute1()+" "+card.getAttribute2()+" "+card.getAttribute3()+" "+card.getAttribute4()+" "+card.getAttribute5();
		}
		}	
		}	
	
	/*
	 * method to choose the player of the game randomly
	 */
	public void randomPlayer(int i) {   //可能会出问题的地方  要调整
		Random random = new Random();
		int randplayer=random.nextInt(i);
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
			choAttribute(humanPlayer);
		}else {
			System.out.println("The activate player is "+activePlayer.getPlayerName());
			System.out.println("The player selected: "+activeplayer.getTopCard().getAttributeString(choiceOfPC(activeplayer)));  //the attribute with the biggest value
		}
	}
	
	/*
	 * method for human user to choose the attribute
	 */
	public int choAttribute(Player player) {
		System.out.println("Please choose the attribute: 1.Size 2.Speed 3.Range 4.Firepower 5.Cargo");
		Scanner input=new Scanner(System.in);
		choice=input.nextInt();
		System.out.print("Your choice: ");
		System.out.println(player.getTopCard().getAttributeString(choice));
		return choice;
	}
	/**
	 * method to print the player's first card in hand
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
			activePlayer.setRoundWin(activePlayer.getRoundWin()+1);
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
	 */
	public int choiceOfPC(Player player) {
		Card topCard=player.getTopCard();
		choice=topCard.getIndex();
		return choice;	
	}
	
	/**
	 * method to show the result of the round：who won the round
	 * @param args
	 */
	public void showResult() {
		if(isdraw==true) {
			System.out.println("The round is a draw!");

		}else if(winner==humanPlayer) {
			System.out.println("You win the round!");
			
		}else {
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
			if(player.getCardInhand().size()==40) {
				finWinner=player;
				System.out.println("The final winner is "+finWinner.getPlayerName());
				
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
		checkList(players);	
	}	
	
	/**
	 * method for the game loop(if the user is eliminated, the computers will finish the game automatically
	 * @param args
	 */
	public void remainLoop() {
		roundNum=roundNum+1;
		choiceOfPC(activePlayer);
	
		getWinner(players, choice);
	
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
			System.out.println("The final winner is "+player.getPlayerName());
		}
		}
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
	
	/*
	 * method to play a game
	 */
	public void gamePlay() {
		System.out.println("Game start!");
		System.out.println("Do you want to see statistics of past games or play a new game?");
		System.out.println("1. See statistics of past games   2.Play a new game");
		Scanner input=new Scanner(System.in);
		int choice=input.nextInt();
		if(choice==1) {
			//get the statistics of the past game from the database 
			//need more code to deal with this part	
		}else if(choice==2) {
			System.out.print("Please choose the number of the AI player:1. 1 player 2. 2 players 3. 3 players 4. 4 players");
			Scanner input1=new Scanner(System.in);
			int choice1=input.nextInt();
			crePlayers(choice1);
			alloCards(choice1);
			randomPlayer(choice1+1);
			gameLoop();
			methodLoop();
		}else {
			System.out.println("Please enter the correct choice!");
		}

		/*
		 * close the printwriter object
		 */
	}
	//----------------------------------------------------------------------
	/**
	 * method to view the statistics of past games
	 */
	public void viewStatistics() {
		
	}
	/**
	 * method to start a game and choose the number of players
	 * @param choice
	 */
	public void startGame(int choice) {
		System.out.println("Game start!");
		System.out.println("Please choose the number of the AI players:1-5");
		crePlayers(choice);
		alloCards(choice);
		randomPlayer(choice+1);	
	}
	
}

