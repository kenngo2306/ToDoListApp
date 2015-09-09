package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import db.DBUser;
import model.Todouser;



/**
 * Servlet implementation class ServletLogin
 */
@WebServlet("/Login")
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("login post");
		String username = request.getParameter("userName");
		String password = request.getParameter("password");
		
		String errorMessage = "";
		
		Todouser tempUser = new Todouser();
		tempUser.setUserName(username);
		tempUser.setUserPassword(password);
		
		// get back a user id with a user name and password, if fail, return 0
		long userId = DBUser.login(tempUser);
		
		//if login success
		if (userId>0)
		{
			Todouser user = DBUser.getUser(userId);
			
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			
			System.out.println("login success");
			getServletContext().getRequestDispatcher("/ToDoList").forward(request, response);
		}
		//if login fail
		else
		{
			errorMessage="Login failed. Please try again!";
			request.setAttribute("errorMessage", errorMessage);
			getServletContext().getRequestDispatcher("/LoginForm.jsp").forward(request, response);
		}
	}

}
