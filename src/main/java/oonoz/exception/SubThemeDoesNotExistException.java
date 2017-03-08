package oonoz.exception;

/**
 * The Class SubThemeDoesNotExistException.
 */
public class SubThemeDoesNotExistException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new sub theme does not exist exception.
	 *
	 * @param message the message
	 */
	public SubThemeDoesNotExistException(String message) {
		super(message);
	}
	
	/**
	 * Instantiates a new sub theme does not exist exception.
	 *
	 * @param id the id
	 */
	public SubThemeDoesNotExistException(long id){
		super("The subtheme with id " + id + " does not exist");
	}
}
