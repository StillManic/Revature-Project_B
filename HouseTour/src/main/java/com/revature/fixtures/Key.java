package com.revature.fixtures;

public class Key extends Fixture {
	public Key(String name, String shortDescription, String longDescription) {
		super(name, shortDescription, longDescription);
	}

	@Override
	public void examine() {
		System.out.println(this.getLongDescription());
	}
}
