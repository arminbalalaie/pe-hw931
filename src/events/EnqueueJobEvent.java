package events;

import simulator.EventsHeap;
import simulator.Job;

public class EnqueueJobEvent extends Event {

	public EnqueueJobEvent(EventsHeap heap, Job job, double triggerTime) {
		super(heap, job, triggerTime);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doIt() {
		if (this.simulator.isServerAvailable() && this.simulator.isEmptyQueue()) {
			EndProcessEvent endProcessEvent = new EndProcessEvent(heap, job,
					job.getProcessingTime() + this.simulator.getClock());
			heap.pushToEvents(endProcessEvent);
			job.startProcess();
		} else if (!this.simulator.isFullQueue()) {
			ExpireJobEvent jobEvent = new ExpireJobEvent(heap, job,
					job.getDeadlineTime());
			heap.pushToEvents(jobEvent);
			job.enqueue();

		}else {
			job.block();
		}

	}

}
