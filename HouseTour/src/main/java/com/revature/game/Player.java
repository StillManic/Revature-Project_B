package com.revature.game;

import java.util.HashMap;
import java.util.Map;

import com.revature.fixtures.Fixture;
import com.revature.fixtures.Room;

public class Player {
	private Room currentRoom;
	private Map<String, Fixture> inventory = new HashMap<String, Fixture>();
	
	public Player() {}
	
	public Player(Room currentRoom) {
		this.currentRoom = currentRoom;
	}
	
	public void setCurrentRoom(Room room) {
		this.currentRoom = room;
	}
	
	public Room getCurrentRoom() {
		return this.currentRoom;
	}
	
	public boolean addItemToInventory(Fixture item) {
		if (item == null) return false;
		this.inventory.put(item.getName(), item);
		return true;
	}
	
	public Fixture getItemFromInventory(String name) {
		if (name == null || name.isEmpty()) return null;
		return this.inventory.get(name.toLowerCase());
	}
	
	public Fixture[] getInventory() {
		return this.inventory.values().toArray(new Fixture[this.inventory.size()]);
	}
	
	public boolean hasItem(String name) {
		return this.inventory.containsKey(name.toLowerCase());
	}
}
