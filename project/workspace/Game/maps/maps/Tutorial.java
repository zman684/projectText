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
				if(col == 9){
					System.out.print(table[row][col]);
				}else{
					System.out.print(table[row][col] + ",");
				}
			}
			System.out.println();
		}
	}
}