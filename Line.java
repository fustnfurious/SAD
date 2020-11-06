import java.util.ArrayList;
import java.util.Observable;

@SuppressWarnings("deprecation")
public class Line extends Observable {
	
	protected ArrayList<Character> line;
	int pos=0;
	boolean overrideMode;
	
	public Line() {
		line = new ArrayList<Character>();
		overrideMode = false;
	}
	
	public void arrowLeft() {
		if (pos!=0) {
			//view.left();
			pos--;
			setChanged();
			notifyObservers(new Changes(true, Changes.LEFT, 1));
		}
		
	}
	
	public void arrowRight() {
		if (pos<this.line.size()) {
			//view.right();
			pos++;
			setChanged();
			notifyObservers(new Changes(true, Changes.RIGHT, 1));
		}
	}
	public void backSpace() {
		if (this.pos!=0) {
			if(this.pos!=line.size()) { //si no esborra des del final
				line.remove(this.pos-1);
				pos--;
				/*view.left();
				for(int i=pos; i<line.size();i++) { // imprimim la resta de la linia
					view.write(line.get(i));
				}
				view.write((char)32); //espai
				view.home(line.size()-pos +1);*/
			} else { 					// si esborra des del final
				line.remove(this.pos-1);
				pos--;
				/*view.left();
				view.write((char)32); //espai
				view.left();*/
			}
		}
		
	}
	
	public void home() {
		//view.home(pos);
		int posInc = pos;
		pos=0;
		setChanged();
		notifyObservers(new Changes(true, Changes.LEFT, posInc));
		
	}
	
	public void end() {
		//view.end(line.size()-pos);
		int posInc = line.size()-pos;
		pos=line.size();
		setChanged();
		notifyObservers(new Changes(true, Changes.RIGHT, posInc));
	}
	
	public void switchMode() {
		overrideMode=!overrideMode;
	}
	public void sup() {
		if (this.pos!=line.size()) {
				line.remove(this.pos);
				/*for(int i=pos; i<line.size();i++) { // imprimim la resta de la linia
					view.write(line.get(i));
				}
				view.write((char)32); //espai
				view.home(line.size()-pos+1);*/
		}
	}
	
	public void add(char c) {
		if(overrideMode) {
			if (pos==line.size()) {// final de la linia
				line.add(c);
				pos++;
				//view.write(c);
			} else {
				line.set(pos, c);
				pos++;
				//view.write(c);
			}
		} else {
				line.add(pos,c);
				pos++;
				setChanged();
				notifyObservers(new Changes(false, Changes.RIGHT, 1, Changes.ADD, c));
				//view.write(c);
				
				/*for(int i=pos; i<line.size();i++) { // imprimim la resta de la linia
					view.write(line.get(i));
				}
				view.write((char)32); //espai
				view.home(line.size()-pos);*/
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
