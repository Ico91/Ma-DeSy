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
	private List<Event> eventLog;
	
	public EventLog() {
		eventLog = new CopyOnWriteArrayList<Event>();
	}
	
	public void addEvent(Event event) {
		this.eventLog.add(event);
	}

	@Override
	public String toString() {
		return "EventLog [eventLog=" + eventLog + "]";
	}

	public List<Event> getEventLog() {
		return eventLog;
	}
	
	/**
	 * Returns a list of events based on the arguments for
	 * from and to dates.
	 * @param fromDate
	 * @param toDate
	 * @return List of {@link madesy.model.Event}
	 */
	public List<Event> getEventsFromPeriod(Date fromDate, Date toDate) {
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
}
