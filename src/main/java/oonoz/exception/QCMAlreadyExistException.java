package oonoz.exception;

public class QCMAlreadyExistException extends Exception {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new QCM does not exist exception.
	 *
	 * @param message the message
	 */
	public QCMAlreadyExistException(String message) {
		super(message);
	}

}
