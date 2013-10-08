package madesy.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import madesy.storage.PickingStorage;

public class ClientWorker extends BaseWorker {
	private PickingStorage pickingStorage;

	public ClientWorker(String id, PickingStorage pickingStorage) {
		super(id);
		this.pickingStorage = pickingStorage;
	}

	@Override
	public void doWork() {
		pickingStorage.newPicking(this.makeNewPicking());
	}

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
