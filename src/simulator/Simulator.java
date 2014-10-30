package simulator;

import java.util.Queue;

import events.Event;

public class Simulator {
	private Queue<Job> jobQueue;
	private Queue<Event> eventQueue;
	private int serverCount;
	private int availableServers;
	private double lambda;
	private double serviceTimeAverage;
	private boolean isExponentialDeadline;
	private int population;
	private int totalJobCreated;
	private double clock;
	
	public Simulator(int population)
	{
		this.population = population;
	}
	
	public double getLambda() {
		return lambda;
	}

	public void setLambda(double lambda) {
		this.lambda = lambda;
	}
	
	private Job generateNewJob()
	{
		return null;
	}
	
	public void occupyServer()
	{
		if(availableServers > 0)
			availableServers--;
	}
	
	public void releaseServer()
	{
		if(availableServers<serverCount)
			availableServers++;
	}
	
	public boolean isServerAvailable()
	{
		return availableServers>0?true:false;
	}
	
	public void resetJobGenerator()
	{
		totalJobCreated = 0;
	}
	
	public void simulate()
	{
		
	}
}
