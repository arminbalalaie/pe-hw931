package events;

import simulator.EventsHeap;
import simulator.Job;
import simulator.JobQueue;

public class ExpireJobEvent extends Event {


	

	public ExpireJobEvent(EventsHeap heap, Job job, double triggerTime,
			JobQueue jobQueue) {
		super(heap, job, triggerTime, jobQueue);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doIt() {
		this.job.expire();
	}

}
