package simulator;

public class Job {
	JobState state;
	double processingTime;
	double creationTime;
	double startTime;
	double deadlineTime;
	
	public void enqueue()
	{}
	
	public void expire()
	{}
	
	public void finish()
	{}
	
	public void block()
	{}
}
