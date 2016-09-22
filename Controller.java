package swen222_group_project.control;

import javax.swing.JFrame;
import javax.swing.UIManager;

import swen222_group_project.ui.WorldFrame;

/**
 * The controller is a bridging class between the UI and the game. It handles
 * actions that go between the two halves of the program.
 */
public class Controller {

	private WorldFrame view;

	public static void main(String[] args) {
		new Controller();
	}

	public Controller() {
		// sets look and feel
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					JFrame.setDefaultLookAndFeelDecorated(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
		view = new WorldFrame(this);
	}

	public void handle(String cmd){
		if(cmd.equals("Start")){
			System.out.println("START BUTTON");
		}
	}
}