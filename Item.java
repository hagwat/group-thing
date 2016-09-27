package swen222_group_project.Item;

import java.awt.Graphics;

public abstract class Item {
	public int id;
	public double x;
	public double y;
	public double z;

	public Item(int id, double x, double y, double z){
		this.id = id;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public abstract String itemDescription();

	public abstract void draw(Graphics g);
}
