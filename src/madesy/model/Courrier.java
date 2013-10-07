package madesy.model;

import java.util.Random;

import madesy.storage.PickingStorage;

public class Courrier extends Person {

	public Courrier(String id, PickingStorage pickingStorage) {
		super(id, pickingStorage);
	}

	public void run() {
		while (!Thread.currentThread().isInterrupted()) {
			dispatchPicking();
			Random rand = new Random();
			try {
				Thread.sleep(rand.nextInt(1000));
			} catch (InterruptedException e) {
				System.out.println("Courrier " + this.id + " is interrupted.");
				return;
			}
		}
	}

	private void dispatchPicking() {
		Picking dispatchedPicking = pickingStorage.pickingToDispatch();
		if (dispatchedPicking == null)
			return;

		Random rand = new Random();

		try {
			Thread.sleep(rand.nextInt(1000) + 1000);
		} catch (InterruptedException e) {
			System.out.println("Courrier " + this.id + " is interrupted.");
			return;
		}

		takePicking(dispatchedPicking);
	}

	private void takePicking(Picking picking) {
		pickingStorage.markPickingTaken(picking);
	}
}
