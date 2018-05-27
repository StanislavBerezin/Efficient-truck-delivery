/**
 * 
 */
package UnitTests;

import org.junit.Before;
import org.junit.Test;

import Exceptions.CSVFormatException;
import Exceptions.FormException;
import Exceptions.StockException;
import assignment2.Store;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

/**
 * @author Brant Geeves
 *
 */

public class testStore {

	
	String fileLocation = "C:\\Users\\Brant\\git\\CAB302_Assignment_2\\src\\assignment2\\csv\\item_properties.csv";
	
	
	@Before
	public void setupStore(){
		
		Store.getInstance().resetCapital();
		Store.getInstance().getInventory().removeAllItems();
		
	}
	
	@Test
	public void testInstance(){
		Store.getInstance();
	}
	
	@Test
	public void testSetName() throws FormException{
		Store.getInstance().setStoreName("My Cool Store");
		assertEquals("My Cool Store", Store.getInstance().getStoreName());
	}
	
	@Test
	public void testGetCapital() throws FormException{
		assertEquals(100000.00, Store.getInstance().getCapital(), 0);
	}
	
	@Test
	public void testAddCapital() throws FormException{
		Store.getInstance().addCapital(1000);
		assertEquals(101000.00, Store.getInstance().getCapital(), 0);
	}
	
	@Test
	public void testLowerCapital() throws FormException{
		Store.getInstance().lowerCapital(1000);
		assertEquals(99000.00, Store.getInstance().getCapital(), 0);
	}
	
	@Test
	public void testGetInventory(){
		Store.getInstance().getInventory();
	}
	
	@Test (expected = FormException.class)
	public void testSetNameTooShort() throws FormException{
		Store.getInstance().setStoreName("");
	}
	
	@Test (expected = StockException.class)
	public void testCreateGuiDataNoInventory() throws StockException{
		Store.getInstance().createGuiData();
	}
	
	@Test
	public void testCreateGuiData() throws StockException, CSVFormatException, IOException{
		
		Store.getInstance().getInventory().addInventory(fileLocation);
		//ItemPropertyImporter.getCSVItems(fileLocation);
		Object[][] data = Store.getInstance().createGuiData();
		assertEquals("rice", data[0][0]);
		
	}
	
	@Test
	public void testSetNameLengthOne() throws FormException{
		
		Store.getInstance().setStoreName("A");
	}
	
	
	
	
	
	

}
