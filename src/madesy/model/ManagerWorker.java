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
			Map<PickingStatus, Integer> countOfPickingStates) {
		int newPickings = countOfPickingStates.get(PickingStatus.NEW);
		int dispachedPickings = countOfPickingStates
				.get(PickingStatus.DISPATCHED);
		int takenPickings = countOfPickingStates.get(PickingStatus.TAKEN);

		logReport("NEW: " + newPickings + " DISPACHED: " + dispachedPickings
				+ " TAKEN: " + takenPickings);
	}

	private Map<PickingStatus, Integer> getCountOfAllPickingStates() {
		Map<PickingStatus, Integer> countOfPickingStates = new HashMap<PickingStatus, Integer>();
		List<Picking> listOfPickings = this.pickingStorage.getPickings();

		countOfPickingStates.put(PickingStatus.NEW, 0);
		countOfPickingStates.put(PickingStatus.DISPATCHED, 0);
		countOfPickingStates.put(PickingStatus.TAKEN, 0);

		for (Picking p : listOfPickings) {
			PickingStatus state = p.getPickingStates();
			int count = countOfPickingStates.get(state) + 1;

			countOfPickingStates.put(state, count);
		}

		return countOfPickingStates;
	}

}
