package simulator;

import java.util.ArrayList;

public class ErrorCalculator {
	private ArrayList<Double> errors = new ArrayList<Double>();
	private double max=0;
	private double sum=0;
	private int count=0;
	
	public void addError(double err)
	{
		if(err>max)
			max=err;
		count++;
		sum += err;
		errors.add(err);
	}
	
	public double getAverageError()
	{
		return sum/count;
	}
	
	public double getMaxError()
	{
		return max;
	}
}
