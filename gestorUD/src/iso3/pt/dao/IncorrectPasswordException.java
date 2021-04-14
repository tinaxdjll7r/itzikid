package iso3.pt.dao;

@SuppressWarnings("serial")
public class IncorrectPasswordException extends Exception {
	public IncorrectPasswordException(String msg) {
        super(msg);
    }
	
	public IncorrectPasswordException(String msg, Throwable reason) {
        super(msg, reason);
    }
}
