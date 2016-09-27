package swen222_group_project.Item;

public abstract class PickUpItem extends Item{

	public PickUpItem(int id, double x, double y, double z) {
		super(id, x, y, z);
	}

	@Override
	public abstract String itemDescription();

}
