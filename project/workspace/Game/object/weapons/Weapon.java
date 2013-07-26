package weapons;

import objects.IObject;

public class Weapon implements IObject{
	private String name;
	private int dmg;
	private double weight;
	private String type;
	private final String objectType = "weapon";
	private int level;

	public Weapon(String name, String type, int dmg, double weight, int level){
		this.name = name;
		this.type = type;
		this.dmg = dmg;
		this.weight = weight;
		this.level = level;
	}

	public String toString(){
		String summary = name + ", " + type + ", " + dmg + ", " + weight + ", " + level;
		return summary;
	}

	public String getName() {
		return name;
	}

	public int getDmg() {
		return dmg;
	}

	public double getWeight() {
		return weight;
	}

	public String getType() {
		return type;
	}

	@Override
	public String getObjectType() {
		return objectType;
	}

	@Override
	public int getDef() {
		return 0;
	}

	@Override
	public int getLevel() {
		return level;
	}

}
