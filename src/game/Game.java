package game;

import characters.*;

public class Game {
	public static Player player;
	public static final int playerHP = 40;
	private static int monstersLeft;
	
	public Game(int monsterCount) {
		player = new Player(playerHP);
		Map map = new Map(monsterCount);
		monstersLeft = monsterCount;
	}
	
	public int getMonstersLeft() {
		return monstersLeft;
	}
	
	public static void defeatMonster() {
		monstersLeft--;
	}
}
