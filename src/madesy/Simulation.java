package madesy;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import madesy.model.ClientWorker;
import madesy.model.CourrierWorker;
import madesy.model.ManagerWorker;
import madesy.storage.PickingStorage;

public class Simulation {
	public static void main(String[] args) {
		PickingStorage pickingsStorage = new PickingStorage();
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
		
		pool.shutdown();
	}
}
