package madesy.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import madesy.storage.EventLog;

/**
 * Class used to generate reports, based on status of all available in
 * PickingStorage pickings
 * 
 * @author Krasimir Atanasov
 * 
 */
public class ManagerWorker extends BaseWorker {
	private EventLog eventLog;

	/*
	 * private static final String TOO_MANY_NEW = "Too many new pickings";
	 * private static final String TOO_MANY_DISPACHED =
	 * "Too many dispached pickings"; private static final String TOO_MANY_TAKEN
	 * = "Too many taken pickings";
	 */
	public ManagerWorker(String id, EventLog eventLog) {
		super(id);
		this.eventLog = eventLog;
	}

	@Override
	public void doWork() {
		// generateReports(getCountOfAllPickingStates());
	}

	public String getEventsForPeriod(Date fromDate, Date toDate) {
		List<Event> eventsForPeriod = new ArrayList<Event>();
		Set<String> courriersId = new HashSet<String>();

		for (Event event : eventLog.getEventLog()) {
			if ((event.getDate().after(fromDate) && event.getDate().before(
					toDate))
					|| event.getDate().equals(fromDate)
					|| event.getDate().equals(toDate)) {
				eventsForPeriod.add(event);
				String[] data = analizeMetaData(event.getMetaData());
				courriersId.add(data[1]);
			}
		}
		if (!eventsForPeriod.isEmpty()) {
			//makeReportForPicking(eventsForPeriod);
		}

		return null;
	}

/*/private String makeReportForPicking(List<Event> events) {
		Map<EventType, Integer> countOfEventType = new HashMap<EventType, Integer>(); 
		
		countOfEventType.put(EventType, 0);
		countOfEventType.put(PickingStatus.DISPATCHED, 0);
		countOfEventType.put(PickingStatus.TAKEN, 0);
				  
				 for (Event e : events) { EventType state =
				 e.getEventType(); int count = countOfPickingStates.get(state) + 1;
				  
				  countOfPickingStates.put(state, count); 
		return null;
	}*/

	private String[] analizeMetaData(String metaData) {
		String[] data = metaData.split(",");

		return data;
	}

	/*
	 * private void logReport(String report) { System.out.println(report); }
	 * 
	 * private void generateReports( Map<PickingStatus, Integer>
	 * countOfPickingStates) { int newPickings =
	 * countOfPickingStates.get(PickingStatus.NEW); int dispachedPickings =
	 * countOfPickingStates .get(PickingStatus.DISPATCHED); int takenPickings =
	 * countOfPickingStates.get(PickingStatus.TAKEN);
	 * 
	 * logReport("NEW: " + newPickings + " DISPACHED: " + dispachedPickings +
	 * " TAKEN: " + takenPickings); }
	 * 
	 * private Map<PickingStatus, Integer> getCountOfAllPickingStates() {
	 * Map<PickingStatus, Integer> countOfPickingStates = new
	 * HashMap<PickingStatus, Integer>(); List<Picking> listOfPickings =
	 * this.pickingStorage.getPickings();
	 * 
	 * countOfPickingStates.put(PickingStatus.NEW, 0);
	 * countOfPickingStates.put(PickingStatus.DISPATCHED, 0);
	 * countOfPickingStates.put(PickingStatus.TAKEN, 0);
	 * 
	 * for (Picking p : listOfPickings) { PickingStatus state =
	 * p.getPickingStates(); int count = countOfPickingStates.get(state) + 1;
	 * 
	 * countOfPickingStates.put(state, count); }
	 * 
	 * return countOfPickingStates; }
	 */

}
