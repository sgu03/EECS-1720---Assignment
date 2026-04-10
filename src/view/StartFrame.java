package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.characters.PlayerType;
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
	private JLabel typeLabel;
	private JComboBox<String> playerType;
	public JLabel typeDescription;
	
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
		
		JPanel typePanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 0);
		gbc.anchor = GridBagConstraints.WEST;
		
		typeLabel = new JLabel("Choose your character type: ");
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.EAST;
		typePanel.add(typeLabel, gbc);
		
		String[] typeOptions = {"Normal", "Warrior", "Lucky", "Tank", "Rogue"};
		playerType = new JComboBox<>(typeOptions);
		playerType.setActionCommand("PICK");
		playerType.addActionListener(controller);
		playerType.setMaximumSize(new Dimension(80, 25));
		playerType.setMinimumSize(new Dimension(80, 25));
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.WEST;
		typePanel.add(playerType, gbc);

		typeDescription = new JLabel(game.getPlayer().getTypeDescription());
		typeDescription.setPreferredSize(new Dimension(150, 15));
		typeDescription.setMinimumSize(new Dimension(150, 15));
		typeDescription.setMaximumSize(new Dimension(150, 15));
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.anchor = GridBagConstraints.WEST;
		typePanel.add(typeDescription, gbc);
		
		pane.add(Box.createVerticalStrut(38));
		pane.add(text);
		pane.add(Box.createVerticalStrut(10));
		pane.add(slider);
		pane.add(level);
		pane.add(Box.createVerticalStrut(10));
		pane.add(typePanel);
		pane.add(Box.createVerticalStrut(20));
		pane.add(start);
		pane.add(Box.createVerticalStrut(50));
		
		this.setVisible(true);
		this.requestFocus();
	}
	
	public int getDifficulty() {
		return difficulty;
	}
	
	public String getPlayerType() {
		return playerType.getSelectedItem().toString();
	}
	
	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
		level.setText("You are now choosing dungeon level " + difficulty);
	}
}
