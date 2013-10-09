package madesy.model.workers;

import madesy.model.Picking;
import madesy.storage.PickingStorage;

public class CourrierWorker extends BaseWorker {
	private PickingStorage pickingStorage;

	public CourrierWorker(PickingStorage pickingStorage, int sleepTime) {
		super(sleepTime);
		this.pickingStorage = pickingStorage;
	}

	@Override
	public void doWork() {
		dispatechPicking();
	}

	private void dispatechPicking() {
		Picking dispatchedPicking = pickingStorage.pickingToDispatch(id);
		if (dispatchedPicking == null)
			return;
		threadToSleep();
		takePicking(dispatchedPicking);
	}

	private void takePicking(Picking picking) {
		pickingStorage.markPickingTaken(picking, id);
	}
	
	public PickingStorage getPickingStorage() {
		return pickingStorage;
	}

	public void setPickingStorage(PickingStorage pickingStorage) {
		this.pickingStorage = pickingStorage;
	}

}
