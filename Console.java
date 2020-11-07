import java.util.Observable;
import java.util.Observer;

@SuppressWarnings("deprecation")
public class Console implements Observer{
	
	public void left(int pos) {
		if(pos!=0) {
		System.out.print("\u001B["+pos+"D");
		}
	}
	public void right(int fin) {
		System.out.print("\u001B["+fin+"C");
	}
	public void write(char c) {
		System.out.print(c);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		Changes c = (Changes) arg;
		if (c.onlyCursor) { // nomes movem cursor sense modificar linia
			if(c.right) {
				right(c.incPos);
			} else {
				left(c.incPos);
			}
		} else {
			if (c.right) { // escriure 
				if(c.incPos==0) { // override
					write(c.ch);
				} else { // no override
					write(c.ch);
					for (int i=0; i<c.rest.size();i++) {
						write(c.rest.get(i));
					}
					left(c.rest.size());
				}
			} else {  //esborrar o suprimir
					left(c.incPos); //veure Changes.SUP/BKSP
					for (int i=0; i<c.rest.size();i++) {
						write(c.rest.get(i));
					}
					write((char) 32);
					left(c.rest.size()+1);
			}
		}
		
	}
}
