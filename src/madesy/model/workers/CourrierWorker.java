package madesy.model.workers;

import madesy.model.Picking;
import madesy.model.PickingService;
import madesy.storage.EventLog;
import madesy.storage.PickingStorage;

/**
 * Worker process used to simulate basic duties of a courier, which are
 * dispatching and taking a picking.
 */
public class CourrierWorker extends BaseWorker {
	private PickingService pickingService;

	public CourrierWorker(String id, PickingStorage pickingStorage,
			EventLog eventLog, int sleepTime) {
		super(id, sleepTime);
		this.pickingService = new PickingService(eventLog, pickingStorage);
	}

	@Override
	public void doWork() {
		Picking picking = pickingService.getPickingByCourierId(id);
		if (picking != null) {
			String pickingId = picking.getId();
			pickingService.setTaken(id, pickingId);
		}
	}

	public String getId() {
		return this.id;
	}

}
