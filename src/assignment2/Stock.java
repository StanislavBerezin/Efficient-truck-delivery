package assignment2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import Exceptions.CSVFormatException;

/** This class represents a Stock object, which can be used to store
 *  an inventory of Items and manipulate the inventory.
 * 
 * @author Stanislav Berezin
*/

public class Stock {
	
	private List<Item> inventory;
	
	/**
	 * Represents a Stock object, which can hold an inventory of Items
	 * 
	 */
	public Stock() { 
		 
		inventory = new ArrayList<Item>();
	}
	
	/** This method is used to add an Item objects to the inventory via an Item Properties csv file.
	 * 	Throws an exception if there are any issues with the format of the csv file
	 * 
	 * @param fileLocation - file location of the Item Property file as a string 
	 * @throws IOException - throws this exception when a problem is detected with IO
	 * @throws CSVFormatException - throws this exception for issues around csv file format and data
	 */
	
	public void addInventory(String fileLocation) throws CSVFormatException, IOException {

		List<Item> itemList = ItemPropertyImporter.getCSVItems(fileLocation);

		for (Item item : itemList) {

			inventory.add(item);
		}
	}
	
	/** Returns the inventory as a list of Items
	 * 
	 * @return inventory
	 */
	public List<Item> stockList(){
		
		return inventory;
		
	}
	
	/**
	 *  Clears all items from the inventory. This method is not used in the main program,
	 *  but is required for unit testing.
	 */
	public void removeAllItems(){
		
		inventory.clear();
		
	}
	
}
