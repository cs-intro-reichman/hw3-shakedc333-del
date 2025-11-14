// Implements algebraic operations and the square root function without using 
// the Java operations a + b, a - b, a * b, a / b, a % b, and without calling 
// Math.sqrt. All the functions in this class operate on int values and
// return int values.

public class Algebra {
	public static void main(String args[]) {
		// Tests some of the operations
		System.out.println(plus(2, 3)); // 2 + 3
		System.out.println(minus(7, 2)); // 7 - 2
		System.out.println(minus(2, 7)); // 2 - 7
		System.out.println(times(3, 4)); // 3 * 4
		System.out.println(plus(2, times(4, 2))); // 2 + 4 * 2
		System.out.println(pow(5, 3)); // 5^3
		System.out.println(pow(3, 5)); // 3^5
		System.out.println(div(12, 3)); // 12 / 3
		System.out.println(div(5, 5)); // 5 / 5
		System.out.println(div(25, 7)); // 25 / 7
		System.out.println(mod(25, 7)+"->4"); // 25 % 7
		System.out.println(mod(120, 6)+"->0"); // 120 % 6
		System.out.println(sqrt(36)+"----->6");
		System.out.println(sqrt(263169));
		System.out.println(sqrt(76123));
		System.out.println(sqrt(8));
	}

	// Returns x1 + x2
	public static int plus(int x1, int x2) {
		int result = x1;
		while (x2 > 0) {
			result++;
			x2--;
		}
		return result;
	}

	// Returns x1 - x2
	public static int minus(int x1, int x2) {
		int result = x1;
		while (x2 > 0) {
			result--;
			x2--;
		}
		return result;
	}

	// Returns x1 * x2
	public static int times(int x1, int x2) {
		int result = 0;
		while (x2 > 0) {
			result = plus(result, x1);
			x2--;
		}
		return result;
	}

	// Returns x^n (for n >= 0)
	public static int pow(int x, int n) {
		if (n == 0)
			return 1;
		int result = 1;

		while (n > 0) {
			result = times(result, x); 
			n--; 
		}
		return result;
	}

	// Returns the integer part of x1 / x2
	public static int div(int x1, int x2) {
		int result = x1;
		int counter=0;
		while (x2 <=result) {
			result = minus(result, x2);
			counter++;
		}
		return counter;
	}

	// Returns x1 % x2
	public static int mod(int x1, int x2) {
	int howMuch=div(x1, x2);
	int times= times(howMuch, x2);
	int result = minus(x1, times);
	
		return result;
	}
	
	
	// Returns the integer part of sqrt(x)
	public static int sqrt(int x) {
		int counter=0;
		int pow=0;
		while(pow<x){
			counter++;
			pow=times(counter, counter);
		}
		if (pow(counter, 2)==x)
			return counter;
		else counter--;	
		return counter; 
	}
}