package oonoz.exception;


/**
 * The Class PlayerNotExistException.
 * 
 * Description :
 * 		Throw when the player search does not exist.
 */
public class PlayerNotExistException extends Exception {
	

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new player already exist exception.
	 *
	 * @param message the message
	 */
	public PlayerNotExistException(String message){
		super(message);
	}

}
