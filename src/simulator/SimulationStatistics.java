package simulator;

public class SimulationStatistics {
	private static SimulationStatistics instance=null;
	private int blocked=0;
	private int expired=0;
	private int finished=0;
	
	public SimulationStatistics()
	{
		
	}
	
	public static SimulationStatistics getInstance()
	{
		if(instance==null)
			instance = new SimulationStatistics();
		return instance;
	}
	
	public void reset()
	{
		blocked=0;
		expired=0;
		finished=0;
	}
	
	public void incrementExpired()
	{
		expired++;
	}
	
	public void incrementBlocked()
	{
		blocked++;
	}
	
	public double getDepartureProbability()
	{
		return finished/(finished+blocked+expired);
	}
	
	public double getBlockingProbability()
	{
		return blocked/(finished+blocked+expired);
	}
}
