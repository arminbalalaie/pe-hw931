package analytic;

import org.apache.commons.math3.util.CombinatoricsUtils;

// @author Ali Ramezanzadeh, Naeim 
public abstract class Analytical {

    double lambda, theta;
    private final int k = 12;
    private double P_0;

    public Analytical(double lambda, double theta) {
        this.lambda = lambda;
        this.theta = theta;
        P_0 = P_0();
    }

    private double P_0() {
        double P0 = 0;

        // sumOf : P0_P12 = 1
        double sum = 1 + lambda + (Math.pow(lambda, 2) / 2);
        for (int i = 3; i <= k; i++) {
            sum += Math.pow(lambda, i) * phi(i - 2, 2) / CombinatoricsUtils.factorial(i - 2);
        }
        P0 = 1 / sum;
        return P0;
    }

    public double P_n(int n) {
        if (n < 0) {
            return 0;
        }

        if (n == 0) {
            return P_0;
        }
        if (n == 1) {
            return P_0 * lambda;
        }
        if (n == 2) {
            return P_0 * (Math.pow(lambda, 2) / 2);
        }
        
        return P_0 * Math.pow(lambda, n) * phi(n - 2, 2) / CombinatoricsUtils.factorial(n - 2);
    }

    abstract double phi(int n, int c);
    

    public double P_Blocked() {
        return P_n(12);
    }

    public double P_Deadline() {
        return (1 + P_0 - ((1 - P_0) * (2 / lambda))) - P_Blocked();
    }

}
