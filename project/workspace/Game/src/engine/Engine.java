package engine;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import weapons.Weapon;
import maps.Move;
import maps.Point;
import methods.Invo;

public class Engine {
	private static User user;
	private static Invo[] invo;
	private static Point location;
	private static int Heading;
	private static Move[] map;
	private static int startX;
	private static final int startY = 6;

	/**
	 * All the commands that a user can type in
	 */
	public static void menu() {
		while (true) {
			Scanner in = new Scanner(System.in);
			System.out.print(": ");
			String action = in.nextLine();
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
			} else if (action.equals("exit")) {
				System.out.println("goodbye");
				break;
			} else if (action.equals("clear")) {
				clear();
			} else {
				System.out
						.println("That is not a correct command please try again.");
			}
		}
	}

	/**
	 * Clears the menu so all text is gone
	 */
	private static void clear() {
		menu();
	}

	/**
	 * Player types in equip then the console asks the user what they want to
	 * equip
	 */
	public static void equip() {
		int i;
		Scanner in = new Scanner(System.in);

		Weapon[] weapons = Utils.read("object/weapons/weapons.csv");

		for (i = 0; i < weapons.length; i++) {
			System.out.println(weapons[i]);
		}

		System.out.print("Equip what: ");
		String item = in.nextLine();

		if (item.equals(Weapon.getName())) {
			User.setRightHand(item);
			System.out.println(item + " equiped");
		} else {
			System.out.println("You can not equip that item!");
		}
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
		Scanner in = new Scanner(System.in);
		System.out.print("Pickup what: ");
		String item = in.nextLine();
		Invo a = new Invo(item);
		System.out.println("Do you want to pick up: " + item + "?");
		System.out
				.print("If this is correct please press 1, or press 2 if it is not: ");
		String c = in.nextLine();
		int option = Integer.parseInt(c);

		if (option == 1) {
			Invo[] temp = new Invo[invo.length + 1];
			for (int i = 0; i < invo.length; i++) {
				temp[i] = invo[i];
			}
			temp[temp.length - 1] = a;
			invo = temp;

			saveItem("list/invo.csv", invo);
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
		user = new User(name);
		invo = new Invo[1];
		Heading = 3;
		map = new Move[] {
				mapMaker("xxxxx"),
				mapMaker("xexxx"),
				mapMaker("xoxxx"),
				mapMaker("xmxxx"),
				mapMaker("xooox"),
				mapMaker("xoxtx"),
				mapMaker("xsxxx"),
				mapMaker("xxxxx") };
		location = new Point(startX, startY);
		String summary = "\nWelcome " + name + "!";
		summary += "\nIf you need help please type 'help'";
		summary += "\nOr if you need to know the commands you can type in 'commands'";
		System.out.println(summary);
	}

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
		System.out.println("Current Location : "
				+  "(" + (int)location.getX() + "," + (int) location.getY() + ")");
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