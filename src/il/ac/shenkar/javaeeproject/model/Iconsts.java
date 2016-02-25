package il.ac.shenkar.javaeeproject.model;

/**
 *	All strings 
 */
public interface Iconsts {
		
	//CONTROLLER ERRORS
	final String MISSING_ARGUMENTS_ERROR = "Error: Missing Username or password.";
	final String INVALID_ARGUMENTS_ERROR = "Error: Invalid Username/password combination.";
	final String INVALID_USERNAME = "Error: Invalid Username.";
	final String FILL_FIELDS = "Please fill all the fields";
	final String LIST_ERROR = "Sorry cant show your tasks please try again !!!";
	
	//HIBERNATE ERRORS 
	final String LOGIN_FAILED = "Error: login failed check the connection";
	final String SIGN_UP_FAILED = "Error: signup failed, check the connection or username is already exist";
	final String GET_TASKS_FAILED = "Error: get the tasks failed";
	final String UPDATE_TASK_FAILED = "Error: update task failed, check the connection";
	final String ADD_TASK_FAILED = "Error: insert task failed, check the connection";
	final String DELETE_TASK_FAILED = "Error: delete task failed, check the connection";
	final String CLOSE_FAILED = "Error: connection dosn't closed";
	final String TASK_ACCESS = "Error, You don't have access to this task";
	

	//ATTRIBUTES
	final String USER_NAME = "mail";
	final String PASSWORD = "pass";
	final String RESULT = "RESULT";
	final String CURRENT_USER = "user";
	final String TITLE = "title";
	final String DESCRIPTION = "taskBody";
	final String ID = "id";
	final String ALL_TASKS = "tasks";

	//URL STRINGS logout
	final String LOGIN_PATH = "login";
	final String LOGOUT_PATH = "logout";
	final String MENU_PATH = "menu";
	final String ABOUT_PATH = "about";
	final String SIGNUP_PATH = "signup";
	final String LIST_PATH = "list";
	final String NEW_SIGNUP_PATH = "newSignup";
	final String INSERT_PATH = "insert";
	final String DELETE_PATH = "delete";
	final String EDIT_PATH = "edit";
	final String ADD_TASK_PATH = "addTask";
	final String REMOVE_TASK_PATH = "removetask";
	final String EDIT_TASK_PATH = "edittask";
	final String TASKS_LIST_PATH = "tasklist";
	
	//JSP URLS 
	final String LOGIN_PATH_JSP = "/login.jsp";
	final String MENU_PATH_JSP = "/menu.jsp";
	final String SIGNUP_PATH_JSP = "/signup.jsp";
	final String ADD_TASK_PATH_JSP = "/addTask.jsp";
	final String ERROR_PATH_JSP = "/error.jsp";
	final String TASK_LIST_PATH_JSP = "/tasklist.jsp";
	final String REMOVE_TASK_PATH_JSP = "/removetask.jsp";
	final String EDIT_TASK_PATH_JSP = "/edittask.jsp";
	final String ABOUT_PATH_JSP = "/about.jsp";
}
