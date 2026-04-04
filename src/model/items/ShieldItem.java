package model.items;

import model.characters.Player;
import model.characters.Monster;

public class ShieldItem extends Item {

	private int shieldValue;
	
	public ShieldItem(String name, int shieldValue) {
		super(name);
		this.shieldValue = shieldValue;
		this.msg = "Can block " + shieldValue + "HP attack";
	}

	//gives player a shield

	@Override
	public void use(Player player, Monster monster) {
		if (player == null) {
			return;
		}

		player.addShield(shieldValue);
	}

}
