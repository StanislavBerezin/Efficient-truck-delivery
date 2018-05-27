/**
 * 
 */
package Exceptions;

/**Exception class to handle issues pertaining to Stock and Items.
 * Extends Exception
 * 
 * @author Brant Geeves
 *
 */
public class StockException extends Exception {

	private static final long serialVersionUID = 4618189075496875096L;

	/**Single input constructor, the error message,
	 * which is passed back to the Exception super type.
	 * 
	 * @param message - the error message
	 */
	public StockException(String message) {
		super(message);
	}

}
