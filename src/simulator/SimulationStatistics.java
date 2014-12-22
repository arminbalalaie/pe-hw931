package simulator;

import java.util.HashMap;
import java.util.Iterator;


public class SimulationStatistics {
	private static SimulationStatistics instance=null;
	private double blocked;
	private HashMap<JobQueue, Double> expired;
	private HashMap<JobQueue, Double> finished;
	
	public SimulationStatistics()
	{
		blocked = 0.0;
		expired = new HashMap<JobQueue, Double>();
		finished = new HashMap<JobQueue, Double>();
	}
	
	public static SimulationStatistics getInstance()
	{
		if(instance==null)
			instance = new SimulationStatistics();
		return instance;
	}
	
	public void reset()
	{
		blocked = 0.0;
		expired = new HashMap<JobQueue, Double>();
		finished = new HashMap<JobQueue, Double>();
	}
	
	public void incrementExpired(JobQueue queue)
	{
		if(expired.get(queue)==null)
			expired.put(queue, 0.0);
		expired.put(queue, expired.get(queue)+1);
	}
	
	public void incrementBlocked()
	{
		blocked++;
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
		return finished.get(queue)/(finished.get(queue)+blocked+expired.get(queue));
	}
	
	public double getExpiredProbability(JobQueue queue)
	{
//		System.out.println("blocked : " + blocked);
		double f=0.0,e=0.0,b=blocked;
		for(Iterator<JobQueue> it=finished.keySet().iterator();it.hasNext();)
		{
			JobQueue queue1 = it.next();
			if(!expired.containsKey(queue1))
				expired.put(queue1, 0.0);
			
			f += finished.get(queue1);
			e += expired.get(queue1);
		}
		
		return expired.get(queue)/(e+b+f);
	}
	
	public double getBlockingProbability()
	{
		double f=0.0,e=0.0,b=blocked;
		for(Iterator<JobQueue> it=finished.keySet().iterator();it.hasNext();)
		{
			JobQueue queue = it.next();
			if(!expired.containsKey(queue))
				expired.put(queue, 0.0);
			
			f += finished.get(queue);
			e += expired.get(queue);
		}
		System.out.println(b);
		System.out.println(e+b+f);
		return b/(e+b+f);
	}
	
	public String getQueueSizes()
	{
		String ret="";
		for(Iterator<JobQueue> it=finished.keySet().iterator();it.hasNext();)
		{
			ret += it.next().queueSize;
		}
		return ret;
	}
}
