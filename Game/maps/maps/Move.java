package maps;

public class Move {
	private String location;

	public Move(String location){
		this.location = location;
	}

	private String getLocation(){
		return location;
	}

	@Override
	public String toString() {
		return location;
	}

	public int length(){
		return getLocation().length();
	}

	public static boolean equals(Move[] map, Move[] map2){
		for(int i = 0; i < map.length; i++){
			for(int j = 0; j < map[i].toString().length(); j++){
				if(map[i].toString().charAt(j) != map2[i].toString().charAt(j)){
					return false;
				}
			}
		}
		return true;

	}
}
