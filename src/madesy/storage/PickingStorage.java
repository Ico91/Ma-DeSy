package madesy.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import madesy.builder.EventBuilder;
import madesy.model.Event;
import madesy.model.Picking;
import madesy.model.types.EventType;
import madesy.model.types.PickingStatus;

/**
 * Container for all pickings of all clients, providing
 * the necessary concurrent methods on it, for adding
 * a new picking and changing the status of a specified
 * one.
 *
 */
public class PickingStorage {
	private List<Picking> pickings;
	private EventLog eventLog;
	private EventBuilder eventBuilder;
	private final Lock lock;
	
	public PickingStorage(EventLog eventLog) {
		pickings = new ArrayList<Picking>();
		this.eventLog = eventLog;
		eventBuilder = new EventBuilder();
		lock = new ReentrantLock();
	}
	
	/**
	 * Adds the newly created picking in the collection,
	 * @param picking
	 */
	public void newPicking(Picking picking) {
		lock.lock();
		try {
			pickings.add(picking);
			Event newEvent = eventBuilder.addEvent(EventType.NEW_PICKING).addMetaData(picking.getId()).build();
			eventLog.add(newEvent);
		} finally {
			lock.unlock();
		}
	}
	
	/**
	 * Returns the first available picking marked as NEW
	 * in order to be dispatched.
	 * @param courrierId - Id of the courier, requesting
	 * to dispatch a picking.
	 * @return If any exists, returns the first new picking.
	 */
	public Picking pickingToDispatch(String courrierId) {
		Picking picking = null;
		lock.lock();
		try {
			for(int i = 0; i < pickings.size(); i ++) {
				// get index of first picking marked as new
				if(pickings.get(i).getPickingStates() == PickingStatus.NEW) {
					pickings.get(i).setPickingStates(PickingStatus.DISPATCHED);
					picking = pickings.get(i);
					String metaData = generateMetaData(picking.getId(), courrierId);
					Event newEvent = eventBuilder.addEvent(EventType.DISPATCH_PICKING).addMetaData(metaData).build();
					eventLog.add(newEvent);
					break;
				}
			}
		} finally {
			lock.unlock();
		}
		
		return picking;
	}
	
	/**
	 * Changes the status of a picking, which previously was
	 * marked as dispatched, to taken.
	 * @param picking - The picking which was delivered.
	 * @param courrierId - Id of the courier who delivered the picking.
	 */
	public void markPickingTaken(Picking picking, String courrierId) {
		lock.lock();
		try {
			int index = pickings.indexOf(picking);
			pickings.get(index).setPickingStates(PickingStatus.TAKEN);
			String metaData = generateMetaData(picking.getId(), courrierId);
			Event newEvent = eventBuilder.addEvent(EventType.TAKE_PICKING).addMetaData(metaData).build();
			eventLog.add(newEvent);
		} finally {
			lock.unlock();
		}
	}

	/**
	 * Generates meta data for the event log.
	 * @param pickingId - Id of the picking being delivered
	 * @param courrierId - Id of the courier 
	 * @return 
	 */
	private String generateMetaData(String pickingId, String courrierId) {
		String metaData = pickingId + "," + courrierId;
		return metaData;
	}

	public List<Picking> getPickings() {
		return pickings;
	}
	
}
