package villagers;

import java.util.ArrayList;

import objects.IObject;


public class Merchant {
	private static String name;
	private static ArrayList<IObject> invo;
	private static int level;

	//TODO: Work on the merchant
	public Merchant(String name, int lvl) {
		this.name = name;
		level = lvl;
		invo = new ArrayList<IObject>();
	}

	public static String getName() {
		return name;
	}
	
	public static IObject[] makeInvo(){
		for(int i = 0; i < 7; i++){
			
		}
		return null;
	}
}
