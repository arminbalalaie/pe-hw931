package events;

import simulator.Job;
import simulator.Simulator;

public class EndProcessEvent extends Event {

	

	public EndProcessEvent(Simulator simulator, Job job, double triggerTime)
	{
		super(simulator, job, triggerTime);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doIt() {
		this.job.finish();
		simulator.releaseServer();
		Job newJob = simulator.getJobQueue().dequeue();
		if(newJob!=null)
		{
			newJob.startProcess();
			EndProcessEvent event = new EndProcessEvent(simulator, newJob,
				newJob.getProcessingTime() + this.simulator.getClock());
			simulator.getEventsHeap().pushToEvents(event);
		}
	}
}
