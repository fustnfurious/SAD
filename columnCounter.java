
import java.io.*;

public class columnCounter {
	
	
	public static void main(String[] args) {
		getColumns();
	}
	public static int getColumns() {
		String cmd = "tput cols";
		int cols=0;
		try {
			Process pr = Runtime.getRuntime().exec(cmd);
			BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
			cols = Integer.parseInt(in.readLine());
			System.out.println("Hi ha "+ cols + " columnes.");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return cols;
	}
}
