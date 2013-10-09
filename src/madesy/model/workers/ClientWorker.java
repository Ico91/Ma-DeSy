package madesy.model.workers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import madesy.model.Picking;
import madesy.model.types.PickingStatus;
import madesy.storage.PickingStorage;

/**
 * Worker process used to simulate a client, requesting
 * to send a new picking.
 *
 */
public class ClientWorker extends BaseWorker {
	private PickingStorage pickingStorage;

	public ClientWorker(PickingStorage pickingStorage, int sleepTime) {
		super(sleepTime);
		this.pickingStorage = pickingStorage;
	}

	@Override
	public void doWork() {
		threadToSleep();
		pickingStorage.newPicking(this.makeNewPicking());
		threadToSleep();
	}

	/**
	 * Creates a random new picking.
	 * @return
	 */
	private Picking makeNewPicking() {
		String pickingId = UUID.randomUUID().toString();
		Random random = new Random();

		List<Integer> barcodes = new ArrayList<Integer>();
		Integer barcode = random.nextInt(100000000);
		barcodes.add(barcode);
		Picking picking = new Picking(pickingId, barcodes, PickingStatus.NEW,
				this.id);

		return picking;
	}
	
	public PickingStorage getPickingStorage() {
		return pickingStorage;
	}

	public void setPickingStorage(PickingStorage pickingStorage) {
		this.pickingStorage = pickingStorage;
	}
}
