package madesy.model;

import java.util.Random;

import madesy.storage.PickingStorage;

public abstract class BaseWorker implements Runnable {
	protected String id;
	protected PickingStorage pickingStorage;
	
	public BaseWorker(String id, PickingStorage pickingStorage) {
		this.id = id;
		this.pickingStorage = pickingStorage;
	}
	
	public abstract void  doWork();
	
	@Override
	public void run() {
		while (!Thread.currentThread().isInterrupted()) {
			doWork();
			threadToSleep(1000);
		}
	}

	protected void threadToSleep(long interval) {
		Random rand = new Random();
		try {
			Thread.sleep(rand.nextInt(1000) + interval);
		} catch (InterruptedException e) {
			System.out.println("Courrier " + this.id + " is interrupted.");
			return;
		}
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", pickingStorage="
				+ pickingStorage + "]";
	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BaseWorker other = (BaseWorker) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public PickingStorage getPickingStorage() {
		return pickingStorage;
	}

	public void setPickingStorage(PickingStorage pickingStorage) {
		this.pickingStorage = pickingStorage;
	}
	
}
