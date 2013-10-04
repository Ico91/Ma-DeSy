package madesy.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import madesy.storage.PickingStorage;

public class Client extends Person {
	
	private int live;
	
	public Client(String id, String name, PickingStorage pickingStorage) {
		super(id, name, pickingStorage);
		live = 0;
	}
	
	@Override
	public void run() {		
		while(!Thread.currentThread().isInterrupted()) {
			super.getPickingStorage().newPicking(this.makeNewPicking());
			Random rand = new Random();
			
			try {
				live++;
				System.out.println(this.name + " " + live);
				if ((live == 10)) {
					
					System.out.println(this.name + " is interrupted");
					
					Thread.currentThread().interrupt();
				} else 
					Thread.sleep(rand.nextInt(2000) + 1000);
			} catch(InterruptedException e) {
				// TODO: on interrupt?
			}
		}
	}
	
	private Picking makeNewPicking() {
		String pickingId = UUID.randomUUID().toString();
		Random random = new Random();
		
		List<Integer> barcodes = new ArrayList<Integer>();
		Integer barcode = random.nextInt(100000000);
		barcodes.add(barcode);
		Picking picking = new Picking(pickingId, barcodes, PickingStates.NEW, this.id);
		
		return picking;
	}
	
	
}
