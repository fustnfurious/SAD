import java.util.ArrayList;
import java.util.List;

public class Line {
	protected ArrayList<Character> line;
	protected View view;
	int pos;
	boolean insMode;
	
	public Line() {
		line = new ArrayList<Character>();
		view = new View();
		int pos = 0;
		insMode = true;
	}
	
	public void arrowLeft() {
		if (pos!=0) {
			view.left();
			pos--;
		}
		
	}
	
	public void arrowRight() {
		if (pos<this.line.size()) {
			view.right();
			pos++;
		}
	}
	public void backSpace() {
		if (this.pos!=0) {
			line.remove(this.pos-1);
			pos--;
			view.left();
			List<Character> rest = line.subList(pos, line.size()-1);
			for(int i=0; i<rest.size();i++) {
				view.write(rest.get(i));
			}
			
		}
		
	}
	
	public void home() {
		pos=0;
		view.home();
	}
	
	public void end() {
		pos= line.size();
		view.end(pos);
	}
	
	public void switchMode() {
		insMode=!insMode;
	}
	public void sup() {
		/*if(this.pos<line.size()-1) {
		line.remove(this.pos);
		List<Character> rest = line.subList(pos, line.size()-1);
		view.write(rest);
		}*/
	}
	
	public void add(char c) {
		if(insMode) {
			if (pos==line.size()) {
				line.add(c);
				view.write(c);
			} else {
				
			}
		} else {
			
		}
	}
	
	public int getSize() {
		return line.size();
	}
	
	public String toString() {
		String str="";
		for(int i=0; i<line.size();i++) {
			str = str + line.get(i);
		}
		return str;
	}
	
}
