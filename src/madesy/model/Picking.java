package madesy.model;

import java.util.List;

public class Picking {
	private String id;
	private List<Integer> barcodes;
	private PickingStatus pickingStatus;
	private String senderId;
	
	public Picking(String id, List<Integer> barcodes, PickingStatus pickingStatus, String senderId) {
		this.id = id;
		this.barcodes = barcodes;
		this.pickingStatus = pickingStatus;
		this.senderId = senderId;
	}
	
	@Override
	public String toString() {
		return "Picking [id=" + id + ", barcodes=" + barcodes
				+ ", pickingStatus=" + pickingStatus + ", clientId=" + senderId
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
	public PickingStatus getPickingStates() {
		return pickingStatus;
	}
	public void setPickingStates(PickingStatus pickingStatus) {
		this.pickingStatus = pickingStatus;
	}
	public String getClientId() {
		return senderId;
	}
	public void setClientId(String senderId) {
		this.senderId = senderId;
	}
	
	
}
