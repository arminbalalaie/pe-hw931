package events;

import simulator.EventsHeap;
import simulator.Job;

public class CreateJobEvent extends Event {

	public CreateJobEvent(EventsHeap heap, Job job, double triggerTime) {
		super(heap, job, triggerTime);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doIt() {
		EnqueueJobEvent enqueueEvent = new EnqueueJobEvent(this.heap, this.job,
				this.job.getStartTime());
		Job newJob = new Job();
		CreateJobEvent createEvent = new CreateJobEvent(this.heap, newJob,
				newJob.getCreationTime());
		this.heap.pushToEvents(enqueueEvent);
		this.heap.pushToEvents(createEvent);

	}

}
