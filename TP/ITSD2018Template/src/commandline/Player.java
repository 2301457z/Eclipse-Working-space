package commandline;

import java.util.ArrayList;
import java.util.List;

public class Player {
	private final String playerName;//name for the user
	private List<Card> cardInhand; //represent the cards in the player's hand
	private boolean isInGame; // represent whether the player is still in game
	private CardDeck deck;
	
	//constructor
	public Player(String name) {
		this.playerName=name;
		cardInhand=new ArrayList<Card>(); 
		this.isInGame=true;
	}
		
	/*
	 * method to get the number of cards in the player's hand
	 */
	public int getCardNumber() {
		return cardInhand.size();
	}
	
	/*
	 *method to add a card to the player (add a card to the cardInhand)
	 */
	public void addCard(Card card) {
		cardInhand.add(card);
	}
	
	/*
	 * method to take away a card from the player(delete a card from the cardInhand)
	 */
	public void delCard(Card card) {
		cardInhand.remove(card);
	}
	
	/*
	 * method to judge whether the player is still in the game or has been eliminated
	 */
	public boolean statusJudge() {
		if(getCardNumber()==0) {
			isInGame=false;
			return isInGame;
		}else
			isInGame=true;
			return isInGame;			
	}
	
	/**
	 * method to get the card with the specific index
	 */
	public Card getIndexCard(int index) {
		return cardInhand.get(index);
	}
	
	/**
	 * get the top card in the player's hand
	 * @return
	 */
	public Card getTopCard() {
		return cardInhand.get(0);
	}
	
	
	/*
	 * getter and setter methods
	 */
	public List<Card> getCardInhand() {
		return cardInhand;
	}

	public void setCardInhand(List<Card> cardInhand) {
		this.cardInhand = cardInhand;
	}

	public boolean isInGame() {
		return isInGame;
	}

	public void setInGame(boolean isInGame) {
		this.isInGame = isInGame;
	}

	public String getPlayerName() {
		return playerName;
	}
	
	public void forEach() {
		for(Card card:cardInhand) {
			System.out.println(card.getCardName()+" "+card.getAttribute1()+" "+card.getAttribute2()+" "+card.getAttribute3()+" "+card.getAttribute4()+" "+card.getAttribute5());
		}
	}
}
