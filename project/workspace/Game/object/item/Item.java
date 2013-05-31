package item;

import objects.IObject;

public class Item implements IObject{

	private String name;
	private int power;
	private double weight;
	private String effect;
	private final String objectType = "item";

	public Item(String name, String effect, int power, double weight) {
		this.name = name;
		this.effect = effect;
		this.power = power;
		this.weight = weight;
	}

	public String toString() {
		String summary = name + ", " + effect + ", " + power + ", " + weight;
		return summary;
	}

	public String getName() {
		return name;
	}

	public int getPower() {
		return power;
	}

	public double getWeight() {
		return weight;
	}

	public String getEffect() {
		return effect;
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
	public int getDef() {
		return 0;
	}

}
