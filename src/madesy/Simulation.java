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
		ExecutorService pool = Executors.newFixedThreadPool(12);
		
		ManagerWorker manager = new ManagerWorker(eventLog, 5000);
		
		pool.submit(new ClientWorker(100));
		pool.submit(new ClientWorker(750));
		pool.submit(new ClientWorker(500));
		pool.submit(new ClientWorker(1000));
		pool.submit(new ClientWorker(500));
		pool.submit(new CourrierWorker(3000));
		pool.submit(new CourrierWorker(2500));
		pool.submit(new CourrierWorker(2000));
		pool.submit(new CourrierWorker(4500));
		pool.submit(new CourrierWorker(3500));

		pool.submit(manager);
		pool.submit(new SimulationSupervisor(pool, eventLog, 5, 5000));
		
	}
}
