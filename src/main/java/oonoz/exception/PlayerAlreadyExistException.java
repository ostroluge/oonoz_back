package oonoz.exception;


/**
 * The Class PlayerAlreadyExistException.
 * 
 * Description :
 * 		Throw when a player with already exist username or mail is create.
 */
public class PlayerAlreadyExistException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new player already exist exception.
	 *
	 * @param message the message
	 */
	public PlayerAlreadyExistException(String message){
		super(message);
	}
}
