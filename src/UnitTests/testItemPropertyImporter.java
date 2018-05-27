/**
 * 
 */
package UnitTests;

import org.junit.Test;

import Exceptions.CSVFormatException;
import assignment2.Item;
import assignment2.ItemPropertyImporter;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

/**
 * @author Stanislav Berezin
 *
 */
public class testItemPropertyImporter { 

	String fileLocation = "C:\\Users\\Brant\\git\\CAB302_Assignment_2\\src\\assignment2\\csv\\item_properties.csv";
	String badLocation = "C:\\Users\\Brant\\git\\CAB302_Assignment_2\\src\\assignment2\\csv\\test_docs\\comma_TEST.txt";
	String missingPropertiesLocation = "C:\\Users\\Brant\\git\\CAB302_Assignment_2\\src\\assignment2\\csv\\test_docs\\missing_item_properties.csv";
	String extraPropertiesLocation = "C:\\Users\\Brant\\git\\CAB302_Assignment_2\\src\\assignment2\\csv\\test_docs\\extra_item_properties.csv";
	String emptyFileLocation = "C:\\Users\\Brant\\git\\CAB302_Assignment_2\\src\\assignment2\\csv\\test_docs\\emptyTest.csv";
	
	List<Item> inventory;
	
	@Test
	public void testGetCsvItems() throws CSVFormatException, IOException{
		List<Item> inventory = ItemPropertyImporter.getCSVItems(fileLocation);
		assertEquals(24, inventory.size()); 
	}
	
	@Test (expected = CSVFormatException.class)
	public void testGetCsvItemsNoCommas() throws CSVFormatException, IOException{
		ItemPropertyImporter.getCSVItems(badLocation);
	}
	
	@Test (expected = CSVFormatException.class)
	public void testGetCsvItemsNotEnoughItemProperties() throws CSVFormatException, IOException{
		ItemPropertyImporter.getCSVItems(missingPropertiesLocation);
	}
	
	@Test (expected = CSVFormatException.class)
	public void testGetCsvItemsTooManyItemProperties() throws CSVFormatException, IOException{
		ItemPropertyImporter.getCSVItems(extraPropertiesLocation);
	}
	@Test (expected = NullPointerException.class)
	public void testGetCsvItemsEmptyItemProperties() throws CSVFormatException, IOException{
		ItemPropertyImporter.getCSVItems(emptyFileLocation);
	}
	
	
	@Test
	public void testISCsv(){
		String goodFileName = "testName.csv";
		assertEquals(true, ItemPropertyImporter.isFileCSV(goodFileName));
	}
	
	@Test
	public void testNOTCsv(){
		String badFileName = "testName";
		assertEquals(false, ItemPropertyImporter.isFileCSV(badFileName));
	}

}
