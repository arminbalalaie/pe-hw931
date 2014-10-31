package events;

import simulator.Job;
import simulator.Simulator;

public class ExpireJobEvent extends Event {

	public ExpireJobEvent(Simulator simulator, Job job, double triggerTime) {
		super(simulator, job, triggerTime);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doIt() {
		if (this.job.expire())
			this.simulator.getJobQueue().remove(this.job);

	}

}
