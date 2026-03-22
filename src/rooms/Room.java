package rooms;

import characters.*;
import game.*;

public abstract class Room {
	protected boolean isClear = false;
	
	public abstract String toString();
	
	public void clear() {
		isClear = true;
	}
	
	public boolean getClearStatus() {
		return isClear;
	}
}
