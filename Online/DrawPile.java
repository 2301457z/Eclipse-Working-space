/**
 * The class DrawPile is used to store the draw cards
 */
import java.util.ArrayList;
import java.util.List;

public class DrawPile {
	private List<Card> drawCards;
	
	/*
	 * constructor
	 */
	public DrawPile() {
		drawCards=new ArrayList<Card>();
	}
	
	/**
	 * method to add a card to the draw pile
	 */
	public void addCard(Card card) {
		drawCards.add(card);
	}
	/**
	 * method to remove a card from the draw pile
	 */
	public void removeCard(List<Card> cards) {
		drawCards.removeAll(cards);
	}

	public List<Card> getDrawCards() {
		return drawCards;
	}

	public void setDrawCards(List<Card> drawCards) {
		this.drawCards = drawCards;
	}
}
