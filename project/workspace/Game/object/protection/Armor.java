package protection;

import objects.IObject;

public class Armor implements IObject{
	private String name;
	private int def;
	private double weight;
	private String type;
	private final String objectType = "armor";

	public Armor(String name, String type, int def, double weight) {
		this.name = name;
		this.type = type;
		this.def = def;
		this.weight = weight;
	}

	public String toString() {
		String summary = name + ", " + type + ", " + def + ", " + weight;
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

}
