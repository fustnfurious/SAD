import java.util.ArrayList;

public class Changes {
	
	public static final boolean LEFT = false;
	public static final boolean RIGHT = true;
	public static final int SUP = 0;
	public static final int BKSP = 1;
	public boolean onlyCursor;
	public boolean right;
	public int incPos;
	public char ch;
	public ArrayList<Character> rest;
	
	
	//nomes movem cursor
	public Changes(boolean onlyCursor, boolean right, int incPos) {
		this.onlyCursor = onlyCursor;
		this.right = right;
		this.incPos = incPos;
	}

	//esborrem chars
	public Changes(boolean onlyCursor, boolean right, int incPos, ArrayList<Character> rest) {
		this.onlyCursor = onlyCursor;
		this.right = right;
		this.incPos = incPos;
		this.rest = rest;
	}
	
	//afegim chars
	public Changes(boolean onlyCursor, boolean right, int incPos, char ch, ArrayList<Character> rest) {
		this.onlyCursor = onlyCursor;
		this.right = right;
		this.incPos = incPos;
		this.ch = ch;
		this.rest = rest;
	}
	
}
