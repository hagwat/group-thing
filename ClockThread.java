package thing;

public class ClockThread extends Thread {

private final int delay;
private final World world;
private final WorldFrame display;

public ClockThread(int delay, World world, WorldFrame display) {
	this.delay = delay;
	this.world = world;
	this.display = display;
}


	public void run(){
		while(true){
			try{
				Thread.sleep(500);
				world.clockTick();
			}catch(InterruptedException e){
				e.printStackTrace();
			}
			
		}
	}

}
