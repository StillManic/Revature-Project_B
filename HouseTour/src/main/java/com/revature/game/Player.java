package com.revature.game;

import com.revature.fixtures.Room;

public class Player {
	private Room currentRoom;
	
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
}
