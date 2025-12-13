package error;

public abstract class LoginError extends Exception{
	public LoginError(String s) {
		super(s);
	}
}