package rooms;

import characters.Monster;

public class MonsterRoom extends Room {
	private Monster monster;
	private int level;
	
	public MonsterRoom(Monster monster) {
		this.monster = monster;
		this.level = difficultyLevel(monster.getHp());
		name = "Monster";
	}
	
	public Monster getMonster() {
		return monster;
	}
	
	private int difficultyLevel(int hp) {
		int difficulty;
		if (hp <= game.Map.MONSTER_MIN_HP + 10) {
			difficulty = 1;
		} else if (hp <= game.Map.MONSTER_MIN_HP + 20) {
			difficulty = 2;
		} else {
			difficulty = 3;
		}
		return difficulty;
	}
	
	public String toString() {
		return "Monster Room [Level " + level + "]";
	}
}
