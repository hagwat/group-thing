package swen222_group_project;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import swen222_group_project.control.ClockThread;
import swen222_group_project.control.Master;
import swen222_group_project.control.Slave;
import swen222_group_project.game.GameWorld;
import swen222_group_project.ui.WorldFrame;


public class Main {

	private static final int DEFAULT_CLK_PERIOD = 20;
	private static final int DEFAULT_BROADCAST_CLK_PERIOD = 5;

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		String filename = null;
		boolean server = false;
		int nclients = 0;
		String url = null;
		int gameClock = DEFAULT_CLK_PERIOD;
		int broadcastClock = DEFAULT_BROADCAST_CLK_PERIOD;
		int port = 32768; // default

		// TODO Arguments section
		for (int i = 0; i != args.length; ++i) {
			if (args[i].startsWith("-")) {
				String arg = args[i];
				if (arg.equals("-help")) {
					// usage();
					System.exit(0);
				} else if (arg.equals("-server")) {
					server = true;
					nclients = Integer.parseInt(args[++i]);
				} else if (arg.equals("-connect")) {
					url = args[++i];
				} else {
					filename = args[i];
				}
			}
		}

		// Sanity checks
		if (url != null && server) {
			System.out.println("Cannot be a server and connect to another server!");
			System.exit(1);
		} else if (url != null && gameClock != DEFAULT_CLK_PERIOD) {
			System.out.println("Cannot overide clock period when connecting to server.");
			System.exit(1);
		//} else if (url == null && filename == null) {
		//	System.out.println("Board file must be provided for single user, or server mode.");
		//	System.exit(1);
		}

		try {
			if (server) {
				System.out.println("server mode");
				GameWorld world = createWorldFromFile(filename);
				runServer(port,nclients,gameClock,broadcastClock, world);
			} else if (url != null) {
				System.out.println("client mode");
				runClient(url,port);
			} else {
				System.out.println("single user mode");
				GameWorld world = createWorldFromFile(filename);
				 singleUserGame(gameClock, world);
			}
		} catch (IOException ioe) {
			System.out.println("I/O error: " + ioe.getMessage());
			ioe.printStackTrace();
			System.exit(1);
		}
		System.exit(0);
	}



	public static void runServer(int port, int nclients, int gameClock, int broadcastClock, GameWorld game) {
		ClockThread clk = new ClockThread(gameClock, game, null);
		// Listen for connections
		System.out.println("Server listening on port " + port);
		System.out.println("Server awaiting " + nclients + " clients");
		try {
			Master[] connections = new Master[nclients];
			// Now, we await connections.
			ServerSocket ss = new ServerSocket(port);
			while (true) {
				// Wait for a socket
				Socket s = ss.accept();
				System.out.println("ACCEPTED CONNECTION FROM: " + s.getInetAddress());
				int uid = game.registerAvatar();
				connections[--nclients] = new Master(s, uid, broadcastClock, game);
				connections[nclients].start();
				if (nclients == 0) {
					System.out.println("ALL CLIENTS ACCEPTED --- GAME BEGINS");
					multiUserGame(clk, game, connections);
					System.out.println("ALL CLIENTS DISCONNECTED --- GAME OVER");
					return; // done
				}
			}
		} catch (IOException e) {
			System.err.println("I/O error: " + e.getMessage());
		}
	}

	private static void runClient(String addr, int port) throws IOException {
		Socket s = new Socket(addr, port);
		System.out.println("CLIENT CONNECTED TO " + addr + ":" + port);
		new Slave(s).run();
	}


	private static void singleUserGame(int gameClock, GameWorld world)
			throws IOException {

		int playerID = world.registerAvatar();

		WorldFrame display = new WorldFrame(null);

		ClockThread clock = new ClockThread(50, world, display);

		// byte[] state = world.toByteArray(); TODO

		clock.start();

		while (display.isVisible()) {

			// keep going until the frame becomes invisible
			world.setState(GameWorld.READY);
			pause(3000);
			world.setState(GameWorld.PLAYING);
			// now, wait for the game to finish
			while (world.state() == GameWorld.PLAYING) {
				Thread.yield();
			}
			// If we get here, then we're in game over mode
			pause(3000);
			// Reset board state
			// world.fromByteArray(state);

		}
	}

	/**
	 *
	 */
	private static void multiUserGame(ClockThread clk, GameWorld game,
			Master... connections) throws IOException {
		// save initial state of board, so we can reset it.
		byte[] state = game.toByteArray();

		clk.start(); // start the clock ticking!!!

		// loop forever
		while(atleastOneConnection(connections)) {
			game.setState(GameWorld.READY);
			pause(3000);
			game.setState(GameWorld.PLAYING);
			// now, wait for the game to finish
			while(game.state() == GameWorld.PLAYING) {
				Thread.yield();
			}
			// If we get here, then we're in game over mode
			pause(3000);
			// Reset board state
			game.setState(GameWorld.WAITING);
			game.fromByteArray(state);
		}
	}
	private static boolean atleastOneConnection(Master... connections) {
		for (Master m : connections) {
			if (m.isAlive()) {
				return true;
			}
		}
		return false;
	}

	private static GameWorld createWorldFromFile(String filename) throws IOException {
		// TODO
		System.out.println("Game world created: " + filename);
		return new GameWorld();
	}

	private static void pause(int delay) {
		try {
			Thread.sleep(delay);
		} catch(InterruptedException e){
		}
	}
}
