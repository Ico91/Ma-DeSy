package madesy.storage;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import madesy.model.Picking;
import madesy.model.PickingStates;

public class PickingStorage {
	private List<Picking> pickings;
	private final Lock lock;
	
	public PickingStorage() {
		pickings = new CopyOnWriteArrayList<Picking>();
		lock = new ReentrantLock();
	}
	
	public void newPicking(Picking picking) {
		pickings.add(picking);
	}
	
	public Picking pickingToDispatch() {
		Picking picking = null;
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
		lock.lock();
		try {
			int index = pickings.indexOf(picking);
			pickings.get(index).setPickingStates(PickingStates.TAKEN);
		} finally {
			lock.unlock();
		}
	}
	
	public List<Picking> getPickings() {
		return this.pickings;
	}
}
