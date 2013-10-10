package madesy.storage;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

import madesy.model.Event;
import madesy.model.types.EventType;

/**
 * Contains a list of events for all worker threads activities on the picking
 * storage.
 */
public class EventLog {
	private List<Event> eventLog = new CopyOnWriteArrayList<Event>();

	public EventLog() {
	}

	public void add(Event event) {
		this.eventLog.add(event);
	}

	/**
	 * Returns a list of events based on the arguments for from and to dates.
	 * 
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

	public List<Event> getEvents(EventType eventType, Date fromDate, Date toDate) {
		List<Event> eventsForPeriod = new ArrayList<Event>();

		for (Event event : eventLog) {
			if (event.getEventType() == eventType
					&& (event.getDate().after(fromDate) && event.getDate()
							.before(toDate))
					|| event.getDate().equals(fromDate)
					|| event.getDate().equals(toDate)) {
				eventsForPeriod.add(event);

			}
		}
		return eventsForPeriod;
	}

	public Set<String> getCouriersId() {
		Set<String> couriersId = new HashSet<String>();
		for (int i = 0; i < eventLog.size(); i++) {
			String[] data = eventLog.get(i).getMetaData().split(",");
			if (data.length > 1)
				couriersId.add(data[1]);
		}
		return couriersId;
	}

	@Override
	public String toString() {
		return "EventLog [eventLog=" + eventLog + "]";
	}

	public List<Event> getEvents() {
		return eventLog;
	}

}
