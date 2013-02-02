package engine;

public class User {
	private static String name;
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
	
	
	public User(String name) {
		User.name = name;
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
	//Setters
	public void setName(String name) {
		User.name = name;
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
	public void setRightHand(String rightHand) {
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