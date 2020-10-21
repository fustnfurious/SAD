
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
	public void home(int pos) {
		System.out.print("\u001B["+pos+"D");
	}
	public void end(int fin) {
		System.out.print("\u001B["+fin+"C");
	}
	public void write(char c) {
		System.out.print(c);
	}
}
