package madesy.model;

import madesy.storage.PickingStorage;

public class Courrier extends Person {

	public Courrier(String id, PickingStorage pickingStorage) {
		super(id, pickingStorage);
	}

	@Override
	public void doWork() {
		dispatchPicking();
	}

	private void dispatchPicking() {
		Picking dispatchedPicking = pickingStorage.pickingToDispatch();
		if (dispatchedPicking == null)
			return;
		threadToSleep(2000);
		takePicking(dispatchedPicking);
	}

	private void takePicking(Picking picking) {
		pickingStorage.markPickingTaken(picking);
	}

}
