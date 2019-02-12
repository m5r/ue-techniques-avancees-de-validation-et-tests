
public class DivisionEntiere {

	//@ requires y != 0;
	//@ ensures Math.abs(\result * y) <= Math.abs(x);
	//@ ensures Math.abs(x) - Math.abs(\result * y) < Math.abs(y);
	//@ ensures \result != 0 ==> ((\result >= 0) <==> (x>= 0 <==> y>=0));
	public static int IntDiv(int x, int y) {
		int z = 0;
		int signe = 1;
		if (x < 0) {
			signe = -1;
			x = -x;
		}
		if (y < 0) {
			signe = -signe;
			y = -y;
		}
		if (y == 0) {
			y=y; // Instruction factice pour voir si elle
				// a été couverte
		}
		// while (x > y) { // Version erronée
		while (x >= y) {
			x = x - y;
			z = z + 1;
		}
		z = signe * z;
		return z;
	}
}
