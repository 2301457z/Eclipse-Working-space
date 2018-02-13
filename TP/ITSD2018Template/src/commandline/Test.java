package commandline;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import com.sun.corba.se.impl.orbutil.closure.Constant;

public class Test {
	private String textfile="StarCitizenDeck.txt";
	private CardDeck cardDeck;
	private static final String LINE="------------------------------------------";
	private PrintWriter pw=null;
	//constructor
	public Test() {
		cardDeck=new CardDeck();
		File file=new File("toptrumps.log");
		try {
		//	pw=new PrintWriter("toptrumps.log");
			pw=new PrintWriter(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//	/**
//	 * method to print the complete card deck
//	 */
//	public void completeDeck() {
//		pw.println("Description Size Speed Range Firepower Cargo");
//		for(Card card:cardDeck.getInicardlist()) {
//			String line=card.getCardName()+" "+card.getAttribute1()+" "+card.getAttribute2()+" "+card.getAttribute3()+" "+card.getAttribute4()+" "+card.getAttribute5();
//			pw.println(line);
//		}	
//		pw.println(LINE);
//	}
//	
//	/**
//	 * method to print the shuffled cards deck
//	 * @param args
//	 */
//	public void shuffledDeck() {
//		pw.println("Description Size Speed Range Firepower Cargo");
//		for(Card card:cardDeck.getShufcardlist()) {
//			String line=card.getCardName()+" "+card.getAttribute1()+" "+card.getAttribute2()+" "+card.getAttribute3()+" "+card.getAttribute4()+" "+card.getAttribute5();
//			pw.println(line);
//		}	
		
//		pw.println(LINE);
//		try {
//			if(pw!=null) 
//				pw.close();
//		}catch(Exception e) {
//			System.out.println(e);
//		}
//		pw.println(LINE);
//	}

	public String getTextfile() {
		return textfile;
	}
	public void setTextfile(String textfile) {
		this.textfile = textfile;
	}
	public CardDeck getCardDeck() {
		return cardDeck;
	}
	public void setCardDeck(CardDeck cardDeck) {
		this.cardDeck = cardDeck;
	}
	public PrintWriter getPw() {
		return pw;
	}
		
	public void setPw(PrintWriter pw) {
		this.pw = pw;
	}
	public static String getLine() {
		return LINE;
	}
	
}
