package simulator;

import java.util.LinkedList;


public class JobQueue
{
	private int queueSize;
	private LinkedList<Job> jobs;
	
	public JobQueue(int size)
	{
		queueSize = size;
		jobs = new LinkedList<Job>();
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
	
	public void remove(Job job){
		this.jobs.remove(job);
	}
	
	public int getQueueSize()
	{
		return jobs.size();
	}
	
	public boolean isEmpty() {
		return jobs.isEmpty();
	}

	public boolean isFull() {
		return jobs.size() >= this.queueSize ? true : false;
	}


	
}
