package analytic;


public class ExponentialAnalytical extends Analytical {

	public ExponentialAnalytical(int k, double lambda, double t) {
		super(k, lambda, t);
	}

	@Override
	double phi(int n, int c) {
		double num1 = this.factorial(n);
		double num2 = c;
		for (int i = 1; i < n + 1; i++) {
			num2 = num1 * (c + i / t);
		}
		return num1 / num2;
	}
}
