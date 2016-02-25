package il.ac.shenkar.javaeeproject.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import il.ac.shenkar.javaeeproject.model.Iconsts;
import il.ac.shenkar.javaeeproject.model.Task;
import il.ac.shenkar.javaeeproject.model.TasksPlatformException;
import il.ac.shenkar.javaeeproject.model.ToDoListDAO;
import il.ac.shenkar.javaeeproject.model.User;
import il.ac.shenkar.javaeeproject.model.UserPlatformException;

/**
 * Servlet implementation class UserDaoController
 */
@WebServlet("/usercontroller/*")
public class UserDaoController extends HttpServlet implements Iconsts {
	
	private static final long serialVersionUID = 1L;
    private HttpSession session; 
    private final Logger logger;
    private RequestDispatcher dispatcher;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserDaoController() {
        super();
        logger = Logger.getLogger(UserDaoController.class);
        dispatcher  = null;
    }
   
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		StringBuffer sb = request.getRequestURL();
		String url = sb.toString();
//		String mail = null;
		
		//checking if the user is login
		this.session = request.getSession(true);
		User user = (User) session.getAttribute(CURRENT_USER);
		if (user == null) {
			if(url.endsWith(SIGNUP_PATH)){
				pageRouting(request, response, "/"+SIGNUP_PATH);
			} else {
				pageRouting(request, response, "/"+LOGIN_PATH);
			}
		} else {
			pageRouting(request, response, url);
		}
	}

	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.session = request.getSession(true);
		StringBuffer sb = request.getRequestURL();
		String url = sb.toString();
		String reqURL = null;
		
		//Login
		if (url.endsWith(LOGIN_PATH)) {
			reqURL = startLogin(request, response);
			
		//New sign up
		} else if (url.endsWith(NEW_SIGNUP_PATH)) {
			reqURL = newSignUp(request, response);
			
		//Insert new Task
		} else if(url.endsWith(INSERT_PATH)){
			reqURL = insertNewTask(request, response);
			
		//Remove the requested Task
		} else if (url.endsWith(DELETE_PATH)) {
			reqURL = removeTask(request, response);
			
		//Edit the requested Task	
        } else if (url.endsWith(EDIT_PATH)) {
        	reqURL = editTask(request, response);
        }	
		
		//Forward to the next page
		moveForward(request, response,"/"+reqURL);
	}
	
	
	/**
	 * this method called from the post method
	 * trying to connect with the parameters that insert by the user 
	 * @param request
	 * @param response
	 */
	private String startLogin(HttpServletRequest request, HttpServletResponse response) {
		
		String tempURL = null;
		String mail = request.getParameter(USER_NAME);
		String pass = request.getParameter(PASSWORD);
		if(!authentication(mail, pass)) {
			request.setAttribute(RESULT, MISSING_ARGUMENTS_ERROR);
			logger.error("This is error on login : " + MISSING_ARGUMENTS_ERROR);
			tempURL = LOGIN_PATH_JSP;
		} else {
			User currentUser = null;
			try {
				currentUser = ToDoListDAO.getInstance().getUser(mail, pass);
				if (currentUser != null && pass.equals(currentUser.getPassword())) {
					session.setAttribute(CURRENT_USER, currentUser);
					logger.info("User Login successfully");
					tempURL = MENU_PATH_JSP;
				}else {
					request.setAttribute(RESULT, INVALID_ARGUMENTS_ERROR);
					logger.error("This is error on login : " + INVALID_ARGUMENTS_ERROR);
					tempURL = LOGIN_PATH_JSP;
				}
			} catch (UserPlatformException e) {
				request.setAttribute(RESULT, e.getMessage());
				logger.error(e.getMessage());
				tempURL = LOGIN_PATH_JSP;
			}
		}
		return tempURL;
	}
	
	/**
	 * this method called from the post method
	 * trying to create new account with the parameters that insert by the user 
	 * @param request
	 * @param response
	 */
	private String newSignUp(HttpServletRequest request, HttpServletResponse response) {
		String tempURL = null;
		String mail = request.getParameter(USER_NAME);
		String pass = request.getParameter(PASSWORD);
		
		if(!authentication(mail, pass)) {
			request.setAttribute(RESULT, MISSING_ARGUMENTS_ERROR);
			logger.error("This is error on sign up : " + MISSING_ARGUMENTS_ERROR);
			tempURL = SIGNUP_PATH_JSP;
		} else if(!isValidEmail(mail)){
			request.setAttribute(RESULT, INVALID_USERNAME);
			logger.error("This is error on sign up : " + INVALID_USERNAME);
			tempURL = SIGNUP_PATH_JSP;
		} else{
			User newUser = new User(mail, pass);
			User currentUser = null;
			try {
				currentUser = ToDoListDAO.getInstance().signup(newUser);
				if (currentUser != null) {
					session.setAttribute(CURRENT_USER, currentUser);
					tempURL = MENU_PATH_JSP;
					request.setAttribute(USER_NAME, mail);
				} else {
					tempURL = SIGNUP_PATH_JSP;
				}
			} catch (UserPlatformException e) {
				request.setAttribute(RESULT, e.getMessage());
				logger.error("This is error on sign up : " + e.getMessage());
				tempURL = SIGNUP_PATH_JSP;
			}
		}
		return tempURL;
	}
	
	/**
	 * this method called from the post method
	 * check if the requested task exists and trying to edit it 
	 * @param request
	 * @param response
	 */
	private String editTask(HttpServletRequest request, HttpServletResponse response) {
		String tempURL = null;
		String taskId = request.getParameter(ID); 
		String title = request.getParameter(TITLE);
		String description = request.getParameter(DESCRIPTION);
		
		if(!authentication(taskId, title, description)) {
			request.setAttribute(RESULT, FILL_FIELDS);
			logger.error("This is error on edit task : " + FILL_FIELDS);
			tempURL = EDIT_TASK_PATH_JSP;
		} else {
			User currentUser = (User) session.getAttribute(CURRENT_USER);
			String mail = currentUser.getMail();
			HashMap<Integer, Integer> tasksId = (HashMap<Integer, Integer>) session.getAttribute("HASH");
			//check if taskId is a valid integer
			int id = 0;
			try {
				id = Integer.parseInt(taskId);
			} catch(NumberFormatException e) {
				request.setAttribute("RESULT", "ID field must be a Number");
				tempURL = EDIT_TASK_PATH_JSP;
				return tempURL;
			}
			if ( tasksId.containsKey(id)) {
				id = tasksId.get(id);
				try {
					Task updatedTask = new Task(mail, title, description);
					ToDoListDAO.getInstance().updateTask(id, updatedTask);
					request.setAttribute(RESULT, "Task No " + taskId + " :  was Edited");
				} 
				catch (TasksPlatformException e) {
					request.setAttribute("RESULT", e.getMessage());
					tempURL = EDIT_TASK_PATH_JSP;
				}
				List<Task> tasks = getTaskList(mail);
				if (tasks != null) {
					request.setAttribute(ALL_TASKS, tasks);
					tempURL = TASK_LIST_PATH_JSP;
				} else {
					request.setAttribute(RESULT, LIST_ERROR);
					logger.error("This is error on tasklist : " + LIST_ERROR);
					tempURL = MENU_PATH_JSP;
				}
			} else {
				request.setAttribute(RESULT, "Task No " + taskId + " :  not Exist");
				tempURL = EDIT_TASK_PATH_JSP;
			}
		}	
		return tempURL;
	}

	/**
	 * this method called from the post method
	 * check if the requested task exists and trying to delete it
	 * @param request
	 * @param response
	 */
	private String removeTask(HttpServletRequest request, HttpServletResponse response) {
		
		String tempURL = null;
		String taskId = request.getParameter(ID);
		if(!authentication(taskId)) {
			request.setAttribute(RESULT, FILL_FIELDS);
			logger.error("This is error on remove task : " + FILL_FIELDS);
			tempURL = REMOVE_TASK_PATH_JSP;
		} else {
			User currentUser = (User) session.getAttribute(CURRENT_USER);
			String mail = currentUser.getMail();
			HashMap<Integer, Integer> tasksId = (HashMap<Integer, Integer>) session.getAttribute("HASH");
			int id = 0;
			try {
				id = Integer.parseInt(taskId);
			} catch(NumberFormatException e) {
				request.setAttribute("RESULT", "ID field must be a Number");
				tempURL = REMOVE_TASK_PATH_JSP;
				return tempURL;
			}
			if(tasksId.containsKey(id)){
				id = tasksId.get(id);
				try {
					ToDoListDAO.getInstance().deleteTask(mail, id);
					request.setAttribute(RESULT, "Task No " + taskId + " :  was Removed");
				} 
				catch (TasksPlatformException e) {
					request.setAttribute("RESULT", e.getMessage());
					tempURL = REMOVE_TASK_PATH_JSP;
				}
				//Update the list 
				List<Task> tasks = getTaskList(mail);
				if (tasks != null) {
					request.setAttribute(ALL_TASKS, tasks);
					tempURL = TASK_LIST_PATH_JSP;
				} else {
					request.setAttribute(RESULT, LIST_ERROR);
					logger.error("This is error on tasklist : " + LIST_ERROR);
					tempURL = MENU_PATH_JSP;
				}
			} else {
				request.setAttribute(RESULT, "Task No " + taskId + " :  not Exist");
				tempURL = REMOVE_TASK_PATH_JSP;
			}
		}
		return tempURL;
	}


	/**
	 * this method called from the post method
	 * get the parameter that insert by the user , create new Task and insert it into the Database
	 * @param request
	 * @param response
	 */
	private String insertNewTask(HttpServletRequest request, HttpServletResponse response) {
		String tempURL = null;
		String title = request.getParameter(TITLE);
		String description = request.getParameter(DESCRIPTION);
		if(!authentication(title, description)) {
			request.setAttribute(RESULT, FILL_FIELDS);
			logger.error("This is error on add task : " + FILL_FIELDS);
			tempURL = ADD_TASK_PATH_JSP;
		} else {
			User currentUser = (User) session.getAttribute(CURRENT_USER);
			String mail = currentUser.getMail();
			Task newTask = new Task(mail, title, description);
			try {
				ToDoListDAO.getInstance().addTask(newTask);
				request.setAttribute(RESULT, "Task  : "  + title + " was Added");
				logger.info("Task  : "  + newTask + " was Added");
			} 
			catch (TasksPlatformException e) {
				request.setAttribute("RESULT", e.getMessage());
				tempURL = ADD_TASK_PATH_JSP;
				logger.error(e.getMessage());
			}
			
			List<Task> tasks = getTaskList(mail);
			if (tasks != null) {
				request.setAttribute(ALL_TASKS, tasks);
				tempURL = TASK_LIST_PATH_JSP;
				logger.info("Sending to user a list of his tasks");
			} else {
				request.setAttribute(RESULT, LIST_ERROR);
				logger.error("This is error on tasklist : " + LIST_ERROR);
				tempURL = MENU_PATH_JSP;
			}
		}
		return tempURL;
	}

	/**
	 * this method called from the GET method and routing to requested URL
	 * select the action by URL address
	 * @param request
	 * @param response
	 * @param url
	 * @param mail
	 * @throws ServletException
	 * @throws IOException
	 */
	private void pageRouting(HttpServletRequest request, HttpServletResponse response, 
			String url) throws ServletException, IOException {
		
		User currentUser = (User) session.getAttribute(CURRENT_USER);
		
		//Log out from the current user
		if (url.endsWith(LOGOUT_PATH)) {
			session.invalidate();
			moveForward(request, response, LOGIN_PATH_JSP);
			
		//Move to SIGNUP page
		} else if (url.endsWith(SIGNUP_PATH)){
			moveForward(request, response, SIGNUP_PATH_JSP);
			
		//Move to LOGIN page
		} else if (url.endsWith(LOGIN_PATH)) {
			moveForward(request, response, LOGIN_PATH_JSP);
			
		//Move to ABOUT page
		} else if (url.endsWith(ABOUT_PATH)) {
			moveForward(request, response, ABOUT_PATH_JSP);
		
		//Move to Task page
		} else if (url.endsWith(LIST_PATH)) {
			String email = currentUser.getMail();
			List<Task> tasks = getTaskList(email);
			if (tasks != null) {
				request.setAttribute(ALL_TASKS, tasks);
				moveForward(request, response, TASK_LIST_PATH_JSP);
			} else {
				request.setAttribute("ERROR", "");
				moveForward(request, response, ERROR_PATH_JSP);
			}
		} 
		//Move to add task page
		else if (url.endsWith(ADD_TASK_PATH)) {
			moveForward(request, response, ADD_TASK_PATH_JSP);
		
		//Move to remove task page
		} else if (url.endsWith(REMOVE_TASK_PATH)) {
			moveForward(request, response, REMOVE_TASK_PATH_JSP);
		
		//Move to edit task page
		} else if (url.endsWith(EDIT_TASK_PATH)) {
			moveForward(request, response, EDIT_TASK_PATH_JSP);
		
		//Default - move to MENU page
		} else {
			moveForward(request, response, MENU_PATH_JSP);
		}
	}
	
	
	/**
	 * move forward to the requested page (URL)
	 * @param request
	 * @param response
	 * @param url
	 */
	private void moveForward(HttpServletRequest request, 
			HttpServletResponse response, String url) throws ServletException, IOException {
		
		dispatcher = getServletContext().getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

	
	/**
	 * this method get the current user name that login and return his task list
	 * @param userName
	 * @return List<Task>
	 */
	private List<Task> getTaskList(String userName){
		List<Task> tasks = null;
		try {
			tasks = ToDoListDAO.getInstance().getTasks(userName);
		} 
		catch (TasksPlatformException e) {
			tasks = null;			
		}
		return tasks;
	}
	
	/**
	 * this method called from Login or sign up
	 * checked if the parameters that insert by the user is valid 
	 * @param ... String
	 * @return boolean
	 */
	public boolean authentication(String... strings){
		boolean flag = true;
	    for(String str : strings){
	    	if(str.isEmpty()) flag = false;
	    }
	    return flag;
	}
	
	/**
	 * this method checked if the email address is a valid email
	 * @param emailAddress
	 * @return boolean
	 */
	public boolean isValidEmail(String emailAddress) {
	    return emailAddress.contains(" ") == false && emailAddress.matches(".+@.+\\.[a-z]+");
	} 
	
}
