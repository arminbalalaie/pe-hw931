package simulator;

public class Job {
	JobState state;
	double processingTime;
	double creationTime;
	double startTime;
	double deadlineTime;
	
	public void enqueue()
	{
		if(state==JobState.CREATED)
			state = JobState.SCHEDULED;
	}
	
	public void expire()
	{
		if(state==JobState.SCHEDULED)
			state = JobState.EXPIRED;
	}
	
	public void finish()
	{
		if(state==JobState.PROCESSING)
			state = JobState.FINISHED;
	}
	
	public void block()
	{
		if(state==JobState.CREATED)
			state = JobState.BLOCKED;
	}
}
