package maps;

public class Tutorial {
	public static void readMap() {
		int[][] table = new int[10][10];
		for (int row = 0; row < table.length; row++) {
			for (int col = 0; col < table[row].length; col++) {
				table[row][col] = 1;
			}
		}
		for (int row = 0; row < table.length; row++) {
			for (int col = 0; col < table[row].length; col++) {
				System.out.println(table[row][col] + "\t");
			}
			System.out.println();
		}
	}
}