package madesy.model;

import madesy.model.types.PickingStatus;

public class PickingDispatcher {
	private CourierSupervisor courierSupervisor = new CourierSupervisor();
	
	public void dispatchNewPicking(Picking picking) {
		String courierId = courierSupervisor.getCourierWithLowestPickingsNumber();
		System.out.println("Dispatched to: " + courierId);
		courierSupervisor.incrementCarriedPickings(courierId);
		picking.setPickingStates(PickingStatus.DISPATCHED);
		picking.setCourierId(courierId);
	}
	
}
