package model.items;

import model.characters.Player;
import model.characters.Monster;

public class BombItem extends Item {

	private int damage; 

	public BombItem(String name, int damage) {
		super(name);
		this.damage = damage;
	}

	//Bomb does damage to monsters

	@Override
	public void use(Player player, Monster monster) {
		if (monster == null) {
			return;
		}

		monster.damage(damage);

	}


	
}
