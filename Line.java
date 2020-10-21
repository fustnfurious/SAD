import java.util.ArrayList;
import java.util.List;

public class Line {
	protected ArrayList<Character> line;
	protected View view;
	int pos;
	boolean overrideMode;
	
	public Line() {
		line = new ArrayList<Character>();
		view = new View();
		int pos = 0;
		overrideMode = true;
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
			if(this.pos!=line.size()) { //si no esborra des del final
				line.remove(this.pos-1);
				pos--;
				view.left();
				for(int i=pos; i<line.size();i++) { // imprimim la resta de la linia
					view.write(line.get(i));
				}
				view.write((char)32);
				view.home(line.size()-pos +1);
			} else { 					// si esborra des del final
				line.remove(this.pos-1);
				pos--;
				view.left();
				view.write((char)32);
				view.left();
			}
			
			
		}
		
	}
	
	public void home() {
		view.home(pos);
		pos=0;
	}
	
	public void end() {
		view.end(line.size()-pos);
		pos=line.size();
	}
	
	public void switchMode() {
		overrideMode=!overrideMode;
	}
	public void sup() {
		/*if(this.pos<line.size()-1) {
		line.remove(this.pos);
		List<Character> rest = line.subList(pos, line.size()-1);
		view.write(rest);
		}*/
	}
	
	public void add(char c) {
		if(overrideMode) {
			if (pos==line.size()) {
				line.add(c);
				pos++;
				view.write(c);
			} else {
				line.set(pos, c);
				pos++;
				view.write(c);
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
