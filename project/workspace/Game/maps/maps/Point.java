package maps;

	import java.text.DecimalFormat;

	public class Point {
		private double x;
		private double y;

		/**
		 * Constructs a point object using the specified parameters
		 * @param x x-coord
		 * @param y y-coord
		 */
		public Point(double x, double y){
			this.x = x;
			this.y = y;
		}

		public double getX() {
			return x;
		}
		public double getY() {
			return y;
		}

		public void setX(double x){
			this.x = x;
		}

		public void setY(double y){
			this.y = y;
		}

		public String toString(){
			//formats the coord with 2 decimal values
			DecimalFormat d = new DecimalFormat("0.00");

			return "(" + d.format(x) + ", " + d.format(y) + ")";
		}
}