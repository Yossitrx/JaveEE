package il.ac.shenkar.javaeeproject.model;

import java.util.List;

public interface IToDoListDao {

	public int addTask(int id, String title, String taskBody) throws TasksPlatformException;
	
	public void deleteTask(int id) throws TasksPlatformException;
	
	public void updateTask(int id, String title, String taskBody) throws TasksPlatformException;

	public void getTasks() throws TasksPlatformException;
	
	public void configuration();
	
}
