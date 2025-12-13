package error;

public class BadPassword extends LoginError{
	public BadPassword() {
		super("Bad password");
	}
}
