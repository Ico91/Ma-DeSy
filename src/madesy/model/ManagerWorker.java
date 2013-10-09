package madesy.model;

import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import madesy.builder.EventBuilder;
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
	private Set<String> courriersId;

	public ManagerWorker(EventLog eventLog, int sleepTime) {
		super(sleepTime);
		this.eventLog = eventLog;
		courriersId = new HashSet<String>();
	}

	@Override
	public void doWork() {
		System.out.println(eventLog);
		makeReportForPickings();

	}

	private void makeReportForPickings() {
		Calendar fromDate = Calendar.getInstance();
		Calendar toDate = Calendar.getInstance();
		fromDate.set(2013, 9, 9, 0, 0, 0);
		toDate.set(2013, 9, 10, 0, 0, 0);

		Report report = new Report(UUID.randomUUID().toString(),
				fromDate.getTime(), toDate.getTime());
		List<Event> events = eventLog.getEventsFromPeriod(fromDate.getTime(),
				toDate.getTime());

		Map<EventType, Integer> countOfEventType = countEvents(events);

		if (countOfEventType.get(EventType.NEW_PICKING) > 2 * countOfEventType
				.get(EventType.DISPATCH_PICKING))
			report.addReportElement(ReportType.TOO_MANY_NEW_PICKINGS.getValue());
		else
			report.addReportElement(ReportType.ENOUGH_NEW_PICKINGS.getValue());
		if (countOfEventType.get(EventType.DISPATCH_PICKING) > 2 * countOfEventType
				.get(EventType.TAKE_PICKING))
			report.addReportElement(ReportType.DISPATCH_DELAYED.getValue());
		else
			report.addReportElement(ReportType.DISPATCH_PROPERLY.getValue());

		report.getCourrierInfo().putAll(makeReportForCourriers(events));
		System.out.println();
		System.out.println(report);

		Event managerEvent = new EventBuilder()
				.addEvent(EventType.MANAGER_REPORT).addMetaData(report.getId())
				.build();
		eventLog.addEvent(managerEvent);

	}

	private Map<String, Integer> makeReportForCourriers(List<Event> events) {
		Map<String, Integer> countCourrierPickings = new HashMap<String, Integer>();
		for (String id : courriersId) {
			countCourrierPickings.put(id, 0);
		}

		for (Event e : events) {
			if (e.getEventType() == EventType.TAKE_PICKING) {
				for (String id : countCourrierPickings.keySet()) {
					if (analizeMetaData(e.getMetaData())[1].equals(id)) {
						int count = countCourrierPickings.get(id) + 1;
						countCourrierPickings.put(id, count);
					}
				}
			}
		}

		return countCourrierPickings;
	}

	private Map<EventType, Integer> countEvents(List<Event> events) {
		Map<EventType, Integer> countOfEventType = new HashMap<EventType, Integer>();

		countOfEventType.put(EventType.NEW_PICKING, 0);
		countOfEventType.put(EventType.DISPATCH_PICKING, 0);
		countOfEventType.put(EventType.TAKE_PICKING, 0);

		for (Event e : events) {
			EventType state = e.getEventType();
			if (state == EventType.NEW_PICKING
					|| state == EventType.DISPATCH_PICKING
					|| state == EventType.TAKE_PICKING) {
				int count = countOfEventType.get(state) + 1;
				countOfEventType.put(state, count);

				if (state != EventType.NEW_PICKING) {
					String data = analizeMetaData(e.getMetaData())[1];
					courriersId.add(data);
				}

			}
		}
		return countOfEventType;
	}

	private String[] analizeMetaData(String metaData) {
		String[] data = metaData.split(",");

		return data;
	}

}
