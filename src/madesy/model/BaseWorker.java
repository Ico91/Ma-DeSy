package madesy.model;

import java.util.Random;

public abstract class BaseWorker implements Runnable {
	protected String id;
	
	public BaseWorker(String id) {
		this.id = id;
	}
	
	public abstract void  doWork();
	
	@Override
	public void run() {
		while (!Thread.currentThread().isInterrupted()) {
			doWork();
			threadToSleep();
		}
	}

	protected void threadToSleep() {
		Random rand = new Random();
		try {
			Thread.sleep(rand.nextInt(3000));
		} catch (InterruptedException e) {
			System.out.println("Courrier " + this.id + " is interrupted.");
			Thread.currentThread().interrupt();
		}
	}

	@Override
	public String toString() {
		return "Person [id=" + id + "]";
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
	
}
