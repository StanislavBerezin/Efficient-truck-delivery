package assignment2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Manifest {

	String fileLoc;

	public static void createManifest(String fileLoc) throws IOException {

		// List<String> coldList = new ArrayList<>();
		// List<String> normList = new ArrayList<>();

		ColdTruck currentCold = new ColdTruck();
		OrdinaryTruck currentNorm = new OrdinaryTruck();

		List<Truck> truckList = new ArrayList<>();

		for (Item item : Store.getInstance().inventory) {

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

	public static void receiveManifest(String fileLocation) throws IOException {

		BufferedReader r = new BufferedReader(new FileReader(fileLocation));

		double safeTemp = 999;

		int ordinaryCargo = 0;

		String line = r.readLine();

		while (line != null) {

			System.out.println("safeTemp: " + Double.toString(safeTemp));

			if (Objects.equals(line, ">Refridgerated") || Objects.equals(line, ">Ordinary")) {

				if (safeTemp != 999) {

					Store.getInstance().lowerCapital(900 + 200 * Math.pow(0.7, safeTemp / 5));
					System.out.println("Cold truck cost: $" + Double.toString(900 + 200 * Math.pow(0.7, safeTemp / 5)));

				}

				else if (ordinaryCargo != 0) {

					Store.getInstance().lowerCapital(750 + 0.25 * ordinaryCargo);
					System.out.println("Ordinary truck cost: $" + Double.toString(750 + 0.25 * ordinaryCargo));

				}
				System.out.println(line);
				safeTemp = 999;
				ordinaryCargo = 0;
				line = r.readLine();

			}

			String[] thisLine = line.split(",");

			for (Item item : Store.getInstance().inventory) {

				if (Objects.equals(item.itemName, thisLine[0])) {

					if (item.itemTemp == 999) {

						ordinaryCargo = ordinaryCargo + Integer.parseInt(thisLine[1]);
						System.out.println(ordinaryCargo);

					} else {

						if (item.itemTemp < safeTemp) {
							safeTemp = item.itemTemp;
						}
						System.out.println(safeTemp);
					}

					item.addQuantity(Integer.parseInt(thisLine[1]));
					Store.getInstance().lowerCapital(item.itemCost * Double.parseDouble(thisLine[1]));

				}

			}

			line = r.readLine();
		}

		if (safeTemp != 999) {

			Store.getInstance().lowerCapital(900 + 200 * Math.pow(0.7, safeTemp / 5));
			System.out.println("Cold truck cost: $" + Double.toString(900 + 200 * Math.pow(0.7, safeTemp / 5)));

		}

		else if (ordinaryCargo != 0) {

			Store.getInstance().lowerCapital(750 + 0.25 * ordinaryCargo);
			System.out.println("Ordinary truck cost: $" + Double.toString(750 + 0.25 * ordinaryCargo));

		}
		r.close();

	}

	public static void loadSalesLog(String fileLocation) throws IOException {

		BufferedReader r = new BufferedReader(new FileReader(fileLocation));

		String line = r.readLine();

		while (line != null) {

			String[] thisLine = line.split(",");

			// System.out.println(thisLine[0] + thisLine[1]);

			for (Item item : Store.getInstance().inventory) {

				if (Objects.equals(item.itemName, thisLine[0])) {

					item.removeQuantity(Integer.parseInt(thisLine[1]));
					Store.getInstance().addCapital(item.itemPrice * Double.parseDouble(thisLine[1]));

				}

			}

			line = r.readLine();
		}

		r.close();

	}
}
