package il.ac.shenkar.javaeeproject.model;

import javax.persistence.*;


@Entity
@Table(name = "TASK")
public class Task {

	
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	   @Column(name = "task_id")
	   private int taskID;
	
		@Column (name = "user_id")
		private int userID;

	   @Column(name = "title")
	   private String title;

	   @Column(name = "task_body")
	   private String taskBody;


	   public Task() {}
	   
	   public int getTaskId() {
	      return taskID;
	   }
	   
	   public void setId( int taskID ) {
	      this.taskID = taskID;
	   }
	   
	   public int getUserId() {
		      return userID;
		   }
		   
	   public void setUserId( int userID ) {
		      this.userID = userID;
	   	}
	   
	   public String getTitle() {
	      return title;
	   }
	   public void setTitle( String title ) {
	      this.title = title;
	   }
	   public String getTaskBody() {
	      return taskBody;
	   }
	   public void setTaskBody( String taskBody ) {
	      this.taskBody = taskBody;
	   }
}
