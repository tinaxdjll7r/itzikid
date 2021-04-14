package iso3.pt.dao;

@SuppressWarnings("serial")
public class UserNotFoundException extends Exception {
	public UserNotFoundException(String msg) {
        super(msg);
    }
	
	public UserNotFoundException(String msg, Throwable reason) {
        super(msg, reason);
    }
}
