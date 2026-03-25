package model.rooms;

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
	public void gachaPull (Player player, int pulls) {

		if (player.getGachaTickets() < pulls) { 
			System.out.println("Not enough tickets!");
			return;
		}

		for (int i = 0; i < pulls; i++) {
			player.useGachaTickets();

			Item item = generateRandomItem();

			System.out.println("Pulled: " + item.getName());

			Backpack backpack = player.getBackPack();

			//if backpack is full
			if (backpack.isFull()) {
			System.out.println("Backpack is full!");

			backpack.printBackpack();

			//removes first item to put in new item (might change gimmick later)
			System.out.println("First item removed to make space...");
			backpack.removeItem(0);
		}
		backpack.addItem(item);
	}
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
		return new SupportItem("Lucky Charm");
	}
	else { 
		if (rand.nextBoolean()) {
			return new InstantKillItem("Instant Kill Sword");
		} else {
			return new InstantDeathItem("Cursed Skull");

		  }

		}
	}

}

