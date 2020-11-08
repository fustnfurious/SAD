import java.util.ArrayList;

public class Changes {
	
	public static final int LEFT = 2;
	public static final int RIGHT = 1;
	public static final int UP = 3;
	public static final int DOWN = 4;
	public static final int SUP = 0;
	public static final int BKSP = 1;
	public static final int Override = 0;
	public static final int NotOverride = 1;
	public static final boolean OnlyCursor = true;
	public static final boolean NotOnlyCursor = false;
	public static final boolean ADD = true;
	public static final boolean DEL = false;
	
	public boolean onlyCursor;
	public boolean add; //afegir o treure
	public int direction;
	public int incPos;
	public char ch;
	public ArrayList<Character> rest;
	
	
	//nomes movem cursor
	public Changes(boolean onlyCursor, int direction, int incPos) {
		this.onlyCursor = onlyCursor;
		this.direction=direction;
		this.incPos = incPos;
	}

	//esborrem chars
	public Changes(boolean onlyCursor, boolean add, int incPos, ArrayList<Character> rest) {
		this.onlyCursor = onlyCursor;
		this.add = add;
		this.incPos = incPos;
		this.rest = rest;
	}
	
	//afegim chars
	public Changes(boolean onlyCursor, boolean add, int incPos, char ch, ArrayList<Character> rest) {
		this.onlyCursor = onlyCursor;
		this.add = add;
		this.incPos = incPos;
		this.ch = ch;
		this.rest = rest;
	}
	
}
