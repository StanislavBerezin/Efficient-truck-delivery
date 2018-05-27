package assignment2;

/** This class extends the Truck class to represent a refridgerated truck.
 *  Cold Trucks can hold any type of item in cargo.
 *  The cost of a cold truck is not dependent on the cargo quantity, only the lowest
 *  safe temperature of the cargo.
 * 
 * @author Stanislav Berezin
*/
public class ColdTruck extends Truck {
	
	/**
	 *  This is the constructor method to create a new ColdTruck object.
	 *  When a new ColdTruck is created, the truck type is added to the 
 	 *  inherited Truck object as a string, which is used to generate
 	 *  an entry on a manifest.
 	 */
	
	int capacity = 800;
	double cost;
	int cargoCount = 0;
	int safeTemp = 10; 
	
	public ColdTruck() {
		super();
		this.cargoList.add(">Refridgerated");

	}

	/** Calculates the cost of the particular truck, based on the current safe temperature. 
	 * 	
	 * @return the trucks cost 
	 */
	@Override
	public double calculateCost() {

		cost = 900 + 200 * Math.pow(0.7, safeTemp / 5);

		return cost;

	}

	/** Calculates the remaining cargo capacity of the truck
	 * 
	 * @return the remaining capacity
	 */
	@Override
	public int calculateCapacity() {

		int remainingCapacity = capacity - cargoCount;

		return remainingCapacity;
	}

	/** Adds cargo to the truck. The calculateCapacity method must be used first to determine
	 *  the remaining capacity.
	 * 
	 * @param cargoLine - a string containing: [item name,quantity]
	 * @param quantity - the cargo quantity to be added as an integer
	 * @param newTemp - the safeTemp of the new cargo
	 */
	public void addCargo(String cargoLine, int quantity, int newTemp) {

		this.cargoList.add(cargoLine);
		this.cargoCount = cargoCount + quantity;
		if (newTemp < safeTemp) {
			safeTemp = newTemp;
		}
	}
	
	/**Returns the safe temperature of the load cargo
	 * @return safeTemp - the current safe temperature of the loaded cargo 
	 */
	public int getSafeTemp(){
		
		return safeTemp;
		
	}

}
