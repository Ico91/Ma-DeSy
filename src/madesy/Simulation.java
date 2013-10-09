package madesy;

import java.util.Calendar;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import madesy.model.workers.ClientWorker;
import madesy.model.workers.CourrierWorker;
import madesy.model.workers.ManagerWorker;
import madesy.storage.EventLog;
import madesy.storage.PickingStorage;

public class Simulation {
	public static void main(String[] args) {
		EventLog eventLog = new EventLog();
		PickingStorage pickingsStorage = new PickingStorage(eventLog);
		ExecutorService pool = Executors.newFixedThreadPool(12);
		
		ManagerWorker manager = new ManagerWorker(eventLog, 5000);
		Calendar fromDate = Calendar.getInstance();
		Calendar toDate = Calendar.getInstance();
		fromDate.set(2013, 9, 9, 0, 0, 0);
		toDate.set(2013, 9, 10, 0, 0, 0);
		manager.setFromDate(fromDate.getTime());
		manager.setToDate(toDate.getTime());
		
		pool.submit(new ClientWorker(pickingsStorage, 100));
		pool.submit(new ClientWorker(pickingsStorage, 750));
		pool.submit(new ClientWorker(pickingsStorage, 500));
		pool.submit(new ClientWorker(pickingsStorage, 1000));
		pool.submit(new ClientWorker(pickingsStorage, 500));
		pool.submit(new CourrierWorker(pickingsStorage, 3000));
		pool.submit(new CourrierWorker(pickingsStorage, 2500));
		pool.submit(new CourrierWorker(pickingsStorage, 2000));
		pool.submit(new CourrierWorker(pickingsStorage, 4500));
		pool.submit(new CourrierWorker(pickingsStorage, 3500));

		pool.submit(manager);
		pool.submit(new SimulationOverseer(pool, eventLog, 5, 5000));
		
	}
}
