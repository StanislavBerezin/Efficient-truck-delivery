package assignment2;

public class OrdinaryTruck extends Truck {

	int capacity = 1000;
	double cost;
	int cargoCount = 0;

	public OrdinaryTruck() {
		super();
		this.cargoList.add(">Ordinary");

	}

	@Override
	public double calculateCost() {

		cost = 750 + 0.25 * cargoCount;

		return cost;

	}

	@Override
	public int calculateCapacity() {

		int remainingCapacity = capacity - cargoCount;

		return remainingCapacity;
	}

	public void addCargo(String cargoLine, int quantity) {

		cargoList.add(cargoLine);
		cargoCount = cargoCount + quantity;

	}

}
