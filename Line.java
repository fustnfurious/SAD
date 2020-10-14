import java.util.ArrayList;

public class Line {
	protected ArrayList<Character> line;
	
	public Line() {
		line = new ArrayList<Character>();
	}
	
	public void backSpace(int pos) {
		if (pos!=0) {
			line.remove(pos-1);
		}
	}
	
	public void supr(int pos) {
		if (pos!=0) {
			line.remove(pos-1);
		}
	}
	
	public void add(int pos, char c) {
		line.add(pos, c);
	}
	
	public int getSize() {
		return line.size();
	}
	
	public String toString() {
		return line.toString();
	}
	
}
