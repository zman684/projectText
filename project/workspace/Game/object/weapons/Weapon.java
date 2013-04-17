package weapons;

public class Weapon {
	private String name;
	private int dmg;
	private double weight;
	private String type;

	public Weapon(String name, String type, int dmg, double weight){
		this.name = name;
		this.type = type;
		this.dmg = dmg;
		this.weight = weight;
	}

	public String toString(){
		String summary = name + "," + type + "," + dmg + "," + weight;
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

}
