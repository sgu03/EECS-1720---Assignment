package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;
import java.awt.Image;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.game.Game;
import controller.GameController;

public class StartFrame extends JFrame implements ChangeListener {
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
		pane.setBackground(new Color(240, 240, 245));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(new ImageIcon(getClass().getResource("/images/gameIcon.png")).getImage());
		setSize(new Dimension(450, 450));
		setLocationRelativeTo(null);
		setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		
		//adding title
		JLabel titleLabel = new JLabel("Dungeon Gacha Adventure");
		titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
		titleLabel.setForeground(new Color(70, 70, 100));
		titleLabel.setAlignmentX(CENTER_ALIGNMENT);
		titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
		
		text = new JLabel("Please drag the bar to select Dungeon Difficulty:");
		text.setFont(new Font("Arial", Font.ROMAN_BASELINE, 13));
		text.setForeground(new Color(50, 50, 70));
		text.setPreferredSize(new Dimension(400, 40));
		text.setAlignmentX(CENTER_ALIGNMENT);
		
		slider = new JSlider(1, 5, 3);
		slider.addChangeListener(this);
		slider.setMajorTickSpacing(1); // adding ticks to slider
		slider.setPaintTicks(true);
		slider.setPreferredSize(new Dimension(350, 60));
		slider.setMaximumSize(new Dimension(350, 60));
		slider.setAlignmentX(CENTER_ALIGNMENT);
		
		level = new JLabel();
		level.setText("You are now choosing dungeon level " + slider.getValue());
		level.setFont(new Font("Arial", Font.BOLD, 14));
		level.setForeground(new Color(100, 100, 140));
		level.setPreferredSize(new Dimension(400, 40));
		level.setAlignmentX(CENTER_ALIGNMENT);
		
		start = new JButton("START GAME");
		start.setFont(new Font("Arial", Font.BOLD, 14));
		start.setBackground(new Color(70, 130, 200));
		start.setForeground(Color.WHITE);
		start.setFocusPainted(false);
		start.setActionCommand("START");
		start.addActionListener(controller);
		start.setPreferredSize(new Dimension(180, 45));
		start.setMaximumSize(new Dimension(200, 50));
		start.setAlignmentX(CENTER_ALIGNMENT); 
		
		start.addMouseListener(new java.awt.event.MouseAdapter(){
			public void mouseEntered(java.awt.event.MouseAdapter e) {
				start.setBackground(new Color(180, 160, 220));
				start.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
			}
			
			public void mouseExited(java.awt.event.MouseEvent e) {
				start.setBackground(new Color(70, 130, 200));
			}
			
		});
		
		pane.add(Box.createVerticalStrut(30));
		pane.add(titleLabel);
		pane.add(Box.createVerticalStrut(20));
		pane.add(text);
		pane.add(Box.createVerticalStrut(15));
		pane.add(slider);
		pane.add(Box.createVerticalStrut(15));
		pane.add(level);
		pane.add(Box.createVerticalStrut(35));
		pane.add(start);
		pane.add(Box.createVerticalStrut(40));
		
		this.setVisible(true);
		this.requestFocus();
	}
	
	public int getDifficulty() {
		return difficulty;
	}
	
	@Override
	public void stateChanged(ChangeEvent e) {
		if (e.getSource() == slider) {
			difficulty = slider.getValue();
			level.setText("You are now choosing dungeon level " + difficulty);
		}
	}
}
