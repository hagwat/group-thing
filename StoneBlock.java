package swen222_group_project.block;
import java.awt.Color;
import java.awt.Graphics;

public class StoneBlock implements Block{

	private Color c;
	private int x;
	private int y;
	private int z;
	private int size;

	public StoneBlock(int x, int y, int z, int size){
		this.x = x;
		this.y = y;
		this.z = z;

		int r = (int) (Math.random()*255);
		int g = (int) (Math.random()*255);
		int b = (int) (Math.random()*255);

		c = new Color(r,g,b);
	}

	public Color getColor(){
		return c;
	}

	public Boolean collideable(){
		return true;
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
