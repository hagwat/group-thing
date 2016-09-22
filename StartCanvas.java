package swen222_group_project.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import swen222_group_project.control.Controller;

public class StartCanvas extends JPanel {

	public StartCanvas(Controller ctrl){


		JButton startBtn = new JButton("Start");
		startBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				ctrl.handle("Start");
			}
		});
		add(startBtn);
		}

	@Override
	public void paint(Graphics g){
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, getWidth(), getHeight() / 2);
		g.setColor(Color.RED);
		g.fillRect(0, getHeight() / 2, getWidth(), getHeight() / 2);

	}

}
