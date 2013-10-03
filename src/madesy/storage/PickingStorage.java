package madesy.storage;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import madesy.model.Picking;

public class PickingStorage {
	private List<Picking> pickings;
	
	public PickingStorage() {
		pickings = new CopyOnWriteArrayList<Picking>();
	}
	
	public void newPicking(Picking picking) {
		
	}
	
	public Picking pickingToDispatch() {
		return null;
	}
	
	public void markPickingTaken() {
		
	}
}
