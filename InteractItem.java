package swen222_group_project.Item;

import java.awt.Graphics;

public abstract class InteractItem extends Item{

	public InteractItem(int id, double x, double y, double z) {
		super(id, x, y, z);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String itemDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub

	}

	public abstract void interact();

}
