/**
 * 
 */
package UnitTests;

import org.junit.Before;
import org.junit.Test;

import Exceptions.CSVFormatException;
import assignment2.Stock;

import static org.junit.Assert.*;

import java.io.IOException;

/**
 * @author Brant Geeves
 *
 */

public class testStock { 

	Stock stock;
	String fileLocation = "C:\\Users\\Brant\\git\\CAB302_Assignment_2\\src\\assignment2\\csv\\item_properties.csv";
	
	@Before
	public void setupStock(){
		stock = null;
	}
	
	@Test
	public void testConstruction(){
		stock = new Stock();
	}
	
	@Test
	public void testAddInventory() throws CSVFormatException, IOException{
		stock = new Stock();
		stock.addInventory(fileLocation);
	}
	
	@Test
	public void TestStockList() throws CSVFormatException, IOException{
		stock = new Stock();
		stock.addInventory(fileLocation);
		stock.stockList();
		assertEquals(24, stock.stockList().size());
	}
	
	@Test
	public void TestRemoveAllItems() throws CSVFormatException, IOException{
		stock = new Stock();
		stock.addInventory(fileLocation);
		stock.stockList();
		stock.removeAllItems();
		assertEquals(0, stock.stockList().size());
	}

}
