package com.revature.game;

import com.revature.fixtures.Letter;
import com.revature.fixtures.MailBox;
import com.revature.fixtures.Room;

public class RoomManager {
//	private static Room[] rooms = new Room[14];
	private static Room startingRoom;
	
	public static void init() {
		Room outside = new Room("Outside", "Outside", "You find yourself outside facing North.\n" +
				"There is a house in front of you with a mailbox.\n" + 
				"Two paths lead West and East, each rounding a corner.");
		
		Room frontPorch = new Room("Front Porch", "The front porch", "You are standing on a small porch facing East.\n" +
				"In front of you is an open door that leads to a large room.\n" + 
				"You can see a desk against the North wall a few feet inside of the room.\n" +
				"A path leads to the South");
		
		Room backPorch = new Room("Back Porch", "The back porch", "You are standing on a small porch facing West.\n" +
				"In front of you is an open door that leads to a small room.\n" +
				"On the North wall you see a washer and dryer. On the wall across the room is a furnace.\n" + 
				"A path leads to the South");
		
		Room livingRoom = new Room("Living Room", "A large living room", "You are standing in a living room facing South.\n" +
				"You see a fireplace on the wall in front of you. To the right of the fireplace is a television on a stand. Behind you is a large desk.\n" +
				"A door on the North wall leads to a room with a large bed.\nThe Eastern wall of the room opens into a smaller room with a table and chairs under a ceiling fan.\n" +
				"A door on the Western wall leads outside.");
		
		Room washRoom = new Room("Wash Room", "A small wash room", "You are standing in a small wash room facing West.\n" +
				"To your right are a washer and dryer. Embeded in the wall in front of you is a furnace.\n" +
				"To the South you see a room with a sink, a dishwasher, a refridgerator, and a stove.\nTo the East is a door that leads outside.");
		
		Room masterBedroom = new Room("Master Bedroom", "The master bedroom", "You are standing in a bedroom facing North.\n" +
				"A queen-size bed sits against the North wall. A ceiling fan hangs above the bed.\nYou notice a television on a stand on the South wall, and a dresser next to it tucked in the corner.\n" +
				"A door on the South wall leads to a room with a fireplace.\nA door on the East wall leads to a bathroom.\nThere is a door on the North wall.");
		
		Room masterBathroom = new Room("Master Bathroom", "The master bathroom", "You are standing in a bathroom facing East.\n" +
				"On the North wall is a mirror above a large counter top with a sink. Next to the sink is a toilet.\n" + 
				"On the East wall is a large bathtub with a shower.\nA door on the West wall leads to a bedroom.");
		
		Room masterCloset = new Room("Master Bedroom Closet", "A large walk-in closet", "You are standing in a walk-in closet.\nIt is dark.\nFind the door.");
		
		Room diningRoom = new Room("Dining Room", "The dining room", "You are standing next to a large table and 4 chairs, facing North.\n" +
				"Against the wall behind you are a couple of bookcases.\n" +
				"To the West you can see a large room with a fireplace.\nOn the South wall next to the bookcases is a hallway.\nTo the North you can see a kitchen.");
		
		Room hallway = new Room("Hallway", "A short hallway", "You are at the end of a short hallway facing South.\n" +
				"To the East is a small bathroom.\nIn front of you is a small bedroom. To the West is a slightly bigger bedroom.");
		
		Room bedroom1 = new Room("Small Bedroom", "A small bedroom", "You are in a small bedroom facing East.\n" +
				"Behind you is a small desk and a twin-size bed sits tucked into the corner.\n" +
				"Against the wall in front of you, you spot a dresser in the corner. To the right of the dresser is an old CRT Television on a stand to the left of a bookshelf.\n" +
				"On the North wall is a door that leads to a short hallway.");
		
		Room bedroom2 = new Room("Bigger Bedroom", "A slightly larger bedroom", "You are in a small bedroom facing West.\n" +
				"Against the wall on your right is a desk.\nDirectly in front of you is a large bookshelf. A twin-size bed sits to the left of the bookshelf.\n" +
				"Tucked into the far left corner is a small side table with a fan and an alarm clock.\nAgainst the wall behind you, tucked into the corner is a dresser. Next to the dresser is another, smaller, bookshelf.\n" +
				"A door to the East leads to a short hallway.");
		
		Room bathroom = new Room("Bathroom", "A small bathroom", "You are standing in a small bathroom facing North.\n" +
				"You can see your reflection in the mirror in front of you.\nBelow the mirror is a sink. To the right of the sink you spot a toilet.\n" +
				"The Eastern wall contains a small bathtub with a shower.\nA door to the West leads to a small hallway.");
		
		Room kitchen = new Room("Kitchen", "The kitchen", "You are standing in a kitchen facing South.\n" +
				"Directly behind you is a refridgerator.\nTo your left is a long countertop. Sunk into the counter top is a sink.\n" + 
				"Next to the sink, underneath the countertop, is a dishwasher. Next to the dishwasher is a stove with an oven.\n" +
				"To the North is a small room with a washer and dryer.\nTo the South is a dining room.");
		
		outside.initExits(null, null, frontPorch, backPorch);	// N, S, W, E
		frontPorch.initExits(null, outside, null, livingRoom);
		backPorch.initExits(null, outside, washRoom, null);
		livingRoom.initExits(masterBedroom, null, outside, diningRoom);
		washRoom.initExits(null, kitchen, null, backPorch);
		masterBedroom.initExits(masterCloset, livingRoom, null, masterBathroom);
		masterBathroom.initExits(null, null, masterBedroom, null);
		masterCloset.initExits(null, masterBedroom, null, null);
		diningRoom.initExits(kitchen, hallway, livingRoom, null);
		hallway.initExits(diningRoom, bedroom1, bedroom2, bathroom);
		bedroom1.initExits(hallway, null, null, null);
		bedroom2.initExits(null, null, null, hallway);
		bathroom.initExits(null, null, hallway, null);
		kitchen.initExits(washRoom, diningRoom, null, null);
		
		
		
		Letter letter = new Letter("letter", "a letter", "\n\n\tHey,\n\n\t   I left the keys to the closet in a drawer in the kitchen.\n\n\t\t\t\t\t\t\t\t   -John\n\n\n\n\t\t\t\t\tPS: You've been eaten by a grue...\n");
		MailBox mailbox = new MailBox("mailbox", "a small mailbox", "An average-size metal mailbox on a pole.", letter);
		outside.addFixture(mailbox.getName(), mailbox);
		
		
		
		startingRoom = outside;
		
//		rooms[0]  = outside;
//		rooms[1]  = frontPorch;
//		rooms[2]  = backPorch;
//		rooms[3]  = livingRoom;
//		rooms[4]  = washRoom;
//		rooms[5]  = masterBedroom;
//		rooms[6]  = masterBathroom;
//		rooms[7]  = masterCloset;
//		rooms[8]  = diningRoom;
//		rooms[9]  = hallway;
//		rooms[10] = bedroom1;
//		rooms[11] = bedroom2;
//		rooms[12] = bathroom;
//		rooms[13] = kitchen;
	}
	
//	public static Room[] getRooms() {
//		return rooms;
//	}
	
	public static Room getStartingRoom() {
		return startingRoom;
	}
}
