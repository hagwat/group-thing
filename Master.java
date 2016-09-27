package swen222_group_project.control;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

//<<<<<<< HEAD:src/swen222_group_project/Master.java
//=======
import swen222_group_project.game.GameWorld;

//testing
//>>>>>>> 37646a1cb650b70ab7752d315bf164c287f66770:src/swen222_group_project/control/Master.java

public class Master extends Thread{
	private final GameWorld board;
	private final int broadcastClock;
	private final int uid;
	private final Socket socket;

	public Master(Socket socket, int uid, int broadcastClock, GameWorld board) {
		this.board = board;
		this.broadcastClock = broadcastClock;
		this.socket = socket;
		this.uid = uid;
	}

	public void run() {
		try {
			DataInputStream input = new DataInputStream(socket.getInputStream());
			DataOutputStream output = new DataOutputStream(socket.getOutputStream());
			// First, write the period to the stream
			output.writeInt(uid);
			boolean exit=false;
			while(!exit) {
				try {
					if(input.available() != 0) {
						// read direction event from client.
						int dir = input.readInt();
						switch(dir) {//TODO read client input and move players on the board accordingly
							case 1:
								board.getPlayer(uid).jump();
								break;
							case 3:
								board.getPlayer(uid).moveRight();
								break;
							case 4:
								board.getPlayer(uid).moveLeft();
								break;
						}
					}
					//board.player.move();
					byte[] state = board.toByteArray();
					output.writeInt(state.length);
					output.write(state);
					output.flush();
					Thread.sleep(broadcastClock);
				} catch(InterruptedException e) {

				}
			}
			socket.close(); // release socket ... v.important!
		} catch(IOException e) {
			System.err.println("PLAYER " + uid + " DISCONNECTED");
			//board.disconnectPlayer(uid);
		}
	}
}
