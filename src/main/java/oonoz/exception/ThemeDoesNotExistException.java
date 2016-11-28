package oonoz.exception;

/**
 * The Class ThemeDoesntExistException.
 */
public class ThemeDoesNotExistException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new theme doesnt exist exception.
	 *
	 * @param message the message
	 */
	public ThemeDoesNotExistException(String message) {
		super(message);
	}
}
