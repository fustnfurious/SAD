package Reading;
import java.io.*;
import java.util.ArrayList;

public class EditableBufferedReader extends BufferedReader{
	int[] sca;
	protected int[] text;
	protected int pos=0;
	
	public EditableBufferedReader(InputStreamReader in) {
		super(in);
	}
	
	
	@Override
	public String readLine() {
		setRaw();
		int c=0;
		while (true) {
			try {
				c=read();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if(c==-1) {
				break;
			}
			if (isSpecialCharacter(c)) {
				switch(c) {
				
				}
			} else {
				text[pos]=c;
				pos++;
			}
			actualitzaVista();
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
	
	public static void esborra(int position) {
		
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