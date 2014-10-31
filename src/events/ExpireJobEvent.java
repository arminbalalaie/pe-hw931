package events;

import simulator.EventsHeap;
import simulator.Job;
import simulator.JobQueue;
import simulator.Simulator;

public class ExpireJobEvent extends Event {


	

	public ExpireJobEvent(Simulator simulator, Job job, double triggerTime)
	{
		super(simulator, job, triggerTime);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doIt() {
		this.job.expire();
	}

}
