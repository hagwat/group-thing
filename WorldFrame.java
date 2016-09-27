package swen222_group_project.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import swen222_group_project.game.GameWorld;


public class WorldFrame extends JFrame{

	private JComponent menuBar;
	private JPanel canvas;
	private GameWorld world;

	public WorldFrame(GameWorld world) {
		super("Game");

		this.world = world;

		// sets border layout
		getContentPane().setLayout(new BorderLayout());

		//add starting canvas

		canvas = new StartCanvas(this);
		add(canvas, BorderLayout.CENTER);

		// what to do on close
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we) {
				Object[] options = { "Yes", "No" };
				int r = JOptionPane.showOptionDialog(new JOptionPane(), "Are you sure you want to quit INSERT_GAME_NAME?", "",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
				if (r == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});

		// centre window on screen
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((dim.width/2)-(this.getPreferredSize().width/2), (dim.height/2)-(this.getPreferredSize().height/2));



		// visibility
		pack();
		setResizable(false);
		setVisible(true);


	}

	@Override
	public Dimension getPreferredSize(){
		return new Dimension(500, 500);
	}

	public void changeCanvas(JPanel canvas) {
		this.remove(this.canvas);
		this.canvas = canvas;
		this.add(canvas);
		pack();
	}

	public void handle(String cmd){
		if(cmd.equals("Start")){
			System.out.println("START BUTTON");
			changeCanvas(new MainCanvas(world));
		}
	}

	public static void main(String[] args){
		new WorldFrame(new GameWorld());
	}
}
