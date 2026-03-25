package model.rooms;

import model.characters.Monster;

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
	
	public int getLevel() {
		return level;
	}
	
	private int difficultyLevel(int hp) {
		int difficulty;
		if (hp <= model.game.Map.MONSTER_MIN_HP + 10) {
			difficulty = 1;
		} else if (hp <= model.game.Map.MONSTER_MIN_HP + 20) {
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
