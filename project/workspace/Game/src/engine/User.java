package engine;

import java.util.ArrayList;

import objects.IObject;
import protection.Armor;

import weapons.Weapon;

public class User {
	private String name;
	private int password;
	private double health;
	private double mana;
	private int level;
	private double exp;
	private ArrayList<Weapon> wepInvo;
	private int score;
	private boolean end;
	private IObject rightHand;
	private IObject leftHand;
	private Armor feet;
	private Armor legs;
	private Armor torso;
	private Armor head;
	private Armor back;

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
		level = 1;
		exp = 0.0;
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
			int level, double exp, boolean end, int score, IObject rightHand,
			IObject leftHand, Armor feet, Armor legs, Armor torso, Armor head,
			Armor back) {
		this.name = name;
		this.password = password;
		this.health = health;
		this.mana = mana;
		this.level = level;
		this.exp = exp;
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

	public int getWt(){
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
		this.name = name;
	}

	public void setPassword(int password) {
		this.password = password;
	}

	public void setHealth(double health) {
		this.health = health;
	}

	public void setMana(double mana) {
		this.mana = mana;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public void setEnd(boolean end) {
		this.end = end;
	}

	public void setRightHand(IObject rightHand) {
		this.rightHand = rightHand;
	}

	public void setLeftHand(IObject leftHand) {
		this.leftHand = leftHand;
	}

	public void setFeet(Armor boots) {
		this.feet = boots;
	}

	public void setLegs(Armor legs) {
		this.legs = legs;
	}

	public void setTorso(Armor shirt) {
		this.torso = shirt;
	}

	public void setHead(Armor head) {
		this.head = head;
	}

	public void setBack(Armor back) {
		this.back = back;
	}
}
