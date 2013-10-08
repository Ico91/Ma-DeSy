package madesy.storage;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import madesy.model.Event;

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
	
	
}
