package engine;

import item.Item;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import objects.IObject;
import protection.Armor;
import start.Fight;
import weapons.Weapon;
import maps.Move;
import maps.Point;
import methods.Invo;
import monsters.Monster;

public class Engine {
	private static User user;
	private static ArrayList<IObject> invo;
	private static Point location;
	private static int Heading;
	private static Move[] map;
	private static int startX;
	private static char curLoc;
	private static int startY;
	private static IObject[] worldObjects;
	private static Move[] worldMaps;
	private static boolean dev;
	private static Move[] chapter1;
	private static Move[] chapter2;
	private static Move[] chapter3;
	private static boolean end;
	private static boolean level2Lever;
	private static Move[] leverTest;
	private static Move[] monsterTest;
	private static Move[] devMap;
	private static double expLimit;
	private static Move[] pickupTest;
	private static int level;
	private static IObject[] chapter1Objects;
	private static IObject[] mapObjects;
	private static boolean pickup;
	private static int pickupItem;
	private static int story;
	private static Move[] chapter1Dream;
	private static IObject[] chapter2Objects;

	/**
	 * All the commands that a user can type in
	 */
	public static void menu() {
		while (!end) {
			//Sets to false but for certain commands it will turn true
			checker();
			mapChecker();
			Scanner in = new Scanner(System.in);
			// Command line
			System.out.print(": ");
			String action = in.nextLine().toLowerCase();
			// A big "if" statement for all the commands
			// of the game
			if (action.equals("forward") || action.equals("go forward")
					|| action.equals("move forward")) {
				goForward();
				pickup = false;
			} else if (action.equals("back") || action.equals("go back")
					|| action.equals("move back")) {
				goBack();
				pickup = false;
			} else if (action.equals("left") || action.equals("turn left")) {
				System.out.println("You turn left");
				pickup = true;
				turnLeft();
			} else if (action.equals("right") || action.equals("turn right")) {
				System.out.println("You turn right");
				pickup = true;
				turnRight();
			} else if (action.equals("location")) {
				pickup = true;
				location();
			} else if (action.equals("equipment")) {
				pickup = true;
				System.out.println(user.equipment());
			} else if (action.equals("equip")) {
				pickup = true;
				equip();
			} else if (action.equals("pickup")) {
				pickUp();
				pickup = false;
			} else if (action.equals("map")) {
				pickup = true;
				showMap();
			} else if (action.equals("status")) {
				pickup = true;
				System.out.println(user);
			} else if (action.equals("help")) {
				pickup = true;
				help();
			} else if (action.equals("commands")) {
				pickup = true;
				commands();
			} else if (action.equals("look around")) {
				pickup = true;
				lookAround();
			} else if (action.equals("look")) {
				pickup = true;
				look();
			} else if (action.equals("invo")) {
				pickup = true;
				System.out.println(invo());
			} else if (action.equals("exit")) {
				System.out.println("goodbye");
				break;
			} else if (action.equals("pull lever")) {
				pickup = true;
				pullLever();
			} else if (action.equals("clear")) {
				pickup = true;
				clear();
			} else if (action.length() > 6) {
				if (action.substring(0, 6).equals("equip ")) {
					pickup = true;
					equipItem(action.substring(6, action.length()));
//				} else if (action.substring(0, 7).equals("pickup ")) {
//					pickupItem(action.substring(7, action.length()));
				} else {
					System.out
							.println("That is not a correct command please try again.");
				}
			} else {
				// If it is not in the command "if" block
				// it must not be a command
				pickup = true;
				System.out
						.println("That is not a correct command please try again.");
			}
		}
	}

	public static void pullLever() {
		if (curLoc == 'l') {
			System.out.print("You pull the lever and ");
			if (level2Lever == false) {
				System.out.println("you hear something move!");
				level2Lever = true;
			} else {
				System.out.println("nothing happens...");
			}
		} else {
			System.out.println("There is nothing to pull.");
		}
		char letter;
		String line = "";
		Move[] newMap = new Move[map.length];
		for (int i = 0; i < map.length; i++) {
			for (int j = map[i].toString().length() - 1; j >= 0; j--) {
				if (map[i].toString().charAt(j) != 'L') {
					letter = map[i].toString().charAt(j);
				} else {
					letter = 'o';
				}
				line = letter + line;
			}
			newMap[i] = mapMaker(line);
			line = "";
		}
		map = newMap;
	}

//	public static void pickupItem(String item) {
//		int i = 0;
//		boolean itemIsValid = false;
//		for (int j = 0; j < mapObjects.length; j++) {
//			// Makes sure the item is by them and they don't just put in a
//			// random name
//			if (mapObjects[j].getName().toLowerCase()
//					.equals(item.toLowerCase())) {
//				i = j;
//				itemIsValid = true;
//			}
//		}
//		if (itemIsValid) {
//			invo.add(mapObjects[i]);
//			System.out.println("You picked up " + mapObjects[i]);
//		} else {
//			System.out.println("You can not pick that up.");
//		}
//	}

	public static void lookAround() {
		// Does 4 turns and 4 looks
		int end = Heading;
		mapChecker();
		for (int i = 0; i < 4; i++) {
			checker();
			if (Heading == 1) {
				System.out.print("To the south ");
			} else if (Heading == 2) {
				System.out.print("To the east ");
			} else if (Heading == 3) {
				System.out.print("To the north ");
			} else if (Heading == 4) {
				System.out.print("To the west ");
			}
			look();
			turnRight();
		}
		Heading = end;

	}

	public static void equipItem(String item) {
		String equip = "";
		Scanner in = new Scanner(System.in);
		for (int j = 0; j < invo.size(); j++) {
			// When the item they entered equals a items name
			// in their invo
			if (item != null) {
				if (item.toLowerCase().equals(
						invo.get(j).getName().toLowerCase())) {
					System.out.println();
					if (invo.get(j).getLevel() > user.getLevel()) {
						System.out.println("You are to low level for that!");
					} else if (invo.get(j).getObjectType().equals("weapon")) {
						// Set their right hand to that weapon
						System.out
								.println("Would you like to equip to your right or left hand?");
						String option = in.nextLine().toLowerCase();
						if (option.equals("right")) {
							if (user.getRightHand() != null) {
								invo.add(user.getRightHand());
							}
							user.setRightHand(((Weapon) invo.get(j)));
						}
						if (option.equals("left")) {
							if (user.getLeftHand() != null) {
								invo.add(user.getLeftHand());
							}
							user.setLeftHand(((Weapon) invo.get(j)));
						}
						// Change the text
						equip = ((Weapon) invo.get(j)).getName() + " equipped";
						// Remove the item from the invo
						invo.remove(j);
						j = invo.size();
						break;
						// If it is armor
					} else if (invo.get(j).getObjectType().equals("armor")) {
						String type = ((Armor) invo.get(j)).getType();
						// Check to see what type of armor it is
						switch (type) {
						case "Back":
							invo.add(user.getBack());
							user.setBack((Armor) check(invo.get(j).getName()));
							equip = invo.get(j).getName() + " equipped";
							invo.remove(j);
							j = invo.size();
							break;
						case "Legs":
							invo.add(user.getLegs());
							user.setLegs((Armor) check(invo.get(j).getName()));
							equip = invo.get(j).getName() + " equipped";
							invo.remove(j);
							j = invo.size();
							break;
						case "Torso":
							invo.add(user.getTorso());
							user.setTorso((Armor) check(invo.get(j).getName()));
							equip = invo.get(j).getName() + " equipped";
							invo.remove(j);
							j = invo.size();
							break;
						case "Head":
							invo.add(user.getHead());
							user.setHead((Armor) check(invo.get(j).getName()));
							equip = invo.get(j).getName() + " equipped";
							invo.remove(j);
							j = invo.size();
							break;
						case "Feet":
							invo.add(user.getFeet());
							user.setFeet((Armor) check(invo.get(j).getName()));
							equip = invo.get(j).getName() + " equipped";
							invo.remove(j);
							j = invo.size();
							break;
						}
					}

					// If it is not a Weapon or Armor then
					// it must be an item
				} else {
					equip = "You can not equip that item!";
				}
			}
		}
		System.out.println(equip);
	}

	/**
	 * 1 = south 2 = east 3 = north 4 = west =========== m = monster e = end t =
	 * treasure x = forest or wall
	 */
	public static void look() {
		char front = map[(int) location.getY() - 1].toString().charAt(
				(int) (location.getX()));
		if (Heading == 1) {
			front = map[(int) location.getY() + 1].toString().charAt(
					(int) (location.getX()));
		} else if (Heading == 3) {
			front = map[(int) location.getY() - 1].toString().charAt(
					(int) (location.getX()));
		} else if (Heading == 2) {
			front = map[(int) location.getY()].toString().charAt(
					(int) (location.getX() + 1));
		} else {
			front = map[(int) location.getY()].toString().charAt(
					(int) (location.getX() - 1));
		}
		if (front == 'm') {
			System.out.println("There is a monster ahead!");
		} else if (front == 'x') {
			System.out.println("A dense forest lies ahead...");
		} else if (front == 'o') {
			System.out.println("You see a trail.");
		} else if (front == 'l') {
			System.out.println("You see a lever ahead!");
		}
	}

	public static void mapChecker() {
		// ALL MONSTER LOCATION//
		curLoc = map[(int) location.getY()].toString().charAt(
				(int) (location.getX()));
		if (curLoc == 'f') {// f == End Game
			System.out.println("YOU WON!");
			end = true;
		} else if (curLoc == 'n') {
			story();
		} else if (curLoc == 'e') {// e == End Area
			level++;
			newLevel(level);
			System.out.println("You enter a new area");
		} else if (curLoc == 'l') {// l == Lever
			System.out.println("You are by a lever.");
		} else if (curLoc == 'a') {
			armorDraw();
			pickup = true;
		} else if (curLoc == 'w') {
			weaponDraw();
			pickup = true;
		} else if (curLoc == 'i') {
			itemDraw();
			pickup = true;
		}
		if (curLoc == 'g') {
			Monster goblin = new Monster("Goblin", 150, 1);
			Fight goblinFight = new Fight(user, goblin, "Plains");
			System.out.println(goblinFight.battle());
			if (goblinFight.isPlayerDead() == true) {
				goBack();
				goblinFight.respawn();
			}
			if (goblinFight.playerWin() == true) {
				removeCurLoc();
				goblinFight.restart();
				checker();
			}
			if(goblinFight.playerFlee() == true){
				goBack();
				goblinFight.respawn();
			}
		} else if (curLoc == 't') {
			Monster troll = new Monster("Troll", 500, 5);
			Fight trollFight = new Fight(user, troll, "Plains");
			System.out.println(trollFight.battle());
			if (trollFight.isPlayerDead() == true) {
				goBack();
				trollFight.respawn();
			}
			if (trollFight.playerWin() == true) {
				removeCurLoc();
				trollFight.restart();
				checker();
			}
			if(trollFight.playerFlee() == true){
				goBack();
				trollFight.respawn();
			}
		} else if (curLoc == 'd') {
			Monster first = new Monster();
			Fight firstFight = new Fight(user, first, "Chapter1");
			System.out.println(firstFight.battle());
			if (firstFight.isPlayerDead() == true) {
				goBack();
				firstFight.respawn();
			}
			if (firstFight.playerWin() == true) {
				removeCurLoc();
				firstFight.restart();
				checker();
				story();
			}
		} else {

		}
	}

	public static void story() {
		if (story == 0) {
			System.out.println("Dad: " + user.getName()
					+ " go get the Wooden Sword in front of you.");
			wait(700);
			System.out
					.println("System: "
							+ "Use 'pickup wooden sword' then 'equip wooden sword', if you need help type in 'help' or 'commands'");
		} else if (story == 1) {
			System.out.println("Dad: Nice son!...");
			wait(400);
			System.out.println("Mom: " + user.getName()
					+ " it is time for bed!");
			wait(300);
			System.out.println("System: Type 'map' to see where are you");
		} else if (story == 2) {
			System.out.println("System: You go to your bed and fall asleep...");
			newLevel(1);
			wait(1000);
			System.out.println("3 Hours later");
			wait(1000);
			System.out.println("Mom: AHHHHHHH!");
		} else if (story == 3) {
			System.out
					.println("System: You again see your mom and dad lying there lifeless, you see your raided house, and Sargent Gelecki walking away.");
			wait(2000);
			newLevel(2);
			mapObjects = chapter2Objects;
			user.setRightHand(chapter2Objects[4]);
			user.setLegs((Armor) chapter2Objects[5]);
			user.setTorso((Armor) chapter2Objects[6]);
			user.setFeet((Armor) chapter2Objects[7]);
			System.out.println("You wake from your dream..");
			wait(500);
			System.out
					.println(user.getName() + ":"+ " Two days and I'll be at your camp (Bandit leader), you just wait...");
		}
		story++;
	}

	public static void wait(int i) {
		try {
			Thread.sleep(i);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static void itemDraw() {
		int counter = 0;
		for (IObject a : mapObjects) {
			if (a.getObjectType().equals("item")) {
				if (a.getLevel() <= (user.getLevel() + 2)) {
					counter++;
				}
			}
		}
		Integer[] numbers = new Integer[counter];
		int count = 0;
		int item = 0;
		for (IObject a : mapObjects) {
			if (a.getObjectType().equals("item")) {
				if (a.getLevel() <= (user.getLevel() + 2)) {
					numbers[count] = item;
					count++;
				}
			}
			item++;
		}
		int itemNum = 0;
		boolean breakNow = false;
		while (breakNow == false) {
			itemNum = (int) ((Math.random() * mapObjects.length) + 1);
			for (Integer i : numbers) {
				if (i == itemNum) {
					breakNow = true;
					break;
				}
			}
		}
		pickupItem = itemNum;
		System.out.println("You found " + mapObjects[itemNum]);
		removeCurLoc();
	}

	private static void weaponDraw() {
		int counter = 0;
		for (IObject a : mapObjects) {
			if (a.getObjectType().equals("weapon")) {
				if (a.getLevel() <= (user.getLevel() + 2)) {
					counter++;
				}
			}
		}
		Integer[] numbers = new Integer[counter];
		int count = 0;
		int weapon = 0;
		for (IObject a : mapObjects) {
			if (a.getObjectType().equals("weapon")) {
				if (a.getLevel() <= (user.getLevel() + 2)) {
					numbers[count] = weapon;
					count++;
				}
			}
			weapon++;
		}
		int weaponNum = 0;
		boolean breakNow = false;
		while (breakNow == false) {
			weaponNum = (int) ((Math.random() * mapObjects.length) + 1);
			for (Integer i : numbers) {
				if (i == weaponNum) {
					if (!mapObjects[weaponNum].getName().equals("Null")) {
						breakNow = true;
						break;
					}
				}
			}
		}
		pickupItem = weaponNum;
		System.out.println("You found " + mapObjects[weaponNum]);
		removeCurLoc();
	}

	private static void armorDraw() {
		int counter = 0;
		for (IObject a : mapObjects) {
			if (a.getObjectType().equals("armor")) {
				if (a.getLevel() <= (user.getLevel() + 2)) {
					counter++;
				}
			}
		}
		Integer[] numbers = new Integer[counter];
		int count = 0;
		int armor = 0;
		for (IObject a : mapObjects) {
			if (a.getObjectType().equals("armor")) {
				if (a.getLevel() <= (user.getLevel() + 2)) {
					numbers[count] = armor;
					count++;
				}
			}
			armor++;
		}
		int armNum = 0;
		boolean breakNow = false;
		while (breakNow == false) {
			armNum = (int) ((Math.random() * mapObjects.length) + 1);
			for (Integer i : numbers) {
				if (i == armNum) {
					breakNow = true;
					break;
				}
			}
		}
		pickupItem = armNum;
		System.out.println("You found " + mapObjects[armNum]);
		removeCurLoc();
	}

	public static void removeCurLoc() {
		char letter;
		String line = "";
		Move[] newMap = new Move[map.length];
		for (int i = 0; i < map.length; i++) {
			if (i != location.getY()) {
				newMap[i] = map[i];
			} else {
				for (int j = map[i].toString().length() - 1; j >= 0; j--) {
					if (j != location.getX()) {
						letter = map[(int) location.getY()].toString()
								.charAt(j);
					} else {
						letter = 'o';
					}
					line = letter + line;
				}
				newMap[(int) location.getY()] = mapMaker(line);
			}
		}
		map = newMap;
	}

	/**
	 * Clears the menu so all text is gone
	 */
	// TODO: Clear the menu
	public static void clear() {
		for (int i = 0; i < 100; i++) {
			System.out.println();
		}
		menu();
	}

	/**
	 * Player types in equip then the console asks the user what they want to
	 * equip
	 */
	public static void equip() {
		String equip = "";
		Scanner in = new Scanner(System.in);
		// Prints all the items in your equipment
		for (int i = 0; i < invo.size(); i++) {
			System.out.println(invo.get(i).getName());
		}
		System.out.print("Equip what: ");
		String item = in.nextLine();
		// Look for the item they entered and see if
		// it is an item, weapon, or armor
		for (int j = 0; j < invo.size(); j++) {
			// When the item they entered equals a items name
			// in their invo
			if (item != null) {
				if (item.toLowerCase().equals(
						invo.get(j).getName().toLowerCase())) {
					System.out.println();
					if (invo.get(j).getLevel() > user.getLevel()) {
						System.out.println("You are to low level for that!");
					} else if (invo.get(j).getObjectType().equals("weapon")) {
						// Set their right hand to that weapon
						System.out
								.println("Would you like to equip to your right or left hand?");
						String option = in.nextLine().toLowerCase();
						if (option.equals("right")) {
							if (user.getRightHand() != null) {
								invo.add(user.getRightHand());
							}
							user.setRightHand(((Weapon) invo.get(j)));
						}
						if (option.equals("left")) {
							if (user.getLeftHand() != null) {
								invo.add(user.getLeftHand());
							}
							user.setLeftHand(((Weapon) invo.get(j)));
						}
						// Change the text
						equip = ((Weapon) invo.get(j)).getName() + " equipped";
						// Remove the item from the invo
						invo.remove(j);
						j = invo.size();
						break;
						// If it is armor
					} else if (invo.get(j).getObjectType().equals("armor")) {
						String type = ((Armor) invo.get(j)).getType();
						// Check to see what type of armor it is
						switch (type) {
						case "Back":
							invo.add(user.getBack());
							user.setBack((Armor) check(invo.get(j).getName()));
							equip = invo.get(j).getName() + " equipped";
							invo.remove(j);
							j = invo.size();
							break;
						case "Legs":
							invo.add(user.getLegs());
							user.setLegs((Armor) check(invo.get(j).getName()));
							equip = invo.get(j).getName() + " equipped";
							invo.remove(j);
							j = invo.size();
							break;
						case "Torso":
							invo.add(user.getTorso());
							user.setTorso((Armor) check(invo.get(j).getName()));
							equip = invo.get(j).getName() + " equipped";
							invo.remove(j);
							j = invo.size();
							break;
						case "Head":
							invo.add(user.getHead());
							user.setHead((Armor) check(invo.get(j).getName()));
							equip = invo.get(j).getName() + " equipped";
							invo.remove(j);
							j = invo.size();
							break;
						case "Feet":
							invo.add(user.getFeet());
							user.setFeet((Armor) check(invo.get(j).getName()));
							equip = invo.get(j).getName() + " equipped";
							invo.remove(j);
							j = invo.size();
							break;
						}
					}

					// If it is not a Weapon or Armor then
					// it must be an item
				} else {
					equip = "You can not equip that item!";
				}
			}
		}
		System.out.println(equip);
	}

	/**
	 * moves forward one adds or subtracts x or y depending on which way they
	 * are facing
	 */
	public static void goForward() {
		// north == 1
		// east == 2
		// south == 3
		// west == 4
		Point newLocation;
		if (Heading == 1) {
			newLocation = new Point(location.getX(), location.getY() + 1);
		} else if (Heading == 2) {
			newLocation = new Point(location.getX() + 1, location.getY());
		} else if (Heading == 3) {
			newLocation = new Point(location.getX(), location.getY() - 1);
		} else {
			newLocation = new Point(location.getX() - 1, location.getY());
		}
		char nextLoc = read((int) newLocation.getX(), (int) newLocation.getY());
		if (nextLoc != 'x' && nextLoc != 'L') {
			location = newLocation;
			System.out.println("You moved forward");
		} else if (nextLoc == 'L') {
			System.out
					.println("You see a wall... but something seems different");
		} else {
			System.out.println("You can't go that way!");
		}
	}

	/**
	 * Moves back one adds or subtracts x or y depending on which way they are
	 * facing
	 */
	public static void goBack() {
		Point newLocation;
		if (Heading == 1) {
			newLocation = new Point(location.getX(), location.getY() - 1);
		} else if (Heading == 2) {
			newLocation = new Point(location.getX() - 1, location.getY());
		} else if (Heading == 3) {
			newLocation = new Point(location.getX(), location.getY() + 1);
		} else {
			newLocation = new Point(location.getX() + 1, location.getY());
		}
		char nextLoc = read((int) newLocation.getX(), (int) newLocation.getY());
		if (nextLoc != 'x' && nextLoc != 'L') {
			location = newLocation;
			System.out.println("You moved backwards");
		} else if (nextLoc == 'L') {
			System.out
					.println("You see a wall... but something seems different");
		} else {
			System.out.println("You can't go that way!");
		}
	}

	/**
	 * Turns right once and changes the heading
	 */
	public static void turnLeft() {
		Heading += 1;
	}

	/**
	 * Turns left once and changes the heading
	 */
	public static void turnRight() {
		Heading -= 1;
	}

	/**
	 * Tells the user which direction they are facing and what their location is
	 */
	public static void location() {
		if (Heading == 1) {
			System.out.println("You are facing South.");
		} else if (Heading == 2) {
			System.out.println("You are facing East.");
		} else if (Heading == 3) {
			System.out.println("You are facing North.");
		} else if (Heading == 4) {
			System.out.println("You are facing West.");
		} else {
			System.out.println(Heading);
		}
		System.out.println("Your Coords are, " + location.toString());
	}

	/**
	 * Player types in pickup then the console will ask what to pick up
	 */
	public static void pickUp() {
		if (pickup == true) {
			System.out.println("You picked up "
					+ mapObjects[pickupItem].getName());
			invo.add(mapObjects[pickupItem]);
		}else{
			System.out.println("There is nothing to pick up...");
		}
//		else {
//			int i = 0;
//			boolean itemIsValid = false;
//			Scanner in = new Scanner(System.in);
//			System.out.print("Pickup what: ");
//			String item = in.nextLine();
//			for (int j = 0; j < mapObjects.length; j++) {
//				// Makes sure the item is by them and they don't just put in a
//				// random name
//				if (mapObjects[j].getName().toLowerCase()
//						.equals(item.toLowerCase())) {
//					i = j;
//					itemIsValid = true;
//				}
//			}
//			if (itemIsValid) {
//				invo.add(mapObjects[i]);
//				System.out.println("You picked up " + mapObjects[i]);
//			} else {
//				System.out.println("You can not pick that up.");
//			}
//		}
	}

	/**
	 * Saves the item to the invo (autosave)
	 * 
	 * @param acqs
	 *            Array of invo
	 */
	public static void saveItem(String path, Invo[] acqs) {
		BufferedWriter file;

		try {
			file = new BufferedWriter(new FileWriter(path));
			for (int i = 0; i < acqs.length; i++) {
				file.write(acqs[i].getItem() + "\n");
			}
			file.write("\n");
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Lists of commands that the user name use
	 */
	public static void commands() {
		String commands = "Commands:\n-help";
		commands += "\n-forward";
		commands += "\n-back";
		commands += "\n-left";
		commands += "\n-right";
		commands += "\n-look around";
		commands += "\n-location";
		commands += "\n-map";
		commands += "\n-status";
		commands += "\n-pickup";
		commands += "\n-equipment";
		commands += "\n-invo";
		commands += "\n-exit";
		System.out.println(commands);
	}

	/**
	 * Help and promotion message
	 */
	public static void help() {
		String help = "Help:\n";
		help += "This game is a text adventure created by";
		help += "\nZach Eriksen, if you need to know your";
		help += "\nsurrounding's description again please type";
		help += "\n'look around' or if you need to know what you";
		help += "\ncan type for commands please type 'commands'";
		help += "\nif you have any more questions, ideas, or just";
		help += "\nwant to say somthing please email me at: ";
		help += "\ntoorealc@yahoo.com";
		System.out.println(help);
	}

	/**
	 * Starts the game
	 */
	public static void start() {
		// ALL DEFAULT MAPS, WEAPONS, AND ITEMS//
		// new Weapon("Null", "Null", 0, 0, 0),
		// new Weapon("Sword", "Melee", 5, 4.0, 1),// 0
		// new Weapon("Staff", "Magic", 5, 4.0, 1),// 1
		// new Weapon("Bow", "Range", 5, 4.0, 1),// 2
		// new Weapon("Dagger", "Melee", 5, 4.0, 5),
		// new Weapon("dumb1", "Melee", 0, 1.0, 0),
		// new Weapon("dumb2", "Melee", 0, 1.0, 0),
		// new Weapon("dumb3", "Melee", 0, 1.0, 0),
		// new Weapon("dumb4", "Melee", 0, 1.0, 0),
		// new Weapon("GodSword", "Melee", 10000, 1.0, 0),
		// new Armor("Helm", "Head", 5, 4.0, 1),// 4
		// new Armor("Top", "Torso", 5, 4.0, 1),// 5
		// new Armor("Legs", "Legs", 5, 4.0, 1),// 6
		// new Armor("Boots", "Feet", 5, 4.0, 1),// 7
		// new Armor("Cape", "Back", 5, 4.0, 1),// 8
		// new Armor("Dev Boots", "Feet", 10000, 1.0, 0),
		/**
		 * ITEMS HAVE THESE EFFECTS
		 * 		-'heal'
		 * 		-'mana'
		 * 		-'str'
		 */
		chapter1Objects = new IObject[] { new Weapon("Null", "Null", 0, 0, 0),
				new Weapon("Wooden Sword", "Melee", 1, 2.0, 0) };
		chapter2Objects = new IObject[] { new Weapon("Null", "Null", 0, 0, 0),
				new Weapon("Bone Bow", "Range", 4, 2.0, 0),
				new Weapon("Iron Sword", "Melee", 3, 2.5, 0),
				new Weapon("Oak Staff", "Magic", 2, 3.0, 0),
				new Weapon("Bronze Dagger", "Melee", 2, 2, 0),
				new Armor("Leather Chaps", "legs", 1, 1.0, 0),
				new Armor("Cloth Body", "torso", 0, 1.0, 0),
				new Armor("Boots", "feet", 1, 2.0, 0),
				new Item("Bandaid", "heal", 25, .25)};
		// MAPS NEW TO HAVE ONE 'S' FOR THEIR STARING POSITION//
		// MONSTERS//
		// g == Goblin
		// t == troll
		// NPCS//
		// OBJECTS//
		// l == lever && L == wall that will open
		leverTest = new Move[] { mapMaker("xxx"), mapMaker("xLxxx"),
				mapMaker("xsoolx"), mapMaker("xxxxx") };
		monsterTest = new Move[] { mapMaker("xxx"), mapMaker("xtx"),
				mapMaker("xtx"), mapMaker("xgx"), mapMaker("xtx"),
				mapMaker("xtx"), mapMaker("xgx"), mapMaker("xgx"),
				mapMaker("xgx"), mapMaker("xgx"), mapMaker("xgx"),
				mapMaker("xwx"), mapMaker("xix"), mapMaker("xtx"),
				mapMaker("xgx"), mapMaker("xsx"), mapMaker("xxx"), };
		chapter1 = new Move[] { mapMaker("xxxxxxx"), mapMaker("xoooxxx"),
				mapMaker("xxxoxox"), mapMaker("xooooox"), mapMaker("xxxnxxx"),
				mapMaker("zxooxzz"), mapMaker("zxdxxzz"), mapMaker("zxwxzzz"),
				mapMaker("zxsxzzz"), mapMaker("zxxxzzz") };
		chapter1Dream = new Move[] { mapMaker("xxxxxxx"), mapMaker("xosoxxx"),
				mapMaker("xxxnxox"), mapMaker("xooooox"), mapMaker("xxxoxxx"),
				mapMaker("zxooxzz"), mapMaker("zxdxxzz"), mapMaker("zxoxzzz"),
				mapMaker("zxoxzzz"), mapMaker("zxxxzzz") };
		chapter2 = new Move[] { mapMaker("xxx"), mapMaker("xex"),
				mapMaker("xox"), mapMaker("xox"), mapMaker("xox"),
				mapMaker("xox"), mapMaker("xox"), mapMaker("xsx"),
				mapMaker("xxx") };
		chapter3 = new Move[] { mapMaker("xxxxx"), mapMaker("xfxxx"),
				mapMaker("xLxxx"), mapMaker("xgxxx"), mapMaker("xotox"),
				mapMaker("xoxlx"), mapMaker("xsxxx"), mapMaker("xxxxx") };
		pickupTest = new Move[] { mapMaker("xxxxx"), mapMaker("xxwxx"),
				mapMaker("xxsax"), mapMaker("xxixx") };
		level = 1;
		mapStart(chapter1);
		map = chapter1;
		mapObjects = chapter2Objects;
		Scanner in = new Scanner(System.in);
		String name = "";
		level2Lever = false;
		boolean end = false;
		boolean done = false;
		while (done != true) {
			System.out.print("What is your name: ");
			name = in.nextLine();
			if (name.toLowerCase().equals("dev")) {
				// Ask for the dev password three times
				for (int i = 0; i < 3; i++) {
					System.out.print("Password: ");
					String pass = in.nextLine();
					// If they got that password right
					// stop asking and log them in
					if (pass.equals("7895123")) {
						dev = true;
						i = 3;
						done = true;
					} else {
						// Else ask again
						System.out.println("Please try again");
					}
				}
				// If they did not get the password right in the three
				// attempts then ask for their name again
				if (!dev) {
				}
			} else if (name.length() < 3) {
				System.out.println("That name is too short...");
			} else {
				done = true;
			}
		}
		story = 0;
		user = new User(name);
		story();
		Heading = 3;
		if (dev) {
			invo = new ArrayList<IObject>();
			// Sets so the dev will have a sword in their hand
			user.setRightHand(new Weapon("GodSword", "God", 10000, 0, 0));
		} else {
			invo = new ArrayList<IObject>();
		}
		location = new Point(startX, startY);
		curLoc = map[(int) location.getY()].toString().charAt(
				(int) (location.getX()));
		// String summary = "\nWelcome " + name + "!";
		// summary += "\nIf you need help please type 'help'";
		// summary +=
		// "\nOr if you need to know the commands you can type in 'commands'";
		// // summary += "\nThis is (World name), it is world of chaos"
		// System.out.println(summary);
		expLimit = user.getLevel() * 1000;
	}

	/**
	 * Checks the name to see if it is a game item
	 * 
	 * @param name
	 *            name of the the item they, most likely, want to pickup
	 * @return the real item's info
	 */
	public static IObject check(String name) {
		for (IObject o : worldObjects) {
			if (o.getName().toLowerCase().equals(name.toLowerCase())) {
				return o;
			}
		}
		return null;
	}

	/**
	 * @return prints out all of the Items and Weapons in the users Invo
	 */
	public static String invo() {
		String invoList = "";
		invoList += "Weapons:";
		invoList += "\n";
		for (IObject a : invo) {
			System.out.println(a);
			if (a.getObjectType().equals("weapon")) {
				invoList += "\t" + a.getName();
				invoList += "\n";
			}
		}
		invoList += "Armor:";
		invoList += "\n";
		for (IObject a : invo) {
			if (a.getObjectType().equals("armor")) {
				invoList += "\t" + a.getName();
				invoList += "\n";
			}
		}
		invoList += "Items:";
		invoList += "\n";
		for (IObject a : invo) {
			if (a.getObjectType().equals("item")) {
				invoList += "\t" + a.getName();
				invoList += "\n";
			}
		}
		return invoList;
	}
	
	/**
	 * Returns items for the fight method
	 */
	public static void fightInvo(){
		for (IObject a : invo) {
			if (a.getObjectType().equals("item")) {
				System.out.println(a.getName());
			}
		}
	}

	/**
	 * @param str
	 *            The map
	 */
	public static Move mapMaker(String str) {
		return new Move(str);
	}

	public static void mapStart(Move[] map) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length(); j++) {
				if (map[i].toString().charAt(j) == 's') {
					startX = j;
					startY = i;
				}
			}
		}
	}

	public static void newLevel(int level) {
		if (level == 1) {
			map = chapter1Dream;
			mapStart(map);
			location.setX(startX);
			location.setY(startY);
		} else if (level == 2) {
			map = chapter2;
			mapStart(map);
			// mapObjects = chapter2Objects;
			location.setX(startX);
			location.setY(startY);
		} else if (level == 3) {
			map = chapter3;
			mapStart(map);
			// mapObjects = chapter3Objects;
			location.setX(startX);
			location.setY(startY);
		}
	}

	public static char read(int x, int y) {
		return map[y].toString().charAt(x);
	}

	public static void checker() {
		if (!end) {
			for (Weapon a : user.getInvo()) {
				if (a.getName().equals("Null")) {
					user.getInvo().remove(a);
				}
			}
			expLimit = user.getLevel() * 1000;
			if (user.getExp() > expLimit) {
				user.addLevel();
				System.out.println("You Leved up... you are now "
						+ user.getLevel());
				checker();
			}
			for (int i = 0; i < invo.size(); i++) {
				if (invo.get(i) == null) {
					invo.remove(i);
				}
			}
			if (Heading > 4) {
				Heading = 1;
			} else if (Heading < 1) {
				Heading = 4;
			}
		}
	}

	public static String showMap() {
		if (!dev) {
			System.out
					.println("Current Location : " + "("
							+ (int) location.getX() + ","
							+ (int) location.getY() + ")");
			String sum = "";
			int y = 0;
			boolean start = true;
			if (start == true) {
				System.out.print(" ");
				for (int topX = 0; topX < map[0].toString().length(); topX++) {
					System.out.print(topX);
				}
				System.out.println();
				start = false;
			}
			if (map.length > 9) {
				for (int i = 0; i < map.length; i++) {
					if (i < 10) {
						System.out.print(y + " ");
					} else {
						System.out.print(y);
					}
					for (int j = 0; j < map[i].length(); j++) {
						if (i == location.getY() && j == location.getX()) {
							System.out.print("C");
						} else if (map[i].toString().charAt(j) == 'x'
								|| map[i].toString().charAt(j) == 'L') {
							System.out.print("x");
						} else if (map[i].toString().charAt(j) == 'z') {
							System.out.print(" ");
						} else {
							System.out.print("o");
						}
					}
					System.out.println();
					y++;
				}
				System.out.println();
				return sum;
			} else {
				for (int i = 0; i < map.length; i++) {
					System.out.print(y);
					for (int j = 0; j < map[i].length(); j++) {
						if (i == location.getY() && j == location.getX()) {
							System.out.print("C");
						} else if (map[i].toString().charAt(j) == 'x'
								|| map[i].toString().charAt(j) == 'L') {
							System.out.print("x");
						} else {
							System.out.print("o");
						}
					}
					System.out.println();
					y++;
				}
				System.out.println();
				return sum;
			}
		} else {
			// DEVELOPERS MAP //
			System.out
					.println("Current Location : " + "("
							+ (int) location.getX() + ","
							+ (int) location.getY() + ")");
			String sum = "";
			int y = 0;
			boolean start = true;
			if (start == true) {
				System.out.print(" ");
				for (int topX = 0; topX < map[0].toString().length(); topX++) {
					System.out.print(topX);
				}
				System.out.println();
				start = false;
			}
			if (map.length > 9) {
				for (int i = 0; i < map.length; i++) {
					if (i < 10) {
						System.out.print(y + " ");
					} else {
						System.out.print(y);
					}
					for (int j = 0; j < map[i].length(); j++) {
						if (i == location.getY() && j == location.getX()) {
							System.out.print("C");
						} else {
							System.out.print(map[i].toString().charAt(j));
						}
					}
					System.out.println();
					y++;
				}
				System.out.println();
				return sum;
			} else {
				for (int i = 0; i < map.length; i++) {
					System.out.print(y);
					for (int j = 0; j < map[i].length(); j++) {
						if (i == location.getY() && j == location.getX()) {
							System.out.print("C");
						} else {
							System.out.print(map[i].toString().charAt(j));
						}
					}
					System.out.println();
					y++;
				}
				System.out.println();
				return sum;
			}
		}
	}

	public static IObject getWorldObject(int i) {
		return worldObjects[i];
	}
}