package assignment2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Store {

	String storeName;
	double storeCapital;
	static final Store instance = new Store();
	List<Item> inventory = new ArrayList<Item>();

	// private constructor to avoid client applications to use constructor
	public Store() {
		this.storeName = "My Store";
		this.storeCapital = 100000;
	}

	public static Store getInstance() {
		return instance;
	}

	// This function will actually take a call to the ItemPropertyImporter as input,
	// it reads the CSV
	// and returns a list of item objects, each with 0 quantity

	public void addInventory(String fileLoc) throws IOException {

		List<Item> itemList = ItemPropertyImporter
				.getCSVItems(fileLoc);
				
		for (Item item : itemList) {

			inventory.add(item);

		}

	}

	public List<String> makeManifest() {

		List<String> manifest = new ArrayList<>();

		for (Item item : inventory) {

			if (item.quantity <= item.reorderPoint) {

				int orderAmt = item.reorderPoint + item.reorderAmount - item.quantity;

				String manifestLine = item.itemName + "\t" + Integer.toString(orderAmt);

				manifest.add(manifestLine);

			}

		}

		return manifest;

	}

	public Object[][] createGuiData() {

		Object[][] data = new Object[inventory.size()][7];
		java.util.Iterator<Item> invItr = inventory.iterator();

		for (int i = 0; i < inventory.size(); i++) {

			if (invItr.hasNext()) {

				Item item = invItr.next();

				data[i][0] = item.itemName;
				data[i][1] = item.quantity;
				data[i][2] = item.itemCost;
				data[i][3] = item.itemPrice;
				data[i][4] = item.reorderPoint;
				data[i][5] = item.reorderAmount;
				data[i][6] = item.itemTemp;
			}

		}

		return data;
	}
	public String getStoreName(){
		
		return this.storeName;
		
	}
	
	public void setStoreName(String name){
		
		this.storeName = name;
		
		
	}

}
