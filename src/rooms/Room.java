package rooms;

import characters.*;
import game.*;

public class Room {
	protected boolean isClear = false;
	
	public void clear() {
		isClear = true;
	}
	
	public boolean getClearStatus() {
		return isClear;
	}
}
