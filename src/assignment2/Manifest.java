package assignment2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import Exceptions.CSVFormatException;
import Exceptions.DeliveryException;
import Exceptions.StockException;

/** This class contains static methods for creating and receiving manifests and receiving sales logs.
 * 	Manifests are created as a text files.
 * 
 * @author Brant Geeves
*/
public class Manifest {

	/**This static method creates a manifest as a text file, given a file location as input.
	 * The manifest contains a list of Trucks and their cargo, based on what items within the inventory
	 * have reached or surpassed their reorder point. The maximum quantity required in stock is the reorder
	 * point plus the reorder amount. The actual order amount is this quantity minus the amount already in stock. 
	 * The method throws exceptions when it detects an incorrect file extension or when there is no stock
	 * that needs to be replenished.
	 * 
	 * @param fileLoc - file location of the Item Property file as a string 
	 * @throws IOException - throws this exception when a problem is detected with IO
	 * @throws CSVFormatException - throws this exception for issues around csv file format and data
	 * @throws StockException - throws this exception when an issue regarding Stock or Items arises
	 */
		
	public static void createManifest(String fileLoc) throws IOException, CSVFormatException, StockException {

		if(!ItemPropertyImporter.isFileCSV(fileLoc)){ 
			
			throw new CSVFormatException("Please save the Manifest with .csv extension");
			
		}

		ColdTruck currentCold = new ColdTruck();
		OrdinaryTruck currentNorm = new OrdinaryTruck();

		List<Truck> truckList = new ArrayList<>();
		
		if(!anyItemsToOrder()){
			
			throw new StockException("No Manifest created. No items need replenishing.");
			
		}

		for (Item item : Store.getInstance().getInventory().stockList()) {

			if (item.quantity <= item.reorderPoint) {

				int orderAmt = item.reorderPoint + item.reorderAmount - item.quantity;

				if (item.itemTemp == 999) {

					if (currentNorm.calculateCapacity() > orderAmt) {

						String manifestLine = item.itemName + "," + Integer.toString(orderAmt);
						currentNorm.addCargo(manifestLine, orderAmt);

					} else {

						int splitAmt = currentNorm.calculateCapacity();
						int remainAmt = orderAmt - splitAmt;

						String splitLine = item.itemName + "," + Integer.toString(splitAmt);
						currentNorm.addCargo(splitLine, splitAmt);

						truckList.add(currentNorm);
						currentNorm = new OrdinaryTruck();

						if (remainAmt > 0) {
							String remainLine = item.itemName + "," + Integer.toString(remainAmt);
							currentNorm.addCargo(remainLine, remainAmt);
						}
					}
				}

				else {

					if (currentCold.calculateCapacity() > orderAmt) {

						String manifestLine = item.itemName + "," + Integer.toString(orderAmt);
						currentCold.addCargo(manifestLine, orderAmt, item.getTemp());

					}

					else {

						int splitAmt = currentCold.calculateCapacity();
						int remainAmt = orderAmt - splitAmt;

						String splitLine = item.itemName + "," + Integer.toString(splitAmt);
						currentCold.addCargo(splitLine, splitAmt, item.getTemp());

						truckList.add(currentCold);
						currentCold = new ColdTruck();

						if (remainAmt > 0) {
							String remainLine = item.itemName + "," + Integer.toString(remainAmt);
							currentCold.addCargo(remainLine, remainAmt, item.getTemp());
						}
					}
				}

			}

		}

		truckList.add(currentNorm);
		truckList.add(currentCold);

		BufferedWriter buffer = new BufferedWriter(new FileWriter(fileLoc));

		for (Truck truck : truckList) {

			List<String> cargoList = truck.getCargo();

			for (String line : cargoList) {

				buffer.write(line);
				buffer.newLine();

			}
			buffer.newLine();
		}

		buffer.close();

	}
	/**This static method receives manifests, from the given file location,
	 * and adjusts the inventory and store capital accordingly.
	 * 
	 * @param fileLocation - file location of the Item Property file as a string 
	 * @throws IOException - throws this exception when a problem is detected with IO
	 * @throws DeliveryException - throws this exception when an issue with importing manifests arises
	 * @throws CSVFormatException - throws this exception for issues around csv file format and data
	 */
	public static void receiveManifest(String fileLocation) throws IOException, DeliveryException, CSVFormatException {

		if(!ItemPropertyImporter.isFileCSV(fileLocation)){
			
			throw new CSVFormatException("You must upload a file with extension: .csv");
		}
		
		BufferedReader r = new BufferedReader(new FileReader(fileLocation));

		double safeTemp = 999;

		int ordinaryCargo = 0;

		String line = r.readLine();
		
		if(!line.contains(">")){
			
			r.close();
			throw new DeliveryException("Manifest format is incorrect. First line should contain [>TruckType].");
		}
		
		if(line.isEmpty()){
			
			r.close();
			throw new CSVFormatException("The Item Properties file is empty. Please choose another file.");
		}

		while (line != null) {

			if (Objects.equals(line, ">Refridgerated") || Objects.equals(line, ">Ordinary")) {

				if (safeTemp != 999) {

					Store.getInstance().lowerCapital(900 + 200 * Math.pow(0.7, safeTemp / 5));
										
				}

				else if (ordinaryCargo != 0) {

					Store.getInstance().lowerCapital(750 + 0.25 * ordinaryCargo);
					
				}
				
							
				safeTemp = 999;
				ordinaryCargo = 0;
				line = r.readLine();
				if(!line.contains(",")){
					
					r.close();
					throw new CSVFormatException("The format of this file is incorrect. Item properties must be separated by a comma.");
				}
			}

			String[] thisLine = line.split(",");

			for (Item item : Store.getInstance().getInventory().stockList()) {

				if (Objects.equals(item.itemName, thisLine[0])) {
					
					if (item.itemTemp == 999) {

						ordinaryCargo = ordinaryCargo + Integer.parseInt(thisLine[1]);
						

					} else {

						if (item.itemTemp < safeTemp) {
							safeTemp = item.itemTemp;
						}
						
					}

					item.addQuantity(Integer.parseInt(thisLine[1]));
					Store.getInstance().lowerCapital(item.itemCost * Double.parseDouble(thisLine[1]));

				}

			}

			line = r.readLine();
		}

		if (safeTemp != 999) {

			Store.getInstance().lowerCapital(900 + 200 * Math.pow(0.7, safeTemp / 5));
			
		}

		else if (ordinaryCargo != 0) {

			Store.getInstance().lowerCapital(750 + 0.25 * ordinaryCargo);
			
		}
		r.close();
		
		if(Store.getInstance().getCapital() < 0){
			
			throw new DeliveryException("Receiving this shipment will put " + Store.getInstance().getStoreName() + "'s capital in the red."
					+ "\n\nManifest upload aborted.\n\nPlease contact Betty in Accounts for further instructions.");
			
		}

	}
	
	/**This static method inputs sales logs, from the given file location, and adjusts the inventory
	 * and store capital accordingly. The process is aborted if there is an issue with the file type or format.
	 * The process is aborted and an exception is thrown if any of the items on the sales log would be negative 
	 * in the inventory and a message is displayed to the user showing those items and the over sold quantity.
	 * 
	 * @param fileLocation - file location of the Item Property file as a string 
	 * @throws IOException - throws this exception when a problem is detected with IO
	 * @throws CSVFormatException - throws this exception for issues around csv file format and data
	 * @throws StockException - throws this exception when an issue regarding Stock or Items arises
	 */
	public static void loadSalesLog(String fileLocation) throws IOException, CSVFormatException, StockException {

		if(!ItemPropertyImporter.isFileCSV(fileLocation)){
			
			throw new CSVFormatException("Please choose Sales Log file with .csv extension");
			
		}
				
		BufferedReader r = new BufferedReader(new FileReader(fileLocation));
		
		boolean shortQuantity = false;
		String missingInventory = "\n";

		String line = r.readLine();
		
		if(line.isEmpty()){
			
			r.close();
			throw new CSVFormatException("The Sales Log file is empty. Please choose another file.");
		}
		
		while (line != null) {

			String[] thisLine = line.split(",");

			if(thisLine.length != 2){
				
				r.close();
				throw new CSVFormatException("The Sales Log file is in the wrong format. Each line should contain only [ item name,quantity ].");
			}

			for (Item item : Store.getInstance().getInventory().stockList()) {

				if (Objects.equals(item.itemName, thisLine[0])) {

					item.removeQuantity(Integer.parseInt(thisLine[1]));
					if(item.getQuantity() < 0){
						
						shortQuantity = true;
						String missingAmt = Integer.toString(Math.abs(item.getQuantity()));
						missingInventory = missingInventory + thisLine[0] + " by " + missingAmt + "\n";
						
					}
					Store.getInstance().addCapital(item.itemPrice * Double.parseDouble(thisLine[1]));

				}

			}

			line = r.readLine();
		}

		r.close();
		if(shortQuantity){
			
			throw new StockException("The following items were oversold:" + missingInventory + "Sales Log upload aborted.\n\n Please audit Sales Log and try again.");
			
		}

	}
	
	/**This method checks the inventory and returns true if any item with the inventory
	 * needs to be replenished, otherwise returns false.
	 * 
	 * @return itemsToOrder
	 */
	private static boolean anyItemsToOrder(){
		
		boolean itemsToOrder = false;
		
		for (Item item : Store.getInstance().getInventory().stockList()) {
			if (item.quantity <= item.reorderPoint) {
				itemsToOrder = true;
				return itemsToOrder;
			}
		}
		
		return itemsToOrder;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
