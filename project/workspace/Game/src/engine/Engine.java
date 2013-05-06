package engine;

import item.Item;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import objects.IObject;
import protection.Armor;
import weapons.Weapon;
import maps.Move;
import maps.Point;
import methods.Invo;

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
	private static boolean admin;

	/**
	 * All the commands that a user can type in
	 */
	public static void menu() {
		while (true) {
			Scanner in = new Scanner(System.in);
			//Command line
			System.out.print(": ");
			String action = in.nextLine();
			//A big "if" statement for all the commands
			//  of the game
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
				System.out.println(user.toString());
			} else if (action.equals("help")) {
				help();
			} else if (action.equals("commands")) {
				commands();
			} else if (action.equals("look around")) {
				System.out.println("You look are and see...");
			} else if (action.equals("invo")) {
				System.out.println(invo());
			} else if (action.equals("exit")) {
				System.out.println("goodbye");
				break;
			} else if (action.equals("clear")) {
				clear();
			} else {
				//If it is not in the command "if" block
				//   it must not be a command
				System.out
						.println("That is not a correct command please try again.");
			}
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
	//TODO: When you equip an item... When it unequips the item
	//           they were wielding, this method should put that
	//			 item in their invo
	public static void equip() {
		String equip = "";
		Scanner in = new Scanner(System.in);
		//Prints all the items in your equipmemt
		for (int i = 0; i < invo.size(); i++) {
			System.out.println(invo.get(i).getName());
		}
		System.out.print("Equip what: ");
		String item = in.nextLine();
		//Look for the item they entered and see if
		//     it is an item, weapon, or armor
		for (int j = 0; j < invo.size(); j++) {
			//When the item they entered equals a items name
			//     in their invo
			if (item.toLowerCase().equals(invo.get(j).getName().toLowerCase())) {
				//Check if it is a weapon
				if (invo.get(j).getObjectType().equals("weapon")) {
					//Set their right hand to that weapon
					User.setRightHand(((Weapon) invo.get(j)).getName());
					//Change the text
					equip = ((Weapon) invo.get(j)).getName() + " equiped";
					//Remove the item from the invo
					invo.remove(j);
					break;
				//If it is armor
				} else if (invo.get(j).getObjectType().equals("armor")) {
					String type = ((Armor) invo.get(j)).getType();
					//Check to see what type of armor it is
					switch (type) {
						case "Hand":
							User.setLeftHand(invo.get(j).getName());
							equip = invo.get(j).getName() + " equiped";
							invo.remove(j);
							break;
						case "Back":
							User.setBack(invo.get(j).getName());
							equip = invo.get(j).getName() + " equiped";
							invo.remove(j);
							break;
						case "Legs":
							User.setLegs(invo.get(j).getName());
							equip = invo.get(j).getName() + " equiped";
							invo.remove(j);
							break;
						case "Torso":
							User.setTorso(invo.get(j).getName());
							equip = invo.get(j).getName() + " equiped";
							invo.remove(j);
							break;
						case "Head":
							User.setHead(invo.get(j).getName());
							equip = invo.get(j).getName() + " equiped";
							invo.remove(j);
							break;
						case "Feet":
							User.setFeet(invo.get(j).getName());
							equip = invo.get(j).getName() + " equiped";
							invo.remove(j);
							break;
					}
				}
			//If it is not a Weapon or Armor then
			//	 it must be an item
			} else {
				equip = "You can not equip that item!";
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
		// Yes or No
		System.out.println("Do you want to pick up: " + item + "?");
		String option = in.nextLine();

		if (option.toLowerCase().equals("yes")) {
			if (itemIsValid) {
				invo.add(worldObjects[i]);
				System.out.println("You picked up " + worldObjects[i]);
			} else {
				System.out.println("You can not pick that up.");
			}
		} else {
			menu();
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
		Scanner in = new Scanner(System.in);
		System.out.print("What is your name: ");
		String name = in.nextLine();
		if (name.toLowerCase().equals("admin")) {
			//Ask for the admin password three times
			for (int i = 0; i < 3; i++) {
				System.out.print("Password: ");
				String pass = in.nextLine();
				//If they got that password right
				//   stop asking and log them in
				if (pass.equals("7895123")) {
					admin = true;
					i = 3;
				} else {
				//Else ask again
					System.out.println("Please try again");
				}
			}
			//If they did not get the password right in the three
			//   attempts then ask for their name again
			if (!admin) {
				System.out.print("What is your name: ");
				name = in.nextLine();
			}
		}
		user = new User(name);
		Heading = 3;
		invo = new ArrayList<IObject>();
		map = new Move[] { mapMaker("xxxxx"), mapMaker("xexxx"),
				mapMaker("xoxxx"), mapMaker("xmxxx"), mapMaker("xooox"),
				mapMaker("xoxtx"), mapMaker("xsxxx"), mapMaker("xxxxx") };
		location = new Point(startX, startY);
		String summary = "\nWelcome " + name + "!";
		summary += "\nIf you need help please type 'help'";
		summary += "\nOr if you need to know the commands you can type in 'commands'";
		System.out.println(summary);
		// ALL DEFAULT MAPS, WEAPONS, AND ITEMS//
		worldObjects = new IObject[] { new Weapon("Sword", "Melee", 5, 4.0),
				new Weapon("Staff", "Magic", 5, 4.0),
				new Weapon("Bow", "Range", 5, 4.0),
				new Weapon("Dagger", "Melee", 5, 4.0),
				new Armor("Helm", "Head", 5, 4.0),
				new Armor("Top", "Torso", 5, 4.0),
				new Armor("Legs", "Legs", 5, 4.0),
				new Armor("Boots", "Feet", 5, 4.0),
				new Armor("Gloves", "Hand", 5, 4.0),
				new Armor("Cape", "Back", 5, 4.0),
				new Item("Beer", "Alchahol", -1, 10) };
		// MAPS NEW TO HAVE ONE 'S' FOR THEIR STARING POSITION//
		worldMaps = new Move[] { mapMaker("xxx"), mapMaker("xex"),
				mapMaker("xox"), mapMaker("xox"), mapMaker("xox"),
				mapMaker("xox"), mapMaker("xox"), mapMaker("xsx"),
				mapMaker("xxx") };

	}

	/**
	 * @return prints out all of the Items and Weapons in the users Invo
	 */
	public static String invo() {
		String invoList = "";
		invoList += "Weapons:";
		invoList += "\n";
		for (IObject a : invo) {
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

	public static String showMap() {
		if (!admin) {
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
}