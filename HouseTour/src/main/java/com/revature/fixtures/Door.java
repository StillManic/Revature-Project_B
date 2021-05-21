package com.revature.fixtures;

public class Door extends Fixture implements Openable, Lockable {
	private boolean isOpen = false;
	private boolean isLocked = false;
	private Room room;
	
	public Door(String name, String shortDescription, String longDescription, Room room, boolean isOpen, boolean isLocked) {
		super(name, shortDescription, longDescription);
		this.room = room;
		this.isOpen = isOpen;
		this.isLocked = isLocked;
	}

	public Room getRoom() {
		if (this.isOpen) return this.room;
		return null;
	}
	
	@Override
	public boolean open() {
		if (!this.isLocked) {
			this.isOpen = true;
			return true;
		}
		return false;
	}

	@Override
	public void close() {
		this.isOpen = false;
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
		if (!this.isOpen) {
			this.isLocked = true;
			return true;
		}
		return false;
	}

	@Override
	public boolean isLocked() {
		return this.isLocked;
	}
}
