package engine;

import java.util.ArrayList;

import objects.IObject;
import protection.Armor;

import weapons.Weapon;

public class User {
	private static String name;
	public static int password;
	private static double health;
	private static double mana;
	private static double level;
	private static ArrayList<Weapon> wepInvo;
	private static int score;
	private static boolean end;
	private static IObject rightHand;
	private static IObject leftHand;
	private static Armor feet;
	private static Armor legs;
	private static Armor torso;
	private static Armor head;
	private static Armor back;

	/**
	 * Constructor for the user
	 *
	 * @param name
	 *            the users name
	 */
	public User(String name) {
		this.name = name;
		password = 1234;
		health = 100;
		mana = 100;
		level = 1.0;
		wepInvo = new ArrayList<Weapon>();
		score = 0;
		end = false;
		rightHand = null;
		leftHand = null;
		feet = new Armor("Boots", "feet", 0, 1);
		legs = new Armor("Rags", "legs", 0, 1);
		torso = null;
		head = null;
		back = null;
	}

	public User(String name, int password, double health, double mana,
			double level, boolean end, int score, IObject rightHand,
			IObject leftHand, Armor feet, Armor legs, Armor torso, Armor head,
			Armor back) {
		this.name = name;
		this.password = password;
		this.health = health;
		this.mana = mana;
		this.level = level;
		this.end = end;
		// wepInvo = new ArrayList<Weapon>();
		this.score = score;
		this.rightHand = rightHand;
		this.leftHand = leftHand;
		this.feet = feet;
		this.legs = legs;
		this.torso = torso;
		this.head = head;
		this.back = back;

	}

	public String toString() {
		int def = 0;
		if (legs != null) {
			def += legs.getDef();
		}
		if (head != null) {
			def += head.getDef();
		}
		if (torso != null) {
			def += torso.getDef();
		}
		if (feet != null) {
			def += feet.getDef();
		}
		if (back != null) {
			def += back.getDef();
		}
		int att = 0;
		if (rightHand != null) {
			if (rightHand.getObjectType().equals("weapon")) {
				att += rightHand.getDmg();
			} else {
				def += rightHand.getDef();
			}
		}
		if (leftHand != null) {
			if (leftHand.getObjectType().equals("weapon")) {
				att += leftHand.getDmg();
			} else {
				def += rightHand.getDef();
			}
		}
		String summary = "Name: " + name;
		summary += "\nHealth: " + health;
		summary += "\nMana: " + mana;
		summary += "\nScore: " + score;
		summary += "\nAttack: " + att;
		summary += "\nDefence: " + def;
		summary += "\nLevel: " + level;
		return summary;
	}

	public String equipment() {
		String right = "";
		String left = "";
		String head = "";
		String torso = "";
		String legs = "";
		String feet = "";
		String back = "";
		if (rightHand == null) {
			right = "Nothing";
		} else {
			right = "" + rightHand;
		}
		if (leftHand == null) {
			left = "Nothing";
		} else {
			left = "" + leftHand;
		}
		if (this.head == null) {
			head = "Nothing";
		} else {
			head = "" + this.head;
		}
		if (this.torso == null) {
			torso = "Nothing";
		} else {
			torso = "" + this.torso;
		}
		if (this.legs == null) {
			legs = "Nothing";
		} else {
			legs = "" + this.legs;
		}
		if (this.feet == null) {
			feet = "Nothing";
		} else {
			feet = "" + this.feet;
		}
		if (this.back == null) {
			back = "Nothing";
		} else {
			back = "" + this.back;
		}
		String summary = "Equipment:";
		summary += "\nRight Hand: " + right;
		summary += "\nLeft Hand: " + left;
		summary += "\nHead: " + head;
		summary += "\nTorso: " + torso;
		summary += "\nLegs: " + legs;
		summary += "\nFeet: " + feet;
		summary += "\nBack " + back;
		return summary;
	}

	// Getters
	public String getName() {
		return name;
	}

	public int getPassword() {
		return password;
	}

	public double getHealth() {
		return health;
	}

	public double getLevel() {
		return level;
	}

	public double getMana() {
		return mana;
	}

	public ArrayList<Weapon> getInvo() {
		return wepInvo;
	}

	public int getScore() {
		return score;
	}

	public boolean isEnd() {
		return end;
	}

	public IObject getRightHand() {
		return rightHand;
	}

	public IObject getLeftHand() {
		return leftHand;
	}

	public Armor getFeet() {
		return feet;
	}

	public Armor getLegs() {
		return legs;
	}

	public Armor getTorso() {
		return torso;
	}

	public Armor getHead() {
		return head;
	}

	public Armor getBack() {
		return back;
	}

	public int getDmg(){
		int att = 0;
		if (rightHand != null) {
			if (rightHand.getObjectType().equals("weapon")) {
				att += rightHand.getDmg();
			} else {
				//Do nothing
			}
		}
		if (leftHand != null) {
			if (leftHand.getObjectType().equals("weapon")) {
				att += leftHand.getDmg();
			} else {
				//Do nothing
			}
		}
		return att;
	}
	public int getDef(){
		int def = 0;
		if (legs != null) {
			def += legs.getDef();
		}
		if (head != null) {
			def += head.getDef();
		}
		if (torso != null) {
			def += torso.getDef();
		}
		if (feet != null) {
			def += feet.getDef();
		}
		if (back != null) {
			def += back.getDef();
		}
		int att = 0;
		if (rightHand != null) {
			if (rightHand.getObjectType().equals("weapon")) {
				//Do nothing
			} else {
				def += rightHand.getDef();
			}
		}
		if (leftHand != null) {
			if (leftHand.getObjectType().equals("weapon")) {
				//Do nothing
			} else {
				def += rightHand.getDef();
			}
		}
		return def;
	}

	public static int getWt(){
		int wt = 0;
		if (legs != null) {
			wt += legs.getWeight();
		}
		if (head != null) {
			wt += head.getWeight();
		}
		if (torso != null) {
			wt += torso.getWeight();
		}
		if (feet != null) {
			wt += feet.getWeight();
		}
		if (back != null) {
			wt += back.getWeight();
		}
		int att = 0;
		if (rightHand != null) {
			if (rightHand.getObjectType().equals("weapon")) {
				wt += ((Weapon) rightHand).getWeight();
			} else {
				wt += ((Armor) rightHand).getWeight();
			}
		}
		if (leftHand != null) {
			if (leftHand.getObjectType().equals("weapon")) {
				wt += ((Weapon) leftHand).getWeight();
			} else {
				wt += ((Armor) leftHand).getWeight();
			}
		}
		return wt;
	}

	// Setters
	public void setName(String name) {
		User.name = name;
	}

	public void setPassword(int password) {
		User.password = password;
	}

	public void setHealth(double health) {
		User.health = health;
	}

	public void setMana(double mana) {
		User.mana = mana;
	}

	public void setScore(int score) {
		User.score = score;
	}

	public void setEnd(boolean end) {
		User.end = end;
	}

	public static void setRightHand(IObject rightHand) {
		User.rightHand = rightHand;
	}

	public static void setLeftHand(IObject leftHand) {
		User.leftHand = leftHand;
	}

	public static void setFeet(Armor boots) {
		User.feet = boots;
	}

	public static void setLegs(Armor legs) {
		User.legs = legs;
	}

	public static void setTorso(Armor shirt) {
		User.torso = shirt;
	}

	public static void setHead(Armor head) {
		User.head = head;
	}

	public static void setBack(Armor back) {
		User.back = back;
	}
}
