package il.ac.shenkar.javaeeproject.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import il.ac.shenkar.javaeeproject.model.ToDoListDAO;

/**
 * Servlet implementation class TaskDaoController
 */
@WebServlet("/controller/*")
public class TaskDaoController extends HttpServlet {
	
	
	private static final long serialVersionUID = 1L;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TaskDaoController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		
		PrintWriter out = response.getWriter();		
//		StringBuffer sb = request.getRequestURL();
//		String url = sb.toString();
//		
//		//creating a variable for storing the reference for the request dispatcher
//		RequestDispatcher dispatcher = null;
//		
//		//comparing the uri of the request to possible texts
//		if(url.endsWith("about")) {		
//			dispatcher = getServletContext().getRequestDispatcher("/about.jsp");
//			
//		} else if(url.endsWith("help")) {
//			dispatcher = getServletContext().getRequestDispatcher("/help.jsp");	
//			
//		} else if(url.endsWith("tasks")) {
//			
//			dispatcher = getServletContext().getRequestDispatcher("/coupons.jsp");
//			
//			List tasks = null;
//			try {
//				tasks = ToDoListDAO.getInstance().getTasks();
//			} 
//			catch (TasksPlatformException e) {
//				dispatcher = getServletContext().getRequestDispatcher("/error.jsp");			
//				e.printStackTrace();
//			}
//			request.setAttribute("tasks", tasks);
//			
//		} else {
//			dispatcher = getServletContext().getRequestDispatcher("/error.jsp");			
//		}
//		
//		//forward the execution to the jsp document that responsible for returning
//		//the view for the very specific action the client sent a request to perform
//		dispatcher.forward(request,response);
		
		out.println("here");

		ToDoListDAO dao = ToDoListDAO.getInstance();
		
//		dao.configuration();
//		
//		
//		try {
//			
//		      /* Add few employee records in database */
//		      Integer taskID1 = dao.addTask(1, "HW", "Math : pages 1-17");
//		      Integer taskID2 = dao.addTask(2, "HW", "English : pages 23-44");
//		      Integer taskID3 = dao.addTask(3, "HW", "Biology : pages 4-9");
//	
//		      /* List down all the employees */
//		      dao.getTasks();
//	
//		      /* Update employee's records */
//		      dao.updateTask(1, "HW1", "Math : pages 22-33");
//	
//		      /* Delete an employee from the database */
//		      dao.deleteTask(taskID2);
//	
//		      /* List down new list of the employees */
//		      dao.getTasks();
//		} catch(TasksPlatformException e) {
//			e.printStackTrace();
//		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
