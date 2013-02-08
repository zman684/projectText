package engine;

import weapons.Weapon;

public class User {
	private static String name;
	public static int password;
	private static double health;
	private static double mana;
	private static String[] invo;
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
		User.name = name;
		User.password = 1234;
		User.health = 100;
		User.mana = 100;
		User.invo = invo;
		User.score = 0;
		User.end = false;
		User.rightHand = "nothing";
		User.leftHand = "nothing";
		User.feet = "Sandals";
		User.legs = "Rags";
		User.torso = "nothing";
		User.head = "nothing";
		User.back = "nothing";
	}
	
	public User(String name, int password, double health, double mana, int score, String rightHand, String leftHand,
			String feet, String legs, String torso, String head, String back) {
				this.name = name;
				this.password = password;
				this.health = health;
				this.mana = mana;
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
	public String[] getInvo() {
		return invo;
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
	public void setInvo(String[] invo) {
		User.invo = invo;
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
	public void setLeftHand(String leftHand) {
		User.leftHand = leftHand;
	}
	public void setFeet(String boots) {
		User.feet = boots;
	}
	public void setLegs(String legs) {
		User.legs = legs;
	}
	public void setTorso(String shirt) {
		User.torso = shirt;
	}
	public void setHead(String head) {
		User.head = head;
	}
}
