package simulator;

public class Job {
	JobState state;
	double processingTime;
	double creationTime;
	double startTime;
	double deadlineTime;

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

	}

	public double getProcessingTime() {
		return processingTime;
	}

	public double getCreationTime() {
		return creationTime;
	}

	public double getStartTime() {
		return startTime;
	}

	public double getDeadlineTime() {
		return deadlineTime;
	}

}
