package model.game;

import java.util.*;

import model.characters.*;
import model.items.*;
import model.rooms.*;

public class Game {
	public static final int PLAYER_HP = 40;	
	
	private Player player;
	private Map map;
	private int monstersLeft;
	private ArrayList<Room> nextRoomList;
	private Room currentRoom;
	
	private String actionMsg;	// to store result of actions
	private boolean inBattle;	// to enable/disable battle action buttons in UI
	private boolean inGacha;	// to enable/disable pull gacha buttons in UI
	private boolean lose;
	private boolean win;
	
	private Item pendingItem;	// newly pulled item that cannot be added yet
	private boolean waitingForDiscard;	// waiting for user to choose which item to discard
	
	public Game(int monsterCount) {
		player = new Player(PLAYER_HP);
		map = new Map(monsterCount);
		monstersLeft = monsterCount;
		inBattle = false;
		lose = false;
		win = false;
		waitingForDiscard = false;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public int getMonstersLeft() {
		return monstersLeft;
	}
	
	public ArrayList<Room> getNextRoomList() {
		return nextRoomList;
	}
	
	public Room getCurrentRoom() {
		return currentRoom;
	}
	
	public String getActionMsg() {
		return actionMsg;
	}
	
	public Item getPendingItem() {
		return pendingItem;
	}
	
	public boolean isInBattle() {
		return inBattle;
	}
	
	public boolean isInGacha() {
		return inGacha;
	}
	
	public boolean isGameOver() {
		return lose;
	}
	
	public boolean isWin() {
		return win;
	}
	
	public boolean isWaitingForDiscard() {
		return waitingForDiscard;
	}
	
	// method to be called every time hp is changed
	public void updateGameStatus() {
		if (lose || win) return;
		if (player.getHp() <= 0) {
			lose = true;
			actionMsg += "\nYou die...";
		} else if (monstersLeft <= 0) {
			win = true;
			actionMsg += "\nAll monsters are defeated! You win!!!";
		}
		if (lose || win) {
		    inBattle = false;
		    inGacha = false;
		}
	}
	
	public void startGame() {
		actionMsg = "Welcome to Dungeon Gacha Adventure Game!";
		enterGachaRoom();
	}
	
	public void enterGachaRoom() {
		if (lose || win) return;
		inGacha = true;
		inBattle = false;
		waitingForDiscard = false;
		pendingItem = null;
		currentRoom = map.getGachaRoom();	
		nextRoomList = map.newNextRoomList();
		actionMsg = "Entered Gacha Room.";
	}
	
	// when pull gacha button is pressed
	public void pullGacha() {
		if (!inGacha || waitingForDiscard) return;
		GachaRoom gRoom = (GachaRoom) currentRoom;
		Item item = gRoom.gachaPull(player);
		if (item == null) {
			actionMsg = "Not enough gacha tickets!";
			return;
		} else if (player.getBackpack().isFull()) {
			pendingItem = item;
			waitingForDiscard = true;
			actionMsg = "You pulled " + item.getName() + " but your backpack is full!";
			actionMsg += "\nPlease choose an item to discard.";
		} else {
			player.getBackpack().addItem(item);
			actionMsg = "You pulled " + item.getName() + "!";
		}
	}
	
	// when player decides which item to discard
	public void discardItem(int index) {
		if (!inGacha || !waitingForDiscard || pendingItem == null) return;
		Backpack backpack = player.getBackpack();
		
		if (index < 0 || index > backpack.size()) {
			actionMsg = "Invalid item choice.";
			return;
		}

		// if index = backpack.size(), it means player wants to discard the newly pulled item
		if (index == backpack.size()) {
			actionMsg = "Discarded " + pendingItem.getName() + ".";
		} else {	
			// player wants to remove an item from backpack
			actionMsg = "Removed " + backpack.getItem(index).getName() + " and added " + pendingItem.getName() + ".";
			backpack.removeItem(index);
			backpack.addItem(pendingItem);
		}
		pendingItem = null;
		waitingForDiscard = false;
	}
	
	public void chooseNextRoom(int index) {
		if (nextRoomList == null || index < 0 || index >= nextRoomList.size()) {
			actionMsg += "\nInvalid room choice. Please choose again.";
			return;
		}
		currentRoom = nextRoomList.get(index);
		inGacha = false;
		enterRoom(currentRoom);
	}

	// Enter rooms except Gacha Room
	public void enterRoom(Room room) {
		if (room instanceof MonsterRoom) {
			inBattle = true;
			actionMsg = "Entered Monster Room.\n[Your Turn]";
		} else if (room instanceof EventRoom) {
			EventRoom eRoom = (EventRoom) room;
			actionMsg = "Entered " + eRoom.getName() + " Room.\n";
			actionMsg += eRoom.applyEvent(player);
			eRoom.clear();
			updateGameStatus();
			if (!lose && !win) enterGachaRoom();
		}
	}
	
	// If player chooses Action 1: Use an Item
	public void useItem(int index) {
		if (!(currentRoom instanceof MonsterRoom)) return;
		MonsterRoom mRoom = (MonsterRoom) currentRoom;
		Monster monster = mRoom.getMonster();
		if (player.getBackpack().getItemList().isEmpty()) {
			actionMsg += "\nYou don't have any item!";
			return;
		} else { 
			// use an item and remove it from backpack
			// actionMsg = "You used a xxx item!";
		}
		if (!checkMonsterDefeated()) {
			monsterTurn(false);
		} else {
			updateGameStatus();
			if (!lose && !win) enterGachaRoom();
		}
	}
	
	// If player chooses Action 2: Normal Attack
	public void normalAttackMonster() {
		if (!(currentRoom instanceof MonsterRoom)) return;
		MonsterRoom mRoom = (MonsterRoom) currentRoom;
		Monster monster = mRoom.getMonster();
		if (player.attack()) {
			monster.damage(5);
			actionMsg += "\nAttack successful! [Monster -5 HP]";
		} else {
			actionMsg = "\nAttack failed!";
		}
		if (!checkMonsterDefeated()) {
			monsterTurn(false);
		} else {
			updateGameStatus();
			if (!lose && !win) enterGachaRoom();
		}
	}
	
	// If player choose Action 3: Dodge
	public void dodgeMonsterAttack() {
		if (!(currentRoom instanceof MonsterRoom)) return;
		monsterTurn(true);
	}
	
	private void monsterTurn(boolean isDodge) {
		if (!(currentRoom instanceof MonsterRoom)) return;
		actionMsg = "[Monster Turn]";
		MonsterRoom mRoom = (MonsterRoom) currentRoom;
		Monster monster = mRoom.getMonster();
		if (isDodge) {
			if (player.dodge()) {
				actionMsg += "\nDodge successful!";
			} else {
				player.damage(5);
				actionMsg += "\nDodge failed! [-5 HP]";
				updateGameStatus();
			}
		} else if (monster.attack()){
			player.damage(5);
			actionMsg += "\nMonster attack successful! [-5 HP]";
			updateGameStatus();
		} else {
			actionMsg += "\nMonster attack failed!";
		}
	}
	
	public boolean checkMonsterDefeated() {
		if (!(currentRoom instanceof MonsterRoom)) return false;
		MonsterRoom mRoom = (MonsterRoom) currentRoom;
		Monster monster = mRoom.getMonster();
		if (monster.getHp() <= 0) {
			mRoom.clear();
			monstersLeft--;
			actionMsg += "\nDefeat Monster!";
			return true;
		} else {
			return false;
		}
	}
	
}
