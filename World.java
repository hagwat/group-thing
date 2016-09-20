package thing;

import java.util.ArrayList;


public class World {

	private volatile int ticktock = 1;

	public synchronized void clockTick() {

		if (ticktock == 1) {
			ticktock = 2;
			System.out.println("tick");
		} else {
			ticktock = 1;
			System.out.println(" tock");
		}
	}

	public synchronized int registerAvatar() {
		return 1;
	}

	
	private int state;
	
	public static final int WAITING = 0;
	public static final int READY = 1;
	public static final int PLAYING = 2;
	public static final int GAMEOVER = 3;
	public static final int GAMEWON = 4;
	
	public void setState(int state) {
		this.state = state;
	}
	
	public int state() {
		return state;
	}
	
	
}
