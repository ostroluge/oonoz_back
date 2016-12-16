package oonoz.exception;

/**
 * The Class QCMCreationException.
 * 
 * Description :
 */
public class QCMCreationException extends Exception {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new QCM does not exist exception.
	 *
	 * @param message the message
	 */
	public QCMCreationException(String message) {
		super(message);
	}

}
