package swen222_group_project.block;
import java.awt.Color;
import java.awt.Graphics;

public interface Block {
	public Color getColor();
	public Boolean collideable();
	public void toggle();
	public void draw(Graphics g);
}
