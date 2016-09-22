package swen222_group_project.control;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import swen222_group_project.game.GameWorld;


public class Slave extends Thread implements KeyListener {
	private final Socket socket;
	private GameWorld game;
	private DataOutputStream output;
	private DataInputStream input;
	private int uid;
	private int totalSent;

	/**
	 * Construct a slave connection from a socket. A slave connection does no
	 * local computation, other than to display the current state of the board;
	 * instead, board logic is controlled entirely by the server, and the slave
	 * display is only refreshed when data is received from the master
	 * connection.
	 *
	 * @param socket
	 * @param dumbTerminal
	 */
	public Slave(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		try {
			output = new DataOutputStream(socket.getOutputStream());
			input = new DataInputStream(socket.getInputStream());

			// First job, is to read the period so we can create the clock
			uid = input.readInt();
			int width = input.readInt();
			int height = input.readInt();
			int bitwidth = width%8 == 0 ? width : width+8;
			int bitsize = (bitwidth/8)*height;
			byte[] wallBytes = new byte[bitsize];
			input.read(wallBytes);
			game = new GameWorld();
			//game.wallsFromByteArray(wallBytes);
			//WorldFrame display = new WorldFrame("Pacman (client@" + socket.getInetAddress() + ")",game,uid,this);
			boolean exit=false;
			long totalRec = 0;

			while(!exit) {
				// read event
				int amount = input.readInt();
				byte[] data = new byte[amount];
				input.readFully(data);
				//game.fromByteArray(data);
				//display.repaint();
				totalRec += amount;
				// print out some useful information about the amount of data
				// sent and received
				System.out.print("\rREC: " + (totalRec / 1024) + "KB ("
						+ (rate(amount) / 1024) + "KB/s) TX: " + totalSent
						+ " Bytes");
			}
			socket.close(); // release socket ... v.important!
		} catch(IOException e) {
			System.err.println("I/O Error: " + e.getMessage());
			e.printStackTrace(System.err);
		}
	}

	/**
	 * The following method calculates the rate of data received in bytes/s, albeit
	 * in a rather coarse manner.
	 *
	 * @param amount
	 * @return
	 */
	private int rate(int amount) {
		rateTotal += amount;
		long time = System.currentTimeMillis();
		long period = time - rateStart;
		if(period > 1000) {
			// more than a second since last calculation
			currentRate = (rateTotal * 1000) / (int) period;
			rateStart = time;
			rateTotal = 0;
		}

		return currentRate;
	}
	private int rateTotal = 0;   // total accumulated this second
	private int currentRate = 0; // rate of reception last second
	private long rateStart = System.currentTimeMillis();  // start of this accumulation period

	// The following intercept keyboard events from the user.

	public void keyPressed(KeyEvent e) {
		try {
			int code = e.getKeyCode();
			if(code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_KP_RIGHT) {
				output.writeInt(3);
				totalSent += 4;
			} else if(code == KeyEvent.VK_LEFT || code == KeyEvent.VK_KP_LEFT) {
				output.writeInt(4);
				totalSent += 4;
			} else if(code == KeyEvent.VK_UP) {
				output.writeInt(1);
				totalSent += 4;
			} else if(code == KeyEvent.VK_DOWN) {
				output.writeInt(2);
				totalSent += 4;
			}
			output.flush();
		} catch(IOException ioe) {
			// something went wrong trying to communicate the key press to the
			// server.  So, we just ignore it.
		}
	}

	public void keyReleased(KeyEvent e) {
	}

	public void keyTyped(KeyEvent e) {

	}
}
