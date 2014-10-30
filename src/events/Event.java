package events;

import java.beans.EventHandler;

import simulator.EventsHeap;
import simulator.Job;
import simulator.Simulator;

public abstract class Event {
	protected double triggerTime;
	protected Job job;
	protected EventsHeap heap;
	protected Simulator simulator;

	public Event(EventsHeap heap, Job job, double triggerTime) {
		this.heap = heap;
		this.job = job;
		this.triggerTime = triggerTime;
	}

	public double getTriggerTime() {
		return triggerTime;
	}

	public abstract void doIt();
}
