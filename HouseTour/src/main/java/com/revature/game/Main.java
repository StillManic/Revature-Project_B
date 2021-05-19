package com.revature.game;

import java.util.Scanner;

import com.revature.fixtures.Room;

public class Main {
	private static Player player = new Player();
	private static boolean quit;
	
	private static void printRoom() {
		System.out.println("Enter \"quit\" to quit the game.\n");
		
		if (player.getCurrentRoom() != null) {
			System.out.println(player.getCurrentRoom().getName());						// Print room name
			System.out.println();														// Blank line
			System.out.println(player.getCurrentRoom().getLongDescription());			// Print long description
			System.out.println();														// Blank line
			
			// Begin printing exits
			System.out.println("Exits:");
			if (player.getCurrentRoom().getName().equals("Master Bedroom Closet")) {	// Special case for the closet
				System.out.println("????");
				return;
			}
			
			// Print all exits for the current room
			for (int i = 0; i < player.getCurrentRoom().getExits().length; i++) {
				Room exit = player.getCurrentRoom().getExits()[i];
				
				if (exit != null) {
					switch (i) {
						case 0: System.out.println("[North]: " + exit.getShortDescription()); break;
						case 1: System.out.println("[South]: " + exit.getShortDescription()); break;
						case 2: System.out.println("[West]:  " + exit.getShortDescription()); break;
						case 3: System.out.println("[East]:  " + exit.getShortDescription()); break;
					}
				}
			}
			
			System.out.print("> ");
		}
	}
	
	private static String[] collectInput(Scanner scanner) {
		String[] input = null;
		
		// Get the next line from the scanner, convert it to lowercase, then split on spaces.
		if (scanner.hasNext()) input = scanner.nextLine().toLowerCase().split(" ");
		
		return input;
	}
	
	private static void parse(String[] command) {
		if (command == null || command.length == 0) return;
		
		switch (command[0]) {
			case "north":
			case "south":
			case "west":
			case "east":
			case "go":
				if (command[0].equals("go")) {
					if (command.length == 2) {
						if (player.getCurrentRoom().getExit(command[1]) == null) {
							System.out.println("You can't go that way.");
							System.out.print("> ");
							break;
						} else {
							player.setCurrentRoom(player.getCurrentRoom().getExit(command[1]));
							printRoom();
							break;
						}
					} else {
						System.out.println("Where would you like to go?");
						System.out.print("> ");
						break;
					}
				} else {
					if (player.getCurrentRoom().getExit(command[0]) == null) {
						System.out.println("You can't go that way.");
						System.out.print("> ");
						break;
					} else {
						player.setCurrentRoom(player.getCurrentRoom().getExit(command[0]));
						printRoom();
						break;
					}
				}
			case "quit": quit = true; break;
			case "room": printRoom(); break;
			default:
				System.out.println("I don't understand.");
				System.out.print("> ");
				break;
		}
	}
	
	public static void main(String[] args) {
		RoomManager.init();
		player = new Player(RoomManager.getStartingRoom());
		Scanner scanner = new Scanner(System.in);
		
		printRoom();
		do {	// while (!quit)
			parse(collectInput(scanner));
		} while (!quit);
		
		System.out.println("Thanks for playing!");
		scanner.close();
	}
}
