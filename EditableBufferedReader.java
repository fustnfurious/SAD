import java.io.*;
@SuppressWarnings("deprecation")
public class EditableBufferedReader extends BufferedReader{
	public final int LEFT = 68; //^[[D
	public final int RIGHT = 67;//^[[C
	public final int UP = 65; //^[[A
	public final int DOWN = 66;//^[[B
	public final int BKSPC = 127;
	public final int SUP = 51; //^[[3~
	public final int ENTER = 13;
	public final int INS = 50; //^[[2~
	public final int HOME = 72; //^[[H
	public final int END = 70; //^[[F
	public final int CtrlD = 4;
	public final int ESC = 27;
	public final int MOUSE = 60;
	
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
	public final int _UP = -11;
	public final int _DOWN=-12;
	public final int _MOUSE=-13;
	
	
	int[] sca = {_LEFT, _RIGHT, _BKSPC, _SUP, _INS, _CtrlD, _HOME, _END, _ERR, _UP, _DOWN, _MOUSE};
	protected Line line;
	protected Console console;
	
	public EditableBufferedReader(InputStreamReader in) {
		super(in);
		line = new Line(getCols());
		console = new Console(getCols());
		line.addObserver(console);
		
	}
	
	public int tradueix() throws IOException{
		int c = read();
		switch (c) {
		case CtrlD: return _CtrlD;
		case BKSPC: return _BKSPC;
		case ENTER: return _ENTER;
		case ESC: 
			c=read();
			c=read();
			switch(c) {
			case MOUSE: return _MOUSE;
			case LEFT: return _LEFT;
			case RIGHT: return _RIGHT;
			case UP: return _UP;
			case DOWN: return _DOWN;
			case SUP: 
				read();
				return _SUP;
			case INS:
				read();
				return _INS;
			case HOME: return _HOME;
			case END: return _END;
			default: return _ERR;
			}
		default: return c;
		}
	}
	
	
	@Override
	public String readLine() {
		startMouse();
		setRaw();
		int c=0;
		while (c!=_CtrlD) {
			try {
				c=tradueix();
				if (isSpecialCharacter(c)) {
					switch (c) {
					case _MOUSE:
						line.setMouse(getMousePos());
						break;
					case _LEFT:
						line.arrowLeft();
						break;
					case _RIGHT:
						line.arrowRight();
						break;
					case _UP:
						line.arrowUp();
						break;
					case _DOWN:
						line.arrowDown();
						break;
					case _BKSPC:
						line.backSpace();
						break;
					case _INS:
						line.switchMode();
						break;
					case _SUP:
						line.sup();
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
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		unsetRaw();
		stopMouse();
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
	
	public void startMouse(){
        String[] cmd = {"/bin/sh", "echo -e \\e[?1000h \\e[?1006h"};
        try {
                Runtime.getRuntime().exec(cmd);
        } catch (IOException e) {
                e.printStackTrace();
        }
	}
	
	public void stopMouse() {
		String[] cmd = {"/bin/sh", "echo -e \\e[?1000l"};
        try {
                Runtime.getRuntime().exec(cmd);
        } catch (IOException e) {
                e.printStackTrace();
        }
	}
	
	public Object getMousePos() {
		int c=2;
		return new Object() {
			public int posHor=c;
			public int posVer=0;
		};
	}
	
	public int getCols() {
		String cmd = "tput cols";
		int cols=0;
		try {
			Process pr = Runtime.getRuntime().exec(cmd);
			BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
			cols = Integer.parseInt(in.readLine());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return cols;
	}
	
	public Boolean isSpecialCharacter(int c) {
		for(int i=0; i<sca.length; i++) {
			if(c==sca[i]) {
				return true;
			}
		}
		return false;
	}
}