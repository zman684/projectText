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
}
