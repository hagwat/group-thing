package swen222_group_project.control;

import swen222_group_project.game.GameWorld;
import swen222_group_project.ui.WorldFrame;

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
				Thread.sleep(delay);
				world.clockTick();
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}

}
