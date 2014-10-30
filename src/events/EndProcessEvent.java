package events;

import simulator.EventsHeap;
import simulator.Job;

public class EndProcessEvent extends Event {

	public EndProcessEvent(EventsHeap heap, Job job, double triggerTime) {
		super(heap, job, triggerTime);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doIt() {
		this.job.finish();
		Job newJob = this.simulator.getFirstJob();
		EndProcessEvent event = new EndProcessEvent(this.heap, newJob,
				newJob.getProcessingTime() + this.simulator.getClock());
		this.heap.pushToEvents(event);

	}
}
