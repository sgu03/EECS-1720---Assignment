package model.rooms;

import java.util.ArrayList;
import java.util.Random;
import model.characters.Player;
import model.items.*;

public class GachaRoom extends Room {

	private Random rand = new Random();


	public GachaRoom() {
		isClear = true;
		name = "Gacha";
	}
	
	@Override
	public String toString() {
		return "Gacha Room";
	}

	//performing the gacha pulls 
	public Item gachaPull (Player player) {
		// changed strategy to only allow one pull each time (instead of multiple pulls)
		
		if (player.getGachaTickets() <= 0) { 
			//System.out.println("Not enough tickets!");
			return null;
		}
		
		player.useGachaTickets();
		Item pulledItem = generateRandomItem();
		return pulledItem;
		
		// moved backpack management to game class
		// -> so player can choose which item to remove when backpack is full

		/*for (int i = 0; i < pulls; i++) {
			player.useGachaTickets();

			Item item = generateRandomItem();

			//System.out.println("Pulled: " + item.getName());
			
			Backpack backpack = player.getBackpack();

			//if backpack is full
			if (backpack.isFull()) {
			System.out.println("Backpack is full!");

			backpack.printBackpack();

			//removes first item to put in new item (might change gimmick later)
			System.out.println("First item removed to make space...");
			backpack.removeItem(0);
			}
			backpack.addItem(item);
		}*/
	}

	//gacha probabilities
	private Item generateRandomItem() {
		int roll = rand.nextInt(100);
		
		if (roll < 60) {
			if (rand.nextBoolean()) {
				return new PotionItem("Healing Potion", 10);
			} else {
				return new PotionItem("Poison Potion", -5);
			}
		}
		else if (roll < 80) {
			if (rand.nextBoolean()) {
				return new ShieldItem("Shield", 5);
			} else {
				return new BombItem("Bomb", 15);
			}
		}
		else if (roll < 95) {
			return new LuckyCharm("Lucky Charm");
		}
		else { 
			if (rand.nextBoolean()) {
				return new InstantKillSword("Instant Kill Sword");
			} else {
				return new CursedSkull("Cursed Skull");

			}

		}
	}

}

