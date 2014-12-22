package simulator;

import java.util.Random;
import random.ConstantGenerator;
import random.ExponentialGenerator;
import random.RandomGenerator;
import events.CreateJobEvent;
import events.Event;

public class Simulator {
	private int[] queueSizes = {9, 11};
	private int queueCount = 2;
	private JobQueue[] jobQueues;
	private EventsHeap eventsHeap;
	private double lambda;
	private double meanWaitTime = 2;
	private double serviceTimeAverage = 1;
	private boolean isExponentialDeadline;
	private int population;
	private int totalJobCreated = 0;
	private double clock;
	private RandomGenerator watingRandomGenerator;
	private RandomGenerator processingRandomGenerator;
	private RandomGenerator arrivalRandomGenerator;

	public Simulator(int population, boolean isExponential) {
		this.population = population;
		isExponentialDeadline = isExponential;
		jobQueues = new JobQueue[queueCount];
		for(int i=0;i<queueCount;i++)
			jobQueues[i] = new JobQueue(queueSizes[i],1);
		this.eventsHeap = new EventsHeap(10000);
		this.clock = 0;

	}
	
	public boolean isQueueAvailable()
	{
		for(int i=0;i<queueCount;i++)
			if(!jobQueues[i].isFull())
				return true;
		return false;
	}
	
	public JobQueue getAvailableQueue()
	{
		JobQueue ret = null;
		int maxCapacity = 0;
		
		if(!jobQueues[1].isFull() && jobQueues[0].getQueueSize()>jobQueues[1].getQueueSize())
		{
			return jobQueues[1];
		}
		else if(!jobQueues[0].isFull() && jobQueues[0].getQueueSize()<jobQueues[1].getQueueSize())
		{
			return jobQueues[0];
		}
		else{
			Random r = new Random();
			double rand = r.nextDouble();
//			System.out.println(rand);
			if(rand > 0.5)
				return jobQueues[1];
			else
				return jobQueues[0];
		}
	}
	
	public JobQueue getJobQueue(int num) {
		return jobQueues[num];
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
		if (totalJobCreated >= population)
			return null;
		double processingTime = this.processingRandomGenerator.generate();
		double startTime = clock + this.arrivalRandomGenerator.generate();
		double deadlineTime = startTime + this.watingRandomGenerator.generate();
		Job newJob = new Job(processingTime, startTime, deadlineTime);
		totalJobCreated++;
		return newJob;
	}

	public void resetJobGenerator() {
		totalJobCreated = 0;
	}

	public void initRandomGenerators() {
		if (this.isExponentialDeadline)
			watingRandomGenerator = new ExponentialGenerator(
					1 / this.meanWaitTime);
		else
			watingRandomGenerator = new ConstantGenerator(this.meanWaitTime);
		processingRandomGenerator = new ExponentialGenerator(
				1 / this.serviceTimeAverage);
		arrivalRandomGenerator = new ExponentialGenerator(this.lambda);
	}

	public void simulate(ErrorCalculator blockError,
			ErrorCalculator expiredError) {
		this.initRandomGenerators();
		SimulationStatistics.getInstance().reset();
		Event firstEvent = new CreateJobEvent(this, null, clock);
		eventsHeap.pushToEvents(firstEvent);
		while (eventsHeap.hasEvents()) {
			Event event = eventsHeap.popFromEvents();
			clock = event.getTriggerTime();
			event.doIt();
		}

//		Analytical anal = isExponentialDeadline ? new ExponentialAnalytical(
//				queueSize + serverCount, lambda, 2) : new ConstantAnalytical(
//				queueSize + serverCount, lambda, 2);
//		double analyticBlockingProbability = anal.pN(queueSize+serverCount);
//		double analyticExpirationProbability = anal.pD();
		double simulationBlockingProbability = SimulationStatistics
				.getInstance().getBlockingProbability();
		double simulationExpirationProbability1 = SimulationStatistics
				.getInstance().getExpiredProbability(jobQueues[0]);
		double simulationExpirationProbability2 = SimulationStatistics
				.getInstance().getExpiredProbability(jobQueues[1]);
//		// calculate error
//		blockError.addError(Math.abs(simulationBlockingProbability
//				- analyticBlockingProbability));
//		expiredError.addError(Math.abs(simulationExpirationProbability
//				- analyticExpirationProbability));
//		// print statistics
		System.out.printf("%2.1f\t%.4f\t%.4f\t%.4f\t\n", lambda,
				simulationBlockingProbability, simulationExpirationProbability1,simulationExpirationProbability2);
		// System.out.println(lambda + "\t"
		// + SimulationStatistics.getInstance().getBlockingProbability()
		// + "\t\t\t"
		// + SimulationStatistics.getInstance().getDepartureProbability());
	}

	public double getClock() {
		return clock;
	}

}
