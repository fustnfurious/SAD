
import java.io.*;
import java.util.ArrayList;
public class EditableBufferedReader extends BufferedReader{
	public final int LEFT = 279168;
	public final int RIGHT = 279167;
	public final int BKSPC = 127;
	public final int SUP = 279151126;
	public final int ENTER = 13;
	public final int INS = 279150126;
	public final int CtrlD = 4;
	int[] sca = {LEFT, RIGHT, BKSPC, SUP, INS, CtrlD};
	public static ArrayList<String> text = new ArrayList<>();
	protected int pos=0;
	
	public EditableBufferedReader(InputStreamReader in) {
		super(in);
	}
	
	
	@Override
	public String readLine() {
		setRaw();
		int c=0;
		while (c!=CtrlD) {
			try {
				c=read();
				if (isSpecialCharacter(c)) {
					switch (c) {
					case LEFT:
						if(pos!=0) {
							pos--;
						}
						break;
					case RIGHT:
						if(pos<text.size()) {
							pos++;
						}
						break;
					case BKSPC:
						esborra(pos);
						pos--;
						break;
					case INS:
						break;
					case SUP:
						suprimeix(pos);
						pos--;
						break;
					case CtrlD:
						break;
					
					}
				} else {
					char b = (char) c;
					text.add(pos, String.valueOf(b));
					pos++;
					System.out.print(b);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		unsetRaw();
		return text.toString();
	}
	
	public void setRaw() {
		String[] cmd = {"/bin/sh", "-c", "stty -echo raw </dev/tty"};
		try {
			Runtime.getRuntime().exec(cmd);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void unsetRaw() {
		String[] cmd = {"/bin/sh", "-c", "stty cooked </dev/tty"};
		try {
			Runtime.getRuntime().exec(cmd);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void esborra(int pos) {
		if (pos!=0) {
			text.remove(pos-1);
		}
	}
	public static void suprimeix(int pos) {
		if (pos!=0) {
			text.remove(pos);
		}
	}
	public void actualitzaVista() {
		
	}
	
	public Boolean isSpecialCharacter(int c) {
		for(int i=0; i<sca.length; i++) {
			if(c==i) {
				return true;
			}
		}
		return false;
	}
}