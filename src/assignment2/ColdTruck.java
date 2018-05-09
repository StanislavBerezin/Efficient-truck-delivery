package assignment2;

public class ColdTruck extends Truck {
	
//	extends the truck class, so in truck can add 
	//some abstract classes that will be applicable 
	//only to refregirated truck.
	double temp;
	double truckCost;
	
	public void temperatureRequired () {
		//based on the coldest item's requirement
		//need to go through the list
	}
	
	
	//calculating cost as per requirenment
	public double calculateCost(double temperature) {
		this.temp = temperature
				
		this.truckCost = 900 + (200 * (java.lang.Math.pow(0.7,(this.temp/5))));
		
		return this.truckCost;
	}

}
