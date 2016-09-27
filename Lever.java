package swen222_group_project.Item;

import java.awt.Graphics;
import java.util.ArrayList;

import swen222_group_project.block.Block;
import swen222_group_project.block.ToggleBlock;

public class Lever extends InteractItem{
	private ArrayList<ToggleBlock> blocks;
	private int state = 1;

	public Lever(int id, double x, double y, double z, int state) {
		super(id, x, y, z);
		this.state = state;
	}

	public boolean add(ToggleBlock block){
		state = -state;
		return blocks.add(block);
	}

	@Override
	public void interact() {
		for(Block b:blocks){
			b.toggle();
		}
	}

}
