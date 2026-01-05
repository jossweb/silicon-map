package error;

/**
 * Base class for login-related exceptions |
 * Classe de base pour les exceptions liées à la connexion.
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