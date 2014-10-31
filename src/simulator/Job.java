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
		if(state==null)
			state = JobState.CREATED;
	}
	
	}

	public void enqueue() {
		if (state == JobState.CREATED)
			state = JobState.SCHEDULED;
	}

	public boolean expire() {
		if (state == JobState.SCHEDULED) {
			state = JobState.EXPIRED;
			return true;
		}
		return false;
	}

	public void finish() {
		if (state == JobState.PROCESSING)
			state = JobState.FINISHED;
	}

	public void block() {
		if (state == JobState.CREATED)
			state = JobState.BLOCKED;
	}

	public void startProcess() {
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
