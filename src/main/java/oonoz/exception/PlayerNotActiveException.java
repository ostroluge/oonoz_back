package oonoz.exception;

/**
 * The Class PlayerNotActiveException.
 * 
 * Description :
 * 		Throw when a player with not active state is use.
 */
public class PlayerNotActiveException extends Exception  {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new player not active exception.
	 *
	 * @param message the message
	 */
	public PlayerNotActiveException(String message){
		super(message);
	}

}
