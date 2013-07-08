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
	private static final int startY = 6;
	private static IObject[] worldObjects;
	private static Move[] worldMaps;
	private static boolean dev;
	private static Monster goblin;

	/**
	 * All the commands that a user can type in
	 */
	public static void menu() {
		while (true) {
			checker();
			mapChecker();
			Scanner in = new Scanner(System.in);
			// Command line
			System.out.print(": ");
			String action = in.nextLine();
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
			}else if (action.substring(0,6).equals("equip ")){
				equipItem(action.substring(6,action.length()));
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
				look();
			} else if (action.equals("invo")) {
				System.out.println(invo());
			} else if (action.equals("exit")) {
				System.out.println("goodbye");
				break;
			} else if (action.equals("clear")) {
				clear();
			} else {
				// If it is not in the command "if" block
				// it must not be a command
				System.out
						.println("That is not a correct command please try again.");
			}
		}
	}

	private static void equipItem(String item) {
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
							User.setLegs((Armor) check(invo.get(j).getName()));
							equip = invo.get(j).getName() + " equipped";
							invo.remove(j);
							j = invo.size();
							break;
						case "Torso":
							invo.add(user.getTorso());
							User.setTorso((Armor) check(invo.get(j).getName()));
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

	// Look method for *ALLY TAYLOR*
	private static void look() {
		if (location.getX() == 1 && location.getY() == 6) {
			if (Heading == 3) {
				System.out.println("There is a monster ahead!");
			} else {
				System.out.println("There is a dense forest...");
			}
		}
	}

	private static void mapChecker() {
		// ALL MONSTER LOCATION//
		if (location.getX() == 1 && location.getY() == 5) {
			Fight test = new Fight(user, goblin, "Plains");
			System.out.println(test.battle());
		}

	}

	/**
	 * Clears the menu so all text is gone
	 */
	// TODO: Clear the menu
	private static void clear() {
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
							User.setLegs((Armor) check(invo.get(j).getName()));
							equip = invo.get(j).getName() + " equipped";
							invo.remove(j);
							j = invo.size();
							break;
						case "Torso":
							invo.add(user.getTorso());
							User.setTorso((Armor) check(invo.get(j).getName()));
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
		if (Heading == 1) {
			Point newLocation = new Point(location.getX(), location.getY() + 1);
			if (read((int) newLocation.getX(), (int) newLocation.getY()) != 'x') {
				location = newLocation;
				System.out.println("You moved forward");
			} else {
				System.out.println("You can't go that way!");
			}
		} else if (Heading == 2) {
			Point newLocation = new Point(location.getX() + 1, location.getY());
			if (read((int) newLocation.getX(), (int) newLocation.getY()) != 'x') {
				location = newLocation;
				System.out.println("You moved forward");
			} else {
				System.out.println("You can't go that way!");
			}
		} else if (Heading == 3) {
			Point newLocation = new Point(location.getX(), location.getY() - 1);
			if (read((int) newLocation.getX(), (int) newLocation.getY()) != 'x') {
				location = newLocation;
				System.out.println("You moved forward");
			} else {
				System.out.println("You can't go that way!");
			}
		} else {
			Point newLocation = new Point(location.getX() - 1, location.getY());
			if (read((int) newLocation.getX(), (int) newLocation.getY()) != 'x') {
				location = newLocation;
				System.out.println("You moved forward");
			} else {
				System.out.println("You can't go that way!");
			}
		}
	}

	/**
	 * Moves back one adds or subtracts x or y depending on which way they are
	 * facing
	 */
	public static void goBack() {
		if (Heading == 1) {
			Point newLocation = new Point(location.getX(), location.getY() - 1);
			if (read((int) newLocation.getX(), (int) newLocation.getY()) != 'x') {
				location = newLocation;
				System.out.println("You moved back");
			} else {
				System.out.println("You can't go that way!");
			}
		} else if (Heading == 2) {
			Point newLocation = new Point(location.getX() - 1, location.getY());
			if (read((int) newLocation.getX(), (int) newLocation.getY()) != 'x') {
				location = newLocation;
				System.out.println("You moved back");
			} else {
				System.out.println("You can't go that way!");
			}
		} else if (Heading == 3) {
			Point newLocation = new Point(location.getX(), location.getY() + 1);
			if (read((int) newLocation.getX(), (int) newLocation.getY()) != 'x') {
				location = newLocation;
				System.out.println("You moved back");
			} else {
				System.out.println("You can't go that way!");
			}
		} else {
			Point newLocation = new Point(location.getX() + 1, location.getY());
			if (read((int) newLocation.getX(), (int) newLocation.getY()) != 'x') {
				location = newLocation;
				System.out.println("You moved back");
			} else {
				System.out.println("You can't go that way!");
			}
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
		} else if (Heading > 4) {
			Heading = 1;
		} else if (Heading < 1) {
			Heading = 4;
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
	 * @param path
	 *            Which file to save to
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
		worldObjects = new IObject[] { new Weapon("Sword", "Melee", 5, 4.0),// 0
				new Weapon("Staff", "Magic", 5, 4.0),// 1
				new Weapon("Bow", "Range", 5, 4.0),// 2
				new Weapon("Dagger", "Melee", 5, 4.0),// 3
				new Armor("Helm", "Head", 5, 4.0),// 4
				new Armor("Top", "Torso", 5, 4.0),// 5
				new Armor("Legs", "Legs", 5, 4.0),// 6
				new Armor("Boots", "Feet", 5, 4.0),// 7
				new Armor("Cape", "Back", 5, 4.0),// 8
				new Item("Beer", "Alchahol", -1, 10) };// 9
		// MAPS NEW TO HAVE ONE 'S' FOR THEIR STARING POSITION//
		worldMaps = new Move[] { mapMaker("xxx"), mapMaker("xex"),
				mapMaker("xox"), mapMaker("xox"), mapMaker("xox"),
				mapMaker("xox"), mapMaker("xox"), mapMaker("xsx"),
				mapMaker("xxx") };
		// CREATE THE MONSTERS HERE//
		// Then put the monsters//
		// in the if statement to//
		// check if the player//
		// step on one//
		goblin = new Monster("Goblin", 100, 1);

		Scanner in = new Scanner(System.in);
		String name = "";
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
			}else{
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
			//Sets so the dev will have a sword in their hand
			user.setRightHand(worldObjects[0]);
		} else {
			invo = new ArrayList<IObject>();
		}
		map = new Move[] { mapMaker("xxxxx"), mapMaker("xexxx"),
				mapMaker("xoxxx"), mapMaker("xmxxx"), mapMaker("xooox"),
				mapMaker("xoxtx"), mapMaker("xsxxx"), mapMaker("xxxxx") };
		location = new Point(startX, startY);
		String summary = "\nWelcome " + name + "!";
		summary += "\nIf you need help please type 'help'";
		summary += "\nOr if you need to know the commands you can type in 'commands'";
		System.out.println(summary);
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
	 * @param str
	 *            The map
	 */
	public static Move mapMaker(String str) {
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == 's') {
				startX = i;
			}
		}
		return new Move(str);
	}

	public static char read(int x, int y) {
		return map[y].toString().charAt(x);
	}

	public static void checker() {
		for (int i = 0; i < invo.size(); i++) {
			if (invo.get(i) == null) {
				invo.remove(i);
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
			for (int i = 0; i < map.length; i++) {
				System.out.print(y);
				for (int j = 0; j < map[i].length(); j++) {
					if (map[i].toString().charAt(j) == 'x') {
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
			for (int i = 0; i < map.length; i++) {
				System.out.print(y);
				for (int j = 0; j < map[i].length(); j++) {
					System.out.print(map[i].toString().charAt(j));
				}
				System.out.println();
				y++;
			}
			System.out.println();
			return sum;
		}
	}

	public static IObject getWorldObject(int i) {
		return worldObjects[i];
	}
}
