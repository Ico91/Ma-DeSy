package madesy.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import madesy.storage.PickingStorage;

/**
 * Class used to generate reports, based on status of all available in
 * PickingStorage pickings
 * 
 * @author Krasimir Atanasov
 * 
 */
public class Manager extends Person {
	private static final String TOO_MANY_NEW = "Too many new pickings";
	private static final String TOO_MANY_DISPACHED = "Too many dispached pickings";
	private static final String TOO_MANY_TAKEN = "Too many taken pickings";

	public Manager(PickingStorage pickingStorage) {
		super(UUID.randomUUID().toString(), pickingStorage);
	}

	@Override
	public void run() {
		System.out.println("manager started");
		generateReports(getCountOfAllPickingStates());
		while (!Thread.currentThread().isInterrupted()) {
			generateReports(getCountOfAllPickingStates());
			Random rand = new Random();

			try {
				Thread.sleep(rand.nextInt(1000) + 1000);
			} catch (InterruptedException e) {
				System.out.println("Manager is interrupted.");
				return;
			}
		}
	}

	private void logReport(String report) {
		System.out.println(report);
	}

	private void generateReports(
			Map<PickingStates, Integer> countOfPickingStates) {
		int newPickings = countOfPickingStates.get(PickingStates.NEW);
		int dispachedPickings = countOfPickingStates
				.get(PickingStates.DISPATCHED);
		int takenPickings = countOfPickingStates.get(PickingStates.TAKEN);

		logReport("NEW: " + newPickings + " DISPACHED: " + dispachedPickings
				+ " TAKEN: " + takenPickings);
	}

	private Map<PickingStates, Integer> getCountOfAllPickingStates() {
		Map<PickingStates, Integer> countOfPickingStates = new HashMap<PickingStates, Integer>();
		List<Picking> listOfPickings = this.pickingStorage.getPickings();

		countOfPickingStates.put(PickingStates.NEW, 0);
		countOfPickingStates.put(PickingStates.DISPATCHED, 0);
		countOfPickingStates.put(PickingStates.TAKEN, 0);

		for (Picking p : listOfPickings) {
			PickingStates state = p.getPickingStates();
			int count = countOfPickingStates.get(state) + 1;

			countOfPickingStates.put(state, count);
		}

		return countOfPickingStates;
	}

}
