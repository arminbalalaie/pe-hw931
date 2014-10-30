package events;

import simulator.EventsHeap;
import simulator.Job;
import simulator.JobQueue;

public class CreateJobEvent extends Event {

	public CreateJobEvent(EventsHeap heap, Job job, double triggerTime,
			JobQueue jobQueue) {
		super(heap, job, triggerTime, jobQueue);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doIt() {
		EnqueueJobEvent enqueueEvent = new EnqueueJobEvent(this.heap, this.job,
				this.job.getStartTime(), this.jobQueue);
		Job newJob = new Job();
		CreateJobEvent createEvent = new CreateJobEvent(this.heap, newJob,
				newJob.getCreationTime(), this.jobQueue);
		this.heap.pushToEvents(enqueueEvent);
		this.heap.pushToEvents(createEvent);

	}

}
