package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Font;
import java.awt.Color;
import java.awt.Image;

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
		slider.addChangeListener(controller);
		slider.setMajorTickSpacing(1); // adding ticks to slider
		slider.setPaintTicks(true);
		slider.setPreferredSize(new Dimension(350, 60));
		slider.setMaximumSize(new Dimension(350, 60));
		slider.addChangeListener(controller);
		slider.setPreferredSize(new Dimension(250, 50));
		slider.setMaximumSize(new Dimension(300, 50));
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
		
		JPanel typePanel = new JPanel(new GridBagLayout());
		typePanel.setBackground(new Color(240, 240, 245));
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
		
		//pane.add(Box.createVerticalStrut(38));
		pane.add(text);
		pane.add(Box.createVerticalStrut(10));
		start.addMouseListener(controller);
		
		pane.add(Box.createVerticalStrut(30));
		pane.add(titleLabel);
		pane.add(Box.createVerticalStrut(20));
		pane.add(text);
		pane.add(Box.createVerticalStrut(15));
		pane.add(slider);
		pane.add(Box.createVerticalStrut(15));
		pane.add(level);
		pane.add(Box.createVerticalStrut(10));
		pane.add(typePanel);
		pane.add(Box.createVerticalStrut(20));
		pane.add(start);
		pane.add(Box.createVerticalStrut(40));
		
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
