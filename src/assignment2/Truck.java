package assignment2;

import java.util.ArrayList;
import java.util.List;

/** An abstract class to represent a Truck.
 * This class is extended by ColdTruck and Ordinary Truck to represent the truck types.
 * 
 * @author Stanislav Berezin
*/

public abstract class Truck {

	List<Item> inventory;

	protected List<String> cargoList = new ArrayList<>(); 

	public abstract double calculateCost();

	public abstract int calculateCapacity();

	
	/**Returns the trucks current cargo as a list of Strings.
	 * When a new truck is initialised, a string with the truck type
	 * is added to the cargo list, in order to identify the truck
	 * type when it is added to a manifest.
	 * 
	 * @return cargoList
	 */
	public List<String> getCargo() { 

		return this.cargoList;
	}

}
