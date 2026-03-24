package model.rooms;

import model.characters.*;
import model.game.*;

public abstract class Room {
	protected boolean isClear = false;
	protected String name;	// appeared in UI [Current Room]
	
	public abstract String toString();	// description appeared in next room lists
	
	public void clear() {
		isClear = true;
	}
	
	public boolean getClearStatus() {
		return isClear;
	}
}
