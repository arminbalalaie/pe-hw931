package simulator;

import events.CreateJobEvent;
import events.Event;

public class Simulator {
	private int queueSize = 12;
	private JobQueue jobQueue;
	private EventsHeap eventsHeap;
	private int serverCount=2;
	private int availableServers=2;
	private double lambda;
	private double serviceTimeAverage=1;
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

	public JobQueue getJobQueue() {
		return jobQueue;
	}

	public EventsHeap getEventsHeap() {
		return eventsHeap;
	}

	public double getLambda() {
		return lambda;
	}

	public void setLambda(double lambda) {
		this.lambda = lambda;
	}

	public Job generateNewJob() {
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
		SimulationStatistics.getInstance().reset();
		Event firstEvent = new CreateJobEvent(this, null, clock);
		eventsHeap.pushToEvents(firstEvent);
		while(eventsHeap.hasEvents())
		{
			Event event = eventsHeap.popFromEvents();
			clock = event.getTriggerTime();
			event.doIt();
		}
		
		//print statistics
		System.out.println(lambda + "\t" + SimulationStatistics.getInstance().getBlockingProbability() +  "\t" + SimulationStatistics.getInstance().getDepartureProbability());
	}

	public double getClock() {
		return clock;
	}

}
