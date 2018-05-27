package assignment2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import Exceptions.CSVFormatException;

/** This class contains static methods for importing an Item Properties csv file.
 * 
 * @author Brant Geeves
*/

public class ItemPropertyImporter { 

	
	/**This static method takes a csv file location as input,
	 * which the user selects via a file chooser within the Intro GUI.
	 * The class throws exceptions when its detects an issue with the csv file;
	 * either the extension or the the format of the data within it.
	 * 
	 * @param fileLocation - file location of the Item Property file as a string 
	 * @return inventory - a list of Items, which can be passed to a Stock object to create an inventory
	 * @throws CSVFormatException - throws this exception for issues around csv file format and data
	 * @throws IOException - throws this exception when a problem is detected with IO
	 */
	public static List<Item> getCSVItems(String fileLocation) throws CSVFormatException, IOException {

		if(!isFileCSV(fileLocation)){
			throw new CSVFormatException("You must upload a file with extension: .csv");
		}
		
		
		List<Item> inventory = new ArrayList<Item>();

		BufferedReader r = new BufferedReader(new FileReader(fileLocation)); 

		String line = r.readLine();
		
		if(line.isEmpty()){
			
			r.close();
			throw new CSVFormatException("The Item Properties file is empty. Please chose another file.");
		}

		while (line != null) {
			
			if(!line.contains(",")){
				
				r.close();
				throw new CSVFormatException("The format of this file is incorrect. Item properties must be separated by a comma.");
			}

			String[] thisLine = line.split(",");
			
			ArrayList<String> itemList = new ArrayList<String>();

			for (String item : thisLine) {

				itemList.add(item);

			}
			
			if(itemList.size() < 5){
				
				r.close();
				throw new CSVFormatException("The Items Properties file is missing one or more property values.");				
				
			}
			
			if(itemList.size() > 6){
				
				r.close();
				throw new CSVFormatException("The Items Properties file contains too many property values.");				
				
			}

			if (itemList.size() == 5) {

				String itemName = itemList.get(0);
				Double itemCost = Double.parseDouble(itemList.get(1));
				Double itemPrice = Double.parseDouble(itemList.get(2));
				Integer reorderPoint = Integer.parseInt(itemList.get(3));
				Integer reorderAmount = Integer.parseInt(itemList.get(4));
				Integer itemTemp = 999;

				Item newItem = new Item(itemName, itemCost, itemPrice, reorderPoint, reorderAmount, itemTemp);
				inventory.add(newItem);
			}

			if (itemList.size() == 6) {

				String itemName = itemList.get(0);
				Double itemCost = Double.parseDouble(itemList.get(1));
				Double itemPrice = Double.parseDouble(itemList.get(2));
				Integer reorderPoint = Integer.parseInt(itemList.get(3));
				Integer reorderAmount = Integer.parseInt(itemList.get(4));
				Integer itemTemp = Integer.parseInt(itemList.get(5));

				Item newItem = new Item(itemName, itemCost, itemPrice, reorderPoint, reorderAmount, itemTemp);
				inventory.add(newItem);
			}
						
			line = r.readLine();
			
		}
		
		r.close();

		return inventory;

	}
	
	/**
	 * 
	 * This method checks if the given file has the .csv extension
	 * 
	 * Modified from code by EboMike
	 * Source: https://stackoverflow.com/questions/3571223/how-do-i-get-the-file-extension-of-a-file-in-java
	 * 
	 * @author Brant Geeves
	 * @param fileName
	 *            the file name to retrieve extension from
	 * @return extension
	 * 			  the extension of the file as a string
	 */
	
	public static boolean isFileCSV(String fileName){
		
		boolean isCSV = false;
		
		String extension = "";

		int i = fileName.lastIndexOf('.');
		if (i > 0) {
		    extension = fileName.substring(i+1);
		}
		
		if(Objects.equals(extension, "csv")){isCSV = true;}
		
		return isCSV;
	}

}