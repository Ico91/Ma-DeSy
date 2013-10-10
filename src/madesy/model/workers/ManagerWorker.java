package madesy.model.workers;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import madesy.model.Event;
import madesy.model.Events;
import madesy.model.Report;
import madesy.model.types.EventType;
import madesy.model.types.ReportType;
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
	private Date fromDate;

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	private Date toDate;

	public ManagerWorker(EventLog eventLog, int sleepTime) {
		super(sleepTime);
		this.eventLog = eventLog;
		courriersId = new HashSet<String>();
	}

	@Override
	public void doWork() {
		System.out.println();
		System.out.println(eventLog);
		Report report = new Report(UUID.randomUUID().toString(), fromDate,
				toDate);
		List<Event> events = eventLog.getEvents(fromDate, toDate);
		
		report.getReport().addAll(makeReportForPickings(events));
		report.getCourrierInfo().putAll(makeReportForCourriers(events));
		
		addToEventLog(report);
		
		System.out.println(report);
	}

	/**
	 * Generates a report based on the status of the pickings.
	 * 
	 * @param events
	 * @return 
	 */
	private List<String> makeReportForPickings(List<Event> events) {
		List<String> reportList = new ArrayList<String>();
		Map<EventType, Integer> countOfEventType = countEvents(events);

		if (countOfEventType.get(EventType.NEW_PICKING) > 2 * countOfEventType
				.get(EventType.DISPATCH_PICKING))
			reportList.add(ReportType.TOO_MANY_NEW_PICKINGS.getValue());
		else
			reportList.add(ReportType.ENOUGH_NEW_PICKINGS.getValue());
		if (countOfEventType.get(EventType.DISPATCH_PICKING) > 2 * countOfEventType
				.get(EventType.TAKE_PICKING))
			reportList.add(ReportType.DISPATCH_DELAYED.getValue());
		else
			reportList.add(ReportType.DISPATCH_PROPERLY.getValue());
		
		return reportList;

	}

	/**
	 * Generates report consisting of information based on the
	 * work done by the couriers.
	 * @param events
	 * @return Map, where key is the id of each courier and value
	 * the number of pickings delivered.
	 */
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
	
	/**
	 * Adds the report creation event to the event log.
	 * @param report
	 */
	private void addToEventLog(Report report) {
		eventLog.add(Events.managerReport(report.getId()));
	}

	/**
	 * Determines the number of pickings in each state
	 * @param events
	 * @return Map, where key is each state of the pickings,
	 * and value - their number.
	 */
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
