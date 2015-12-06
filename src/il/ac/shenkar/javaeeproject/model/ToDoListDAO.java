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
	private static SessionFactory factory;

	
	private ToDoListDAO() {}
	
	public static ToDoListDAO getInstance() {
		
		System.out.println("SFasf");
		if (instance == null) {
			instance = new ToDoListDAO();
		}
		return instance;
	}
	
	
	
	public void configuration() {
		try{
            factory = new AnnotationConfiguration().
                      configure().
                      addAnnotatedClass(Task.class).
                      buildSessionFactory();
         }catch (Throwable e) { 
            System.err.println("Failed to create sessionFactory object." + e);
            throw new ExceptionInInitializerError(e); 
         }
	}
	
	@Override
	public int addTask(int taskID, String title, String taskBody) throws TasksPlatformException {
		
		Session session = factory.openSession();
	      Transaction tx = null;
	      Integer id = null;
	      try{
	         tx = session.beginTransaction();
	         Task task = new Task();
	         task.setId(taskID);
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
	public void deleteTask(int taskID) throws TasksPlatformException {
		
	      Session session = factory.openSession();
	      Transaction tx = null;
	      try{
	         tx = session.beginTransaction();
	         Task task = (Task)session.get(Task.class, taskID); 
	         session.delete(task); 
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	}

	@Override
	public void getTasks() throws TasksPlatformException {
		
		  Session session = factory.openSession();
	      Transaction tx = null;
	      try{
	         tx = session.beginTransaction();
	         List tasks = session.createQuery("FROM TASK").list(); 
	         for (Iterator iterator = tasks.iterator(); iterator.hasNext();){
	            Task task = (Task) iterator.next(); 
	            System.out.print("Task ID: " + task.getId()); 
	            System.out.print("  Title: " + task.getTitle()); 
	            System.out.println("  Body: " + task.getTaskBody()); 
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
	public void updateTask(int taskID, String title, String taskBody) throws TasksPlatformException {
		
		  Session session = factory.openSession();
	      Transaction tx = null;
	      try{
	         tx = session.beginTransaction();
	         Task task = (Task)session.get(Task.class, taskID); 
	         
	         task.setTitle(title);
	         task.setTaskBody(taskBody);
	         
			 session.update(task); 
	         tx.commit();
	         
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	}


}
