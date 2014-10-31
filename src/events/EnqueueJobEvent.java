package events;

import simulator.EventsHeap;
import simulator.Job;
import simulator.JobQueue;
import simulator.Simulator;

public class EnqueueJobEvent extends Event {

	

	public EnqueueJobEvent(Simulator simulator, Job job, double triggerTime) {
		super(simulator, job, triggerTime);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doIt() {
		if (this.simulator.isServerAvailable() && simulator.getJobQueue().isEmpty()) {
			EndProcessEvent endProcessEvent = new EndProcessEvent(simulator, job,
					job.getProcessingTime() + this.simulator.getClock());
			simulator.getEventsHeap().pushToEvents(endProcessEvent);
			job.startProcess();
		} else if (simulator.getJobQueue().isFull()) {
			ExpireJobEvent jobEvent = new ExpireJobEvent(simulator, job, job.getDeadlineTime());
			simulator.getEventsHeap().pushToEvents(jobEvent);
			job.enqueue();
			simulator.getJobQueue().enqueue(job);
		}else {
			job.block();
		}

	}

}
