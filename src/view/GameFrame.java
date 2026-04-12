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
	private JButton gachaButton;
	private JButton attackButton;
	private JButton dodgeButton;
	private JButton[] roomButtons;
	private JButton[] itemButtons;
	private JPanel discardPanel;

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
	private ImageIcon questionMarkImage = new ImageIcon(getClass().getResource("/images/questionMark1.png"));

	private static final Color DARK_BLUE = new Color(29, 52, 97);
	private static final Color LIGHT_BLUE = new Color(0, 75, 255);
	private static final Color LIGHT_GREEN = new Color(235, 255, 220);
	private static final Color GOLD = new Color(255, 218, 94);
	private static final Color MID_BLUE = new Color(70, 130, 200);
	private static final Color GREEN = new Color(43, 208, 114);
	private static final Color RED = new Color(222, 57, 38);
	private static final Color PURPLE = new Color(149, 35, 146);

	public GameFrame(Game game) {
		super("Dungeon Gacha Adventure");
		this.game = game;
		this.controller = new GameController(game, this);
		
		currentRoomLabel = new JLabel();
		monstersLeftLabel = new JLabel();
		playerHpLabel = new JLabel();
		ticketLabel = new JLabel();
		dodgeChanceLabel = new JLabel();
		shieldLabel = new JLabel();
		monsterHpLabel = new JLabel();
		monsterLevelLabel = new JLabel();
		discardPanel = new JPanel();
		actionArea = new JTextArea();
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(950, 850));
		setLocationRelativeTo(null);
		setLayout(new BorderLayout(10, 10));
		getContentPane().setBackground(DARK_BLUE);

		// info panel (NORTH)
		JPanel infoPanel = createInfoPanel();
		add(infoPanel, BorderLayout.NORTH);

		// center panel (CENTER) - FIXED: was calling createInfoPanel
		JPanel centerPanel = createCenterPanel();
		add(centerPanel, BorderLayout.CENTER);

		// control panel (SOUTH) - FIXED: was calling createInfoPanel
		JPanel controlsPanel = createControlsPanel();
		add(controlsPanel, BorderLayout.SOUTH);
		
		addKeyListener(controller);
		setFocusable(true);

		this.setVisible(true);
		refresh();
	}

	private JPanel createInfoPanel() {
		JPanel infoPanel = new JPanel();
		infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.X_AXIS));
		infoPanel.setBackground(DARK_BLUE);
		infoPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		// Dungeon Info Panel
		JPanel dungeonInfoPanel = new JPanel(new GridLayout(2, 1, 2, 5));
		dungeonInfoPanel.setBackground(DARK_BLUE);
		dungeonInfoPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(GOLD),
				"Dungeon Status", TitledBorder.LEFT, TitledBorder.TOP, new Font("Arial", Font.BOLD, 11), GOLD));
		dungeonInfoPanel.setPreferredSize(new Dimension(130, 60));

		//currentRoomLabel = new JLabel();
		currentRoomLabel.setForeground(LIGHT_BLUE);
		currentRoomLabel.setFont(new Font("Arial", Font.PLAIN, 12));

		//monstersLeftLabel = new JLabel();
		monstersLeftLabel.setForeground(LIGHT_GREEN);
		monstersLeftLabel.setFont(new Font("Arial", Font.PLAIN, 12));

		dungeonInfoPanel.add(currentRoomLabel);
		dungeonInfoPanel.add(monstersLeftLabel);
		infoPanel.add(dungeonInfoPanel);

		// Player Info Panel
		JPanel playerInfoPanel = new JPanel(new GridLayout(2, 2, 10, 5));
		playerInfoPanel.setBackground(DARK_BLUE);
		playerInfoPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(GREEN),
				"Player Status", TitledBorder.LEFT, TitledBorder.TOP, new Font("Arial", Font.BOLD, 11), GREEN));
		playerInfoPanel.setPreferredSize(new Dimension(220, 60));

		//playerHpLabel = new JLabel();
		playerHpLabel.setForeground(GREEN);
		playerHpLabel.setFont(new Font("Arial", Font.BOLD, 12));

//		ticketLabel = new JLabel();
		ticketLabel.setForeground(GOLD);
		ticketLabel.setFont(new Font("Arial", Font.BOLD, 12));

//		dodgeChanceLabel = new JLabel();
		dodgeChanceLabel.setForeground(MID_BLUE);
		dodgeChanceLabel.setFont(new Font("Arial", Font.BOLD, 12));

//		shieldLabel = new JLabel();
		shieldLabel.setForeground(PURPLE);
		shieldLabel.setFont(new Font("Arial", Font.BOLD, 12));

		playerInfoPanel.add(playerHpLabel);
		playerInfoPanel.add(ticketLabel);
		playerInfoPanel.add(dodgeChanceLabel);
		playerInfoPanel.add(shieldLabel);
		infoPanel.add(playerInfoPanel);

		// Monster Info Panel - FIXED: was saying "Player Status"
		JPanel monsterInfoPanel = new JPanel(new GridLayout(2, 1, 2, 5));
		monsterInfoPanel.setBackground(DARK_BLUE);
		monsterInfoPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(RED),
				"Current Monster Status", TitledBorder.LEFT, TitledBorder.TOP, new Font("Arial", Font.BOLD, 11), RED));
		monsterInfoPanel.setPreferredSize(new Dimension(150, 60));

//		monsterHpLabel = new JLabel();
		monsterHpLabel.setForeground(RED);
		monsterHpLabel.setFont(new Font("Arial", Font.BOLD, 12));

//		monsterLevelLabel = new JLabel();
		monsterLevelLabel.setForeground(LIGHT_GREEN);
		monsterLevelLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		monsterInfoPanel.add(monsterHpLabel);
		monsterInfoPanel.add(monsterLevelLabel);
		infoPanel.add(monsterInfoPanel);

		return infoPanel;
	}

	private JPanel createCenterPanel() {
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.X_AXIS));
		centerPanel.setBackground(DARK_BLUE);

		// Game Log Area
//		actionArea = new JTextArea();
		actionArea.setEditable(false);
		actionArea.setLineWrap(true);
		actionArea.setWrapStyleWord(true);
		actionArea.setOpaque(false);
		actionArea.setForeground(LIGHT_GREEN);
		actionArea.setFont(new Font("Arial", Font.PLAIN, 12));

		JScrollPane scrollPane = new JScrollPane(actionArea);
		TitledBorder gameLogBorder = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(GOLD), "Game Log",
				TitledBorder.LEFT, TitledBorder.TOP, new Font("Arial", Font.BOLD, 12), GOLD);
		scrollPane.setBorder(gameLogBorder);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.getVerticalScrollBar().setBackground(DARK_BLUE);

		gameLogPanel = new BackgroundPanel(gachaRoomBG);
		gameLogPanel.setPreferredSize(new Dimension(500, 400));
		gameLogPanel.setLayout(new BorderLayout());
		gameLogPanel.add(scrollPane, BorderLayout.CENTER);
		centerPanel.add(gameLogPanel);

		// Display Panel
		displayPanel = new JPanel();
		displayPanel.setLayout(new BorderLayout(10, 10));
		displayPanel.setPreferredSize(new Dimension(300, 400));
		displayPanel.setBackground(DARK_BLUE);
		displayPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(GOLD), "Display",
				TitledBorder.LEFT, TitledBorder.TOP, new Font("Arial", Font.BOLD, 12), GOLD));

		displayImage = new JLabel();
		displayImage.setHorizontalAlignment(JLabel.CENTER);
		displayImage.setPreferredSize(new Dimension(300, 220));
		displayPanel.add(displayImage, BorderLayout.CENTER);

		displayText = new JTextArea();
		displayText.setEditable(false);
		displayText.setOpaque(true);
		displayText.setBackground(PURPLE);
		displayText.setForeground(Color.WHITE);
		displayText.setFont(new Font("Arial", Font.PLAIN, 12));
		displayText.setCaretPosition(0);

		JScrollPane displayScroll = new JScrollPane(displayText);
		displayScroll.setBorder(BorderFactory.createEmptyBorder());
		displayScroll.setOpaque(false);
		displayScroll.setPreferredSize(new Dimension(300, 160));
		displayPanel.add(displayScroll, BorderLayout.SOUTH);

		showPlayerDisplay();
		centerPanel.add(displayPanel);
		return centerPanel;
	}

	private JPanel createControlsPanel() {
		JPanel controlsPanel = new JPanel();
		controlsPanel.setLayout(new BoxLayout(controlsPanel, BoxLayout.Y_AXIS));
		controlsPanel.setBackground(DARK_BLUE);
		controlsPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 10));

		// Gacha Panel
		JPanel gachaPanel = new JPanel(new GridLayout(1, 1, 5, 5));
		gachaPanel.setBackground(DARK_BLUE);
		gachaPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(GOLD), "Gacha",
				TitledBorder.LEFT, TitledBorder.TOP, new Font("Arial", Font.BOLD, 11), GOLD));

		gachaButton = createStyledButton("(G) Pull Gacha", "GACHA", GOLD);
		gachaPanel.add(gachaButton);
		controlsPanel.add(gachaPanel);

		// Room Panel
		JPanel roomPanel = new JPanel(new GridLayout(1, 3, 5, 5));
		roomPanel.setBackground(DARK_BLUE);
		roomPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(GOLD), "Choose Room",
				TitledBorder.LEFT, TitledBorder.TOP, new Font("Arial", Font.BOLD, 11), GOLD));

		roomButtons = new JButton[3];
		for (int i = 0; i < roomButtons.length; i++) {
			roomButtons[i] = createStyledButton("Room " + (i + 1), "ROOM" + i, MID_BLUE);
			roomPanel.add(roomButtons[i]);
		}
		controlsPanel.add(roomPanel);

		// Battle Panel
		JPanel battlePanel = new JPanel(new GridLayout(1, 2, 10, 5));
		battlePanel.setBackground(DARK_BLUE);
		battlePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(RED), "Battle",
				TitledBorder.LEFT, TitledBorder.TOP, new Font("Arial", Font.BOLD, 11), RED));

		attackButton = createStyledButton("(A) Attack", "ATTACK", RED); // FIXED: use createStyledButton
		dodgeButton = createStyledButton("(D) Dodge", "DODGE", MID_BLUE); // FIXED: use createStyledButton

		battlePanel.add(attackButton);
		battlePanel.add(dodgeButton);
		controlsPanel.add(battlePanel);

		// Backpack Panel
		JPanel backpackPanel = new JPanel(new GridLayout(1, 5, 8, 5));
		backpackPanel.setBackground(DARK_BLUE);
		backpackPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(GREEN),
				"Backpack / Use Item", TitledBorder.LEFT, TitledBorder.TOP, new Font("Arial", Font.BOLD, 11), GREEN));

		itemButtons = new JButton[5];
		for (int i = 0; i < itemButtons.length; i++) {
			itemButtons[i] = createStyledButton("Empty", "ITEM" + i, new Color(60, 70, 85)); // FIXED: correct syntax
			backpackPanel.add(itemButtons[i]);
		}
		controlsPanel.add(backpackPanel);

		// Discard Panel
		//discardPanel = new JPanel();
		discardPanel.setLayout(new GridLayout(1, 6, 8, 5));
		discardPanel.setBackground(DARK_BLUE);
		discardPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(RED), "Discard Choice",
				TitledBorder.LEFT, TitledBorder.TOP, new Font("Arial", Font.BOLD, 11), RED));
		controlsPanel.add(discardPanel);

		return controlsPanel;
	}

	private JButton createStyledButton(String text, String actionCommand, Color bgColor) {
		JButton button = new JButton(text);
		button.setActionCommand(actionCommand);
		button.setBackground(bgColor);
		button.setForeground(Color.BLACK);
		button.setFocusPainted(false);
		button.setBorderPainted(false);
		button.setFont(new Font("Arial", Font.BOLD, 12));
		button.setCursor(new Cursor(Cursor.HAND_CURSOR));

		// Hover effect
		button.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent e) {
				button.setBackground(bgColor.brighter());
			}

			public void mouseExited(java.awt.event.MouseEvent e) {
				button.setBackground(bgColor);
			}
		});

		button.addActionListener(controller);
		button.addKeyListener(controller);
		button.setFocusable(false);
		return button;
	}

	private JButton buildButton(String text, String actionCommand) {
		return createStyledButton(text, actionCommand, new Color(70, 80, 100));
	}

	public void refresh() {
		// Update HP colors
		int hp = game.getPlayer().getHp();
		playerHpLabel.setText("Player HP: " + hp);
		if (hp <= 20) {
			playerHpLabel.setForeground(Color.RED);
		} else if (hp <= 50) {
			playerHpLabel.setForeground(Color.ORANGE);
		} else {
			playerHpLabel.setForeground(GREEN);
		}

		dodgeChanceLabel.setText("Next Dodge Chance: " + (int) (game.getPlayer().getDodgeChance() * 100) + "%");
		ticketLabel.setText("Gacha Tickets: " + game.getPlayer().getGachaTickets());
		shieldLabel.setText("Shield Count: " + game.getPlayer().getShield());
		monstersLeftLabel.setText("Monsters Left: " + game.getMonstersLeft());
		currentRoomLabel.setText("Current Room: " + game.getCurrentRoom().toString());
		actionArea.setText(game.getActionMsg());
		displayText.setCaretPosition(0);

		if (game.getCurrentRoom() instanceof MonsterRoom) {
			MonsterRoom monsterRoom = (MonsterRoom) game.getCurrentRoom();
			int monsterHp = monsterRoom.getMonster().getHp();
			monsterHpLabel.setText("Monster HP: " + monsterHp);
			if (monsterHp <= 20) {
				monsterHpLabel.setForeground(Color.RED);
			} else {
				monsterHpLabel.setForeground(RED);
			}
			monsterLevelLabel.setText("Monster Level: " + monsterRoom.getLevel());
			gameLogPanel.setBackground(monsterRoomBG);
		} else if (game.getCurrentRoom() instanceof EventRoom) {
			monsterHpLabel.setText("Monster HP: ???");
			monsterHpLabel.setForeground(LIGHT_GREEN);
			monsterLevelLabel.setText("Monster Level: ???");
			gameLogPanel.setBackground(eventRoomBG);
		} else {
			monsterHpLabel.setText("Monster HP: N/A");
			monsterHpLabel.setForeground(LIGHT_GREEN);
			monsterLevelLabel.setText("Monster Level: N/A");
			gameLogPanel.setBackground(gachaRoomBG);
		}

		ArrayList<Room> rooms = game.getNextRoomList();
		for (int i = 0; i < roomButtons.length; i++) {
			boolean available = rooms != null && i < rooms.size() && game.isInGacha() && !game.isWaitingForDiscard();
			roomButtons[i].setEnabled(available);
			if (available) {
				roomButtons[i].setText("(" + (i + 1) + ") " + rooms.get(i).toString());
				if (rooms.get(i) instanceof MonsterRoom) {
					roomButtons[i].setBackground(new Color(180, 50, 50));
				} else {
					roomButtons[i].setBackground(MID_BLUE);
				}
			} else {
				roomButtons[i].setText("Room " + (i + 1));
				roomButtons[i].setBackground(new Color(60, 70, 85));
			}
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
		char[] keys = { 'Q', 'W', 'E', 'R', 'T' };
		for (int i = 0; i < itemButtons.length; i++) {
			boolean hasItem = i < backpack.size();
			itemButtons[i].setEnabled(hasItem && game.isInBattle());
			if (hasItem) {
				itemButtons[i].setText("(" + keys[i] + ") " + backpack.getItem(i).getName());
				itemButtons[i].setToolTipText(backpack.getItem(i).getMsg());
				itemButtons[i].setBackground(GREEN);
			} else {
				itemButtons[i].setText("(" + keys[i] + ") Empty");
				itemButtons[i].setToolTipText("Empty slot");
				itemButtons[i].setBackground(new Color(60, 70, 85));
			}
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
			JButton discardButton = createStyledButton(backpack.getItem(i).getName(), "DISCARD" + i, RED);
			discardButton.setFocusable(false);
			discardPanel.add(discardButton);
		}

		JButton discardNewButton = createStyledButton("New: " + game.getPendingItem().getName(),
				"DISCARD" + backpack.size(), GOLD);
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
		String guide = "Your character: " + game.getPlayer().getPlayerType();
		guide += "\nDefeat all monsters to escape from the Dungeon!";
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
		String msg = "A FIERCE MONSTER APPEARS! \n\n";
		msg += "Monster Level: " + level + "\n\n";
		msg += "Prepare for battle!\n";
		msg += "Use [A] to attack or [D] to dodge!";
		showDisplay(monsterImage, msg);
	}

	public void showEventDisplay() {
		showDisplay(questionMarkImage, "A random event will occur!");
	}

	public void showItemDisplay(Item item) {
		if (item == null) {
			showDisplay(backpackImage, "Your backpack is empty!\nPull gacha to get items!");
		} else if (item instanceof BombItem) {
			showDisplay(bombImage, "BOMB\n\n" + item.getMsg(), 1000, 1000);
		} else if (item instanceof CursedSkull) {
			showDisplay(skullImage, "CURSED SKULL\n\n" + item.getMsg() + "\n\n⚠WARNING: This will kill you!");
		} else if (item instanceof InstantKillSword) {
			showDisplay(swordImage, "INSTANT KILL SWORD\n\n" + item.getMsg() + "\n\nOne-hit kill any monster!");
		} else if (item instanceof LuckyCharm) {
			showDisplay(luckyCharmImage, "LUCKY CHARM\n\n" + item.getMsg());
		} else if (item instanceof PotionItem) {
			PotionItem potion = (PotionItem) item;
			String type = potion.getHpChange() > 0 ? "HEALING POTION" : "POISON POTION";
			showDisplay(potionImage, type + "\n\n" + item.getMsg());
		} else if (item instanceof ShieldItem) {
			showDisplay(shieldImage, "SHIELD\n\n" + item.getMsg());
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
			g2d.setColor(new Color(0, 0, 0, 180));
			g2d.fillRect(0, 0, getWidth(), getHeight());
		}
	}
}
