package madesy;

import java.util.List;
import java.util.concurrent.ExecutorService;

import madesy.model.Event;
import madesy.model.types.EventType;
import madesy.model.workers.BaseWorker;
import madesy.storage.EventLog;

public class SimulationOverseer extends BaseWorker {
	private EventLog eventLog;
	private int terminationCount;
	private ExecutorService service;

	public SimulationOverseer(ExecutorService service, EventLog eventLog, int terminationCount, int sleepTime) {
		super(sleepTime);
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
		{
			service.shutdownNow();
			System.out.println("Terminated.");
		}
	}
	

}
