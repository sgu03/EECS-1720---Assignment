package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;

import model.game.Game;
import model.rooms.EventRoom;
import view.*;

public class GameController implements ActionListener, KeyListener {
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
				Timer timer = new Timer(1500, new ActionListener() {
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
		view.refresh();
	}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {}
}
