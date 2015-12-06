package il.ac.shenkar.javaeeproject.model;

import javax.persistence.*;


@Entity
@Table(name = "TASK")
public class Task {

	@Id @GeneratedValue
	   @Column(name = "id")
	   private int id;

	   @Column(name = "title")
	   private String title;

	   @Column(name = "task_body")
	   private String taskBody;


	   public Task() {}
	   
	   public int getId() {
	      return id;
	   }
	   
	   public void setId( int id ) {
	      this.id = id;
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
