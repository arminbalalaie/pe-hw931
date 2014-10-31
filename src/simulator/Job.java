package simulator;

import random.ExponentialGenerator;
import random.RandomGenerator;

public class Job {
	JobState state=null;
	double processingTime;
//	double creationTime;
	double startTime;
	double deadlineTime;

	public Job(double processingTime, double startTime,
			double deadlineTime) {
		super();
		this.processingTime = processingTime;
		this.startTime = startTime;
		this.deadlineTime = deadlineTime;
		}
	public void create()
	{
//		System.out.println("Job created : " + this.getStartTime());
		if(state==null)
			state = JobState.CREATED;
	
	
	}

	public void enqueue() {
//		System.out.println("enqueue");
		if (state == JobState.CREATED)
			state = JobState.SCHEDULED;
	}

	public boolean expire() {
//		System.out.println("expire");
		if (state == JobState.SCHEDULED) {
			state = JobState.EXPIRED;
			return true;
		}
		return false;
	}

	public void finish() {
//		System.out.println("finish");
		if (state == JobState.PROCESSING)
		{
			state = JobState.FINISHED;
			SimulationStatistics.getInstance().incrementFinished();
		}
	}

	public void block() {
//		System.out.println("block");
		if (state == JobState.CREATED)
		{
			state = JobState.BLOCKED;
			SimulationStatistics.getInstance().incrementBlocked();
		}
	}

	public void startProcess() {
//		System.out.println("Process");
		if(state==JobState.SCHEDULED)
			state = JobState.PROCESSING;
	}

	public double getProcessingTime() {
		return processingTime;
	}



	public double getStartTime() {
		return startTime;
	}

	public double getDeadlineTime() {
		return deadlineTime;
	}

}
