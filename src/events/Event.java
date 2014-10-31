package events;

import simulator.EventsHeap;
import simulator.Job;
import simulator.JobQueue;
import simulator.Simulator;

public abstract class Event {
	protected double triggerTime;
	protected Job job;
	protected Simulator simulator;

	public Event(Simulator simulator, Job job, double triggerTime) {
		this.simulator = simulator;
		this.job = job;
		this.triggerTime = triggerTime;
	}

	public double getTriggerTime() {
		return triggerTime;
	}

	public abstract void doIt();
}
