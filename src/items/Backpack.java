package items;

import java.util.ArrayList;

public class Backpack {
	private int capacity;
	private ArrayList<Item> items;
	
	public Backpack() {
		capacity = 5;
		items = new ArrayList<Item>(capacity);
	}
	
	public ArrayList<Item> getItemList() {
		return items;
	}
}
