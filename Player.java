package swen222_group_project.control;

public class Player {
	private double x;
	private double y;
	private double xS;
	//gravity
	private double yS = 4;
	//horizontalMovement
	private double speed = 5;
	//initial jump speed
	private double jSpeed = 0;
	//touching platform
	private boolean touchingGround = false;
	private int id;

	public Player(double x, double y, int id){
		this.x = x;
		this.y = y;
		this.id = id;
	}

	public void move(){
		x = x + xS;
		if(!touchingGround){
			y = y + jSpeed;
			jSpeed = jSpeed - yS;
		}
	}

	public void jump(){
		if(touchingGround){
			touchingGround = false;
			jSpeed = 10;
		}
	}

	public boolean touchingGround(){
		return touchingGround;
	}

	public void moveRight(){
		xS = xS + speed;
	}

	public void moveLeft(){
		xS = xS - speed;
	}
}
