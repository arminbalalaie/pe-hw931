package simulator;


public class Job {
	JobState state=null;
	double processingTime;
//	double creationTime;
	double startTime;
	double deadlineTime;
	private JobQueue queue;

	
	public Job(double processingTime, double startTime,
			double deadlineTime) {
		super();
		this.processingTime = processingTime;
		this.startTime = startTime;
		this.deadlineTime = deadlineTime;
		queue = null;
	}
	
	public JobQueue getQueue() {
		return queue;
	}
	public void setQueue(JobQueue queue) {
		this.queue = queue;
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
			SimulationStatistics.getInstance().incrementExpired(queue);
			return true;
		}
		return false;
	}

	public void finish() {
//		System.out.println("finish");
		if (state == JobState.PROCESSING)
		{
			state = JobState.FINISHED;
			SimulationStatistics.getInstance().incrementFinished(queue);
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
