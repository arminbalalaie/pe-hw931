package analytic;


public class ConstantAnalytical extends Analytical {

	public ConstantAnalytical(int k, double lambda, double t) {
		super(k, lambda, t);
	}

	@Override
	double phi(int n, int c) {
		double num1 = this.factorial(n)
				/ Math.pow(c, n + 1);
		double num2 = 0;
		for (int i = 0; i < n; i++) {
			num2 += (Math.pow(c * this.t, i) / this.factorial(i));
		}
		return num1 * (1 - Math.exp(-(c * this.t)) * num2);
	}

}
