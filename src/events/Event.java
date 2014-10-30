package events;

import simulator.Job;
import simulator.Simulator;

public abstract class Event {
	int triggerTime;
	Job job;
	Simulator simulator;
	
	public abstract void doIt();
}
