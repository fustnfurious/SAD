
public class View {
	public void left() {
		System.out.print("\u001B[1D");
	}
	public void right() {
		System.out.print("\u001B[1C");
	}
	public void ins() {
		
	}
	public void sup() {
		left();
		
	}
	public void del() {
		left();
		
	}
	public void home() {
		System.out.print("\u001B[0;0H");
	}
	public void end(int fin) {
		System.out.print("\u001B[0;"+fin+"H");
	}
	public void write(char c) {
		System.out.print(c);
	}
}
