package simulator;

import java.util.HashMap;

public class SimulationStatistics {
	private static SimulationStatistics instance=null;
	private HashMap<JobQueue, Double> blocked;
	private HashMap<JobQueue, Double> expired;
	private HashMap<JobQueue, Double> finished;
	
	public SimulationStatistics()
	{
		blocked = new HashMap<>();
		expired = new HashMap<>();
		finished = new HashMap<>();
	}
	
	public static SimulationStatistics getInstance()
	{
		if(instance==null)
			instance = new SimulationStatistics();
		return instance;
	}
	
	public void reset()
	{
		blocked = new HashMap<>();
		expired = new HashMap<>();
		finished = new HashMap<>();
	}
	
	public void incrementExpired(JobQueue queue)
	{
		if(expired.get(queue)==null)
			expired.put(queue, 0.0);
		expired.put(queue, expired.get(queue)+1);
	}
	
	public void incrementBlocked(JobQueue queue)
	{
		if(blocked.get(queue)==null)
			blocked.put(queue, 0.0);
		blocked.put(queue, blocked.get(queue)+1);
	}
	
	public void incrementFinished(JobQueue queue)
	{
		if(finished.get(queue)==null)
			finished.put(queue, 0.0);
		finished.put(queue, finished.get(queue)+1);
	}
	
	public double getDepartureProbability(JobQueue queue)
	{
//		System.out.println("finished:" + finished);
		return finished.get(queue)/(finished.get(queue)+blocked.get(queue)+expired.get(queue));
	}
	
	public double getBlockingProbability(JobQueue queue)
	{
//		System.out.println("blocked : " + blocked);
		return blocked.get(queue)/(finished.get(queue)+blocked.get(queue)+expired.get(queue));
	}
	
	public double getExpiredProbability(JobQueue queue)
	{
//		System.out.println("blocked : " + blocked);
		return expired.get(queue)/(finished.get(queue)+blocked.get(queue)+expired.get(queue));
	}
}
