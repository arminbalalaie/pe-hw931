package simulator;

import java.util.LinkedList;


public class JobQueue
{
	public int queueSize;
	private LinkedList<Job> jobs;
	private int availableServers;
	private int serverCount;
	
	public JobQueue(int size, int serverCount)
	{
		queueSize = size;
		jobs = new LinkedList<Job>();
		this.serverCount = serverCount;
		availableServers = serverCount;
	}
	
	public void occupyServer() {
		if (availableServers > 0)
			availableServers--;
	}

	public void releaseServer() {
		if (availableServers < serverCount)
			availableServers++;
	}

	public boolean isServerAvailable() {
		return availableServers > 0 ? true : false;
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
	
	public int getAvailableCapacity()
	{
		return queueSize-jobs.size();
	}
	
	public boolean isEmpty() {
		return jobs.isEmpty();
	}

	public boolean isFull() {
		return jobs.size() >= this.queueSize ? true : false;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Job job : jobs) {
			sb.append(job.getStartTime() + " ");
		}
		return sb.toString();
	}

	
}
