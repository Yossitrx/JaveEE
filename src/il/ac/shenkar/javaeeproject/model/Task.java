package il.ac.shenkar.javaeeproject.model;

import javax.persistence.*;

/**
 * Task Class
 * TASK table in the Database
 */
@Entity
@Table(name = "TASK")
public class Task {

	
	//Every define variables represent an column in the Task table
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	   @Column(name = "task_id")
	   private int taskID;
	
		@Column (name = "user_id")
		private String userName;

	   @Column(name = "title")
	   private String title;

	   @Column(name = "task_body")
	   private String taskBody;


	   /**
	   * constructor
	   */
	   public Task() {}
	   
	   /**
	   * constructor
	   * @param userName
	   * @param title
	   * @param description
	   */
	   public Task(String userName, String title, String description) {
		   this.setUserName(userName);
		   this.setTitle(title);
		   this.setTaskBody(description);
	   }
	   
	   
	   /**
	   * getter to taskID field
	   * @return taskID
	   */
	   public int getTaskId() {
	      return taskID;
	   }
	   
	   /**
	   * setter to taskID field
	   * @param user taskID
	   */
	   public void setId( int taskID ) {
	      this.taskID = taskID;
	   }
	   
	   /**
	   * getter to userName field
	   * @return userName
	   */
	   public String getUserName() {
	      return userName;
	   }
		   
	   /**
	   * setter to userName field
	   * @param userName
	   */
	   public void setUserName( String userName ) {
		      this.userName = userName;
	   	}
	   
	   /**
	   * getter to title field
	   * @return title
	   */
	   public String getTitle() {
	      return title;
	   }
	   
	   /**
	   * setter to title field
	   * @param title
	   */
	   public void setTitle( String title ) {
	      this.title = title;
	   }
	   
	   /**
	   * getter to taskBody field
	   * @return taskBody
	   */
	   public String getTaskBody() {
	      return taskBody;
	   }
	   
	   /**
	   * setter to taskBody field
	   * @param taskBody
	   */
	   public void setTaskBody( String taskBody ) {
	      this.taskBody = taskBody;
	   }
}
