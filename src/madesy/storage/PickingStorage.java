package madesy.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import madesy.builder.EventBuilder;
import madesy.model.Event;
import madesy.model.EventType;
import madesy.model.Picking;
import madesy.model.PickingStatus;

public class PickingStorage {
	private List<Picking> pickings;
	private EventLog eventLog;
	private EventBuilder eventBuilder;
	private final Lock lock;
	
	public PickingStorage() {
		pickings = new ArrayList<Picking>();
		eventLog = new EventLog();
		eventBuilder = new EventBuilder();
		lock = new ReentrantLock();
	}
	
	public void newPicking(Picking picking) {
		lock.lock();
		try {
			pickings.add(picking);
			Event newEvent = eventBuilder.addEvent(EventType.NEW_PICKING).addMetaData(picking.getId()).build();
			eventLog.addEvent(newEvent);
			System.out.println(eventLog);
		} finally {
			lock.unlock();
		}
	}
	
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
					eventLog.addEvent(newEvent);
					System.out.println(eventLog);
					break;
				}
			}
		} finally {
			lock.unlock();
		}
		
		return picking;
	}
	
	public void markPickingTaken(Picking picking, String courrierId) {
		lock.lock();
		try {
			int index = pickings.indexOf(picking);
			pickings.get(index).setPickingStates(PickingStatus.TAKEN);
			String metaData = generateMetaData(picking.getId(), courrierId);
			Event newEvent = eventBuilder.addEvent(EventType.TAKE_PICKING).addMetaData(metaData).build();
			eventLog.addEvent(newEvent);
			System.out.println(eventLog);
		} finally {
			lock.unlock();
		}
	}

	private String generateMetaData(String pickingId, String courrierId) {
		String metaData = pickingId + "," + courrierId;
		return metaData;
	}

	public List<Picking> getPickings() {
		return pickings;
	}
	
}
