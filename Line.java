import java.util.ArrayList;

public class Line {
	protected ArrayList<Character> line;
	int pos;
	boolean insMode;
	
	public Line() {
		line = new ArrayList<Character>();
		int pos = 0;
		insMode = false;
	}
	
	public void arrowLeft() {
		if (pos!=0) {
			pos--;
		}
	}
	
	public void arrowRight() {
		if (pos<this.line.size()-1) {
			pos++;
		}
	}
	public void backSpace() {
		if (this.pos!=0) {
			line.remove(this.pos-1);
			pos--;
		}
		
	}
	
	public void home() {
		pos=0;
	}
	
	public void end() {
		pos= line.size()-1;
	}
	
	public void supr() {
		if(this.pos<line.size()-1) {
		line.remove(this.pos);
		}
	}
	
	public void add(char c) {
		line.add(this.pos, c);
		pos++;
	}
	
	public int getSize() {
		return line.size();
	}
	
	public String toString() {
		return line.toString();
	}
	
}
