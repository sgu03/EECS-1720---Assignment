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
import javax.swing.border.TitledBorder;

import controller.GameController;
import model.game.Game;
import model.items.*;
import model.rooms.*;

public class GameFrame extends JFrame {
	private final Game game;
	private final GameController controller;

	private final JLabel playerHpLabel;
	private final JLabel dodgeChanceLabel;
	private final JLabel ticketLabel;
	private final JLabel shieldLabel;
	private final JLabel monsterHpLabel;
	private final JLabel monstersLeftLabel;
	private final JLabel currentRoomLabel;
	private final JLabel monsterLevelLabel;
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
	
	private JPanel displayPanel;
	private JLabel displayImage;
	private JTextArea displayText;
	
	private ImageIcon backpackImage = new ImageIcon(getClass().getResource("/images/backpack0.png"));
	private ImageIcon bombImage = new ImageIcon(getClass().getResource("/images/bomb.png"));
	private ImageIcon skullImage = new ImageIcon(getClass().getResource("/images/cursedSkull.png"));
	private ImageIcon swordImage = new ImageIcon(getClass().getResource("/images/instantKillSword.png"));
	private ImageIcon luckyCharmImage = new ImageIcon(getClass().getResource("/images/luckyCharm.png"));
	private ImageIcon monsterImage = new ImageIcon(getClass().getResource("/images/monster.png"));
	private ImageIcon playerImage = new ImageIcon(getClass().getResource("/images/player.png"));
	private ImageIcon potionImage = new ImageIcon(getClass().getResource("/images/potionItem.png"));
	private ImageIcon shieldImage = new ImageIcon(getClass().getResource("/images/shieldItem.png"));
	private ImageIcon questionMarkImage = new ImageIcon(getClass().getResource("/images/questionMark.png"));

	public GameFrame(Game game) {
		super("Dungeon Gacha Adventure");
		this.game = game;
		this.controller = new GameController(game, this);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(900, 800));
		setLocationRelativeTo(null);
		setLayout(new BorderLayout(10, 10));

		JPanel infoPanel = new JPanel();
		infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.X_AXIS));
		
		JPanel dungeonInfoPanel = new JPanel(new GridLayout(2, 1, 2, 0));
		dungeonInfoPanel.setBorder(BorderFactory.createTitledBorder("Dungeon Status"));
		dungeonInfoPanel.setPreferredSize(new Dimension(120, 30));
		currentRoomLabel = new JLabel();
		monstersLeftLabel = new JLabel();
		dungeonInfoPanel.add(currentRoomLabel);
		dungeonInfoPanel.add(monstersLeftLabel);
		infoPanel.add(dungeonInfoPanel);
		
		JPanel playerInfoPanel = new JPanel(new GridLayout(2, 2, 10, 2));
		playerInfoPanel.setBorder(BorderFactory.createTitledBorder("Player Status"));
		playerInfoPanel.setPreferredSize(new Dimension(200, 30));
		playerHpLabel = new JLabel();
		dodgeChanceLabel = new JLabel();
		ticketLabel = new JLabel();
		shieldLabel = new JLabel();
		playerInfoPanel.add(playerHpLabel);
		playerInfoPanel.add(ticketLabel);
		playerInfoPanel.add(dodgeChanceLabel);
		playerInfoPanel.add(shieldLabel);
		infoPanel.add(playerInfoPanel);
		
		JPanel monsterInfoPanel = new JPanel(new GridLayout(2, 1, 2, 2));
		monsterInfoPanel.setBorder(BorderFactory.createTitledBorder("Current Monster Status"));
		monsterHpLabel = new JLabel();
		monsterLevelLabel = new JLabel();
		monsterInfoPanel.add(monsterHpLabel);
		monsterInfoPanel.add(monsterLevelLabel);
		infoPanel.add(monsterInfoPanel);
		
		add(infoPanel, BorderLayout.NORTH);

		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.X_AXIS));
		
		actionArea = new JTextArea();
		actionArea.setEditable(false);
		actionArea.setLineWrap(true);
		actionArea.setWrapStyleWord(true);
		actionArea.setOpaque(false);
		actionArea.setForeground(new Color(235, 255, 220));
		actionArea.setFont(new Font("Serif", Font.PLAIN, 15));
		
		JScrollPane scrollPane = new JScrollPane(actionArea);
		TitledBorder gameLogBorder = BorderFactory.createTitledBorder("Game Log");
		gameLogBorder.setTitleColor(new Color(235, 255, 220));
		scrollPane.setBorder(gameLogBorder);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.getVerticalScrollBar().setOpaque(false);
		scrollPane.getHorizontalScrollBar().setOpaque(false);
		
		gameLogPanel = new BackgroundPanel(gachaRoomBG);
		gameLogPanel.setPreferredSize(new Dimension(500, 400));
		gameLogPanel.setLayout(new BorderLayout());
		gameLogPanel.add(scrollPane, BorderLayout.CENTER);
		centerPanel.add(gameLogPanel);
		
		displayPanel = new JPanel();
		displayPanel.setLayout(new BorderLayout(10, 10));
		displayPanel.setPreferredSize(new Dimension(300, 400));
		displayPanel.setBorder(BorderFactory.createTitledBorder("Display"));
		
		displayImage = new JLabel();
		displayImage.setHorizontalAlignment(JLabel.CENTER);
		displayImage.setPreferredSize(new Dimension(300, 220));
		displayPanel.add(displayImage, BorderLayout.CENTER);
		
		displayText = new JTextArea();
		displayText.setEditable(false);
		displayText.setOpaque(false);
		displayText.setCaretPosition(0);
		
		JScrollPane displayScroll = new JScrollPane(displayText);
		displayScroll.setBorder(BorderFactory.createEmptyBorder());
		displayScroll.setPreferredSize(new Dimension(300, 180));
		displayPanel.add(displayScroll, BorderLayout.SOUTH);
		
		showPlayerDisplay();
		centerPanel.add(displayPanel);
		
		add(centerPanel, BorderLayout.CENTER);

		JPanel controlsPanel = new JPanel();
		controlsPanel.setLayout(new BoxLayout(controlsPanel, BoxLayout.Y_AXIS));
		controlsPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 10));

		JPanel gachaPanel = new JPanel(new GridLayout(1, 1, 5, 5));
		gachaPanel.setBorder(BorderFactory.createTitledBorder("Gacha"));
		gachaButton = buildButton("(G) Pull Gacha", "GACHA");
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
		attackButton = buildButton("(A) Attack", "ATTACK");
		dodgeButton = buildButton("(D) Dodge", "DODGE");
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
		button.addMouseListener(controller);
		button.setFocusable(false);
		return button;
	}

	public void refresh() {
		playerHpLabel.setText("Player HP: " + game.getPlayer().getHp());
		dodgeChanceLabel.setText("Next Dodge Chance: " + (int)(game.getPlayer().getDodgeChance() * 100) + "%");
		ticketLabel.setText("Gacha Tickets: " + game.getPlayer().getGachaTickets());
		shieldLabel.setText("Shield Count:" + game.getPlayer().getShield());
		monstersLeftLabel.setText("Monsters Left: " + game.getMonstersLeft());
		currentRoomLabel.setText("Current Room: " + game.getCurrentRoom().toString());
		actionArea.setText(game.getActionMsg());
		displayText.setCaretPosition(0);

		if (game.getCurrentRoom() instanceof MonsterRoom) {
			MonsterRoom monsterRoom = (MonsterRoom) game.getCurrentRoom();
			monsterHpLabel.setText("Monster HP: " + monsterRoom.getMonster().getHp());
			monsterLevelLabel.setText("Monster Level: " + monsterRoom.getLevel());
			gameLogPanel.setBackground(monsterRoomBG);
		} else if (game.getCurrentRoom() instanceof EventRoom) {
			monsterHpLabel.setText("Monster HP: N/A");
			monsterLevelLabel.setText("Monster Level: N/A");
			gameLogPanel.setBackground(eventRoomBG);
		} else {
			monsterHpLabel.setText("Monster HP: N/A");
			monsterLevelLabel.setText("Monster Level: N/A");
			gameLogPanel.setBackground(gachaRoomBG);
		}

		ArrayList<Room> rooms = game.getNextRoomList();
		for (int i = 0; i < roomButtons.length; i++) {
			boolean available = rooms != null && i < rooms.size() && game.isInGacha() && !game.isWaitingForDiscard();
			roomButtons[i].setEnabled(available);
			roomButtons[i].setText(available ? "(" + (i + 1) + ") " + rooms.get(i).toString() : "Room " + (i + 1));
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
			JButton discardButton = buildButton(backpack.getItem(i).getName(), "DISCARD" + i);
			discardButton.setFocusable(false);
			discardPanel.add(discardButton);
		}
		JButton discardNewButton = buildButton("New: " + game.getPendingItem().getName(), "DISCARD" + backpack.size());
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
			return "(" + key + ") Empty";
		}
		return "(" + key + ") " + item.getName();
	}
	
	private void showDisplay(ImageIcon icon, String text) {
	    displayImage.setIcon(scaleIcon(icon, 220, 220));
	    displayText.setText(text);
	}
	
	private void showDisplay(ImageIcon icon, String text, int w, int h) {
		displayImage.setIcon(scaleIcon(icon, w, h));
	    displayText.setText(text);
	}
	
	public void showPlayerDisplay() {
		String guide = "Defeat all monsters to escape from the Dungeon!";
		guide += "\n\n=== How to Play ===";
		guide += "\n[G] Pull Gacha";
		guide += "\n[1] [2] [3] Choose a Room";
		guide += "\n[A] Attack";
		guide += "\n[D] Dodge";
		guide += "\n[Q] [W] [E] [R] [T] Use Item";
		guide += "\n\n=== Tips ===";
		guide += "\n- You need gacha tickets to pull gacha";
		guide += "\n- If your backpack is full, you must discard one item";
		guide += "\n- Read item descriptions carefully before use";
		guide += "\n- Some items can harm you...";
		showDisplay(playerImage, guide);
		displayText.setCaretPosition(0);
	}
	
	public void showMonsterDisplay(int level) {
		showDisplay(monsterImage, "Monster Level " + level);
	}
	
	public void showEventDisplay() {
		showDisplay(questionMarkImage, "A random event will occur!");
	}
	
	public void showItemDisplay(Item item) {
		if (item == null) {
			showDisplay(backpackImage, "Your backpack");
		} else if (item instanceof BombItem) {
			showDisplay(bombImage, item.getMsg(), 1000, 1000);
		} else if (item instanceof CursedSkull) {
			showDisplay(skullImage, item.getMsg());
		} else if (item instanceof InstantKillSword) {
			showDisplay(swordImage, item.getMsg());
		} else if (item instanceof LuckyCharm) {
			showDisplay(luckyCharmImage, item.getMsg());
		} else if (item instanceof PotionItem) {
			showDisplay(potionImage, item.getMsg());
		} else if (item instanceof ShieldItem) {
			showDisplay(potionImage, item.getMsg());
		}
	}
	
	private ImageIcon scaleIcon(ImageIcon icon, int maxW, int maxH) {
	    Image img = icon.getImage();
	    int w = img.getWidth(null);
	    int h = img.getHeight(null);

	    double scale = Math.min((double) maxW / w, (double) maxH / h);
	    int newW = (int) (w * scale);
	    int newH = (int) (h * scale);

	    Image scaled = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
	    return new ImageIcon(scaled);
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
