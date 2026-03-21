package characters;

import game.Game;
import game.Map;

public class Player extends Character {
	private int gachaTickets;
	
	
	public Player(int hp) {
		super(hp);
		gachaTickets = 5;
		attackChance = 0.6;
		dodgeChance = 0.8;
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
	
 }
