package assignment2;

public class ColdTruck extends Truck {

	int capacity = 800;
	double cost;
	int cargoCount = 0;
	int safeTemp = 10;

	public ColdTruck() {
		super();
		this.cargoList.add(">Refridgerated");

	}

	@Override
	public double calculateCost() {

		cost = 900 + 200 * Math.pow(0.7, safeTemp / 5);

		return cost;

	}

	@Override
	public int calculateCapacity() {

		int remainingCapacity = capacity - cargoCount;

		return remainingCapacity;
	}

	public void addCargo(String cargoLine, int quantity, int newTemp) {

		this.cargoList.add(cargoLine);
		this.cargoCount = cargoCount + quantity;
		if (newTemp < safeTemp) {
			safeTemp = newTemp;
		}

	}
	
	public int getSafeTemp(){
		
		return safeTemp;
		
	}

}
