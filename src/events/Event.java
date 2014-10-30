package events;

import simulator.Job;
import simulator.Scheduler;

public abstract class Event {
	int triggerTime;
	Job job;
	Scheduler scheduler;
	
	public abstract void doIt();
}
