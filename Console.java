import java.util.Observable;
import java.util.Observer;

@SuppressWarnings("deprecation")
public class Console implements Observer{
	public int cursor=0;
	
	public void ins() {
		
	}
	public void sup() {
		
		
	}
	public void del() {
		
		
	}
	public void left(int pos) {
		System.out.print("\u001B["+pos+"D");
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
		if (c.onlyCursor) {
			if(c.right) {
				right(c.incPos);
			} else {
				left(c.incPos);
			}
		} else {
			//marron
		}
		
	}
}
