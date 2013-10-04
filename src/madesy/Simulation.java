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
		
		Thread client1 = new Thread(new Client(UUID.randomUUID().toString(), "client1", pickingsStorage));
		Thread client2 = new Thread(new Client(UUID.randomUUID().toString(), "client2", pickingsStorage));
		Thread client3 = new Thread(new Client(UUID.randomUUID().toString(), "client3", pickingsStorage));
		Thread client4 = new Thread(new Client(UUID.randomUUID().toString(), "client4", pickingsStorage));
	
		Thread courrier1 = new Thread(new Courrier(UUID.randomUUID().toString(), "courrier1", pickingsStorage));
		Thread courrier2 = new Thread(new Courrier(UUID.randomUUID().toString(), "courrier2", pickingsStorage));
		Thread courrier3 = new Thread(new Courrier(UUID.randomUUID().toString(), "courrier3", pickingsStorage));
		Thread courrier4 = new Thread(new Courrier(UUID.randomUUID().toString(), "courrier4", pickingsStorage));
		Thread courrier5 = new Thread(new Courrier(UUID.randomUUID().toString(), "courrier5", pickingsStorage));
		
		Thread manager = new Thread(new Manager(pickingsStorage));
		System.out.println("Before interruption");
		pool.submit(client1);
		pool.submit(client2);
		pool.submit(client3);
		pool.submit(client4);
		pool.submit(courrier1);
		pool.submit(courrier2);
		pool.submit(courrier3);
		pool.submit(courrier4);
		pool.submit(courrier5);
		pool.submit(manager);
		
		if (client1.isInterrupted()) {
			System.out.println(client1.getName() + " is interrupted");
		}
		
		pool.shutdown();
	}
}
