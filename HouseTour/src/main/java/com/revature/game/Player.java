package com.revature.game;

import com.revature.fixtures.Room;

public class Player {
	public Room currentRoom;
	
	public Player() {}
	
	public Player(Room currentRoom) {
		this.currentRoom = currentRoom;
	}
}
