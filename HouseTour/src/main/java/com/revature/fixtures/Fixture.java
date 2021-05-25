package com.revature.fixtures;

public abstract class Fixture {
	private String name, shortDescription, longDescription;
	
	public Fixture(String name, String shortDescription, String longDescription) {
		this.name = name.toLowerCase();
		this.shortDescription = shortDescription;
		this.longDescription = longDescription;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getCapitalName() {
		return ("" + this.name.charAt(0)).toUpperCase() + this.name.substring(1, this.name.length());
	}
	
	public String getShortDescription() {
		return this.shortDescription;
	}
	
	public String getLongDescription() {
		return this.longDescription;
	}
	
	public abstract void examine();
}
