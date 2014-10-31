package random;

import java.util.Random;

public class ExponentialGenerator extends RandomGenerator {
	private Random random = new Random();
	private double lambda;

	public ExponentialGenerator(double lambda) {
		this.lambda = lambda;
	}

	@Override
	public double generate() {
		double rand = random.nextDouble();
		return Math.log(1 - rand) / (-lambda);
	}

}
