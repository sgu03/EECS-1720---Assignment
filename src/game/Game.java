package game;

import characters.*;

public class Game {
	public Player player;
	public final int playerHP = 40;
	private Map map;
	private int monstersLeft;
	
	public Game(int monsterCount) {
		player = new Player(playerHP);
		map = new Map(monsterCount);
		monstersLeft = monsterCount;
	}
	
	public int getMonstersLeft() {
		return monstersLeft;
	}
	
	public void defeatMonster() {
		monstersLeft--;
	}
}
