package com.revature.fixtures;

public abstract class Fixture {
	public String name, shortDescription, longDescription;
	
	public Fixture(String name, String shortDescription, String longDescription) {
		this.name = name;
		this.shortDescription = shortDescription;
		this.longDescription = longDescription;
	}
}
