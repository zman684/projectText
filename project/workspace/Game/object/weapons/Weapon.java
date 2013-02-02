package weapons;

import engine.Utils;

public class Weapon {
	private static String name;
	private static int dmg;
	private static double weight;
	private static String type;

	public Weapon(String name, String type, int dmg, double weight){
		Weapon.name = name;
		Weapon.type = type;
		Weapon.dmg = dmg;
		Weapon.weight = weight;
	}
	
	public String toString(){
		String summary = name + "," + type + "," + dmg + "," + weight;
		return summary;
	}
	
	public static String getName() {
		return name;
	}

	public static int getDmg() {
		return dmg;
	}

	public static double getWeight() {
		return weight;
	}

	public static String getType() {
		return type;
	}

}
