package il.ac.shenkar.javaeeproject.model;

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;

public class ToDoListDAO implements IToDoListDao{

	
	private static ToDoListDAO instance;
	private static SessionFactory factory = new AnnotationConfiguration()
			.configure()
			.addAnnotatedClass(Task.class)
			.addAnnotatedClass(User.class)
			.buildSessionFactory();

	
	private ToDoListDAO() {}
	
	public static ToDoListDAO getInstance() {
		
		if (instance == null) {
			instance = new ToDoListDAO();
		}
		return instance;
	}
	
	
	
	@Override
	public int addTask(int userID, String title, String taskBody) throws TasksPlatformException {
		
		Session session = factory.openSession();
	      Transaction tx = null;
	      Integer id = null;
	      
	      try{
	         tx = session.beginTransaction();
	         Task task = new Task();
	         task.setUserId(userID);
	         task.setTitle(title);
	         task.setTaskBody(taskBody);
	         
	         id = (Integer) session.save(task); 
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	      return id;
	}

	
	@Override
	public void deleteTask(int userID, int taskID) throws TasksPlatformException {
		
	      Session session = factory.openSession();
	      Transaction tx = null;
	      try{
	         tx = session.beginTransaction();
	         Task task = (Task)session.get(Task.class, taskID); 
	         if ( userID == task.getUserId()) {
	        	 session.delete(task); 
		         tx.commit(); 
	         }
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	}

	
	@Override
	public void getTasks(int userId) throws TasksPlatformException {
		
		  Session session = factory.openSession();
	      Transaction tx = null;
	      try{
	         tx = session.beginTransaction();
	         List<Task> tasks = session.createQuery("FROM " + Task.class.getName()).list(); 
	         for (Iterator iterator = tasks.iterator(); iterator.hasNext();){
	            Task task = (Task) iterator.next(); 
	            if( task.getUserId() == userId) {
	            	System.out.print("Task ID: " + task.getTaskId()); 
		            System.out.print("  Title: " + task.getTitle()); 
		            System.out.println("  Body: " + task.getTaskBody());
	            } 
	         }
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	}

	
	@Override
	public void updateTask(int userID, int taskID, String title, String taskBody) throws TasksPlatformException {
		
		  Session session = factory.openSession();
	      Transaction tx = null;
	      try{
	         tx = session.beginTransaction();
	         Task task = (Task)session.get(Task.class, taskID); 
	         
	         if ( task.getUserId() == userID ) {
	        	 
	        	 task.setTitle(title);
		         task.setTaskBody(taskBody);
		         
				 session.update(task); 
		         tx.commit();
	         }
	         
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	}

	public int logIn(int userID, String mail, String password){
		Session session = factory.openSession();
	      Transaction tx = null;
	      Integer id = null;
	      
	      try{
	         tx = session.beginTransaction();
	         User user = new User();
	         user.setUserId(userID);
	         user.setMail(mail);
	         user.setPassword(password);
	         
	         id = (Integer) session.save(user); 
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	      return id;
		
	}



}
