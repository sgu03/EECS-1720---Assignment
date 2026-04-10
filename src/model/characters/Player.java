package model.characters;

import java.util.ArrayList;

import model.items.*;

public class Player extends Character {
	private PlayerType type;
	private String typeDescription;
	private int gachaTickets;
	private Backpack backpack;
	private int shield;
	private int attack;
	private boolean nextDodgeGuaranteed;
	private boolean isLucky;
	
	public Player(int hp) {
		super(hp);
		setPlayerType(PlayerType.NORMAL);
		setTypeDescription();
		dodgeChance = 0.8;
		gachaTickets = 5;
		backpack = new Backpack();
		nextDodgeGuaranteed = false;
	}
	
	public PlayerType getPlayerType() {
		return type;
	}
	
	public void setPlayerType(PlayerType type) {
		this.type = type;
		switch(type) {
			case NORMAL:
				attackChance = 0.6;
				attack = 5;
				isLucky = false;
				hp = 40;
				shield = 0;
				break;
			case WARRIOR:
				attackChance = 1.0;
				attack = 8;
				break;
			case LUCKY:
				isLucky = true;	// will only get positive items
				break;
			case TANK:
				hp += 20;
				break;
			case ROGUE:
				shield = 30;
				break;
		}
		setTypeDescription();
	}
	
	private void setTypeDescription() {
		switch(type) {
			case NORMAL:
				typeDescription = "Balanced adventurer";
				break;
			case WARRIOR:
				typeDescription = "Stronger attack";
				break;
			case LUCKY:
				typeDescription = "Positive items only";
				break;
			case TANK:
				typeDescription = "20 extra HP";
				break;
			case ROGUE:
				typeDescription = "30 extra shields";
				break;
		}
	}
	
	public String getTypeDescription() {
		return typeDescription;
	}

	public int getShield() {
		return shield;
	}
	
	public int getAttack() {
		return attack;
	}
	
	@Override
	public double getDodgeChance() {
		if (nextDodgeGuaranteed) {
			return 1;
		}
		return dodgeChance;
	}
	
	public boolean getIsLucky() {
		return isLucky;
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
			damage(-amount);
		}
	}

	//adds shield
	public void addShield(int value) {
		shield += value;
	}

	//override damage to include shield hp
	public void damage(int damage) {
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
