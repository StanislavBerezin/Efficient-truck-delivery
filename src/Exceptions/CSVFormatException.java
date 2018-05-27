/**
 * 
 */
package Exceptions;

/**Exception class to handle exceptions pertaining to csv file format or data.
 * Extends Exception.
 * 
 * @author Brant Geeves
 */
public class CSVFormatException extends Exception{

	private static final long serialVersionUID = 6409133616361971884L;

	/**Single input constructor, the error message,
	 * which is passed back to the Exception super type.
	 * @param message - the error message
	 */
	public CSVFormatException(String message) {
		super(message);
	}

}
