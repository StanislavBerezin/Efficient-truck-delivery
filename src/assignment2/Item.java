package assignment2;

public class Item {

	String itemName;
	double itemCost;
	double itemPrice;
	int reorderPoint;
	int reorderAmount;
	int itemTemp;
	int quantity = 0;

	// constructor maybe will add more things along the way
	Item(String itemName, double itemCost, double itemPrice, int reorderPoint, int reorderAmount, int itemTemp) {

		this.itemName = itemName;
		this.itemCost = itemCost;
		this.itemPrice = itemPrice;
		this.reorderPoint = reorderPoint;
		this.reorderAmount = reorderAmount;
		this.itemTemp = itemTemp;

	}

	// returning Name of the item
	public String getName() {
		return this.itemName;
	}

	// returning the cost of the item
	public double getCost() {
		return this.itemCost;
	}

	// returning the price of the item
	public double getPrice() {
		return this.itemPrice;
	}

	// returning reordering point
	public int getReorderPoint() {
		return this.reorderPoint;
	}

	// returning reorderAmount
	public int getReorderAmount() {
		return this.reorderAmount;
	}

	// return item safe temp
	public int getTemp() {

		return this.itemTemp;
	}

	public int getQuantity() {

		return this.quantity;

	}

	public void addQuantity(int amount) {

		this.quantity = this.quantity + amount;

	}

	public void removeQuantity(int amount) {

		this.quantity = this.quantity - amount;

	}

}
