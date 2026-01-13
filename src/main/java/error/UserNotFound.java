package error;

/**
 * Exception thrown when a user cannot be found in the database
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
