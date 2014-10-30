package analytic;

import org.apache.commons.math3.util.CombinatoricsUtils;

// @author Ali Ramezanzadeh, Naeim Taheri
public class ConstantAnalytical extends Analytical {

    public ConstantAnalytical(double lambda, double theta) {
        super(lambda, theta);
    }

    @Override
    double phi(int n, int c) {
        // n > 0
        double head = CombinatoricsUtils.factorialDouble(n) / Math.pow(c, n + 1);
        double c_theta = c * theta;
        
        double SigmaReult = 0;
        for (int i = 0; i <= n - 1; i++) {
            SigmaReult += Math.pow(c_theta, i) / CombinatoricsUtils.factorialDouble(i);
        }

        return head * (1 - Math.exp(-c_theta) * SigmaReult);
    }
    
}
