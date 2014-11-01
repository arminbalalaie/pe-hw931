package simulator;

public class SimulationStatistics {
	private static SimulationStatistics instance=null;
	private double blocked=0;
	private double expired=0;
	private double finished=0;
	
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
	
	public void incrementFinished()
	{
		finished++;
	}
	
	public double getDepartureProbability()
	{
//		System.out.println("finished:" + finished);
		return finished/(finished+blocked+expired);
	}
	
	public double getBlockingProbability()
	{
//		System.out.println("blocked : " + blocked);
		return blocked/(finished+blocked+expired);
	}
	
	public double getExpiredProbability()
	{
//		System.out.println("blocked : " + blocked);
		return expired/(finished+blocked+expired);
	}
}
