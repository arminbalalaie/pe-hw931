package analytic;

import org.apache.commons.math3.util.CombinatoricsUtils;

// @author Ali Ramezanzadeh
public class ExponentialAnalytical extends Analytical {

    public ExponentialAnalytical(double lambda, double theta) {
        super(lambda, theta);
    }

    @Override
    double phi(int n, int c) {

        // n > 0
        double nominator = CombinatoricsUtils.factorialDouble(n);
        double denominator = c;

        for (int i = 1; i <= n; i++) {
            denominator *= (c + i / theta);
        }
        return nominator / denominator;
    }
    
}
