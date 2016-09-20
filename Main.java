package thing;

import java.io.IOException;

public class Main {

	private static final int DEFAULT_CLK_PERIOD = 20;
	private static final int DEFAULT_BROADCAST_CLK_PERIOD = 5;

	@SuppressWarnings("unused")
	public static void main(String[] args) {

		String filename = "default world";

		boolean server = false;
		int nclients = 0;
		String url = null;

		int gameClock = DEFAULT_CLK_PERIOD;
		int port = 32768;

		// TODO Arguments section
		for (int i = 0; i < args.length; i++) {
			if (args[i].startsWith("-")) {
				if (args.equals("-server")) {
					server = true;
					nclients = Integer.parseInt(args[++i]);
				}
			}
		}

		// TODO Sanity checks
		if (url != null && server) {
			System.out.println("Cannot be a server and connect to another server!");
			System.exit(1);
		}

		try {
			if (server) {
				World world = createWorldFromFile(filename);
			} else if (url != null) {
				// TODO Run in client mode
				// runClient(url,port);
			} else {
				// TODO single user game
				World world = createWorldFromFile(filename);
				singleUserGame(gameClock, world);
			}
		} catch (IOException ioe) {
			System.out.println("I/O error: " + ioe.getMessage());
			ioe.printStackTrace();
			System.exit(1);
		}
		System.exit(0);
	}

	private static World createWorldFromFile(String filename) throws IOException {
		// TODO
		System.out.println("Game world created: " + filename);
		return new World();
	}

	private static void singleUserGame(int gameClock, World world) throws IOException {

		int playerID = world.registerAvatar();

		WorldFrame display = new WorldFrame();

		ClockThread clock = new ClockThread(50, world, display);

		// byte[] state = world.toByteArray(); TODO

		clock.start();

		while (/* display.isVisible() */true) {

			// keep going until the frame becomes invisible
			world.setState(World.READY);
			pause(3000);
			world.setState(World.PLAYING);
			// now, wait for the game to finish
			while (world.state() == World.PLAYING) {
				Thread.yield();
			}
			// If we get here, then we're in game over mode
			pause(3000);
			// Reset board state
			// world.fromByteArray(state);

		}
	}

	private static void pause(int delay) {
		try {
			System.out.println("pause");
			Thread.sleep(delay);
		} catch (InterruptedException e) {
		}
	}

}
