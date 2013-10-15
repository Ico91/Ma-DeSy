package madesy.model.workers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import madesy.model.Picking;
import madesy.model.PickingService;
import madesy.storage.PickingStorage;

/**
 * Worker process used to simulate a client, requesting to send a new picking.
 * 
 */
public class ClientWorker extends BaseWorker {
	private PickingService pickingService;
	
	public ClientWorker(int sleepTime) {
		super(sleepTime);
	}

	@Override
	public void doWork() {
		threadToSleep();
		// TODO: Create new picking
		threadToSleep();
	}

	/**
	 * Creates a random new picking.
	 * 
	 * @return
	 */
	private Picking makeNewPicking() {
		Random random = new Random();

		List<Integer> barcodes = new ArrayList<Integer>();
		Integer barcode = random.nextInt(100000000);
		barcodes.add(barcode);
		Picking picking = new Picking(this.id);
		picking.setBarcodes(barcodes);

		return picking;
	}
}
