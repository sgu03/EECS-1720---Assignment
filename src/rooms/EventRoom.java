package rooms;

public class EventRoom extends Room {
	private int type;
	public String name;
	
	public EventRoom(int type) {
		this.type = type;
		switch (type) {
			case 0:
				name = "Trap";
				game.Game.player.damage(5);
				break;
			case 1:
				name = "Healing Fountain";
				game.Game.player.heal(10);
				break;
			case 2:
				name = "Curse";
				game.Game.player.curse(0.2);
			case 3:
				name = "Treasure";
				game.Game.player.gainGachaTickets(game.Map.random.nextInt(1, 5));
				break;
		}
	}
}
