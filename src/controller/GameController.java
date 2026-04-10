package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.Timer;
import javax.swing.event.MouseInputAdapter;

import model.game.Game;
import model.items.Backpack;
import model.items.Item;
import model.rooms.*;
import view.*;

public class GameController extends MouseInputAdapter implements ActionListener, KeyListener {
	private final Game game;
	private GameFrame view;
	private StartFrame startView;

	public GameController(Game game, GameFrame view) {
		this.game = game;
		this.view = view;
	}
	
	public GameController(Game game, StartFrame startView) {
		this.game = game;
		this.startView = startView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		handleCommand(e.getActionCommand());
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_G:
				handleCommand("GACHA");
				break;
			case KeyEvent.VK_1:
				handleCommand("ROOM0");
				break;
			case KeyEvent.VK_2:
				handleCommand("ROOM1");
				break;
			case KeyEvent.VK_3:
				handleCommand("ROOM2");
				break;
			case KeyEvent.VK_A:
				handleCommand("ATTACK");
				break;
			case KeyEvent.VK_D:
				handleCommand("DODGE");
				break;
			case KeyEvent.VK_Q:
				handleCommand("ITEM0");
				break;
			case KeyEvent.VK_W:
				handleCommand("ITEM1");
				break;
			case KeyEvent.VK_E:
				handleCommand("ITEM2");
				break;
			case KeyEvent.VK_R:
				handleCommand("ITEM3");
				break;
			case KeyEvent.VK_T:
				handleCommand("ITEM4");
				break;
			default:
				return;
		}
	}

	private void handleCommand(String command) {
		if (command == null || game.isGameOver() || game.isWin()) {
			view.refresh();
			return;
		}
		
		if (command.equals("START")) {
			game.setMonsterCount(startView.getDifficulty());
			this.view = new GameFrame(game);
			startView.setVisible(false);
			return;
		}

		if ("GACHA".equals(command)) {
			game.pullGacha();
		} else if ("ATTACK".equals(command)) {
			game.normalAttackMonster();
		} else if ("DODGE".equals(command)) {
			game.dodgeMonsterAttack();
		} else if (command.startsWith("ROOM")) {
			game.chooseNextRoom(Integer.parseInt(command.substring(4)));
			if (game.getCurrentRoom() instanceof EventRoom) {
				view.refresh();
				Timer timer = new Timer(1000, new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						game.updateGameStatus();				
						if (!game.isGameOver() && !game.isWin()) {
							game.enterGachaRoom();
						}
						view.refresh();
					}
				});
				timer.setRepeats(false);
				timer.start();
				return;
			}
		} else if (command.startsWith("ITEM")) {
			game.useItem(Integer.parseInt(command.substring(4)));
		} else if (command.startsWith("DISCARD")) {
			game.discardItem(Integer.parseInt(command.substring(7)));
		}

		game.updateGameStatus();
		view.showPlayerDisplay();
		view.refresh();
	}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		if (!(e.getSource() instanceof JButton) || view == null) {
			return;
		}
		JButton button = (JButton) e.getSource();
		String command = button.getActionCommand();
		if (command == null) {
			return;
		}
		if (command.startsWith("ITEM") || command.startsWith("DISCARD")) {
			int index;
			boolean discard;
			if (command.startsWith("ITEM")) {
				index = Integer.parseInt(command.substring(4));
				discard = false;
			} else {
				index = Integer.parseInt(command.substring(7));
				discard = true;
			}
			Backpack backpack = game.getPlayer().getBackpack();
			if (discard && index == backpack.size()) {
				view.showItemDisplay(game.getPendingItem());
				return;
			} else if (index < backpack.size()) {
				Item item = backpack.getItem(index);
				if (item != null) {
					view.showItemDisplay(item);
					return;
				}
			}
			view.showItemDisplay(null);
		} else if (command.startsWith("ROOM")) {
			if (game.getCurrentRoom() instanceof GachaRoom) {
				int index = Integer.parseInt(command.substring(4));
				if (game.getNextRoomList() != null && index < game.getNextRoomList().size()) {
					Room room = game.getNextRoomList().get(index);
					if (room instanceof MonsterRoom) {
						MonsterRoom mRoom = (MonsterRoom) room;
						view.showMonsterDisplay(mRoom.getLevel());
					}
					else if (room instanceof EventRoom) {
						view.showEventDisplay();
					}
					else {
						view.showPlayerDisplay();
					}
					return;
				}
			} else {
				view.showPlayerDisplay();
			}
		}
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
		if (view != null) {
			view.showPlayerDisplay();
		}
	}
}
