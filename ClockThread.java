package thing;

public class ClockThread extends Thread {

private final int delay;
private final GameWorld world;
private final WorldFrame display;

public ClockThread(int delay, GameWorld world, WorldFrame display) {
	this.delay = delay;
	this.world = world;
	this.display = display;
}


	public void run(){
		while(true){
			try{
				System.out.println("before delay");
				Thread.sleep(delay);
				System.out.println("after delay");
				world.clockTick();
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}

}
