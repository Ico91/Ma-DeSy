package madesy.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import madesy.model.Events;
import madesy.model.Picking;
import madesy.model.types.PickingStatus;

/**
 * Container for all pickings of all clients, providing
 * the necessary concurrent methods on it, for adding
 * a new picking and changing the status of a specified
 * one.
 *
 */
public class PickingStorage {
	private List<Picking> pickings = new ArrayList<Picking>();
	private EventLog eventLog;
	private final Lock lock = new ReentrantLock();
	
	public PickingStorage(EventLog eventLog) {
		this.eventLog = eventLog;
	}
	
	/**
	 * Adds the newly created picking in the collection,
	 * @param picking
	 */
	public void newPicking(final Picking picking) {
		new シンクロナイザー<Void>() {

			@Override
			Void execute() {
				pickings.add(picking);
				eventLog.add(Events.newPicking(picking.getId()));
				return null;
			}
		}.executeWithLock();
	}
	
	/**
	 * Returns the first available picking marked as NEW
	 * in order to be dispatched.
	 * @param courrierId - Id of the courier, requesting
	 * to dispatch a picking.
	 * @return If any exists, returns the first new picking.
	 */
	public Picking pickingToDispatch(final String courrierId) {
		return new シンクロナイザー<Picking>() {

			@Override
			Picking execute() {
				for(int i = 0; i < pickings.size(); i ++) {
					// get index of first picking marked as new
					if(pickings.get(i).getPickingStates() == PickingStatus.NEW) {
						pickings.get(i).setPickingStates(PickingStatus.DISPATCHED);
						eventLog.add(Events.dispachedPicking(pickings.get(i).getId(), courrierId));
						return pickings.get(i);
					}
				}
				
				return null;
			}
		}.executeWithLock();
	}
	
	/**
	 * Changes the status of a picking, which previously was
	 * marked as dispatched, to taken.
	 * @param picking - The picking which was delivered.
	 * @param courrierId - Id of the courier who delivered the picking.
	 */
	public void markPickingTaken(final Picking picking, final String courrierId) {
		new シンクロナイザー<Void>() {

			@Override
			Void execute() {
				int index = pickings.indexOf(picking);
				pickings.get(index).setPickingStates(PickingStatus.TAKEN);
				eventLog.add(Events.takenPicking(picking.getId(), courrierId));
				return null;
			}
		}.executeWithLock();
	}

	public List<Picking> getPickings() {
		return pickings;
	}
	
	private abstract class シンクロナイザー<T> {
		abstract T execute();
		
		T executeWithLock() {
			lock.lock();
			try {
				return execute();
			} finally {
				lock.unlock();
			}
		}
	}
}
