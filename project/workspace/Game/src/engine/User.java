package engine;

import java.util.ArrayList;

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
	private static String rightHand;
	private static String leftHand;
	private static String feet;
	private static String legs;
	private static String torso;
	private static String head;
	private static String back;

	/**
	 * Constructor for the user
	 * @param name the users name
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
		rightHand = "nothing";
		leftHand = "nothing";
		feet = "Sandals";
		legs = "Rags";
		torso = "nothing";
		head = "nothing";
		back = "nothing";
	}

	public User(String name, int password, double health, double mana, double level, boolean end, int score, String rightHand, String leftHand,
			String feet, String legs, String torso, String head, String back) {
				this.name = name;
				this.password = password;
				this.health = health;
				this.mana = mana;
				this.level = level;
				this.end = end;
//				wepInvo = new ArrayList<Weapon>();
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
		String summary = "Name: " + name;
				summary += "\nHealth: " + health;
				summary += "\nMana: " + mana;
				summary += "\nScore: " + score;
		return summary;
	}

	public String equipment(){
		String summary = "Equipment:";
			summary += "\nRight Hand: " + rightHand;
			summary += "\nLeft Hand: " + leftHand;
			summary += "\nHead: " + head;
			summary += "\nTorso: " + torso;
			summary += "\nLegs: " + legs;
			summary += "\nFeet: " + feet;
			summary += "\nBack " + back;
		return summary;
	}

//	public String invo(){
//
//	}

	//Getters
	public String getName() {
		return name;
	}
	public int getPassword() {
		return password;
	}
	public double getHealth() {
		return health;
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
	public String getRightHand() {
		return rightHand;
	}
	public String getLeftHand() {
		return leftHand;
	}
	public String getFeet() {
		return feet;
	}
	public String getLegs() {
		return legs;
	}
	public String getTorso() {
		return torso;
	}
	public String getHead() {
		return head;
	}
	public String getBack(){
		return back;
	}
	//Setters
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
	public static void setRightHand(String rightHand) {
		User.rightHand = rightHand;
	}
	public static void setLeftHand(String leftHand) {
		User.leftHand = leftHand;
	}
	public static void setFeet(String boots) {
		User.feet = boots;
	}
	public static void setLegs(String legs) {
		User.legs = legs;
	}
	public static void setTorso(String shirt) {
		User.torso = shirt;
	}
	public static void setHead(String head) {
		User.head = head;
	}
	public static void setBack(String back) {
		User.back = back;
	}
}
