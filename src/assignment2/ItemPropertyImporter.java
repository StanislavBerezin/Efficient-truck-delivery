package assignment2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ItemPropertyImporter {

	public static void main(String args[]) throws IOException {

		System.out.println("Main function running...");

		List<Item> inventory = getCSVItems("C:\\Users\\User\\git\\CAB302\\src\\assignment2\\csv\\item_properties.csv");

		for (Item item : inventory) {

			if (item.itemTemp == 999) {
				System.out.println(item.itemName + " doesn't need refridgerating.");
			}

			else {

				System.out.println(item.itemName + " must be refridgerated at " + item.itemTemp + " C or below.");

			}
		}

		System.out.println("Main function complete");

	}

	public static List<Item> getCSVItems(String fileLocation) throws IOException {

		// File("C:\\Users\\User\\git\\CAB302\\src\\assignment2\\csv\\item_properties.csv");

		// Construct a BufferedReader object from the input file

		List<Item> inventory = new ArrayList<Item>();

		BufferedReader r = new BufferedReader(new FileReader(fileLocation));

		// int i = 1;

		// "Prime" the while loop
		String line = r.readLine();
		while (line != null) {

			// Print a single line of input file to console
			// System.out.println("Line " + i + ": " + line);

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