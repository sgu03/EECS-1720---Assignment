package model.items;

import model.characters.Player;
import model.characters.Monster;

public class CursedSkull extends Item {

	public CursedSkull(String name) {
		super(name);
		this.msg = "You are cursed and instantly killed! Bye!";
	}


	//instantly kills the player

	@Override
	public void use(Player player, Monster monster) {
		if (player == null) {
			return;
		}

		player.damage(player.getHp()); //player is super dead
	}

}
