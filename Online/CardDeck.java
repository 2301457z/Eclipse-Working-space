

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class CardDeck {
	private List<Card> inicardlist;   //the initial  deck
	private List<Card> shufcardlist;  //the shuffled deck
	private String textfile="StarCitizenDeck.txt";	//the file storing the card deck
	private Card cardObject;
	
	public CardDeck() {
		this.inicardlist=new ArrayList<Card>(); //instantiate the generic attribute
	}
	
	/*
	 * method to read the text file
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
	
	//iterate the objects in the cardlist
	public void forEach() {
		for(Card card:inicardlist) {
			System.out.println(card.getCardName()+" "+card.getAttribute1()+" "+card.getAttribute2()+" "+card.getAttribute3()+" "+card.getAttribute4()+" "+card.getAttribute5());
		}
	}
	
	/*
	 * method to shuffle the deck top trump cards
	 */
	public List<Card> shuffleCards(List<Card> cards) {
		Collections.shuffle(cards);
		shufcardlist=cards;
		return shufcardlist;
	}


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
