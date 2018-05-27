package assignment2;

/** This class represents an individual item.
 * 
 * @author Brant Geeves
*/
public class Item {
	
	String itemName;
	double itemCost;
	double itemPrice;
	int reorderPoint;
	int reorderAmount;
	int itemTemp;
	int quantity = 0;
	
	/** This class represents an individual Item. Items are added to inventory's
	 * within the Stock class and Stock objects are added to the Store.
	 * 
	 * @param itemName - The items name
	 * @param itemCost - The price the Store pays for the item
	 * @param itemPrice - The price the Store sells the item for
	 * @param reorderPoint - The item is reordered if the amount in stock falls to this amount
	 * @param reorderAmount - The amount to reorder
	 * @param itemTemp - The items safe temperature, so it doesn't perish.
	 */
	public Item(String itemName, double itemCost, double itemPrice, int reorderPoint, int reorderAmount, int itemTemp) {

		this.itemName = itemName;
		this.itemCost = itemCost;
		this.itemPrice = itemPrice;
		this.reorderPoint = reorderPoint;
		this.reorderAmount = reorderAmount;
		this.itemTemp = itemTemp;

	}

	/**Returns the items name
	 * 
	 * @return itemName
	 */
	public String getName() {
		return this.itemName;
	}

	/**Returns the cost the Store pays for the item
	 * 
	 * @return itemCost
	 */
	public double getCost() {
		return this.itemCost;
	}

	/**Returns the price the Stores sells the item for
	 * 
	 * @return itemPrice
	 */
	public double getPrice() {
		return this.itemPrice;
	}

	/**Returns the reorder point of the item
	 * 
	 * @return reorderPoint
	 */
	public int getReorderPoint() {
		return this.reorderPoint;
	}

	/**Returns the reorder amount of the item
	 * 
	 * @return reorderAmount
	 */
	public int getReorderAmount() {
		return this.reorderAmount;
	}

	/**Returns the items safe temperature.
	 * Between -20 and 10 for cold items.
	 * 999 for ordinary items.
	 * 
	 * @return itemTemp
	 */
	public int getTemp() {

		return this.itemTemp;
	}
	/**Returns the quantity in stock
	 * 
	 * @return quantity
	 */
	public int getQuantity() {

		return this.quantity;

	}
	/**Adds the given quantity to stock
	 * 
	 * @param amount - the quantity to add
	 */
	public void addQuantity(int amount) {

		this.quantity = this.quantity + amount;

	}
	/**Removes the given quantity from stock
	 * 
	 * @param amount - the quantity to remove
	 */
	public void removeQuantity(int amount) {

		this.quantity = this.quantity - amount;

	}

}
