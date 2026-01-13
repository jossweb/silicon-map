package error;

/**
 * Exception thrown when a user provides an incorrect password
 * 
 * @author FIGUEIRAS Jossua
 */
public class BadPassword extends LoginError{
	/**
	 * Constructor
	 */
	public BadPassword() {
		super("Bad password");
	}
}
