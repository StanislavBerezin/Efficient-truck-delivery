package assignment2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Exceptions.CSVFormatException;
import Exceptions.FormException;

public class Store {

	String storeName;
	private double storeCapital;
	private static final Store instance = new Store();
	private Stock inventory = new Stock();
	
	// private constructor to avoid client applications to use constructor
	private Store(){
		
		this.storeName = "myStore";
		this.storeCapital = 100000;
		
	}

	public static Store getInstance() {
		return instance;
	}

	// This function will actually take a call to the ItemPropertyImporter as input,
	// it reads the CSV
	// and returns a list of item objects, each with 0 quantity

	

	public Object[][] createGuiData() {

		Object[][] data = new Object[inventory.stockList().size()][7];
		java.util.Iterator<Item> invItr = inventory.stockList().iterator();

		for (int i = 0; i < inventory.stockList().size(); i++) {

			if (invItr.hasNext()) {

				Item item = invItr.next();

				data[i][0] = item.itemName;
				data[i][1] = item.quantity;
				data[i][2] = item.itemCost;
				data[i][3] = item.itemPrice;
				data[i][4] = item.reorderPoint;
				data[i][5] = item.reorderAmount;
				if (item.itemTemp != 999) {
					data[i][6] = item.itemTemp;
				}

			}

		}

		return data;
	}

	public String getStoreName() {

		return this.storeName;

	}

	public void setStoreName(String name) throws FormException {

		if(name.length() < 1){
			
			throw new FormException("You must enter a store name.");
		}
				
		this.storeName = name;

	}

	public double getCapital() {

		return storeCapital;
	}

	public void addCapital(double amount) {

		this.storeCapital = this.storeCapital + amount;

	}

	public void lowerCapital(double cost) {

		this.storeCapital = this.storeCapital - cost;

	}
	
	public Stock getInventory(){
		
		return inventory;
	}

}
