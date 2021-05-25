package com.revature.fixtures;

public class MailBox extends Fixture implements Openable {
	private Letter letter;
	private boolean isOpen = false;
	private boolean hasLetter = false;
	
	public MailBox(String name, String shortDescription, String longDescription) {
		super(name, shortDescription, longDescription);
	}
	
	public MailBox(String name, String shortDescription, String longDescription, Letter letter) {
		this(name, shortDescription, longDescription);
		this.letter = letter;
		this.hasLetter = (letter != null);
	}
	
	public Letter getLetter() {
		if (!this.isOpen || !this.hasLetter) return null;
		this.hasLetter = false;
		return this.letter;
	}
	
	public boolean hasLetter() {
		return this.hasLetter;
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
