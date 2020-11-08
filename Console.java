import java.util.Observable;
import java.util.Observer;

@SuppressWarnings("deprecation")
public class Console implements Observer{
	public int cols;
	
	public Console(int cols) {
		this.cols=cols;
	}
	
	public void left(int pos) {
		if(pos!=0) {
		System.out.print("\u001B["+pos+"D");
		}
	}
	public void right(int fin) {
		System.out.print("\u001B["+fin+"C");
	}
	
	public void up(int inc) {
		System.out.print("\u001B["+inc+"A");
	}
	
	public void down(int inc) {
		System.out.print("\u001B["+inc+"B");
	}
	
	public void write(char c) {
		System.out.print(c);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		Changes c = (Changes) arg;
		if (c.onlyCursor) { // nomes movem cursor sense modificar linia
			switch(c.direction) {
			case Changes.RIGHT: right(c.incPos);
				break;
			case Changes.LEFT: left(c.incPos);
				break;
			case Changes.UP: up(1);
				break;
			case Changes.DOWN: down(1);
				break;
			}
		} else {
			if (c.add) { // escriure 
				if(c.incPos==0) { // override
					write(c.ch);
				} else { // no override
					write(c.ch);
					for (int i=0; i<c.rest.size();i++) {
						write(c.rest.get(i));
					}
					int ver = c.rest.size()/cols;
					int hor = c.rest.size()%cols;
					left(hor);
					if(ver!=0) {
						up(ver);
					}
					
				}
			} else {  //esborrar o suprimir
					left(c.incPos); //veure Changes.SUP/BKSP
					for (int i=0; i<c.rest.size();i++) {
						write(c.rest.get(i));
					}
					write((char) 32);
					int ver = c.rest.size()/cols;
					int hor = c.rest.size()%cols;
					left(hor+1);
					if(ver!=0) {
						up(ver);
					}
			}
		}
		
	}
}
