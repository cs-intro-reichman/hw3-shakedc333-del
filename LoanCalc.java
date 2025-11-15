// Computes the periodical payment necessary to pay a given loan.
public class LoanCalc {

	static double epsilon = 0.001; // Approximation accuracy
	static int iterationCounter; // Number of iterations

	// Gets the loan data and computes the periodical payment.
	// Expects to get three command-line arguments: loan amount (double),
	// interest rate (double, as a percentage), and number of payments (int).
	public static void main(String[] args) {
		// Gets the loan data
		double loan = Double.parseDouble(args[0]);
		double rate = Double.parseDouble(args[1]);
		int n = Integer.parseInt(args[2]);
		System.out.println("Loan = " + loan + ", interest rate = " + rate + "%, periods = " + n);

		// Computes the periodical payment using brute force search
		System.out.print("\nPeriodical payment, using brute force: ");
		System.out.println((int) bruteForceSolver(loan, rate, n, epsilon));
		System.out.println("number of iterations: " + iterationCounter);

		// Computes the periodical payment using bisection search
		System.out.print("\nPeriodical payment, using bi-section search: ");
		System.out.println((int) bisectionSolver(loan, rate, n, epsilon));
		System.out.println("number of iterations: " + iterationCounter);
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////
	// Computes the ending balance of a loan, given the loan amount, the periodical
	// interest rate (as a percentage), the number of periods (n), and the
	// periodical payment.
	private static double endBalance(double loan, double rate, int n, double payment) {
		double balance = loan;
		double r = rate / 100.0;

		for (int i = 0; i < n; i++) {
			balance = (balance - payment) * (1 + r);
		}
		return balance;
	}

	// Uses sequential search to compute an approximation of the periodical payment
	// Side effect: modifies the class variable iterationCounter.
	public static double bruteForceSolver(double loan, double rate, int n, double epsilon) {
		iterationCounter = 0;
		double paymentGuess = loan / n; // Start with an initial guess without interest
		double balance = endBalance(loan, rate, n, paymentGuess); // Calculate balance for this g payment

		while (balance > 0) {
			paymentGuess += epsilon;
			balance = endBalance(loan, rate, n, paymentGuess);
			iterationCounter++;
		}

		return paymentGuess;
	}

	// Uses bisection search to compute an approximation of the periodical payment
	// that will bring the ending balance of a loan close to 0.
	public static double bisectionSolver(double loan, double rate, int n, double epsilon) {
		iterationCounter = 0;
		// for payment = 0 the balance is definitely > 0
		// for payment = loan the balance is definitely < 0
		double lo = 0.0;
		double hi = loan;

		while (hi - lo > epsilon) {
			double mid = (lo + hi) / 2.0;
			double balance = endBalance(loan, rate, n, mid); // balance if we pay mid

			if (balance > 0) 
				lo = mid;// payment too small, need bigger
			else 
				hi = mid;// payment too big, need smaller

			iterationCounter++;
		}

		return (lo + hi) / 2.0;
	}
}