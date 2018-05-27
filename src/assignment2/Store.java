package assignment2;

import Exceptions.FormException;
import Exceptions.StockException;

/** This class uses the Singleton pattern to represent a Store.
 * 	
 * 
 * @author Stanislav Berezin
*/

public class Store {

	String storeName;
	private double storeCapital;
	private static final Store instance = new Store(); 
	private Stock inventory = new Stock();
	
	/** This class uses the Singleton pattern to represent a Store.
	 *  The constructor is private to ensure only a single instance is created.
	 *  The instance is accessed through a public method, which returns the instance.
	 */
	private Store(){
		
		this.storeName = "myStore";
		this.storeCapital = 100000;
		
	}
	/** Part of the Singleton pattern, this method is used to access the single instance of the Store.
	 * 
	 * @return instance
	 */
	public static Store getInstance() {
		return instance;
	}

	/** This method creates a two dimensional array representing the stores inventory.
	 *  The method is called from the GUI, in order to display the inventory as a table.
	 *  Throws a StockException, if there is no inventory to display.
	 * 
	 * @return data
	 * @throws StockException - throws this exception when there is no inventory to display
	 */
	public Object[][] createGuiData() throws StockException {

		if(inventory.stockList().isEmpty()){
			
			throw new StockException("There is no inventory data to show.");
			
		}
		
		Object[][] data = new Object[inventory.stockList().size()][7];
		java.util.Iterator<Item> invItr = inventory.stockList().iterator();

		for (int i = 0; i < inventory.stockList().size(); i++) { 

			if (invItr.hasNext()) {

				Item item = invItr.next();

				data[i][0] = item.itemName; 
				data[i][1] = item.quantity;
				data[i][2] = item.itemCost;
				data[i][3] = item.itemPrice;
				data[i][4] = item.reorderPoint;
				data[i][5] = item.reorderAmount;
				if (item.itemTemp != 999) {
					data[i][6] = item.itemTemp;
				}
			}
		}
		return data;
	}

	/** Returns the stores name.
	 * 
	 * @return storeName
	 */
	public String getStoreName() {

		return this.storeName;

	}
	
	/** Sets the stores name. Is called from the Introduction GUI,
	 *  where the user is compelled to name the store using a form.
	 *  Throws a FormException if no name is given. 
	 * 
	 * @param name - the stores name
	 * @throws FormException - throws this exception when the user leaves a form empty
	 */
	public void setStoreName(String name) throws FormException {

		if(name.length() < 1){
			
			throw new FormException("You must enter a store name.");
		}
				
		this.storeName = name;

	}
	
	/**Returns the stores capital.
	 * 
	 * @return capital
	 */
	public double getCapital() {

		return storeCapital;
	}
	/**Used to add to the store's capital.
	 * 
	 * @param amount - the amount of capital to add
	 */
	public void addCapital(double amount) {

		this.storeCapital = this.storeCapital + amount;

	}
	
	/**Used to lower the stores capital.
	 * 
	 * @param cost - the amount of capital to lower by
	 */
	public void lowerCapital(double cost) {

		this.storeCapital = this.storeCapital - cost;

	}
	
	/**Returns a Stock object containing the stores inventory.
	 * 
	 * @return inventory
	 */
	public Stock getInventory(){
		
		return inventory;
	}
	
	/**Used to reset the stores capital to the initial value.
	 * Not used within the main program, but is required for unit testing.
	 */
	public void resetCapital(){
		
		this.storeCapital = 100000.00;
		
	}

}
