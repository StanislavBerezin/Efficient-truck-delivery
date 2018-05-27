/**
 * 
 */
package UnitTests;

import org.junit.Before;
import org.junit.Test;
import assignment2.OrdinaryTruck;
import static org.junit.Assert.assertEquals;

/**
 * @author Brant Geeves
 *
 */
public class testOrdinaryTruck {

	OrdinaryTruck truck;
	
	@Before
	public void setupOrdinaryTruck(){
		truck = null;
	}
	
	@Test
	public void testConstruction(){
		truck = new OrdinaryTruck();
	}
	
	@Test
	public void testCapacity(){
		truck = new OrdinaryTruck();
		assertEquals(1000, truck.calculateCapacity());
	}
	
	@Test
	public void testCost(){
		truck = new OrdinaryTruck();
		assertEquals(750, truck.calculateCost(), 0);
	}
	
	@Test
	public void testAddCargo(){
		truck = new OrdinaryTruck();
		truck.addCargo("TestName,1000", 1000);
	}
	
	@Test
	public void testNewCapacity(){
		truck = new OrdinaryTruck();
		truck.addCargo("TestName,1000", 1000);
		assertEquals(0, truck.calculateCapacity());
	}
	
	@Test
	public void testNewCost(){
		truck = new OrdinaryTruck();
		truck.addCargo("TestName,1000", 1000);
		assertEquals(1000.0, truck.calculateCost(), 0);
	}
	
}
	
	
	
	
	
	
	
	
	
	
