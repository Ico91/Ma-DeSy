package madesy.storage;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import madesy.model.Picking;
import madesy.model.PickingStates;

public class PickingStorage {
	private List<Picking> pickings;
	
	public PickingStorage() {
		pickings = new CopyOnWriteArrayList<Picking>();
	}
	
	public void newPicking(Picking picking) {
		
	}
	
	public Picking pickingToDispatch() {
		Picking picking = null;
		Lock lock = new ReentrantLock();
		lock.lock();
		try {
			for(int i = 0; i < pickings.size(); i ++) {
				// get index of first picking marked as new
				if(pickings.get(i).getPickingStates().toString().equals(PickingStates.NEW)) {
					pickings.get(i).setPickingStates(PickingStates.DISPATCHED);
					picking = pickings.get(i);
					break;
				}
			}
		} finally {
			lock.unlock();
		}
		return picking;
	}
	
	public void markPickingTaken(Picking picking) {
		
	}
}
