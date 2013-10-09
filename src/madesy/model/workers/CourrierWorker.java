package madesy.model.workers;

import madesy.model.Picking;
import madesy.storage.PickingStorage;

/**
 * Worker process used to simulate basic duties of a courier,
 * which are dispatching and taking a picking.
 */
public class CourrierWorker extends BaseWorker {
	private PickingStorage pickingStorage;

	public CourrierWorker(PickingStorage pickingStorage, int sleepTime) {
		super(sleepTime);
		this.pickingStorage = pickingStorage;
	}

	@Override
	public void doWork() {
		dispatchPicking();
	}

	/**
	 * If available, gets a picking from the picking storage and 
	 * sleeps for a random period of time, to simulate a real-time
	 * delivering, after which invokes the method to mark the picking
	 * as taken.
	 */
	private void dispatchPicking() {
		Picking dispatchedPicking = pickingStorage.pickingToDispatch(id);
		if (dispatchedPicking == null)
			return;
		threadToSleep();
		takePicking(dispatchedPicking);
	}

	/**
	 * Marks the passed as argument picking as taken. 
	 * @param picking
	 */
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
