package madesy.model;

import madesy.storage.PickingStorage;

public abstract class Person implements Runnable {
	protected String id;
	protected PickingStorage pickingStorage;
	
	public Person(String id, PickingStorage pickingStorage) {
		this.id = id;
		this.pickingStorage = pickingStorage;
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
		Person other = (Person) obj;
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
