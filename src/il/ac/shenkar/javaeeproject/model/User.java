package il.ac.shenkar.javaeeproject.model;

import javax.persistence.*;


/**
 * User class
 * USER table in the Database
 */
@Entity
@Table(name = "USER")
public class User {

	//Every define variables represent an column in the User table
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
		@Column (name = "user_id")
		private int userID;

	   @Column(name = "mail",unique=true)
	   private String mail;

	   @Column(name = "password")
	   private String password;


	   /**
	   * constructor
	   */
	   public User() {}
	   
	   /**
	   * constructor
	   * @param mail
	   * @param password
	   */
	   public User(String mail, String password) {
		   this.setMail(mail);
		   this.setPassword(password);
	   }
	   
	   /**
	   * getter to useId field
	   * @return user id
	   */
	   public int getUserId() {
		      return userID;
		   }
		
	   /**
	   * setter to useId field
	   * @param user id
	   */
	   public void setUserId( int userID ) {
		      this.userID = userID;
	   	}
	   
	   /**
	   * getter to mail field
	   * @return mail
	   */
	   public String getMail() {
	      return mail;
	   }
	   
	   /**
	   * setter to mail field
	   * @param mail
	   */
	   public void setMail( String mail ) {
	      this.mail = mail;
	   }
	   
	   
	   /**
	   * getter to password field
	   * @return password
	   */
	   public String getPassword() {
	      return password;
	   }
	   
	   /**
	   * setter to password field
	   * @param password
	   */
	   public void setPassword( String password ) {
	      this.password = password;
	   }
}
