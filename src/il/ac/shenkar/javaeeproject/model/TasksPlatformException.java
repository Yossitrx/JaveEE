package il.ac.shenkar.javaeeproject.model;

public class TasksPlatformException extends Exception{

	public TasksPlatformException(String msg) {
		super(msg);
	}
	public TasksPlatformException(String msg, Throwable throwable) {
		super(msg,throwable);
	}
}
