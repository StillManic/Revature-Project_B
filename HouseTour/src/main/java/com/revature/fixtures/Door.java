package com.revature.fixtures;

public class Door extends Fixture implements Openable, Lockable {
	private boolean isOpen = false;
	private boolean isLocked = false;
	private boolean isActualDoor = true;
//	private Room[] rooms = new Room[2];
	private Room roomA, roomB;
	
	public Door(String name, String shortDescription, String longDescription, Room roomA, Room roomB) {
		super(name, shortDescription, longDescription);
		this.roomA = roomA;
		this.roomB = roomB;
		this.isActualDoor = false;
		this.isOpen = true;
		this.isLocked = false;
	}
	
	public Door(String name, String shortDescription, String longDescription, Room roomA, Room roomB, boolean isOpen, boolean isLocked) {
		super(name, shortDescription, longDescription);
		this.roomA = roomA;
		this.roomB = roomB;
		this.isActualDoor = true;
		this.isOpen = isOpen;
		this.isLocked = isLocked;
	}

	public Room getOppositeRoom(String currentRoomName) {
		if (currentRoomName.toLowerCase().equals(this.roomA.getName())) return this.roomB;
		else if (currentRoomName.toLowerCase().equals(this.roomB.getName())) return this.roomA;
		else return null;
	}
	
	public boolean isActualDoor() {
		return this.isActualDoor;
	}
	
	@Override
	public boolean open() {
		if (!this.isActualDoor) return false;
		if (!this.isLocked) {
			this.isOpen = true;
			return true;
		}
		return false;
	}

	@Override
	public boolean close() {
		if (this.isActualDoor) {
			this.isOpen = false;
			return true;
		}
		return false;
	}

	@Override
	public boolean isOpen() {
		return this.isOpen;
	}

	@Override
	public void unlock() {
		this.isLocked = false;
	}
	
	@Override
	public boolean lock() {
		if (this.isActualDoor && !this.isOpen) {
			this.isLocked = true;
			return true;
		}
		return false;
	}

	@Override
	public boolean isLocked() {
		return this.isLocked;
	}
	
	@Override
	public void examine() {
		
	}
}
