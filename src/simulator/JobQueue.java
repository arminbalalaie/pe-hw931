package simulator;

import java.util.LinkedList;


public class JobQueue
{
	private int queueSize;
	private LinkedList<Job> jobs;
	
	public JobQueue(int size)
	{
		queueSize = size;
	}
	
	public boolean enqueue(Job job)
	{
		if(jobs.size()<queueSize)
		{
			jobs.add(job);
			return true;
		}
		return false;
	}
	
	public Job dequeue()
	{
		return jobs.poll();
	}
	
}
