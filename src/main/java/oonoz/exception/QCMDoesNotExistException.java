package oonoz.exception;

/**
 * The Class QCMDoesNotExistException.
 */
public class QCMDoesNotExistException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new QCM does not exist exception.
	 *
	 * @param message the message
	 */
	public QCMDoesNotExistException(String message) {
		super(message);
	}
	
	/**
	 * Instantiates a new QCM does not exist exception.
	 *
	 * @param id the id
	 */
	public QCMDoesNotExistException(long id){
		super("The QCM with id " + id + " does not exist");
	}
}
