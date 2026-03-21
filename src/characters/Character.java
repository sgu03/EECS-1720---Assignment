package characters;

import game.*;

public class Character {
	protected int hp;
	protected double attackChance;
	protected double dodgeChance;
	protected boolean attackSuccess;
	protected boolean dodgeSuccess;
	
	public Character(int hp) {
		this.hp = hp;
		attackSuccess = false;
		dodgeSuccess = false;
	}
	
	public int getHp() {
		return hp;
	}
	
	public int damage(int damage) {
		return hp - damage;
	}
	
	public int heal(int heal) {
		return hp + heal;
	}

	public boolean attack() {
		if (game.Map.random.nextDouble() <= attackChance) {
			attackSuccess = true;
		} else {
			attackSuccess = false;
		}
		return attackSuccess;
	}
	
	public boolean dodge() {
		if (game.Map.random.nextDouble() <= dodgeChance) {
			dodgeSuccess = true;
		} else {
			dodgeSuccess = false;
		}
		return dodgeSuccess;
	}
}
