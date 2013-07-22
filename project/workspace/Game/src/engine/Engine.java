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
	private static Move[] level1;
	private static Move[] level2;
	private static boolean end;
	private static boolean level2Lever;
	private static Move[] leverTest;
	private static Move[] monsterTest;
	private static Move[] devMap;
	private static double expLimit;

	/**
	 * All the commands that a user can type in
	 */
	public static void menu() {
		while (!end) {
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
			} else if (action.equals("back") || action.equals("go back")
					|| action.equals("move back")) {
				goBack();
			} else if (action.equals("left") || action.equals("turn left")) {
				System.out.println("You turn left");
				turnLeft();
			} else if (action.equals("right") || action.equals("turn right")) {
				System.out.println("You turn right");
				turnRight();
			} else if (action.equals("location")) {
				location();
			} else if (action.equals("equipment")) {
				System.out.println(user.equipment());
			} else if (action.equals("equip")) {
				equip();
			} else if (action.equals("pickup")) {
				pickUp();
			} else if (action.equals("map")) {
				showMap();
			} else if (action.equals("status")) {
				System.out.println(user);
			} else if (action.equals("help")) {
				help();
			} else if (action.equals("commands")) {
				commands();
			} else if (action.equals("look around")) {
				lookAround();
			} else if (action.equals("look")) {
				look();
			} else if (action.equals("invo")) {
				System.out.println(invo());
			} else if (action.equals("exit")) {
				System.out.println("goodbye");
				break;
			} else if (action.equals("pull lever")) {
				pullLever();
			} else if (action.equals("clear")) {
				clear();
			} else if (action.length() > 6) {
				if (action.substring(0, 6).equals("equip ")) {
					equipItem(action.substring(6, action.length()));
				} else if (action.substring(0, 7).equals("pickup ")) {
					pickupItem(action.substring(7, action.length()));
				} else {
					System.out
							.println("That is not a correct command please try again.");
				}
			} else {
				// If it is not in the command "if" block
				// it must not be a command
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

	public static void pickupItem(String item) {
		int i = 0;
		boolean itemIsValid = false;
		for (int j = 0; j < worldObjects.length; j++) {
			// Makes sure the item is by them and they don't just put in a
			// random name
			if (worldObjects[j].getName().toLowerCase()
					.equals(item.toLowerCase())) {
				i = j;
				itemIsValid = true;
			}
		}
		if (itemIsValid) {
			invo.add(worldObjects[i]);
			System.out.println("You picked up " + worldObjects[i]);
		} else {
			System.out.println("You can not pick that up.");
		}
	}

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
					// Check if it is a weapon
					if (invo.get(j).getObjectType().equals("weapon")) {
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
		} else if (curLoc == 'e') {// e == End Area
			newLevel(level2);
			map = level2;
			System.out.println("You enter a new area");
		} else if (curLoc == 'l') {// l == Lever
			System.out.println("You are by a lever.");
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
				removeMonster();
				goblinFight.restart();
				checker();
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
				removeMonster();
				trollFight.restart();
				checker();
			}
		} else {

		}
	}

	public static void removeMonster() {
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
		for (int i = 0; i < 25; i++) {
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
					// Check if it is a weapon
					if (invo.get(j).getObjectType().equals("weapon")) {
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
			System.out.println("You moved forward");
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
		int i = 0;
		boolean itemIsValid = false;
		Scanner in = new Scanner(System.in);
		System.out.print("Pickup what: ");
		String item = in.nextLine();
		for (int j = 0; j < worldObjects.length; j++) {
			// Makes sure the item is by them and they don't just put in a
			// random name
			if (worldObjects[j].getName().toLowerCase()
					.equals(item.toLowerCase())) {
				i = j;
				itemIsValid = true;
			}
		}
		if (itemIsValid) {
			invo.add(worldObjects[i]);
			System.out.println("You picked up " + worldObjects[i]);
		} else {
			System.out.println("You can not pick that up.");
		}
	}

	/**
	 * Saves the item to the invo (autosave)
	 *
	 * @param acqs Array of invo
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
		worldObjects = new IObject[] { new Weapon("Sword", "Melee", 5, 4.0),// 0
				new Weapon("Staff", "Magic", 5, 4.0),// 1
				new Weapon("Bow", "Range", 5, 4.0),// 2
				new Weapon("Dagger", "Melee", 5, 4.0),// 3
				new Armor("Helm", "Head", 5, 4.0),// 4
				new Armor("Top", "Torso", 5, 4.0),// 5
				new Armor("Legs", "Legs", 5, 4.0),// 6
				new Armor("Boots", "Feet", 5, 4.0),// 7
				new Armor("Cape", "Back", 5, 4.0),// 8
				new Item("Beer", "Alchahol", -1, 10),// 9
				new Weapon("GodSword", "Melee", 10000, 1.0),// 10
				new Armor("Dev Boots", "Feet", 10000, 1.0) };
		// MAPS NEW TO HAVE ONE 'S' FOR THEIR STARING POSITION//
		//MONSTERS//
		// g == Goblin
		// t == troll
		//NPCS//
		//OBJECTS//
		// l == lever && L == wall that will open
		devMap = new Move[] { mapMaker("xxxxxxxxxx"), mapMaker("xoooooooox"),
				mapMaker("xoooooooox"), mapMaker("xoooooooox"),
				mapMaker("xoooooooox"), mapMaker("xoooooooox"),
				mapMaker("xoooooooox"), mapMaker("xoooosooox"),
				mapMaker("xoooooooox"), mapMaker("xxxxxxxxxx") };
		leverTest = new Move[] { mapMaker("xxx"), mapMaker("xLxxx"),
				mapMaker("xsoolx"), mapMaker("xxxxx") };
		monsterTest = new Move[] { mapMaker("xxx"), mapMaker("xtx"),
				mapMaker("xtx"), mapMaker("xgx"), mapMaker("xtx"),
				mapMaker("xtx"), mapMaker("xgx"), mapMaker("xgx"),
				mapMaker("xgx"), mapMaker("xgx"), mapMaker("xgx"),
				mapMaker("xtx"), mapMaker("xgx"), mapMaker("xtx"),
				mapMaker("xgx"), mapMaker("xsx"), mapMaker("xxx"), };
		level1 = new Move[] { mapMaker("xxx"), mapMaker("xex"),
				mapMaker("xox"), mapMaker("xox"), mapMaker("xox"),
				mapMaker("xox"), mapMaker("xox"), mapMaker("xsx"),
				mapMaker("xxx") };
		level2 = new Move[] { mapMaker("xxxxx"), mapMaker("xfxxx"),
				mapMaker("xLxxx"), mapMaker("xgxxx"), mapMaker("xotox"),
				mapMaker("xoxlx"), mapMaker("xsxxx"), mapMaker("xxxxx") };
		mapStart(monsterTest);
		map = monsterTest;

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
		user = new User(name);
		Heading = 3;
		if (dev) {
			invo = new ArrayList<IObject>();
			for (IObject o : worldObjects) {
				invo.add(o);
			}
			// Sets so the dev will have a sword in their hand
			user.setRightHand(worldObjects[10]);
			user.setFeet((Armor) worldObjects[11]);
		} else {
			invo = new ArrayList<IObject>();
		}
		location = new Point(startX, startY);
		curLoc = map[(int) location.getY()].toString().charAt(
				(int) (location.getX()));
		String summary = "\nWelcome " + name + "!";
		summary += "\nIf you need help please type 'help'";
		summary += "\nOr if you need to know the commands you can type in 'commands'";
//		summary += "\nThis is (World name), it is world of chaos"
		System.out.println(summary);
		expLimit = user.getLevel() * 1000;
	}

	/**
	 * Checks the name to see if it is a game item
	 *
	 * @param name
	 *            name of the the item
	 *            they, most likely, want to pickup
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
	 * @param str The map
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

	public static void newLevel(Move[] map) {
		mapStart(map);
		location.setX(startX);
		location.setY(startY);
	}

	public static char read(int x, int y) {
		return map[y].toString().charAt(x);
	}

	public static void checker() {
		if (!end) {
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
