package rooms;

import java.util.Random;
import characters.Player;

public class EventRoom extends Room {
	private int type;
	private String name;
	
	private Random random = new Random();
	
	public EventRoom(int type) {
		this.type = type;
		switch (type) {
			case 0:
				name = "Trap";
				break;
			case 1:
				name = "Healing Fountain";
				break;
			case 2:
				name = "Curse";
				break;
			case 3:
				name = "Treasure";
				break;
		}
	}
	
	public String getName() {
		return name;
	}
	
	public String applyEvent(Player player) {
		switch (type) {
			case 0:
				player.damage(5);
				return "Your HP -5";
			case 1:
				player.heal(10);
				return "Your HP +10";
			case 2:
				player.curse(0.2);
				return "Next Dodge Chance -20%";
			case 3:
				int num = random.nextInt(1, 5);
				player.gainGachaTickets(num);
				return "Gain " + num + " gacha tickets!";
		}
		return "";
	}
	
	public String toString() {
		return "Event Room [Random event will occur]";
	}
}
