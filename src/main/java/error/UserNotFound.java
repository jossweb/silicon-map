package error;

public class UserNotFound extends LoginError{
    public UserNotFound(){
        super("Can't find this user in db");
    }
}
