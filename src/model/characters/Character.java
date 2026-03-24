package model.characters;

import java.util.Random;

import model.game.*;

public abstract class Character {
	protected int hp;
	protected double attackChance;
	protected double dodgeChance;
	protected boolean attackSuccess;
	protected boolean dodgeSuccess;
	
	private Random random = new Random();
	
	public Character(int hp) {
		this.hp = hp;
		attackSuccess = false;
		dodgeSuccess = false;
	}
	
	public int getHp() {
		return hp;
	}
	
	public double getAttackChance() {
		return attackChance;
	}
	
	public double getDodgeChance() {
		return dodgeChance;
	}
	
	public void damage(int damage) {
		hp -= damage;
		if (hp <= 0) {
			hp = 0;
		}
	}
	
	public void heal(int heal) {
		hp += heal;
	}

	public boolean attack() {
		if (random.nextDouble() <= attackChance) {
			attackSuccess = true;
		} else {
			attackSuccess = false;
		}
		return attackSuccess;
	}
	
	public boolean dodge() {
		if (random.nextDouble() <= dodgeChance) {
			dodgeSuccess = true;
		} else {
			dodgeSuccess = false;
		}
		return dodgeSuccess;
	}
}
