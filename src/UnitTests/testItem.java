/**
 * 
 */
package UnitTests;

import org.junit.Before;
import org.junit.Test;

import assignment2.Item;

import static org.junit.Assert.assertEquals;

/**
 * @author Stanislav
 *
 */
public class testItem {

	Item myItem;
	String name = "TestName";
	double cost = 1;
	double price = 2;
	int reorderAmt = 225;
	int reorderQty = 300;
	int safeTemp = 999;
	
	@Before
	public void setupItem(){
		
		myItem = null;
		
	}
		
	@Test
	public void testConstruction(){
		
		myItem = new Item(name, cost, price, reorderAmt, reorderQty, safeTemp);
	
	}
	
	@Test
	public void testName(){
		
		myItem = new Item(name, cost, price, reorderAmt, reorderQty, safeTemp);
		assertEquals("TestName", myItem.getName());
		
	}
	
	@Test
	public void testCost(){
		
		myItem = new Item(name, cost, price, reorderAmt, reorderQty, safeTemp);
		assertEquals(1, myItem.getCost(), 0);
		
	}
	
	@Test
	public void testPrice(){
		
		myItem = new Item(name, cost, price, reorderAmt, reorderQty, safeTemp);
		assertEquals(2, myItem.getPrice(), 0);
		
	}
	
	@Test
	public void testReorderPoint(){
		
		myItem = new Item(name, cost, price, reorderAmt, reorderQty, safeTemp);
		assertEquals(225, myItem.getReorderPoint());
		
	}
	
	@Test
	public void testReorderAmount(){
		
		myItem = new Item(name, cost, price, reorderAmt, reorderQty, safeTemp);
		assertEquals(300, myItem.getReorderAmount());
		
	}
	
	@Test
	public void testTemp(){
		
		myItem = new Item(name, cost, price, reorderAmt, reorderQty, safeTemp);
		assertEquals(999, myItem.getTemp());
		
	}
	
	@Test
	public void testQty(){
		
		myItem = new Item(name, cost, price, reorderAmt, reorderQty, safeTemp);
		assertEquals(0, myItem.getQuantity());
		
	}
	
	@Test
	public void testAddQty(){
		
		myItem = new Item(name, cost, price, reorderAmt, reorderQty, safeTemp);
		myItem.addQuantity(5);
		assertEquals(5, myItem.getQuantity());
		
	}
	
	@Test
	public void testRemoveQty(){
		
		myItem = new Item(name, cost, price, reorderAmt, reorderQty, safeTemp);
		myItem.removeQuantity(5);
		assertEquals(-5, myItem.getQuantity());
		
	}
	
}
