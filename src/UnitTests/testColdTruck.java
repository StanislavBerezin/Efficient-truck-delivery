/**
 * 
 */
package UnitTests;

import org.junit.Before;
import org.junit.Test;

import assignment2.ColdTruck;

import static org.junit.Assert.assertEquals;

/**
 * @author Brant Geeves
 *
 */

public class testColdTruck {

	ColdTruck truck;
	
	
	@Before
	public void setupColdTruck(){
		truck = null;
	}
	
	@Test
	public void testConstruction(){
		truck = new ColdTruck();
		
	}
	
	@Test
	public void testCapacity(){
		truck = new ColdTruck();
		assertEquals(800, truck.calculateCapacity());
		
	}
	
	@Test
	public void testCost(){
		truck = new ColdTruck();
		assertEquals(998, truck.calculateCost(), 0);
		
	}
	
	@Test
	public void testAddCargo(){
		truck = new ColdTruck();
		truck.addCargo("TestName,50", 50, -15);
	}
	
	@Test
	public void testNewCapacity(){
		truck = new ColdTruck();
		truck.addCargo("TestName,50", 50, -15);
		assertEquals(750, truck.calculateCapacity());
	}
	
	@Test
	public void testNewSafeTemp(){
		truck = new ColdTruck();
		truck.addCargo("TestName,50", 50, -15);
		assertEquals(-15, truck.getSafeTemp());
	}
	
	@Test
	public void testNewHigherSafeTemp(){
		truck = new ColdTruck();
		truck.addCargo("TestName,50", 50, -15);
		truck.addCargo("Gruel,65", 65, 5);
		assertEquals(-15, truck.getSafeTemp());
	}
	
	

}
