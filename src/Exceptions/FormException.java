package Exceptions;

/**Exception class to handle issues pertaining to input forms.
 * Extends Exception.
 * 
 * @author Brant Geeves
 *
 */
public class FormException extends Exception {

	private static final long serialVersionUID = -7003745044710814247L;
	
	/**Single input constructor, the error message,
	 * which is passed back to the Exception super type.
	 * @param message - the error message
	 */
	public FormException(String message) {
		super(message);
	}

}
