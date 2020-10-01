package practica1;
import java.io.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class EditableBufferedReader extends BufferedReader{
	
	protected final int[] sca = {KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_END, KeyEvent.VK_HOME, KeyEvent.VK_LEFT, KeyEvent.VK_INSERT, KeyEvent.VK_DELETE, KeyEvent.VK_BACK_SPACE};
	protected char[] text;
	protected int position=0;
	
	public EditableBufferedReader(InputStreamReader in) {
		super(in);
	}
	
	@Override
	public int read() {
		
		return 0;
	}
	
	@Override
	public String readLine() {
		char c=0;
		while (c!=KeyEvent.VK_ENTER) {
			c=0;
			if(isSpecialCharacter(c)) {
				switch(c) {
				case KeyEvent.VK_LEFT:position--;
				case KeyEvent.VK_RIGHT:position++;
				case KeyEvent.VK_BACK_SPACE:
				case KeyEvent.VK_DELETE:
				case KeyEvent.VK_INSERT:
				case KeyEvent.VK_HOME: position=0;
				case KeyEvent.VK_END: position=text.length-1;
				}
			} else {
				esborra(9);
			}
				
		}
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
	
	public static void esborra(int position) {
		
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
