package madesy.storage;

import java.util.List;

public class Picking {
	private String id;
	private List<String> barcodes;
	private PickingStates pickingStates;
	private int clientId;
	
	public Picking(String id, List<String> barcodes, PickingStates pickingStates, int clientId) {
		this.id = id;
		this.barcodes = barcodes;
		this.pickingStates = pickingStates;
		this.clientId = clientId;
	}
	
	@Override
	public int hashCode() {;
		return id.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Picking))
			return false;
		Picking other = (Picking) obj;
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
	public List<String> getBarcodes() {
		return barcodes;
	}
	public void setBarcodes(List<String> barcodes) {
		this.barcodes = barcodes;
	}
	public PickingStates getPickingStates() {
		return pickingStates;
	}
	public void setPickingStates(PickingStates pickingStates) {
		this.pickingStates = pickingStates;
	}
	public int getClientId() {
		return clientId;
	}
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	
	
}
