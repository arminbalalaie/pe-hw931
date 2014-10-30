package events;

import simulator.EventsHeap;
import simulator.Job;

public class ExpireJobEvent extends Event {


	public ExpireJobEvent(EventsHeap heap, Job job, double triggerTime) {
		super(heap, job, triggerTime);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doIt() {
		this.job.expire();
	}

}
