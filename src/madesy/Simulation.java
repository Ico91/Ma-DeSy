package madesy;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import madesy.model.Client;
import madesy.model.Courrier;
import madesy.model.Manager;
import madesy.storage.PickingStorage;

public class Simulation {
	public static void main(String[] args) {
		PickingStorage pickingsStorage = new PickingStorage();
		ExecutorService pool = Executors.newFixedThreadPool(10);
		
		pool.submit(new Client(UUID.randomUUID().toString(), "client1", pickingsStorage));
		pool.submit(new Courrier(UUID.randomUUID().toString(), "courrier", pickingsStorage));
		pool.submit(new Manager(pickingsStorage));
		
		
		
	}
}
