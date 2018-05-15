package assignment2;

public class NormalTruck extends Truck {

	// extends the truck class, so in truck can add
	// some abstract classes that will be applicable
	// only to normal truck

	int cargoQuantity;

	public double calculateCost(int cargoQuantity) {
		this.cargoQuantity = cargoQuantity;

		if (cargoQuantity > 1000) {
			System.out.println("You have exceeded cargo capacity");

			return null;

		} else {

			this.truckCost = 750 + (0.25 * this.cargoQuantity);

			return this.truckCost;
		}

	}

}
