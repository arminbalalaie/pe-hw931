package simulator;

import java.util.Queue;

import events.Event;

public class Scheduler {
	Queue<Job> jobQueue;
	Queue<Event> eventQueue;
	int serverCount;
	int availableServers;
	double lambda;
	double serviceTimeAverage;
	boolean isExponentialDeadline;
	int maxJobCount;
	int clock;
	
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
}
