package assignment2;

import java.util.List;

public abstract class Truck {
	
	//will need a constructor and some abstract classes
	//which will be implemented in ColdTruck and NormalTruck files
	
	
	///IMPORTANT
	/// 
	///  Somehow we must be able to go through the list of items
	/// it is needed to confirm that no cold items are placed in normalTruck
	/// PLUS it is needed to confirm the required temperature of a coldTruck
	///  can use loops from your itemImporter
	
	
	double truckCost;
	int truckCapacity;
	List<Item> inventory;
	
	
	
	public abstract double calculateCost();
	public abstract int calculateCapacity();

}
