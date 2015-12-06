package il.ac.shenkar.javaeeproject.model;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ToDoListDAO dao = ToDoListDAO.getInstance();
				
		try {
			
			
		      /* Add few employee records in database */
			
			dao.logIn(1, "morkasus3@gmail.com", "123456");
			dao.logIn(2, "yossitrx@gmail.com", "123456");

				
		      Integer taskID1 = dao.addTask(1, "HW", "Math : pages 1-17");
		      Integer taskID2 = dao.addTask(1, "HW", "English : pages 23-44");
		      Integer taskID3 = dao.addTask(2, "HW", "Biology : pages 4-9");
	
		      /* List down all the employees */
		      dao.getTasks(1);
	
		      /* Update employee's records */
		      dao.updateTask(1,1,"HW1", "Math : pages 22-33");
	
		      /* Delete an employee from the database */
		      dao.deleteTask(1,taskID2);
	
		      /* List down new list of the employees */
		      dao.getTasks(1);
		      
		} catch(TasksPlatformException e) {
			e.printStackTrace();
		}
	}

}
