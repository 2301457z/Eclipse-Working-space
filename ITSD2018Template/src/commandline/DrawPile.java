package commandline;

import java.util.ArrayList;
import java.util.List;
/**
 * DrawPile class is used to contain the draw cards
 * @author 2301457z
 *
 */
public class DrawPile {
	private List<Card> drawCards;
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
