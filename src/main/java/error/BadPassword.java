package error;

/**
 * Exception thrown when a user provides an incorrect password |
 * Exception levée lorsqu'un utilisateur fournit un mot de passe incorrect.
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
