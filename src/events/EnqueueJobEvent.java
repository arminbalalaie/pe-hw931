package events;

import simulator.EventsHeap;
import simulator.Job;
import simulator.JobQueue;

public class EnqueueJobEvent extends Event {

	

	public EnqueueJobEvent(EventsHeap heap, Job job, double triggerTime,
			JobQueue jobQueue) {
		super(heap, job, triggerTime, jobQueue);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doIt() {
		if (this.simulator.isServerAvailable() && this.jobQueue.isEmpty()) {
			EndProcessEvent endProcessEvent = new EndProcessEvent(heap, job,
					job.getProcessingTime() + this.simulator.getClock(),this.jobQueue);
			heap.pushToEvents(endProcessEvent);
			job.startProcess();
		} else if (!this.jobQueue.isFull()) {
			ExpireJobEvent jobEvent = new ExpireJobEvent(heap, job,
					job.getDeadlineTime(),this.jobQueue);
			heap.pushToEvents(jobEvent);
			job.enqueue();
			this.jobQueue.enqueue(job);
		}else {
			job.block();
		}

	}

}
