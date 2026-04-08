package model.items;

import model.characters.Player;
import model.characters.Monster;


public abstract class Item {
	protected String name;
	protected String msg;

	public Item(String name) {
		this.name = name;
		this.msg = "";
	}


	public String getName() {
		return name;

	}
	
	public String getMsg() {
		return msg;
	}

	//use Item
	public abstract void use(Player player, Monster monster);

	@Override
	public String toString() {
		return name;
	}


}
