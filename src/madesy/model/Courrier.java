package madesy.model;

import java.util.Random;

import madesy.storage.PickingStorage;

public class Courrier extends Person {
	
	public Courrier(String id, String name, PickingStorage pickingStorage)
	{
		super(id, name, pickingStorage);
	}
	
	public void run() {
		dispatchPicking();
	}
	
	private void dispatchPicking() {
		Picking dispatchedPicking = pickingStorage.pickingToDispatch();
		System.out.println(dispatchedPicking);
		if(dispatchedPicking == null)
			return;
		
		Random rand = new Random();
		
		try {
			Thread.sleep(5000);
		} catch(InterruptedException e) {
			// TODO: on interrupt?
		}
		
		takePicking(dispatchedPicking);
	}
	
	private void takePicking(Picking picking) {
		pickingStorage.markPickingTaken(picking);
	}
}
