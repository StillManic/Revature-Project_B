package com.revature.game;

import java.util.Scanner;

import com.revature.fixtures.Door;
import com.revature.fixtures.Drawer;
import com.revature.fixtures.Fixture;
import com.revature.fixtures.Letter;
import com.revature.fixtures.MailBox;
import com.revature.fixtures.Room;

public class Main {
	private static Player player = new Player();
	private static boolean quit;
	
	/*
	 * Features to add:
	 * -"examine" command to print descriptions of items/containers
	 * 
	 */
	private static void printRoom() {
		System.out.println("Enter \"quit\" to quit the game.\n");
		
		if (player.getCurrentRoom() != null) {
			System.out.println(player.getCurrentRoom().getCapitalName());				// Print room name
			System.out.println();														// Blank line
			System.out.println(player.getCurrentRoom().getLongDescription());			// Print long description
			System.out.println();														// Blank line
			
			// Begin printing exits
			System.out.println("Exits:");
			if (player.getCurrentRoom().getName().equals("the abyss")) {				// Special case for the abyss
				System.out.println("????\n");
				quit = true;
				return;
			}
			
			// Print all exits for the current room
			for (int i = 0; i < player.getCurrentRoom().getExits().length; i++) {
				Door exit = player.getCurrentRoom().getExits()[i];
				if (exit != null) {
					Room oppositeRoom = exit.getOppositeRoom(player.getCurrentRoom().getName());
					String description;
					
					// Change the short description that gets displayed based on if the door is closed or locked
					if (exit.isOpen()) description = oppositeRoom.getShortDescription();
					else if (exit.isLocked()) description = exit.getLongDescription() + " It is locked.";
					else description = exit.getLongDescription();
					
//					String description = exit.isOpen() ? oppositeRoom.getShortDescription() : "A closed door.";
					switch (i) {
						case 0: System.out.println("[North]: " + description); break;
						case 1: System.out.println("[South]: " + description); break;
						case 2: System.out.println("[West]:  " + description); break;
						case 3: System.out.println("[East]:  " + description); break;
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
		
		switch (command[0].toLowerCase()) {
			/*
			 * Handle movement
			 */
			case "north":
			case "south":
			case "west":
			case "east":
			case "go":
				if (command[0].equals("go")) {
					if (command.length > 1) {
						Door exit = player.getCurrentRoom().getExit(command[1]);
						if (exit == null) {
							System.out.println("You can't go that way.");
							break;
						} else if (!exit.isOpen()) {
							System.out.println("The door is closed.");
							break;
						} else {
							player.setCurrentRoom(exit.getOppositeRoom(player.getCurrentRoom().getName()));
							printRoom();
							return;		// return instead of break to avoid printing "> " twice
						}
					} else {
						System.out.println("Where would you like to go?");
						break;
					}
				} else {
					Door exit = player.getCurrentRoom().getExit(command[0]);
					if (exit == null) {
						System.out.println("You can't go that way.");
						break;
					} else if (!exit.isOpen()) {
						System.out.println("The door is closed.");
						break;
					} else {
						player.setCurrentRoom(exit.getOppositeRoom(player.getCurrentRoom().getName()));
						printRoom();
						return;		// return instead of break to avoid printing "> " twice
					}
				}
			/*
			 * Handle opening of doors and things
			 */
			case "open":
				if (command.length > 1) {
					if (command[1].equalsIgnoreCase("mailbox") && player.getCurrentRoom().getName().equals("outside")) {
						MailBox mailbox = (MailBox) player.getCurrentRoom().getFixture("mailbox");
						mailbox.open();
						System.out.printf("You open the mailbox. %s\n", mailbox.hasLetter() ? "There is a letter inside." : "");
						break;
					} else if (command[1].equalsIgnoreCase("drawer") && player.getCurrentRoom().getName().equals("kitchen")) {
						Drawer drawer = (Drawer) player.getCurrentRoom().getFixture("drawer");
						drawer.open();
						System.out.printf("You open the drawer. %s\n", drawer.hasKey() ? "There is a small brass key inside." : "");
						break;
					} else if (command.length > 2) {		// Handle opening a door based on direction
						if (command[2].equalsIgnoreCase("door")) {
							Door door = player.getCurrentRoom().getExit(command[1].toLowerCase());
							if (door != null) {
								if (!door.isActualDoor()) System.out.println("There is no door there.");
								else if (door.isLocked()) System.out.println("The door is locked.");
								else System.out.println((door.open()) ? "You open the door." : "The door seems to be locked.");
							} else System.out.println("There is no door over there.");
						}
					} else System.out.println("What are you trying to open?");
					
					break;
				} else {
					System.out.println("What would you like to open?");
					break;
				}
			/*
			 * Handle closing of doors and things
			 */
			case "close":
				if (command.length > 1) {
					if (command[1].equalsIgnoreCase("mailbox") && player.getCurrentRoom().getName().equals("outside")) {
						MailBox mailbox = (MailBox) player.getCurrentRoom().getFixture("mailbox");
						mailbox.close();
						System.out.println("You close the mailbox.");
						break;
					} else if (command[1].equalsIgnoreCase("drawer") && player.getCurrentRoom().getName().equals("kitchen")) {
						Drawer drawer = (Drawer) player.getCurrentRoom().getFixture("drawer");
						drawer.close();
						System.out.println("You close the drawer.");
						break;
					} else if (command.length > 2) {		// Handle closing a door based on direction
						if (command[2].equalsIgnoreCase("door")) {
							Door door = player.getCurrentRoom().getExit(command[1].toLowerCase());
							if (door != null) {
								if (!door.isActualDoor()) System.out.println("There is no door there.");
								else {
									System.out.println("You close the door.");
									door.close();
								}
							} else System.out.println("There is no door over there.");
						}
					} else System.out.println("What are you trying to close?");
					
					break;
				} else {
					System.out.println("What would you like to close?");
					break;
				}
			/*
			 * Handle taking items
			 */
			case "take":
				if (command.length > 1) {
					if (command[1].equalsIgnoreCase("letter")) {
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
					} else if (command[1].equalsIgnoreCase("key")) {
						if (player.hasItem("key")) {
							System.out.println("You already have the key.");
							break;
						}
						
						if (player.getCurrentRoom().getName().equals("kitchen")) {
							Drawer drawer = (Drawer) player.getCurrentRoom().getFixture("drawer");
							if (drawer.isOpen()) {
								player.addItemToInventory(drawer.getKey());
								System.out.println("Taken.");
								break;
							} else {
								System.out.println("The drawer is closed.");
								break;
							}
						}
					}

					break;
				} else {
					System.out.println("What do you want to take?");
					break;
				}
			/*
			 * Handle reading the letter
			 */
			case "read":
				if (command.length > 1) {
					if (command[1].equals("letter")) {
						if (player.hasItem("letter")) {
							Letter letter = (Letter) player.getItemFromInventory("letter");
							letter.examine();
							break;
						}
					}
					
					break;
				} else {
					System.out.println("What do you want to read?");
					break;
				}
			/*
			 * Handle using the key on the locked door
			 */
			case "use":
				if (command.length != 4) {
					System.out.println("What are you trying to use? And on what?");
					break;
				}
				
				if (!command[2].equalsIgnoreCase("on")) {
					System.out.println("What are you trying to use that on?");
					break;
				}
				
				if (command[1].equalsIgnoreCase("key")) {
					if (player.hasItem("key")) {
						if (command[3].equalsIgnoreCase("door")) {
							if (player.getCurrentRoom().getName().equals("master bedroom")) {
								Door abyssDoor = player.getCurrentRoom().getExit("north");		// abyss door is to the "north" exit
								abyssDoor.unlock();
								System.out.println("You unlocked the door.");
								break;
							}
						}
					} else {
						System.out.println("You don't have a key.");
						break;
					}
				}
				break;
			/*
			 * Print Player's inventory
			 */
			case "i":
			case "inv":
			case "inventory":
				System.out.println("Your inventory contains:");
				
				Fixture[] inventory = player.getInventory();
				if (inventory.length == 0) System.out.println("Nothing!");
				
				for (Fixture item : inventory) System.out.println(item.getShortDescription());
				
				System.out.println();
				break;
			/*
			 * Print the room
			 */
			case "room": printRoom(); return;	// return instead of break to avoid printing "> " twice
			/*
			 * Quit the game
			 */
			case "quit": quit = true; break;
			/*
			 * Everything else
			 */
			default: System.out.println("I don't understand."); break;
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
