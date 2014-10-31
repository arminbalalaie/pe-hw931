package events;

import simulator.Job;
import simulator.Simulator;

public class CreateJobEvent extends Event {

	public CreateJobEvent(Simulator simulator, Job job, double triggerTime)
	{
		super(simulator, job, triggerTime);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doIt() {
		Job newJob = simulator.generateNewJob();
		
		EnqueueJobEvent enqueueEvent = new EnqueueJobEvent(simulator, newJob, newJob.getStartTime());
		simulator.getEventsHeap().pushToEvents(enqueueEvent);
	}

}
