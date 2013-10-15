package madesy.model;

import java.util.HashMap;
import java.util.Map;

public class CourierSupervisor {
	private Map<String, Integer> couriers = new HashMap<String, Integer>();
	
	public CourierSupervisor() {}
	
	// TODO: fix method name
	public void incrementCourierCarriedPickings(String courierId) {
		int dispatchedPickings = couriers.get(courierId);
		if(dispatchedPickings != 0) {
			couriers.put(courierId, ++dispatchedPickings);
		}
	}
	
	public void decrementCourierCarriedPickings(String courierId) {
		int dispatchedPickings = couriers.get(courierId);
		if(dispatchedPickings != 0) {
			couriers.put(courierId, --dispatchedPickings);
		}
	}
	
	public String getCourierWithLowestPickingsNumber() {
		return null;
	}
}
