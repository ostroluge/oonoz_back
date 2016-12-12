package oonoz.exception;

public class QCMValidationException extends Exception {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new QCM does not exist exception.
	 *
	 * @param message the message
	 */
	public QCMValidationException(String message) {
		super(message);
	}

}
