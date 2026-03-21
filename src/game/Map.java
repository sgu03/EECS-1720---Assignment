package game;

import java.util.*;

import rooms.*;
import characters.*;

public class Map {
	public static final int MONSTER_MAX_HP = 60;
	public static final int MONSTER_MIN_HP = MONSTER_MAX_HP - 30;

	public static Random random = new Random();
	
	public GachaRoom gachaRoom;
	private int monsterCount;
	private MonsterRoom[] monsterRooms;
	private EventRoom[] eventRooms;
	private ArrayList<Room> nextRoomList;
	private ArrayList<Room> availableRooms;

	
	public Map(int monsterCount) {
		// Create the only GachaRoom
		gachaRoom = new GachaRoom();
		
		// Create MonsterRooms based on monsterCount
		this.monsterCount = monsterCount;
		monsterRooms = new MonsterRoom[monsterCount];
		for (int i = 0; i < monsterCount; i++) {
			Monster monster = new Monster(random.nextInt(MONSTER_MIN_HP, MONSTER_MAX_HP+1));
			monsterRooms[i] = new MonsterRoom(monster);
		}
		
		// Create random EventRooms
		eventRooms = new EventRoom[monsterCount*2];
		for (int i = 0; i < eventRooms.length; i++) {
			eventRooms[i] = new EventRoom(random.nextInt(0, 4));
		}
		
		// Initialize next room list
		nextRoomList = newNextRoomList();
		
	}
	
	public int getMonsterCount() {
		return monsterCount;
	}
	
	public void updateAvailableRooms() {
		for (MonsterRoom m : monsterRooms) {
	        if (!m.getClearStatus()) {
	            availableRooms.add(m);
	        }
	    }
	    for (EventRoom e : eventRooms) {
	        if (!e.getClearStatus()) {
	            availableRooms.add(e);
	        }
	    }
	}
	
	public ArrayList<Room> newNextRoomList() {
		nextRoomList.clear();
		updateAvailableRooms();
		
		while (!availableRooms.isEmpty() && nextRoomList.size() < 3) {
			nextRoomList.add(availableRooms.get(random.nextInt(0, availableRooms.size())));
		}
		
		return nextRoomList;
	}
}
