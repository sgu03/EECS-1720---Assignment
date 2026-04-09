package view;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import controller.GameController;
import model.game.Game;
import model.items.Backpack;
import model.items.Item;
import model.rooms.*;

public class GameFrame extends JFrame {
	private final Game game;
	private final GameController controller;

	private final JLabel playerHpLabel;
	private final JLabel ticketLabel;
	private final JLabel monsterHpLabel;
	private final JLabel monstersLeftLabel;
	private final JLabel currentRoomLabel;
	private final JLabel hintLabel;
	private final JTextArea actionArea;
	private final JButton gachaButton;
	private final JButton attackButton;
	private final JButton dodgeButton;
	private final JButton[] roomButtons;
	private final JButton[] itemButtons;
	private final JPanel discardPanel;
	
	private BackgroundPanel gameLogPanel;	
	private ImageIcon gachaRoomBG = new ImageIcon(getClass().getResource("/images/gatchaRoom.png"));
	private ImageIcon monsterRoomBG = new ImageIcon(getClass().getResource("/images/monsterRoom.png"));
	private ImageIcon eventRoomBG = new ImageIcon(getClass().getResource("/images/eventRoom.jpg"));

	public GameFrame(Game game) {
		super("Dungeon Gacha Adventure");
		this.game = game;
		this.controller = new GameController(game, this);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(1920, 1090));
		setLocationRelativeTo(null);
		setLayout(new BorderLayout(10, 10));

		JPanel infoPanel = new JPanel(new GridLayout(2, 3, 8, 8));
		infoPanel.setBorder(BorderFactory.createTitledBorder("Status"));
		playerHpLabel = new JLabel();
		ticketLabel = new JLabel();
		monsterHpLabel = new JLabel();
		monstersLeftLabel = new JLabel();
		currentRoomLabel = new JLabel();
		hintLabel = new JLabel("Keys: G gacha, 1-3 rooms, A attack, D dodge, Q-T items");
		infoPanel.add(playerHpLabel);
		infoPanel.add(ticketLabel);
		infoPanel.add(monsterHpLabel);
		infoPanel.add(monstersLeftLabel);
		infoPanel.add(currentRoomLabel);
		infoPanel.add(hintLabel);
		add(infoPanel, BorderLayout.NORTH);

		actionArea = new JTextArea();
		actionArea.setEditable(false);
		actionArea.setLineWrap(true);
		actionArea.setWrapStyleWord(true);
		actionArea.setOpaque(false);
		actionArea.setForeground(new Color(235, 255, 220));
		
		JScrollPane scrollPane = new JScrollPane(actionArea);
		scrollPane.setBorder(BorderFactory.createTitledBorder("Game Log"));
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.getVerticalScrollBar().setOpaque(false);
		scrollPane.getHorizontalScrollBar().setOpaque(false);
		
		gameLogPanel = new BackgroundPanel(gachaRoomBG);
		gameLogPanel.setLayout(new BorderLayout());
		gameLogPanel.add(scrollPane, BorderLayout.CENTER);
		
		add(gameLogPanel, BorderLayout.CENTER);

		JPanel controlsPanel = new JPanel();
		controlsPanel.setLayout(new BoxLayout(controlsPanel, BoxLayout.Y_AXIS));
		controlsPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 10));

		JPanel gachaPanel = new JPanel(new GridLayout(1, 1, 5, 5));
		gachaPanel.setBorder(BorderFactory.createTitledBorder("Gacha"));
		gachaButton = buildButton("Pull Gacha (G)", "GACHA");
		gachaPanel.add(gachaButton);
		controlsPanel.add(gachaPanel);

		JPanel roomPanel = new JPanel(new GridLayout(1, 3, 5, 5));
		roomPanel.setBorder(BorderFactory.createTitledBorder("Choose Room"));
		roomButtons = new JButton[3];
		for (int i = 0; i < roomButtons.length; i++) {
			roomButtons[i] = buildButton("Room " + (i + 1), "ROOM" + i);
			roomPanel.add(roomButtons[i]);
		}
		controlsPanel.add(roomPanel);

		JPanel battlePanel = new JPanel(new GridLayout(1, 2, 5, 5));
		battlePanel.setBorder(BorderFactory.createTitledBorder("Battle"));
		attackButton = buildButton("Attack (A)", "ATTACK");
		dodgeButton = buildButton("Dodge (D)", "DODGE");
		battlePanel.add(attackButton);
		battlePanel.add(dodgeButton);
		controlsPanel.add(battlePanel);

		JPanel backpackPanel = new JPanel(new GridLayout(1, 5, 5, 5));
		backpackPanel.setBorder(BorderFactory.createTitledBorder("Backpack / Use Item"));
		itemButtons = new JButton[5];
		for (int i = 0; i < itemButtons.length; i++) {
			itemButtons[i] = buildButton("Empty", "ITEM" + i);
			backpackPanel.add(itemButtons[i]);
		}
		controlsPanel.add(backpackPanel);

		discardPanel = new JPanel(new GridLayout(1, 6, 5, 5));
		discardPanel.setBorder(BorderFactory.createTitledBorder("Discard Choice"));
		controlsPanel.add(discardPanel);

		add(controlsPanel, BorderLayout.SOUTH);

		addKeyListener(controller);
		setFocusable(true);
		
		this.setVisible(true);
		refresh();
	}

	private JButton buildButton(String text, String actionCommand) {
		JButton button = new JButton(text);
		button.setActionCommand(actionCommand);
		button.addActionListener(controller);
		button.addKeyListener(controller);
		button.setFocusable(false);
		return button;
	}

	public void refresh() {
		playerHpLabel.setText("Player HP: " + game.getPlayer().getHp());
		ticketLabel.setText("Tickets: " + game.getPlayer().getGachaTickets());
		monstersLeftLabel.setText("Monsters Left: " + game.getMonstersLeft());
		currentRoomLabel.setText("Current Room: " + game.getCurrentRoom().toString());
		actionArea.setText(game.getActionMsg());

		if (game.getCurrentRoom() instanceof MonsterRoom) {
			MonsterRoom monsterRoom = (MonsterRoom) game.getCurrentRoom();
			monsterHpLabel.setText("Monster HP: " + monsterRoom.getMonster().getHp());
			gameLogPanel.setBackground(monsterRoomBG);
		} else if (game.getCurrentRoom() instanceof EventRoom) {
			monsterHpLabel.setText("Monster HP: N/A");
			gameLogPanel.setBackground(eventRoomBG);
			gameLogPanel.repaint();
		} else {
			monsterHpLabel.setText("Monster HP: N/A");
			gameLogPanel.setBackground(gachaRoomBG);
		}

		ArrayList<Room> rooms = game.getNextRoomList();
		for (int i = 0; i < roomButtons.length; i++) {
			boolean available = rooms != null && i < rooms.size() && game.isInGacha() && !game.isWaitingForDiscard();
			roomButtons[i].setEnabled(available);
			roomButtons[i].setText(available ? (i + 1) + ". " + rooms.get(i).toString() : "Room " + (i + 1));
		}

		attackButton.setEnabled(game.isInBattle());
		dodgeButton.setEnabled(game.isInBattle());
		gachaButton.setEnabled(game.isInGacha() && !game.isWaitingForDiscard());

		refreshBackpack();
		refreshDiscardPanel();
		repaint();
		requestFocusInWindow();
	}

	private void refreshBackpack() {
		Backpack backpack = game.getPlayer().getBackpack();
		for (int i = 0; i < itemButtons.length; i++) {
			boolean hasItem = i < backpack.size();
			itemButtons[i].setEnabled(hasItem && game.isInBattle());
			itemButtons[i].setText(hasItem ? shortcutLabel(i, backpack.getItem(i)) : shortcutLabel(i, null));
			itemButtons[i].setToolTipText(hasItem ? backpack.getItem(i).getMsg() : "Empty");
		}
	}

	private void refreshDiscardPanel() {
		discardPanel.removeAll();
		if (!game.isWaitingForDiscard()) {
			discardPanel.revalidate();
			discardPanel.repaint();
			return;
		}

		Backpack backpack = game.getPlayer().getBackpack();
		for (int i = 0; i < backpack.size(); i++) {
			JButton discardButton = buildButton("Drop " + backpack.getItem(i).getName(), "DISCARD" + i);
			discardButton.setFocusable(false);
			discardPanel.add(discardButton);
		}
		JButton discardNewButton = buildButton("Drop New: " + game.getPendingItem().getName(), "DISCARD" + backpack.size());
		discardNewButton.setFocusable(false);
		discardPanel.add(discardNewButton);
		discardPanel.revalidate();
		discardPanel.repaint();
	}

	private String shortcutLabel(int index, Item item) {
		char key;
		switch (index) {
			case 0:
				key = 'Q';
				break;
			case 1:
				key = 'W';
				break;
			case 2:
				key = 'E';
				break;
			case 3:
				key = 'R';
				break;
			default:
				key = 'T';
				break;
		}
		if (item == null) {
			return key + ": Empty";
		}
		return key + ": " + item.getName();
	}
	
	private class BackgroundPanel extends JPanel {
		private ImageIcon background;
		
		public BackgroundPanel(ImageIcon background) {
			this.background = background;
			setOpaque(true);
		}
		
		public void setBackground(ImageIcon background) {
			this.background = background;
		}
		
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2d = (Graphics2D) g;
			g2d.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), this);
			g2d.setColor(new Color(0, 0, 0, 90));	// overlay color
			g2d.fillRect(0, 0, getWidth(), getHeight());
		}
	}
}
