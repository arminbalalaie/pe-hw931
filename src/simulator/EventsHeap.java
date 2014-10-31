package simulator;

import java.util.Comparator;
import java.util.PriorityQueue;

import events.EndProcessEvent;
import events.Event;
import events.ExpireJobEvent;

public class EventsHeap {
	private PriorityQueue<Event> events;

	public EventsHeap(int initialCapacity) {
		events = new PriorityQueue<Event>(initialCapacity, new Comparator<Event>() {

			@Override
			public int compare(Event o1, Event o2) {
				if (o1.getTriggerTime() > (o2.getTriggerTime()))
					return 1;
				else if (o1.getTriggerTime() < (o2.getTriggerTime()))
					return -1;
				else {
					if (o1 instanceof ExpireJobEvent
							|| o1 instanceof EndProcessEvent)
						return -1;
					else if (o2 instanceof ExpireJobEvent
							|| o2 instanceof EndProcessEvent)
						return 1;
					return 1;
				}
			}
		});
	}

	public void pushToEvents(Event event) {
		events.add(event);
	}

	public Event popFromEvents() {
		return events.remove();
	}

}
