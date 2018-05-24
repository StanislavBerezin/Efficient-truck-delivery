package assignment2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class Manifest {

	String fileLoc;

	public static void createManifest(Store store, String fileLoc) throws IOException {

		BufferedWriter buffer = new BufferedWriter(new FileWriter(fileLoc));

		List<String> itemList = store.makeManifest();

		for (String item : itemList) {

			buffer.write(item);
			buffer.newLine();

		}

		buffer.close();

	}

	public static void receiveManifest(Store store, String fileLocation) throws IOException {

		BufferedReader r = new BufferedReader(new FileReader(fileLocation));

		String line = r.readLine();

		while (line != null) {

			String[] thisLine = line.split(",");

			// System.out.println(thisLine[0] + thisLine[1]);

			for (Item item : store.inventory) {

				if (Objects.equals(item.itemName, thisLine[0])) {

					item.addQuantity(Integer.parseInt(thisLine[1]));
					store.lowerCapital(item.itemCost * Double.parseDouble(thisLine[1]));

				}

			}

			line = r.readLine();
		}

		r.close();

	}

	public static void loadSalesLog(Store store, String fileLocation) throws IOException {

		BufferedReader r = new BufferedReader(new FileReader(fileLocation));

		String line = r.readLine();

		while (line != null) {

			String[] thisLine = line.split(",");

			// System.out.println(thisLine[0] + thisLine[1]);

			for (Item item : store.inventory) {

				if (Objects.equals(item.itemName, thisLine[0])) {

					item.removeQuantity(Integer.parseInt(thisLine[1]));
					store.addCapital(item.itemPrice * Double.parseDouble(thisLine[1]));

				}

			}

			line = r.readLine();
		}

		r.close();

	}
}
