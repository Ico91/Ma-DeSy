package madesy.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Class used to overseer the work of the couriers
 * @author hristo
 *
 */
public class CourierSupervisor {
	private Map<String, Integer> couriers = new HashMap<String, Integer>();
	
	public CourierSupervisor() {
		for(User u : Users.getCouriers()) {
			couriers.put(u.getId(), 0);
		}
	}
	
	// TODO: fix method name
	/**
	 * Increases the current number of carried pickings of
	 * the courier, specified by his id.
	 * @param courierId
	 */
	public void incrementCarriedPickings(String courierId) {
		int dispatchedPickings = couriers.get(courierId);
		couriers.put(courierId, ++dispatchedPickings);
	}
	
	/**
	 * Decreases the current number of carried pickings of
	 * the courier, specified by his id.
	 * @param courierId
	 */
	public void decrementCarriedPickings(String courierId) {
		int dispatchedPickings = couriers.get(courierId);
		couriers.put(courierId, --dispatchedPickings);
	}
	
	/**
	 * Finds the courier, currently carrying the lowest number of pickings
	 * @return courierId
	 */
	public String getCourierWithLowestPickingsNumber() {
		int courierPickings = (int)couriers.values().toArray()[0];
		String courierId = (String)couriers.keySet().toArray()[0];
		for(String id : couriers.keySet()) {
			System.out.println("Courier id: " + id + " dispatched pickings: " + couriers.get(id));
			if(couriers.get(id) < courierPickings) {
				courierId = id;
				courierPickings = couriers.get(id);
			}
		}
		
		return courierId;
	}
	
	/**
	 * Adds a new courier to track
	 * @param courierId
	 */
	public void addNewCourier(String courierId) {
		couriers.put(courierId, 0);
	}
	
	/**
	 * Removes a courier from the tracking list
	 * @param courierId
	 */
	public void removeCourier(String courierId) {
		couriers.remove(courierId);
	}
}