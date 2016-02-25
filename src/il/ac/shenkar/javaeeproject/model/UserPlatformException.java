package il.ac.shenkar.javaeeproject.model;

/**
 * UserPlatformException Handles Users errors
 */
public class UserPlatformException extends Exception{

	private static final long serialVersionUID = 1L;

	/**
	 * constructor
	 * @param msg
	 */
	public UserPlatformException(String msg) {
		super(msg);
	}
	
	/**
	 * constructor
	 * @param msg
	 * @param throwable
	 */
	public UserPlatformException(String msg, Throwable throwable) {
		super(msg,throwable);
	}
}
