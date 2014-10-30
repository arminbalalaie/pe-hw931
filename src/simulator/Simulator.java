package simulator;

public class Simulator {
	private int queueSize = 12;
	private JobQueue jobQueue;
	private EventsHeap eventsHeap;
	private int serverCount;
	private int availableServers;
	private double lambda;
	private double serviceTimeAverage;
	private boolean isExponentialDeadline;
	private int population;
	private int totalJobCreated;
	private double clock;

	public Simulator(int population, boolean isExponential) {
		this.population = population;
		isExponentialDeadline = isExponential;
		jobQueue = new JobQueue(queueSize);
		this.eventsHeap = new EventsHeap(10000);
	}

	public double getLambda() {
		return lambda;
	}

	public void setLambda(double lambda) {
		this.lambda = lambda;
	}

	private Job generateNewJob() {
		return null;
	}

	public void occupyServer() {
		if (availableServers > 0)
			availableServers--;
	}

	public void releaseServer() {
		if (availableServers < serverCount)
			availableServers++;
	}

	public boolean isServerAvailable() {
		return availableServers > 0 ? true : false;
	}

	public void resetJobGenerator() {
		totalJobCreated = 0;
	}

	public void simulate() {

	}

	public double getClock() {
		return clock;
	}

}
