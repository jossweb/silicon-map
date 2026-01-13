package error;

/**
 * Base class for login-related exceptions
 * 
 * @author FIGUEIRAS Jossua
 */
public abstract class LoginError extends Exception{
	/**
	* Constructor
	*
	* @param s message
	*/
	public LoginError(String s) {
		super(s);
	}
}