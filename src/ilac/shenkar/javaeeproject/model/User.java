package il.ac.shenkar.javaeeproject.model;

import javax.persistence.*;


@Entity
@Table(name = "USER")
public class User {

	
	@Id @GeneratedValue
		@Column (name = "user_id",unique=true)
		private int userID;

	   @Column(name = "mail")
	   private String mail;

	   @Column(name = "password")
	   private String password;


	   public User() {}
	   
	   public int getUserId() {
		      return userID;
		   }
		   
	   public void setUserId( int userID ) {
		      this.userID = userID;
	   	}
	   
	   public String getMail() {
	      return mail;
	   }
	   public void setMail( String mail ) {
	      this.mail = mail;
	   }
	   
	   public String getPassword() {
	      return password;
	   }
	   public void setPassword( String password ) {
	      this.password = password;
	   }
}
