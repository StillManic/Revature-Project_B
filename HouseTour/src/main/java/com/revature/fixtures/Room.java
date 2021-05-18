package com.revature.fixtures;

public class Room extends Fixture {
	/*
	 * [0]: North
	 * [1]: South
	 * [2]: West
	 * [3]: East
	 */
	private Room[] exits = new Room[4];
	
	public Room(String name, String shortDescription, String longDescription) {
		super(name, shortDescription, longDescription);
	}
	
	public void initExits(Room north, Room south, Room west, Room east) {
		this.exits[0] = north;
		this.exits[1] = south;
		this.exits[2] = west;
		this.exits[3] = east;
	}
	
	public Room[] getExits() {
		return this.exits;
	}
	
	public Room getExit(String direction) {
		switch (direction.toLowerCase()) {
			case "north": return this.exits[0];
			case "south": return this.exits[1];
			case "west":  return this.exits[2];
			case "east":  return this.exits[3];
			default: return null;
		}
	}
}
