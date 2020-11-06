
public class Changes {
	
	public static final boolean LEFT = false;
	public static final boolean RIGHT = true;
	public static final boolean ADD = true;
	public static final boolean SUP = false;
	public boolean onlyCursor;
	public boolean right;
	public int incPos;
	public boolean add;
	public char ch;
	
	
	//nomes movem cursor
	public Changes(boolean onlyCursor, boolean right, int incPos) {
		this.onlyCursor = onlyCursor;
		this.right = right;
		this.incPos = incPos;
	}


	//afegim o treiem chars
	public Changes(boolean onlyCursor, boolean right, int incPos, boolean add, char ch) {
		this.onlyCursor = onlyCursor;
		this.right = right;
		this.incPos = incPos;
		this.add = add;
		this.ch = ch;
	}
	
}
