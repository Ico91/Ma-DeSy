package madesy.storage;

import java.util.ArrayList;
import java.util.List;

import madesy.model.Picking;

/**
 * Container for all pickings of all clients, providing
 * the necessary concurrent methods on it, for adding
 * a new picking and changing the status of a specified
 * one.
 *
 */
public class PickingStorage {
	private List<Picking> pickings = new ArrayList<Picking>();
	
	public PickingStorage() {
	}
	
	public void add(Picking picking) {
		pickings.add(picking);
	}
	
	public Picking getPicking(String pickingId) {
		for(Picking p : pickings) {
			if(p.getId().equals(pickingId))
				return p;
		}
		
		return null;
	}
	
	public List<Picking> getPickings() {
		return pickings;
	}
	
	public void updatePicking(Picking picking) {
		for(Picking p : pickings) {
			if(p.equals(picking)) {
				// TODO: update picking
			}
		}
	}
}
