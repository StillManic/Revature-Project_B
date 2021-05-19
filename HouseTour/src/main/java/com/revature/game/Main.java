package com.revature.game;

import java.util.Scanner;

import com.revature.fixtures.Room;

public class Main {
	private static Player player = new Player();
	private static boolean quit;
	
	private static void printRoom(Player player) {
		if (player.currentRoom != null) {
			System.out.println(player.currentRoom.getName());
			System.out.println();
			System.out.println(player.currentRoom.getLongDescription());
			System.out.println();
			
			System.out.println("Exits:");
			if (player.currentRoom.getName().equals("Master Bedroom Closet")) {
				System.out.println("????");
				return;
			}
			
			for (int i = 0; i < player.currentRoom.getExits().length; i++) {
				Room exit = player.currentRoom.getExits()[i];
				
				if (exit != null) {
					switch (i) {
						case 0: System.out.println("[North]: " + exit.getShortDescription()); break;
						case 1: System.out.println("[South]: " + exit.getShortDescription()); break;
						case 2: System.out.println("[West]:  " + exit.getShortDescription()); break;
						case 3: System.out.println("[East]:  " + exit.getShortDescription()); break;
					}
				}
			}
		}
	}
	
	private static String[] collectInput(Scanner scanner) {
		String[] input = null;
		
		if (scanner.hasNext()) input = scanner.nextLine().toLowerCase().split(" ");
		
		return input;
	}
	
	private static boolean parse(String[] command) {
		if (command == null || command.length == 0) return false;
		
		switch (command[0]) {
			case "go":
				if (command.length == 2) {
					player.currentRoom = player.currentRoom.getExit(command[1]);
					return true;
				} else {
					System.out.println("\"go\" must be followed by a valid direction.");
					return false;
				}
			case "quit": quit = true; return true;
		}
		
		return false;
	}
	
	public static void main(String[] args) {
		RoomManager.init();
		player = new Player(RoomManager.getStartingRoom());
		Scanner scanner = new Scanner(System.in);
		
		do {	// while (!quit)
			System.out.println("Enter \"quit\" to quit the game.\n");
			printRoom(player);
			if (!parse(collectInput(scanner))) System.out.println("Please enter a valid command.");
		} while (!quit);
		
		System.out.println("Thanks for playing!");
		scanner.close();
	}
}
