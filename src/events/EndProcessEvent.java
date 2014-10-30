package events;

import simulator.EventsHeap;
import simulator.Job;
import simulator.JobQueue;

public class EndProcessEvent extends Event {

	

	public EndProcessEvent(EventsHeap heap, Job job, double triggerTime,
			JobQueue jobQueue) {
		super(heap, job, triggerTime, jobQueue);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doIt() {
		this.job.finish();
		Job newJob = this.jobQueue.dequeue();
		EndProcessEvent event = new EndProcessEvent(this.heap, newJob,
				newJob.getProcessingTime() + this.simulator.getClock(),this.jobQueue);
		this.heap.pushToEvents(event);

	}
}
