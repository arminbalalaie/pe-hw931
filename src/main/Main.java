package main;

import simulator.ErrorCalculator;
import simulator.Simulator;

public class Main {

	public static void main(String[] args) {
		if(args.length!=2)
		{
			printUsageRules();
			System.exit(0);
		}
		
		boolean isExponential = Integer.parseInt(args[0])==1?true:false;
		int population = Integer.parseInt(args[1]);
		ErrorCalculator blockError = new ErrorCalculator();
		ErrorCalculator expiredError = new ErrorCalculator();
		
		System.out.println("lambda\tS_Block\tS_Expir\tA_Block\tA_Expir");
		for(double lambda=0.1;lambda<=20.05;lambda+=0.1)
		{
			Simulator simulator = new Simulator(population, isExponential);
			simulator.setLambda(lambda);
			simulator.simulate(blockError, expiredError);
		}
		System.out.println("\nBlocking Error:");
		System.out.printf("Average:%.4f \nMax: %.4f\n", blockError.getAverageError(), blockError.getMaxError());
		System.out.println("\nExpiration Error:");
		System.out.printf("Average:%.4f \nMax: %.4f", expiredError.getAverageError(), expiredError.getMaxError());
	}
	
	private static void printUsageRules()
	{
		System.out.println("Usage Parameters: [isExponential] [population]");
		System.out.println("isExponential ==> 1:Yes - 0:No");
		System.out.println("e.g. simulation 1 100000");
	}
}
