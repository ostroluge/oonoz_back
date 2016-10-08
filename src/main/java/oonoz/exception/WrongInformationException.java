package oonoz.exception;


/**
 * The Class WrongInformationException.
 * 
 * Description :
 * 		Throw when information about player is incorrect.Fields too long or format not valid.
 */
public class WrongInformationException extends Exception {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new wrong information exception.
	 *
	 * @param message the message
	 */
	public WrongInformationException(String message)
	{
		super(message);
	}

}
