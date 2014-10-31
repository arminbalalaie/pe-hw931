package events;

import simulator.Job;
import simulator.Simulator;

public class EnqueueJobEvent extends Event {

	

	public EnqueueJobEvent(Simulator simulator, Job job, double triggerTime) {
		super(simulator, job, triggerTime);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doIt() {
		if(simulator.getJobQueue().enqueue(job))
		{
			job.enqueue();
			if (simulator.isServerAvailable() && simulator.getJobQueue().isEmpty()) 
			{
				Event endProcessEvent = new EndProcessEvent(simulator, job, job.getStartTime()+job.getProcessingTime());
				simulator.occupyServer();
				simulator.getEventsHeap().pushToEvents(endProcessEvent);
				simulator.getJobQueue().dequeue();
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
