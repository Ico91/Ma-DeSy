package madesy;

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
		ExecutorService pool = Executors.newFixedThreadPool(11);
		
		ManagerWorker manager = new ManagerWorker(eventLog, 5000);
		
		pool.submit(new ClientWorker(pickingsStorage, 1000));
		pool.submit(new ClientWorker(pickingsStorage, 1500));
		pool.submit(new ClientWorker(pickingsStorage, 2000));
		pool.submit(new ClientWorker(pickingsStorage, 2500));
		pool.submit(new CourrierWorker(pickingsStorage, 3000));
		pool.submit(new CourrierWorker(pickingsStorage, 2500));
		pool.submit(new CourrierWorker(pickingsStorage, 2000));
		pool.submit(new CourrierWorker(pickingsStorage, 1500));
		pool.submit(new CourrierWorker(pickingsStorage, 1000));

		pool.submit(manager);
		pool.submit(new SimulationSupervisor(pool, eventLog, 15, 5000));
		
	}
}
