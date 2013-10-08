package madesy;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import madesy.model.ClientWorker;
import madesy.model.CourrierWorker;
import madesy.model.ManagerWorker;
import madesy.storage.EventLog;
import madesy.storage.PickingStorage;

public class Simulation {
	public static void main(String[] args) {
		EventLog eventLog = new EventLog();
		PickingStorage pickingsStorage = new PickingStorage(eventLog);
		ExecutorService pool = Executors.newFixedThreadPool(10);
		
		pool.submit(new ClientWorker(UUID.randomUUID().toString(), pickingsStorage));
		pool.submit(new ClientWorker(UUID.randomUUID().toString(), pickingsStorage));
		pool.submit(new ClientWorker(UUID.randomUUID().toString(), pickingsStorage));
		pool.submit(new ClientWorker(UUID.randomUUID().toString(), pickingsStorage));
		pool.submit(new CourrierWorker(UUID.randomUUID().toString(), pickingsStorage));
		pool.submit(new CourrierWorker(UUID.randomUUID().toString(), pickingsStorage));
		pool.submit(new CourrierWorker(UUID.randomUUID().toString(), pickingsStorage));
		pool.submit(new CourrierWorker(UUID.randomUUID().toString(), pickingsStorage));
		pool.submit(new CourrierWorker(UUID.randomUUID().toString(), pickingsStorage));
		//pool.submit(new ManagerWorker(pickingsStorage));
		pool.submit(new ManagerWorker(UUID.randomUUID().toString(), eventLog));
		//pool.submit(new SimulationOverseer(eventLog, 5));
		
		pool.shutdown();
	}
}
