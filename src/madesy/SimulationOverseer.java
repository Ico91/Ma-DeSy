package madesy;

import java.util.List;
import java.util.concurrent.ExecutorService;

import madesy.model.BaseWorker;
import madesy.model.Event;
import madesy.model.EventType;
import madesy.storage.EventLog;

public class SimulationOverseer extends BaseWorker {
	private EventLog eventLog;
	private int terminationCount;
	private ExecutorService service;

	public SimulationOverseer(String id, EventLog eventLog, int terminationCount, ExecutorService service) {
		super(id);
		this.eventLog = eventLog;
		this.terminationCount = terminationCount;
		this.service = service;
	}

	private boolean checkForTermination() {
		List<Event> eventsList = eventLog.getEventLog();
		int count = 0;
		for(Event e : eventsList) {
			if(e.getEventType() == EventType.MANAGER_REPORT) {
				count++;
			}
		}
		
		if (count >= terminationCount) {
			return true;
		}

		return false;
	}

	@Override
	public void doWork() {
		if(checkForTermination())
			service.shutdownNow();
	}
	

}
