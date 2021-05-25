package com.revature.fixtures;

public class Drawer extends Fixture implements Openable {
	private Key key;
	private boolean isOpen = false;
	private boolean hasKey = false;
	
	public Drawer(String name, String shortDescription, String longDescription) {
		super(name, shortDescription, longDescription);
	}
	
	public Drawer(String name, String shortDescription, String longDescription, Key key) {
		this(name, shortDescription, longDescription);
		this.key = key;
		this.hasKey = (key != null);
	}

	public Key getKey() {
		if (!this.isOpen || !this.hasKey) return null;
		this.hasKey = false;
		return this.key;
	}
	
	public boolean hasKey() {
		return this.hasKey;
	}
	
	@Override
	public boolean open() {
		this.isOpen = true;
		return true;
	}
	
	@Override
	public boolean close() {
		this.isOpen = false;
		return true;
	}
	
	@Override
	public boolean isOpen() {
		return this.isOpen;
	}
	
	@Override
	public void examine() {}
}
