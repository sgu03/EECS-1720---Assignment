package model.characters;

import java.util.ArrayList;

import model.items.*;

public class Player extends Character {
	private int gachaTickets;
	private Backpack backpack;
	private int shield;
	private boolean nextDodgeGuaranteed;
	
	
	public Player(int hp) {
		super(hp);
		attackChance = 0.6;
		dodgeChance = 0.8;
		gachaTickets = 5;
		backpack = new Backpack();
		shield = 0;
		nextDodgeGuaranteed = false;
	}

	public int getShield() {
		return shield;
	}
	
	@Override
	public double getDodgeChance() {
		if (nextDodgeGuaranteed) {
			return 1;
		}
		return dodgeChance;
	}
	
	public Backpack getBackpack() {
		return backpack;
	}
	
	public int getGachaTickets() {
		return gachaTickets;
	}
	
	public void useGachaTickets() {
		gachaTickets--;
	}
	
	public void gainGachaTickets(int gainNum) {
		gachaTickets += gainNum;
	}
	
	public void curse(double reduceChance) {
		dodgeChance -= reduceChance;
	}
	
	//Handles both healing and damage (by potion)
	public void changeHP(int amount) {
		if (amount > 0) {
			heal(amount);

		} else if (amount < 0) {
			takeDamage(-amount);
		}
	}

	//adds shield
	public void addShield(int value) {
		shield += value;
	}

	//override damage to include shield hp
	public void takeDamage(int damage) {
		if (shield > 0) {
			if (shield >= damage) {
				shield -= damage;
				damage = 0;
			} else {
				damage -= shield;
				shield = 0;
			}
		}

		super.damage(damage); //uses character's damage method
	}

	// gives player guaranteed dodge for next attack 
	public void boostDodge() {
		nextDodgeGuaranteed = true;
	}
	
	//override dodge to include the boost 
	@Override
	public boolean dodge() {
		if (nextDodgeGuaranteed) {
			nextDodgeGuaranteed = false; //consumed
			return true; 
		}
		return super.dodge();
	}



 }
