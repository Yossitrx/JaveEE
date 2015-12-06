package il.ac.shenkar.javaeeproject.model;

import java.util.List;

public interface IToDoListDao {

	public int addTask(int userID, String title, String taskBody) throws TasksPlatformException;
	
	public void deleteTask(int userID, int taskID) throws TasksPlatformException;
	
	public void updateTask(int userID, int taskID, String title, String taskBody) throws TasksPlatformException;

	public void getTasks(int userID) throws TasksPlatformException;
	
	public int logIn(int userID, String mail, String password);
}
