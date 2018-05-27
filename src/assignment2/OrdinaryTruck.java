package assignment2;

/** This class extends the Truck class to represent an unrefridgerated truck.
 *  Ordinary Trucks should only hold unrefridgerated cargo.
 *  The cost of an ordinary truck is dependent on the cargo quantity.
 * 
 * @author Stanislav Berezin
*/

public class OrdinaryTruck extends Truck {

	/**
	 * This class represents an ordinary, unrefridgerated truck.
	 *  When a new ColdTruck is created, the truck type is added to the 
 	 *  inherited Truck object as a string, which is used to generate
 	 *  an entry on a manifest.
 	 *  
 	 */
	
	int capacity = 1000;
	double cost;
	int cargoCount = 0;

	public OrdinaryTruck() {
		super();
		this.cargoList.add(">Ordinary");

	}
	
	/** Calculates the cost of the particular truck, based on the current cargo quantity. 
	 * 	
	 * @return the trucks cost 
	 */
	@Override
	public double calculateCost() {
		
		cost = 750 + 0.25 * cargoCount;
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
	 */
	public void addCargo(String cargoLine, int quantity) {

		cargoList.add(cargoLine);
		cargoCount = cargoCount + quantity;

	}

}
