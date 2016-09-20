package thing;

import java.net.Socket;

public class Master extends Thread {

	private final World world;
	private final int broadcastClock;
	private final int uid;
	private final Socket socket;

	public Master(Socket socket, int uid, int broadcastClock, World world) {
		this.world = world;
		this.broadcastClock = broadcastClock;
		this.socket = socket;
		this.uid = uid;
	}


}
