package madesy.model;

import java.util.List;

public class Picking {
	private String id;
	private List<Integer> barcodes;
	private PickingStates pickingStatus;
	private String clientId;
	
	public Picking(String id, List<Integer> barcodes, PickingStates pickingStatus, String clientId) {
		this.id = id;
		this.barcodes = barcodes;
		this.pickingStatus = pickingStatus;
		this.clientId = clientId;
	}
	
	@Override
	public String toString() {
		return "Picking [id=" + id + ", barcodes=" + barcodes
				+ ", pickingStatus=" + pickingStatus + ", clientId=" + clientId
				+ "]";
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
	public List<Integer> getBarcodes() {
		return barcodes;
	}
	public void setBarcodes(List<Integer> barcodes) {
		this.barcodes = barcodes;
	}
	public PickingStates getPickingStates() {
		return pickingStatus;
	}
	public void setPickingStates(PickingStates pickingStatus) {
		this.pickingStatus = pickingStatus;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	
	
}
