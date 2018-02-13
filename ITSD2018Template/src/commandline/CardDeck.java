package commandline;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * The CardDeck class is used to put all the cards in a deck,which includes:
 * 1. initial cards deck
 * 2. shuffled cards deck
 * 3. related getters and setters method
 * @author 2301457z
 *
 */
public class CardDeck {
	private List<Card> inicardlist;   //the initial  cards deck
	private List<Card> shufcardlist;  //the shuffled cards deck
	private String textfile="StarCitizenDeck.txt";	//the file storing the card deck
	private Card cardObject;   //the card object(single card)
	
	public CardDeck() {
		this.inicardlist=new ArrayList<Card>(); //instantiate the generic attribute
	}
	
	/*
	 * method to read the card information file and add the cards to the inital cards deck
	 */
	public void readText() {
		FileReader fr=null;
		try {
			fr=new FileReader(textfile);
			Scanner input=new Scanner(fr);
			while(input.hasNext()) {
				String line=null;
				String[] cardArray=null;
				line=input.nextLine();
				cardArray=line.split("\\s+");
				if(!cardArray[0].equals("Description")) {
				cardObject=new Card(cardArray[0],Integer.parseInt(cardArray[1]),Integer.parseInt(cardArray[2]),Integer.parseInt(cardArray[3]),Integer.parseInt(cardArray[4]),Integer.parseInt(cardArray[5]));
				inicardlist.add(cardObject);
				}
			}
			
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			if(fr!=null) {
				fr.close();
			}
		}catch(Exception e) {
			System.out.println("File cannnot be closed.");
		}
	}
	
	/**
	 * method to shuffle the deck top trump cards
	 * @param cards
	 * @return
	 */
	public List<Card> shuffleCards(List<Card> cards) {
		Collections.shuffle(cards);
		shufcardlist=cards;
		return shufcardlist;
	}

	
	/**
	 * getters and setters methods
	 * @return
	 */
	public List<Card> getInicardlist() {
		return inicardlist;
	}


	public void setInicardlist(List<Card> inicardlist) {
		this.inicardlist = inicardlist;
	}


	public List<Card> getShufcardlist() {
		return shufcardlist;
	}


	public void setShufcardlist(List<Card> shufcardlist) {
		this.shufcardlist = shufcardlist;
	}


	public String getTextfile() {
		return textfile;
	}


	public void setTextfile(String textfile) {
		this.textfile = textfile;
	}


	public Card getCardObject() {
		return cardObject;
	}


	public void setCardObject(Card cardObject) {
		this.cardObject = cardObject;
	}
	
}
