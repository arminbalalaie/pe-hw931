package simulator;

import java.util.Random;

public class RandomGenerator {
    public static Random rand = new Random();
    public static double sampleExponentialLambda(double lamba) {
        return -Math.log(1-rand.nextDouble())/lamba;
//        ExponentialDistribution distribution = new ExponentialDistribution(1.0d/lamba);
//        return distribution.sample();
    }
    
    public static double sampleExponentialMean(double mean) {
        return sampleExponentialLambda(1/mean);
    }

    public static double sampleUniformMean(double mean) {
        return mean;
    }
}
