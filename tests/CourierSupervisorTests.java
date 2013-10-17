import static org.junit.Assert.assertEquals;
import madesy.model.CourierSupervisor;

import org.junit.Before;
import org.junit.Test;


public class CourierSupervisorTests {
	private CourierSupervisor cs;
	
	@Before
	public void setUp() {
		cs = new CourierSupervisor();
		cs.addNewCourier("1");
		for(int i = 0; i < 15; i++) {
			cs.incrementCarriedPickings("1");
		}
		cs.addNewCourier("2");
		for(int i = 0; i < 25; i++) {
			cs.incrementCarriedPickings("2");
		}
		cs.addNewCourier("3");
		for(int i = 0; i < 35; i++) {
			cs.incrementCarriedPickings("3");
		}
	}
	
	@Test
	public void test() {
		String expectedId = "1";
		String actualId = cs.getCourierWithLowestPickingsNumber();
		assertEquals("Actual number should be equal to expected", expectedId, actualId);
	}

}
