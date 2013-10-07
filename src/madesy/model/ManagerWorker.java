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
public class ManagerWorker extends BaseWorker {
	private static final String TOO_MANY_NEW = "Too many new pickings";
	private static final String TOO_MANY_DISPACHED = "Too many dispached pickings";
	private static final String TOO_MANY_TAKEN = "Too many taken pickings";

	public ManagerWorker(PickingStorage pickingStorage) {
		super(UUID.randomUUID().toString(), pickingStorage);
	}

	@Override
	public void doWork() {
		generateReports(getCountOfAllPickingStates());
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
