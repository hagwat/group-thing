package swen222_group_project.Item;
import java.awt.Graphics;

public class Door extends InteractItem{

	public Door otherDoor;
	public boolean locked;
	private Key key;

	public Door(int id, double x, double y, double z, boolean locked) {
		super(id, x, y, z);
		this.locked = locked;
	}

	public void linkDoor(Door door){
		otherDoor = door;
		door.setDoor(door);
	}

	public void setDoor(Door door){
		otherDoor = door;
	}

	public void lock(Key key){
		this.key = key;
		locked = true;
	}

	private void unlock(Key key){
		if(locked){
			locked = false;
			key=null;
		}
	}

	@Override
	public String itemDescription() {
		return "leads to an interior or exterior room";
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub

	}

	@Override
	public void interact() {
		if(locked)return;
		else return;
	}


}
