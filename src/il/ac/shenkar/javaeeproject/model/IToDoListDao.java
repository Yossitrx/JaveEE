package il.ac.shenkar.javaeeproject.model;

import java.util.List;

/**
 * ToDoList DAO
 * represents the DAO Interface for ToDoList
 */
public interface IToDoListDao {

	/**
	 * Add Task to database
	 * @param Task
	 * @return Integer 
	 * @throws TasksPlatformException
	 */
	public void addTask(Task task) throws TasksPlatformException;
	
	
	/**
	 * Delete requested Task from database
	 * @param String, Integer
	 * @return void
	 * @throws TasksPlatformException
	 */
	public void deleteTask(String userName, int taskID) throws TasksPlatformException;
	
	
	/**
	 * Update requested task from database
	 * @param Integer, Task
	 * @return void 
	 * @throws TasksPlatformException
	 */
	public void updateTask(int taskID, Task updatedTask) throws TasksPlatformException;
	
	/**
	 * Get all Tasks from database
	 * @param Integer 
	 * @return List<Task> 
	 * @throws TasksPlatformException
	 */
	public List<Task> getTasks(String userName) throws TasksPlatformException;
	
	/**
	 * Sign up new User and insert to database
	 * @param User
	 * @return User
	 * @throws TasksPlatformException
	 */
	public User signup(User user) throws UserPlatformException;
	
	/**
	 * Get the User that login to the system from database
	 * @param String, String
	 * @return User
	 * @throws TasksPlatformException
	 */
	public User getUser(String mail, String password) throws UserPlatformException;
}

