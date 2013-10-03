package madesy.model;

import madesy.storage.PickingStorage;

public class Client extends Person {
	
	public Client(int id, String name, PickingStorage pickingStorage) {
		super(id, name, pickingStorage);
	}
	
	@Override
	public void run() {
	}
	
	private void makeNewPicking() {
		
	}
	
	
}
