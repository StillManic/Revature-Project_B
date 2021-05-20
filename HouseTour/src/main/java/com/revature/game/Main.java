package com.revature.game;

import java.util.Scanner;

import com.revature.fixtures.Fixture;
import com.revature.fixtures.Letter;
import com.revature.fixtures.MailBox;
import com.revature.fixtures.Room;

public class Main {
	private static Player player = new Player();
	private static boolean quit;
	
	private static void printRoom() {
		System.out.println("Enter \"quit\" to quit the game.\n");
		
		if (player.getCurrentRoom() != null) {
			System.out.println(player.getCurrentRoom().getCapitalName());				// Print room name
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
		
		// Get the next line from the scanner, convert it to lower case, then split on spaces.
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
					if (command.length > 1) {
						if (player.getCurrentRoom().getExit(command[1]) == null) {
							System.out.println("You can't go that way.");
							break;
						} else {
							player.setCurrentRoom(player.getCurrentRoom().getExit(command[1]));
							printRoom();
							return;		// return instead of break to avoid printing "> " twice
						}
					} else {
						System.out.println("Where would you like to go?");
						break;
					}
				} else {
					if (player.getCurrentRoom().getExit(command[0]) == null) {
						System.out.println("You can't go that way.");
						break;
					} else {
						player.setCurrentRoom(player.getCurrentRoom().getExit(command[0]));
						printRoom();
						return;		// return instead of break to avoid printing "> " twice
					}
				}
			case "open":
				if (command.length > 1) {
					if (command[1].equals("mailbox") && player.getCurrentRoom().getName().equals("outside")) {
						MailBox mailbox = (MailBox) player.getCurrentRoom().getFixture("mailbox");
						mailbox.open();
						System.out.printf("You open the mailbox. %s\n", mailbox.hasLetter() ? "There is a letter inside." : "");
						break;
					}
					
					break;
				} else {
					System.out.println("What would you like to open?");
					break;
				}
			case "close":
				if (command.length > 1) {
					if (command[1].equals("mailbox") && player.getCurrentRoom().getName().equals("outside")) {
						MailBox mailbox = (MailBox) player.getCurrentRoom().getFixture("mailbox");
						mailbox.close();
						System.out.println("You close the mailbox.");
						break;
					}
					
					break;
				} else {
					System.out.println("What would you like to close?");
					break;
				}
			case "take":
				if (command.length > 1) {
					if (command[1].equals("letter")) {
						if (player.hasItem("letter")) {
							System.out.println("You already have the letter.");
							break;
						}
						
						if (player.getCurrentRoom().getName().equals("outside")) {
							MailBox mailbox = (MailBox) player.getCurrentRoom().getFixture("mailbox");
							if (mailbox.isOpen()) {
								player.addItemToInventory(mailbox.getLetter());
								System.out.println("Taken.");
								break;
							} else {
								System.out.println("The mailbox is closed.");
								break;
							}
						}
					} 

					break;
				} else {
					System.out.println("What do you want to take?");
					break;
				}
			case "read":
				if (command.length > 1) {
					if (command[1].equals("letter")) {
						if (player.hasItem("letter")) {
							Letter letter = (Letter) player.getItemFromInventory("letter");
							System.out.println("The letter says:");
							System.out.println(letter.getLongDescription());
							break;
						}
					}
					
					break;
				} else {
					System.out.println("What do you want to read?");
					break;
				}
			case "i":
			case "inv":
			case "inventory":
				System.out.println("Your inventory contains:");
				
				Fixture[] inventory = player.getInventory();
				if (inventory.length == 0) System.out.println("Nothing!");
				
				for (Fixture item : inventory) System.out.println(item.getShortDescription());
				
				System.out.println();
				break;
			case "room": printRoom(); return;	// return instead of break to avoid printing "> " twice
			case "quit": quit = true; break;
			default:
				System.out.println("I don't understand.");
				break;
		}
		
		if (!quit) System.out.print("> ");
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
