package random;

import java.util.Random;

public class ConstantGenerator extends RandomGenerator {

	private double num;

	public ConstantGenerator(double num) {
		this.num = num;
	}

	@Override
	public double generate() {
		return num;
	}

}
