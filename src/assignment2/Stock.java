/**
 * 
 */
package assignment2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Exceptions.CSVFormatException;

/**
 * @author Brant
 *
 */
public class Stock {

	private List<Item> inventory;
		
	/**
	 * 
	 */
	public Stock() {
		
		inventory = new ArrayList<Item>();
	}

	public void addInventory(String fileLocation) throws CSVFormatException, IOException {

		if(!ItemPropertyImporter.isFileCSV(fileLocation)){
			
			throw new CSVFormatException("You must upload a file with extension: .csv");
		}
				
		List<Item> itemList = ItemPropertyImporter.getCSVItems(fileLocation);

		for (Item item : itemList) {

			inventory.add(item);
		}
	}
	
	public List<Item> stockList(){
		
		return inventory;
	}
	
}
