package assignment2;

public class Item {

	String itemName;
	double itemCost;
	double itemPrice;
	int reorderPoint;
	int reorderAmount;
	double itemTemp;

	// constructor maybe will add more things along the way
	Item(String itemName, double itemCost, double itemPrice, int reorderPoint, int reorderAmount, int itemTemp) {

		this.itemName = itemName;
		this.itemCost = itemCost;
		this.itemPrice = itemPrice;
		this.reorderPoint = reorderPoint;
		this.reorderAmount = reorderAmount;
		this.itemTemp = itemTemp;

	}

}
