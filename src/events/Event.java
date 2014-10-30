package events;

import simulator.EventsHeap;
import simulator.Job;
import simulator.JobQueue;
import simulator.Simulator;

public abstract class Event {
	protected double triggerTime;
	protected Job job;
	protected EventsHeap heap;
	protected Simulator simulator;
	protected JobQueue jobQueue;

	public Event(EventsHeap heap, Job job, double triggerTime, JobQueue jobQueue) {
		this.heap = heap;
		this.job = job;
		this.triggerTime = triggerTime;
		this.jobQueue = jobQueue;
	}

	public double getTriggerTime() {
		return triggerTime;
	}

	public abstract void doIt();
}
