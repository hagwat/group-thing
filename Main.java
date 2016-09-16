package thing;

import java.io.IOException;


public class Main {

	private static final int DEFAULT_CLK_PERIOD = 20;
	private static final int DEFAULT_BROADCAST_CLK_PERIOD = 5;

	@SuppressWarnings("unused")
	public static void main(String[] args) {

		boolean server = false;
		String filename = "default world";

		String url = null;
		int gameClock = DEFAULT_CLK_PERIOD;
		int port = 32768;

		// TODO Arguments section

		// TODO Sanity checks

		try {
			if (server) {
				GameWorld world = createWorldFromFile(filename);
			} else if (url != null) {
				// TODO Run in client mode
				// runClient(url,port);
			} else {
				// TODO single user game
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

	private static GameWorld createWorldFromFile(String filename) throws IOException {
		// TODO
		System.out.println("Game world created: " + filename);
		return new GameWorld();
	}

	private static void singleUserGame(int gameClock, GameWorld world)
			throws IOException {

		int playerID = world.registerAvatar();
		WorldFrame display = new WorldFrame();
		ClockThread clock = new ClockThread(50, world, display);
System.out.println("about to start clock");
		clock.start();






	}

}
