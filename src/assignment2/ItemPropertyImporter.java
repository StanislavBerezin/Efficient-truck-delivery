package assignment2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ItemPropertyImporter {

	public static List<Item> getCSVItems(String fileLocation) throws IOException {

		List<Item> inventory = new ArrayList<Item>();

		BufferedReader r = new BufferedReader(new FileReader(fileLocation));

		String line = r.readLine();

		while (line != null) {

			String[] thisLine = line.split(",");

			ArrayList<String> itemList = new ArrayList<String>();

			for (String item : thisLine) {

				itemList.add(item);

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

			// inventory.add(newItem);

			// Prepare for next loop iteration
			line = r.readLine();
			// i++;
		}

		// Free up file descriptor resources
		r.close();

		return inventory;

	}

}