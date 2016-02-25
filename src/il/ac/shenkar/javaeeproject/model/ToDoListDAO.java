package il.ac.shenkar.javaeeproject.model;

import java.util.List;
import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;

/**
 * IToDoListDAO implementation
 */
public class ToDoListDAO implements IToDoListDao , Iconsts{

	/**
	 * ToDoListDAO instance (singleton)
	 */
	private static ToDoListDAO instance;

	/**
	 * SessionFactory represent the current factory (initialize once on the get instance method)
	 */
	private static SessionFactory factory;
	
	/** 
	 * constructor
	 */
	private ToDoListDAO() {}
	
	/** 
	 * getInstance method
	 * implements the design pattern Singleton on ToDoListDAO instance
	 */
	public static ToDoListDAO getInstance() {
		
		if (instance == null) {
			instance = new ToDoListDAO();
			factory = new AnnotationConfiguration()
					.configure()
					.addAnnotatedClass(Task.class)
					.addAnnotatedClass(User.class)
					.buildSessionFactory();
		}
		return instance;
	}
	
	/*
	* Just like at IToDoListDao - addTask
	*/	
	@Override
	public void addTask(Task task) throws TasksPlatformException {
		
		Session session = factory.openSession();
		Transaction tx = null;
		try{
		   tx = session.beginTransaction();
		   session.save(task); 
		   tx.commit();
		}catch (HibernateException e) {
		   if (tx != null) tx.rollback();
		   throw new TasksPlatformException(ADD_TASK_FAILED + e.getMessage());
		}finally {
			try {
				session.close();
			} catch (HibernateException e){
				throw new TasksPlatformException(CLOSE_FAILED + e.getMessage());
			}
		}
	}

	/*
	* Just like at IToDoListDAO - deleteTask
	*/	
	@Override
	public void deleteTask(String userName, int taskID) throws TasksPlatformException {
		
	      Session session = factory.openSession();
	      Transaction tx = null;
	      try{
	         tx = session.beginTransaction();
	         Task task = (Task)session.get(Task.class, taskID); 
	         if (userName.equals(task.getUserName())) {
	        	 session.delete(task); 
		         tx.commit(); 
	         } else {
	        	 throw new TasksPlatformException(TASK_ACCESS);
	         }
	      }catch (HibernateException e) {
	         if (tx != null) tx.rollback();
	         throw new TasksPlatformException(DELETE_TASK_FAILED + e.getMessage());
	      }finally {
	    	 try {
	    		 session.close();
	    	 } catch (HibernateException e){
	    		 throw new TasksPlatformException(CLOSE_FAILED + e.getMessage());
	    	 } 
	      }
	}

	/*
	* Just like at IToDoListDao - getTasks
	*/	
	@Override
	public List<Task> getTasks(String userName) throws TasksPlatformException {
		
		  Session session = factory.openSession();
		  List<Task> tasks = null;
	      Transaction tx = null;
	      try{
	         tx = session.beginTransaction();
	         tasks = session.createQuery("from "+ Task.class.getName() + " task where task.userName='" + userName +"'").list(); 
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         throw new TasksPlatformException(GET_TASKS_FAILED + e.getMessage());
	      }finally {
	    	 try {
	    		 session.close();
	    	 } catch (HibernateException e){
	    		 throw new TasksPlatformException(CLOSE_FAILED + e.getMessage());
	    	 } 
	      }
	      return tasks;
	}

	/*
	* Just like at IToDoListDao - updateTask
	*/	
	@Override
	public void updateTask(int taskID, Task updatedTask) throws TasksPlatformException {
		
		  Session session = factory.openSession();
	      Transaction tx = null;
	      try{
	         tx = session.beginTransaction();
	         Task task = (Task)session.get(Task.class, taskID); 
	         
	         if ( updatedTask.getUserName().equals(task.getUserName()) ) {
	
	        	 task.setTitle(updatedTask.getTitle());
		         task.setTaskBody(updatedTask.getTaskBody());		         
				 session.update(task); 
		         tx.commit();
	         }
	         
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         throw new TasksPlatformException(UPDATE_TASK_FAILED);
	      }finally {
	    	 try {
	    		 session.close();
	    	 } catch (HibernateException e){
	    		 throw new TasksPlatformException(CLOSE_FAILED);
	    	 } 
	      }
	}
	
	/*
	* Just like at IToDoListDao- signup
	*/	
	public User signup(User user) throws UserPlatformException{
		Session session = factory.openSession();
		Transaction tx = null;
		int id = 0;
		try{
			tx = session.beginTransaction();
			id = (Integer) session.save(user); 
			tx.commit();
		}catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			throw new UserPlatformException(SIGN_UP_FAILED); 
		}finally {
			try {
				session.close();
			} catch (HibernateException e){
				throw new UserPlatformException(CLOSE_FAILED);
			} 
		}
		if (id != 0) {
			return user;	
		}
		return null;
	}	
	
	/*
	* Just like at IToDoListDao - getUser
	*/	
	public User getUser(String mail, String password) throws UserPlatformException{
		Session session = factory.openSession();
	      Transaction tx = null;
	      //User tempUser = new User(mail, password);
	      //User user = null;
	      List<User> users = null;
	      try{
	         tx = session.beginTransaction();
	         //user = (User)session.get(User.class, tempUser.getMail()); 
	         users = session.createQuery("from " + User.class.getName() + " user where user.mail ='" + mail +"'").list();
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         	throw new UserPlatformException(LOGIN_FAILED);
	      }finally {
	    	 try {
	    		session.close();
	    	 } catch(HibernateException e){
	    		 throw new UserPlatformException(CLOSE_FAILED);
	    	 }
	      }
	      if (users.size() > 0) {
		      return users.get(0);	
	      }
	      return null;
	}	
}
