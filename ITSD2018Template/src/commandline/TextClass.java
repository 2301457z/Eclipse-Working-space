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
/**
 * The textClass is used to deal with the file reader and writer method
 * @author 2301457z
 *
 */
public class TextClass {
	private String textfile="StarCitizenDeck.txt";
	private CardDeck cardDeck;
	private static final String LINE="------------------------------------------";
	private PrintWriter pw=null;
	//constructor
	public TextClass() {
		cardDeck=new CardDeck();
		File file=new File("toptrumps.log");
		try {
			pw=new PrintWriter(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
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
