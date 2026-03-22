package game;

import java.util.*;

import characters.*;
import rooms.*;
import items.*;

public class Game {
	private static Scanner scanner = new Scanner(System.in);
	
	public static final int PLAYER_HP = 40;	
	
	public Player player;
	private Map map;
	private int monstersLeft;
	private ArrayList<Room> nextRoomList;
	
	
	public Game(int monsterCount) {
		player = new Player(PLAYER_HP);
		map = new Map(monsterCount);
		monstersLeft = monsterCount;
	}
	
	public int getMonstersLeft() {
		return monstersLeft;
	}
	
	public void chooseNextRoom(ArrayList<Room> list, int index) {
		Room nextRoom = list.get(index-1);
		enterRoom(nextRoom);
	}

	// Enter rooms except Gacha Room
	public void enterRoom(Room room) {
		if (room instanceof MonsterRoom) {
			MonsterRoom mRoom = (MonsterRoom) room;
			System.out.println("\n******Monster Room******");
			while (player.getHp() > 0 && mRoom.getMonster().getHp() > 0) {
				boolean dodge = false;
				System.out.println("\n<Your Turn>");
				System.out.println("Player HP: " + player.getHp());
				System.out.println("Monster HP: " + mRoom.getMonster().getHp());
				System.out.println("Please take action: (1/2/3)");
				System.out.println("1 - Use an item from backpack");
				System.out.println("2 - Normal Attack");
				System.out.printf("3 - Dodge Attack from Monster [Chance: %.0f%%]\n", player.getDodgeChance()*100);
				switch (scanner.nextInt()) {
					case 1:
						// use an item from backpack
						if (player.getBackpack().isEmpty()) {
							System.out.println("You don't have any item!");
							break;
						} else {
							for (int i = 0; i < player.getBackpack().size(); i++) {
								// print item details
								
							}
						}
						break;
					case 2:
						if (player.attack()) {
							mRoom.getMonster().damage(5);
							System.out.println("Attack success! [Monster HP -5]");
						} else {
							System.out.println("Attack fail!");
						}
						break;
					case 3:
						dodge = true;
						break;
				}
				if (mRoom.getMonster().getHp() <= 0) {
					break;
				}
				System.out.println("\n<Monster's Turn>");
				if (dodge && player.dodge()) {
					System.out.println("Dodge success!");
				} else if (dodge) {
					player.damage(5);
					System.out.println("Dodge fail! [Player HP -5]");
				} else {
					player.damage(5);
					System.out.println("Monster attacks! [Player HP -5]");
				}
			}
			System.out.println("Player HP: " + player.getHp());
			System.out.println("Monster HP: " + mRoom.getMonster().getHp());
			if (mRoom.getMonster().getHp() <= 0) {
				System.out.println("Defeat Monster!\n");
				monstersLeft--;
				mRoom.clear();
			}
			
		} else if (room instanceof EventRoom) {
			EventRoom eRoom = (EventRoom) room;
			System.out.println("\n******" + eRoom.getName() + " Room******");
			System.out.println(eRoom.applyEvent(player) + "\n");
			eRoom.clear();
		}
		if (player.getHp() <= 0)  {
			System.out.println("You die...");
		} else if (monstersLeft <= 0) { 	
			System.out.println("All monsters are defeated! You win!!!");
		} else {
			enterGachaRoom();
		}
	}
	
	public void enterGachaRoom() {
		System.out.println("Player HP: " + player.getHp());
		System.out.println("You now have " + player.getGachaTickets() + " gacha tickets");
		System.out.println(monstersLeft + " monsters left");
		
		nextRoomList = map.newNextRoomList();
		System.out.println("\nNext Rooms:");
		for (int i = 0; i < nextRoomList.size(); i++) {
			System.out.println(i+1 + " - " + nextRoomList.get(i).toString());
		}
		
		boolean isFin = false;
		while (!isFin && player.getGachaTickets() > 0) {
			System.out.println("Pull Gacha? (y/n)");
			char input = scanner.next().toCharArray()[0];
			switch (input) {
				case 'y':
					// pull gacha, display gacha result, and check backpack capacity
					break;
				default:
					isFin = true;
					System.out.println("Exiting Gacha Room. Please choose which room to enter next (1-" + nextRoomList.size() + ")");
					break;
			}
		}
		
		int next = scanner.nextInt();
		while (next > nextRoomList.size() || next == 0) {
			next = 0;
			System.out.println("Invalid input. Please choose again: ");
			next = scanner.nextInt();
		}
		chooseNextRoom(nextRoomList, next);
	}
	
	
}
