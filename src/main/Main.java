package main;

import simulator.Simulator;

public class Main {

	public static void main(String[] args) {
		System.out.println(args.length);
		if(args.length!=2)
			printUsageRules();
		
		boolean isExponential = Integer.parseInt(args[0])==1?true:false;
		int population = Integer.parseInt(args[1]);
		
		Simulator simulator = new Simulator(population, isExponential);
		for(double lambda=0.1;lambda<=20;lambda+=0.1)
		{
			simulator.setLambda(lambda);
			simulator.simulate();
		}		
	}
	
	private static void printUsageRules()
	{
		System.out.println("Usage Parameters: [isExponential] [population]");
		System.out.println("isExponential ==> 1:Yes - 0:No");
		System.out.println("e.g. simulation 1 100000");
	}
}
