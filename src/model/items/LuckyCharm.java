package model.items;

import model.characters.Player;
import model.characters.Monster;

public class LuckyCharm extends Item {
	
	public LuckyCharm(String name){
		super(name);
	}

	@Override
	public void use(Player player, Monster monster) {
		if (player == null) {
			return;
		}

		player.boostDodge();
	}


}
