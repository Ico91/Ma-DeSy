package madesy.storage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import madesy.model.Event;

/**
 * Contains a list of events for all worker threads activities
 * on the picking storage.
 */
public class EventLog {
	private List<Event> eventLog = new CopyOnWriteArrayList<Event>();;
	
	public EventLog() {
	}
	
	public void add(Event event) {
		this.eventLog.add(event);
	}

	/**
	 * Returns a list of events based on the arguments for
	 * from and to dates.
	 * @param fromDate
	 * @param toDate
	 * @return List of {@link madesy.model.Event}
	 */
	public List<Event> getEvents(Date fromDate, Date toDate) {
		List<Event> eventsForPeriod = new ArrayList<Event>();
		
		for (Event event : eventLog) {
			if ((event.getDate().after(fromDate) && event.getDate().before(
					toDate))
					|| event.getDate().equals(fromDate)
					|| event.getDate().equals(toDate)) {
				eventsForPeriod.add(event);

			}
		}
		
		return eventsForPeriod;
	}
	
	@Override
	public String toString() {
		return "EventLog [eventLog=" + eventLog + "]";
	}
	
	public List<Event> getEvents() {
		return eventLog;
	}

}
