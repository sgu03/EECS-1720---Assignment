package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.game.Game;
import controller.GameController;

public class StartFrame extends JFrame {
	private Game game;
	private GameController controller;
	private int difficulty;
	
	private JLabel text;
	private JSlider slider;
	private JLabel level;
	private JButton start;
	
	public StartFrame(Game game) {
		super("Dungeon Gacha Adventure");
		this.game = game;
		this.controller = new GameController(game, this);
		this.difficulty = 3;
		
		Container pane = this.getContentPane();
		pane.setPreferredSize(new Dimension(400, 400));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(400, 400));
		setLocationRelativeTo(null);
		setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		
		text = new JLabel("Please drag the bar to select Dungeon Difficulty:");
		text.setPreferredSize(new Dimension(400, 60));
		text.setAlignmentX(CENTER_ALIGNMENT);
		
		slider = new JSlider(1, 5, 3);
		slider.addChangeListener(controller);
		slider.setPreferredSize(new Dimension(250, 50));
		slider.setMaximumSize(new Dimension(300, 50));
		slider.setAlignmentX(CENTER_ALIGNMENT);
		
		level = new JLabel();
		level.setText("You are now choosing dungeon level " + slider.getValue());
		level.setPreferredSize(new Dimension(400, 60));
		level.setAlignmentX(CENTER_ALIGNMENT);
		
		start = new JButton("START GAME");
		start.setActionCommand("START");
		start.addActionListener(controller);
		start.setPreferredSize(new Dimension(180, 50));
		start.setAlignmentX(CENTER_ALIGNMENT);
		
		pane.add(Box.createVerticalStrut(38));
		pane.add(text);
		pane.add(Box.createVerticalStrut(20));
		pane.add(slider);
		pane.add(level);
		pane.add(Box.createVerticalStrut(40));
		pane.add(start);
		pane.add(Box.createVerticalStrut(50));
		
		this.setVisible(true);
		this.requestFocus();
	}
	
	public int getDifficulty() {
		return difficulty;
	}
	
	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
		level.setText("You are now choosing dungeon level " + difficulty);
	}
}
