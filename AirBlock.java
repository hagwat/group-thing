package swen222_group_project.block;
import java.awt.Color;
import java.awt.Graphics;

public class AirBlock implements Block{

	private Color c;
	private int x;
	private int y;
	private int z;
	private int size;

	public AirBlock(int x, int y, int z, int size){
		this.x = x;
		this.y = y;
		this.z = z;

		c=null;
	}

	public Color getColor(){
		return c;
	}

	public Boolean collideable(){
		return false;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getZ() {
		return z;
	}

	public void toggle(){
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub

	}
}
