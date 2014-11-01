package simulator;

import analytic.Analytical;
import analytic.ConstantAnalytical;
import analytic.ExponentialAnalytical;
import random.ConstantGenerator;
import random.ExponentialGenerator;
import random.RandomGenerator;
import events.CreateJobEvent;
import events.Event;

public class Simulator {
	private int queueSize = 10;
	private JobQueue jobQueue;
	private EventsHeap eventsHeap;
	private int serverCount = 2;
	private int availableServers = 2;
	private double lambda;
	private double meanWaitTime = 2;
	private double serviceTimeAverage = 1;
	private boolean isExponentialDeadline;
	private int population;
	private int totalJobCreated=0;
	private double clock;
	private RandomGenerator watingRandomGenerator;
	private RandomGenerator processingRandomGenerator;
	private RandomGenerator arrivalRandomGenerator;

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
		if(totalJobCreated>=population)
			return null;
		double processingTime = this.processingRandomGenerator.generate();
		double startTime = clock + this.arrivalRandomGenerator.generate();
		double deadlineTime = startTime + this.watingRandomGenerator.generate();
		Job newJob = new Job(processingTime, startTime, deadlineTime);
		totalJobCreated++;
		return newJob;
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

	public void initRandomGenerators() {
		if (this.isExponentialDeadline)
			watingRandomGenerator = new ExponentialGenerator(1 / this.meanWaitTime);
		else
			watingRandomGenerator = new ConstantGenerator(this.meanWaitTime);
		processingRandomGenerator = new ExponentialGenerator(1 / this.serviceTimeAverage);
		arrivalRandomGenerator = new ExponentialGenerator(this.lambda);
	}

	public void simulate(ErrorCalculator blockError, ErrorCalculator expiredError) {
		this.initRandomGenerators();
		SimulationStatistics.getInstance().reset();
		Event firstEvent = new CreateJobEvent(this, null, clock);
		eventsHeap.pushToEvents(firstEvent);
		while (eventsHeap.hasEvents()) {
			Event event = eventsHeap.popFromEvents();
			clock = event.getTriggerTime();
			event.doIt();
		}

		Analytical anal = isExponentialDeadline?new ExponentialAnalytical(lambda, 2):new ConstantAnalytical(lambda, 2);
		double analyticBlockingProbability = anal.P_Blocked();
		double analyticExpirationProbability = anal.P_Deadline();
		double simulationBlockingProbability = SimulationStatistics.getInstance().getBlockingProbability();
		double simulationExpirationProbability = SimulationStatistics.getInstance().getExpiredProbability();
		//calculate error
		blockError.addError(Math.abs(simulationBlockingProbability-analyticBlockingProbability));
		expiredError.addError(Math.abs(simulationExpirationProbability-analyticExpirationProbability));
		// print statistics
		System.out.printf("%2.1f\t%.5f\t%.5f\t%.5f\t%.5f\t\n", lambda, 
				simulationBlockingProbability, simulationExpirationProbability,
				analyticBlockingProbability, analyticExpirationProbability);
//		System.out.println(lambda + "\t"
//				+ SimulationStatistics.getInstance().getBlockingProbability()
//				+ "\t\t\t"
//				+ SimulationStatistics.getInstance().getDepartureProbability());
	}

	public double getClock() {
		return clock;
	}

}
