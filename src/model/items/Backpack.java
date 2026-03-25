package model.items;

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

	public boolean isFull() {
		return items.size() >= capacity;
	}

	public boolean addItem(Item item) {
		if (isFull()) {
			return false;
		}

		items.add(item);
		return true;

	}

	public void removeItem(int index) {
		if (index >= 0 && index < items.size()) {
			items.remove(index);
		}
	}

	public Item getItem(int index) {
		if (index >= 0 && index < items.size()) {
			return items.get(index);
		}
		return null;
	}

	public int size() {
		return items.size();
	}

	public void printBackpack() {
		System.out.println("Backpack: ");
		for (int i = 0; i < items.size(); i++) {
			System.out.println(i + ": " + items.get(i).getName());
		}
	}

}
