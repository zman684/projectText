package protection;

import objects.IObject;

public class Armor implements IObject{
	private String name;
	private int def;
	private double weight;
	private String type;
	private final String objectType = "armor";
	private int level;

	public Armor(String name, String type, int def, double weight, int level) {
		this.name = name;
		this.type = type;
		this.def = def;
		this.weight = weight;
		this.level = level;
	}

	public String toString() {
		String summary = name + ", " + type + ", " + "Def: " + def + ", " + "lb: " + weight + ", " + "Lvl: " + level;
		return summary;
	}

	public String getName() {
		return name;
	}

	public int getDef() {
		return def;
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
	public int getDmg() {
		return 0;
	}

	@Override
	public int getLevel() {
		return level;
	}

}
