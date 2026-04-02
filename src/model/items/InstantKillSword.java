package model.items;

import model.characters.Player;
import model.characters.Monster;

public class InstantKillSword extends Item {

	public InstantKillSword(String name) {
		super(name);
	}

	//instantly kills the Monster

	@Override
	public void use(Player player, Monster monster) {
		if (monster == null) {
			return;
		}

		monster.damage(monster.getHp()); //monster is super dead

	}

}
