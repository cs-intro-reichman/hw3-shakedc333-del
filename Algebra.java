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

    // ---------- helper functions for sign handling ----------

    // Returns -x without using the - operator
    public static int negate(int x) {
        int result = 0;
        if (x > 0) {
            while (x > 0) {
                x--;       // move x toward 0
                result--;  // accumulate negative
            }
        } else {
            while (x < 0) {
                x++;       // move x toward 0
                result++;  // accumulate positive
            }
        }
        return result;
    }

    // Returns |x|
    public static int abs(int x) {
        if (x >= 0) {
            return x;
        }
        return negate(x);
    }

    // ---------- algebraic operations ----------

    // Returns x1 + x2
    public static int plus(int x1, int x2) {
        int result = x1;
        if (x2 > 0) {
            while (x2 > 0) {
                result++;
                x2--;
            }
        } else {
            while (x2 < 0) {
                result--;
                x2++;
            }
        }
        return result;
    }

    // Returns x1 - x2
    public static int minus(int x1, int x2) {
        // x1 - x2 = x1 + (-x2)
        int negX2 = negate(x2);
        return plus(x1, negX2);
    }

    // Returns x1 * x2
    public static int times(int x1, int x2) {
        boolean negative = false;

        if (x1 < 0) {
            negative = !negative;
            x1 = abs(x1);
        }
        if (x2 < 0) {
            negative = !negative;
            x2 = abs(x2);
        }

        int result = 0;
        while (x2 > 0) {
            result = plus(result, x1);
            x2--;
        }

        if (negative) {
            result = negate(result);
        }
        return result;
    }

    // Returns x^n (for n >= 0)
    public static int pow(int x, int n) {
        int result = 1;
        while (n > 0) {
            result = times(result, x);
            n--;
        }
        return result;
    }

    // Returns the integer part of x1 / x2
    public static int div(int x1, int x2) {
        if (x2 == 0) {
            // Not supposed to be tested; just a simple guard
            return 0;
        }

        boolean negative = false;

        if (x1 < 0) {
            negative = !negative;
            x1 = abs(x1);
        }
        if (x2 < 0) {
            negative = !negative;
            x2 = abs(x2);
        }

        int quotient = 0;
        while (x1 >= x2) {
            x1 = minus(x1, x2);
            quotient++;
        }

        if (negative) {
            quotient = negate(quotient);
        }
        return quotient;
    }

    // Returns x1 % x2
    public static int mod(int x1, int x2) {
        int howMuch = div(x1, x2);
        int prod = times(howMuch, x2);
        int result = minus(x1, prod);
        return result;
    }

    // Returns the integer part of sqrt(x)
    public static int sqrt(int x) {
        int counter = 0;
        int sq = 0;
        while (sq < x) {
            counter++;
            sq = times(counter, counter);
        }
        if (sq == x) {
            return counter;
        } else {
            counter--;
            return counter;
        }
    }
}