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
	
	public void applyEvent(Player player) {
		switch (type) {
		case 0:
			player.damage(5);
			break;
		case 1:
			player.heal(10);
			break;
		case 2:
			player.curse(0.2);
			break;
		case 3:
			player.gainGachaTickets(random.nextInt(1, 5));
			break;
	}
	}
}
