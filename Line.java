import java.util.ArrayList;
import java.util.Observable;

@SuppressWarnings("deprecation")
public class Line extends Observable {
	
	protected ArrayList<Character> line;
	int pos;
	int posVer;
	int cols;
	boolean overrideMode;
	
	public Line(int cols) {
		line = new ArrayList<Character>();
		overrideMode = false;
		this.cols=cols;
		pos=0;
		posVer=0;
	}
	
	public void setMouse(Object pos) {
		
	}
	
	public void arrowLeft() {
		if(pos!=0) {
			pos--;
			if((pos+1)%cols==0) {
				setChanged();
				notifyObservers(new Changes(Changes.OnlyCursor, Changes.UP, 1));
				setChanged();
				notifyObservers(new Changes(Changes.OnlyCursor, Changes.RIGHT, cols-1));
			} else {
				setChanged();
				notifyObservers(new Changes(Changes.OnlyCursor, Changes.LEFT, 1));
			}
		}
	}
	
	public void arrowRight() {
		if(pos<line.size()) {
			pos++;
			if((pos)%cols==0) {
				setChanged();
				notifyObservers(new Changes(Changes.OnlyCursor, Changes.DOWN, 1));
				setChanged();
				notifyObservers(new Changes(Changes.OnlyCursor, Changes.LEFT, cols-1));
			} else {
				setChanged();
				notifyObservers(new Changes(Changes.OnlyCursor, Changes.RIGHT, 1));
			}
		}
		
	}
	
	public void arrowUp() {
		if(pos-cols>=0) {
			pos-=cols;
			setChanged();
			notifyObservers(new Changes(Changes.OnlyCursor, Changes.UP, 1));
		}
		
	}
	
	public void arrowDown() {
		if(pos+cols<line.size()) {
			pos+=cols;
			setChanged();
			notifyObservers(new Changes(Changes.OnlyCursor, Changes.DOWN, 1));
		} else {
			int posIncLeft=pos+cols-line.size();
			pos=line.size();
			setChanged();
			notifyObservers(new Changes(Changes.OnlyCursor, Changes.DOWN, 1));
			setChanged();
			notifyObservers(new Changes(Changes.OnlyCursor, Changes.LEFT, posIncLeft));
			
			
		}
		
	}
	
	public void backSpace() {
		if (this.pos!=0) {
				line.remove(this.pos-1);
				pos--;
				ArrayList<Character> rest = new ArrayList<>();;
				for(int i=pos; i<line.size();i++) { 
					rest.add(line.get(i));
				}
				setChanged();
				notifyObservers(new Changes(Changes.NotOnlyCursor, Changes.DEL, Changes.BKSP, rest));
		}
		
	}
	
	public void home() {
		int posInc = pos;
		pos=0;
		setChanged();
		notifyObservers(new Changes(Changes.OnlyCursor, Changes.LEFT, posInc));
		
	}
	
	public void end() {
		int posInc = line.size()-pos;
		pos=line.size();
		setChanged();
		notifyObservers(new Changes(Changes.OnlyCursor, Changes.RIGHT, posInc));
	}
	
	public void switchMode() {
		overrideMode=!overrideMode;
	}
	public void sup() {
		if (this.pos!=line.size()) {
				line.remove(this.pos);
				ArrayList<Character> rest = new ArrayList<>();;
				for(int i=pos; i<line.size();i++) { 
					rest.add(line.get(i));
				}
				setChanged();
				notifyObservers(new Changes(Changes.NotOnlyCursor, Changes.DEL, Changes.SUP, rest));
		}
	}
	
	public void add(char c) {
		if(overrideMode) {
			if(pos<line.size()) {
				line.set(pos,c);
			} else {
				line.add(pos, c);
			}
			pos++;
			setChanged();
			notifyObservers(new Changes(Changes.NotOnlyCursor, Changes.ADD, Changes.Override, c, null));
			
		} else {
			line.add(pos,c);
			pos++;
			ArrayList<Character> rest = new ArrayList<>();;
			for(int i=pos; i<line.size();i++) { 
				rest.add(line.get(i));
			}
			setChanged();
			notifyObservers(new Changes(Changes.NotOnlyCursor, Changes.ADD, Changes.NotOverride, c, rest));
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
