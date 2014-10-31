package random;

import java.util.Random;

public class ExponentialGenerator extends RandomGenerator {
	private Random random;
	private double lambda;

	public ExponentialGenerator(double lambda) {
		this.lambda = lambda;
		random = new Random();
	}

	@Override
	public double generate() {
		double rand = random.nextDouble();
		return Math.log(1 - rand) / (-lambda);
	}

}
