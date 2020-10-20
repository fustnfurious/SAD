
import java.io.*;
import java.util.ArrayList;
public class EditableBufferedReader extends BufferedReader{
	public final int LEFT = 68; //^[[D
	public final int RIGHT = 67;//^[[C
	public final int BKSPC = 127;
	public final int SUP = 51; //^[[3~
	public final int ENTER = 13;
	public final int INS = 50; //^[[2~
	public final int HOME = 72; //^[[H
	public final int END = 70; //^[[F
	public final int CtrlD = 4;
	public final int ESC = 27;
	
	public final int _LEFT = -1;
	public final int _RIGHT = -2;
	public final int _BKSPC = -3;
	public final int _SUP = -4;
	public final int _ENTER = -5;
	public final int _INS = -6; 
	public final int _HOME = -7;
	public final int _END = -8;
	public final int _CtrlD = -9;
	public final int _ERR=-10;
	
	
	int[] sca = {_LEFT, _RIGHT, _BKSPC, _SUP, _INS, _CtrlD, _HOME, _END, _ERR};
	protected Line line;
	
	public EditableBufferedReader(InputStreamReader in) {
		super(in);
	}
	
	public int tradueix() {
		int c = read();
		switch (c) {
		case CtrlD: return _CtrlD;
		case BKSPC: return _BKSPC;
		case ESC: 
			c=read();
			c=read();
			switch(c) {
			case LEFT: return _LEFT;
			case RIGHT: return _RIGHT;
			case SUP: return _SUP;
			case INS: return _INS;
			case HOME: return _HOME;
			case END: return _END;
			default: return _ERR;
			}
		default: return c;
		}
	}
	
	
	@Override
	public String readLine() {
		setRaw();
		line = new Line();
		int c=0;
		while (c!=_CtrlD) {
			try {
				c=tradueix();
				if (isSpecialCharacter(c)) {
					switch (c) {
					case _LEFT:
						line.arrowRight();
						break;
					case _RIGHT:
						line.arrowRight();
						break;
					case _BKSPC:
						line.backSpace();
						break;
					case _INS:
						break;
					case _SUP:
						line.supr();
						break;
					case _HOME:
						line.home();
						break;
					case _END:
						line.end();
						break;
					case _ERR:
						break;
					
					}
				} else {
					char b = (char) c;
					line.add(b);
					System.out.print(c);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		unsetRaw();
		return line.toString();
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
		String[] cmd = {"/bin/sh", "-c", "stty echo cooked </dev/tty"};
		try {
			Runtime.getRuntime().exec(cmd);
		} catch (IOException e) {
			e.printStackTrace();
		}
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