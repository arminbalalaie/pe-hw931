package events;

import simulator.Job;
import simulator.JobQueue;
import simulator.Simulator;

public class EnqueueJobEvent extends Event {

	

	public EnqueueJobEvent(Simulator simulator, Job job, double triggerTime) {
		super(simulator, job, triggerTime);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doIt() {
		if(simulator.isQueueAvailable())
		{
			JobQueue queue = simulator.getAvailableQueue();
			job.setQueue(queue);
			queue.enqueue(job);
			job.enqueue();
			if (queue.isServerAvailable() && queue.getQueueSize()==1) 
			{
				Event endProcessEvent = new EndProcessEvent(simulator, job, job.getStartTime()+job.getProcessingTime());
				queue.occupyServer();
				simulator.getEventsHeap().pushToEvents(endProcessEvent);
				queue.dequeue();
				job.startProcess();
			} 	
			else
			{
				ExpireJobEvent jobEvent = new ExpireJobEvent(simulator, job, job.getDeadlineTime());
				simulator.getEventsHeap().pushToEvents(jobEvent);
			}
			
		}
		else
			job.block();
	}

}
