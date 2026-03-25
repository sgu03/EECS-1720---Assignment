package model.items;

import model.characters.Player;
import model.characters.Monster;


public abstract class Item {
	protected String name;

	public Item(String name) {
		this.name = name;

	}


	public String getName() {
		return name;

	}

	//use Item
	public abstract void use(Player player, Monster monster);

	@Override
	public String toString() {
		return name;
	}


}
