package swen222_group_project.block;
import java.awt.Color;
import java.awt.Graphics;

public class ToggleBlock implements Block{
	private Color c;
	private int x;
	private int y;
	private int z;
	private int size;
	private boolean collideable = true;

	public ToggleBlock(int x, int y, int z, int size){
		this.x = x;
		this.y = y;
		this.z = z;

		int r = (int) (Math.random()*255);
		int g = (int) (Math.random()*255);
		int b = (int) (Math.random()*255);

		c = new Color(r,g,b);
	}

	public Color getColor() {
		return c;
	}

	public Boolean collideable() {
		return collideable;
	}

	public void toggle(){
		collideable = !collideable;
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

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub

	}
}
