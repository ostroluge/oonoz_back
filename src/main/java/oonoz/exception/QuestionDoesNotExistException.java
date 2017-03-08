package oonoz.exception;

/**
 * The Class QuestionDoesNotExistException.
 */
public class QuestionDoesNotExistException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new question does not exist exception.
	 *
	 * @param message the message
	 */
	public QuestionDoesNotExistException(String message) {
		super(message);
	}
	
	/**
	 * Instantiates a new question does not exist exception.
	 *
	 * @param id the id
	 */
	public QuestionDoesNotExistException(long id){
		super("The question with id " + id + " does not exist");
	}
}
