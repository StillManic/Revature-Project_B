package com.revature.fixtures;

import java.util.HashMap;
import java.util.Map;

public class Room extends Fixture {
	/*
	 * [0]: North
	 * [1]: South
	 * [2]: West
	 * [3]: East
	 */
	private Room[] exits = new Room[4];
	private Map<String, Fixture> fixtures = new HashMap<String, Fixture>();
	private boolean hasFixtures = false;
	
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
	
	public void addFixture(String fixtureName, Fixture fixture) {
		this.fixtures.put(fixtureName.toLowerCase(), fixture);
		this.hasFixtures = true;
	}
	
	public Fixture getFixture(String fixtureName) {
		return this.fixtures.get(fixtureName);
	}
	
	public boolean hasFixtures() {
		return this.hasFixtures;
	}
}
