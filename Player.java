package swen222_group_project.control;

import java.util.ArrayList;

import com.sun.prism.Graphics;

import swen222_group_project.Item.PickUpItem;
import swen222_group_project.game.GameWorld;

public class Player extends Thread{
	private double x;
	private double y;
	private double z;
	private int viewDir = 0;
	private int viewSize = 3;
	//variables for horizontal movement
	private double xS = 0;
	private double zS = 0;
	private static final double SPEED = 4;
	//variables for vertical movement
	private double yS = 0;
	private static final int GRAVITY =  1;
	//touching platform
	private boolean touchingGround = true;
	private int id;
	private ArrayList<PickUpItem> inventory;
	private boolean alive = true;


	private GameWorld game;

	public Player(double x, double y, double z, int id, GameWorld game){
		this.x = x;
		this.y = y;
		this.z = z;
		this.id = id;
		this.game = game;
		inventory = new ArrayList<PickUpItem>();
	}

	public void move(){
		double[] distance = game.validMove(this, xS, yS+GRAVITY, zS);
		x += distance[0];
		y+=distance[1];
		z += distance[2];
		if(distance[3]==0){
			if(yS>=0)touchingGround = true;
			yS = 0;
			xS = 0;
			zS = 0;
		}else{
			touchingGround = false;
			yS+=GRAVITY;
			if(yS>50)yS=50;
		}
	}

	public void draw(Graphics g){

	}

	public void jump(){
		if(touchingGround){
			touchingGround = false;
			yS=-SPEED;
		}
	}

	public boolean touchingGround(){
		return touchingGround;
	}

	public void moveRight(){
		if(viewDir == 0)xS = SPEED;
		else if(viewDir == 1)zS  = SPEED;
		else if(viewDir == 2)xS = -SPEED;
		else if(viewDir == 3)zS  = -SPEED;

		move();
	}

	public void moveLeft(){
		if(viewDir == 0)xS = -SPEED;
		else if(viewDir == 1)zS  = -SPEED;
		else if(viewDir == 2)xS = SPEED;
		else if(viewDir == 3)zS  = SPEED;

		move();
	}

	public void nullMove(){
		xS = 0;
		zS = 0;
	}

	public long getId(){
		return id;
	}

	public void RotateR(){
		if(touchingGround){
			viewDir = (viewDir + 1)%4;
			nullMove();
		}
	}

	public void RotateL(){
		if(touchingGround){
			viewDir = Math.abs((-viewDir - 1)%4);
			nullMove();
		}
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getZ() {
		return z;
	}

	public ArrayList<PickUpItem> getInventory(){
		return inventory;
	}

	public boolean addItem(PickUpItem item){
		return inventory.add(item);
	}

	public void setX(double x){
		this.x = x;
	}
	public void setY(double y){
		this.y = y;
	}
	public void setZ(double z){
		this.z = z;
	}

	public int getViewDir() {
		return viewDir;
	}

	public int getViewSize(){
		return viewSize;
	}
}
