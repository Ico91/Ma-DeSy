package madesy.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import madesy.storage.PickingStorage;

public class Client extends Person {

	public Client(String id, PickingStorage pickingStorage) {
		super(id, pickingStorage);
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
		Picking picking = new Picking(pickingId, barcodes, PickingStates.NEW,
				this.id);

		return picking;
	}

}
