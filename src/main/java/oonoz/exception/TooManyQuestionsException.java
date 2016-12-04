package oonoz.exception;

/**
 * The Class TooManyQuestionsException.
 */
public class TooManyQuestionsException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new too many questions exception.
	 *
	 * @param message the message
	 */
	public TooManyQuestionsException(String message) {
		super(message);
	}
}
