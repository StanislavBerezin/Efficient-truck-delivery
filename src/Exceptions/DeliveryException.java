/**
 * 
 */
package Exceptions;

/**Exception class to handle issues pertaining to receiving manifests.
 * Extends Exception.
 * 
 * @author Brant Geeves
 */
public class DeliveryException extends Exception {

	private static final long serialVersionUID = -7559916245705912205L;

	/**Single input constructor, the error message,
	 * which is passed back to the Exception super type.
	 * @param message - the error message
	 */
	public DeliveryException(String message) {
		super(message);
	}

}
