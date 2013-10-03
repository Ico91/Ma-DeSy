package madesy.model;

import madesy.storage.PickingStorage;

public abstract class Person implements Runnable {
	protected int id;
	protected String name;
	protected PickingStorage pickingStorage;
	
	public Person(int id, String name, PickingStorage pickingStorage) {
		this.id = id;
		this.name = name;
		this.pickingStorage = pickingStorage;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", pickingStorage="
				+ pickingStorage + "]";
	}

	@Override
	public int hashCode() {
		return id;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public PickingStorage getPickingStorage() {
		return pickingStorage;
	}

	public void setPickingStorage(PickingStorage pickingStorage) {
		this.pickingStorage = pickingStorage;
	}
	
}
