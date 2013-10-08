package madesy.model;

import madesy.storage.PickingStorage;

public class CourrierWorker extends BaseWorker {

	public CourrierWorker(String id, PickingStorage pickingStorage) {
		super(id, pickingStorage);
	}

	@Override
	public void doWork() {
		dispatechPicking();
	}

	private void dispatechPicking() {
		Picking dispatchedPicking = pickingStorage.pickingToDispatch(id);
		if (dispatchedPicking == null)
			return;
		threadToSleep(2000);
		takePicking(dispatchedPicking);
	}

	private void takePicking(Picking picking) {
		pickingStorage.markPickingTaken(picking, id);
	}

}
