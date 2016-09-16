package thing;

import java.util.ArrayList;


public class GameWorld {

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

}
