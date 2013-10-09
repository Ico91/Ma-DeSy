package madesy.model;

import madesy.storage.PickingStorage;

public class CourrierWorker extends BaseWorker {
	private PickingStorage pickingStorage;

	public CourrierWorker(String id, PickingStorage pickingStorage) {
		super(id);
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
