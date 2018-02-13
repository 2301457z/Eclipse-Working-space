
/*
 * Class Card 
 */
public class Card {
	private String cardName; //instance variable represents the name of the card
	//instance variables represent the five attributes
	private int attribute1;
	private int attribute2;
	private int attribute3;
	private int attribute4;
	private int attribute5;
	
	/*
	 * constructor
	 */
	public Card(String cardName,int attribute1,int attribute2,int attribute3,int attribute4,int attribute5) {
		this.cardName=cardName;
		this.attribute1=attribute1;
		this.attribute2=attribute2;
		this.attribute3=attribute3;
		this.attribute4=attribute4;
		this.attribute5=attribute5;
	}
	
	/**
	 * method to get the index of the biggest attribute   
	 * @return
	 */
	public int getIndex() {
		int[] attribute=new int[] {attribute1,attribute2,attribute3,attribute4,attribute5};
		int maxIndex=0;
		for(int i=0;i<5;i++) {
			if(attribute[i]>attribute[maxIndex]) 
				maxIndex=i;
		}
		return maxIndex+1;
	}
	
	/**
	 * method to get the corresponding attribute with index i
	 * @return
	 */
	public int getAttribute(int index) {
		switch(index){
		case 1:return getAttribute1();
		case 2:return getAttribute2();
		case 3:return getAttribute3();
		case 4:return getAttribute4();
		case 5:return getAttribute5();
		default: return 0;
		}	
	}
	
	/**
	 * method to get the attribute String 
	 * @return
	 */
	public String getAttributeString(int index) {
		switch(index){
		case 1:return "Size"+" "+"Value: "+getAttribute1();
		case 2:return "Speed"+" "+"Value: "+getAttribute2();
		case 3:return "Range"+" "+"Value: "+getAttribute3();
		case 4:return "Firepower"+" "+"Value: "+getAttribute4();
		case 5:return "Cargo"+" "+"Value: "+getAttribute5();
		default: return null;
		}	
	}
	
	/*
	 * Getters and Setters method to access to the variables
	 */
	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public int getAttribute1() {
		return attribute1;
	}

	public void setAttribute1(int attribute1) {
		this.attribute1 = attribute1;
	}

	public int getAttribute2() {
		return attribute2;
	}

	public void setAttribute2(int attribute2) {
		this.attribute2 = attribute2;
	}

	public int getAttribute3() {
		return attribute3;
	}

	public void setAttribute3(int attribute3) {
		this.attribute3 = attribute3;
	}

	public int getAttribute4() {
		return attribute4;
	}

	public void setAttribute4(int attribute4) {
		this.attribute4 = attribute4;
	}

	public int getAttribute5() {
		return attribute5;
	}

	public void setAttribute5(int attribute5) {
		this.attribute5 = attribute5;
	}
	
}
