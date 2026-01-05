package error;

/**
 * Exception thrown when a user cannot be found in the database |
 * Exception levée lorsqu'un utilisateur ne peut pas être trouvé dans la base de données.
 * 
 * @author FIGUEIRAS Jossua
 */
public class UserNotFound extends LoginError{
    /**
	* Constructor
	*/
    public UserNotFound(){
        super("Can't find this user in db");
    }
}
