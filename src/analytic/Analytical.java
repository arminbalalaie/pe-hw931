package analytic;

public abstract class Analytical {

	double lambda;
	double t;
	private double p0;
	private int k;

	public Analytical(int k, double lambda, double t) {
		this.k = k;
		this.lambda = lambda;
		this.t = t;
		this.p0 = pZero();
	}

	public int factorial(int n) {
		int fact = 1;
		for (int i = 1; i <= n; i++) {
			fact *= i;
		}
		return fact;
	}

	public double pD() {
		return (1 + this.p0 - (2 / this.lambda) * (1 - this.p0)) - pN(k);
	}

	private double pZero() {
		double p0 = 0;
		double sum = 1 + this.lambda + (Math.pow(this.lambda, 2) / 2);
		for (int i = 3; i <= k; i++) {
			sum += (Math.pow(this.lambda, i) * phi(i - 2, 2) / this.factorial(i - 2));
		}
		p0 = 1 / sum;
		return p0;
	}

	public double pN(int n) {
		if (n < 0) {
			return 0;
		} else if (n == 0) {
			return this.p0;
		} else if (n == 1) {
			return this.p0 * this.lambda;
		} else if (n == 2) {
			return this.p0 * (Math.pow(this.lambda, 2) / 2);
		} else {
			return this.p0 * Math.pow(this.lambda, n) * phi(n - 2, 2)
					/ this.factorial(n - 2);
		}
	}

	abstract double phi(int n, int c);

}
