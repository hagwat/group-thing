package swen222_group_project.Item;
import java.awt.Graphics;

public class Key extends PickUpItem{

	public Key(int id, double x, double y, double z) {
		super(id, x, y, z);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String itemDescription() {
		return "Key to unlock doors";
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub

	}
}
