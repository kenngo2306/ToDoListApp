package servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import db.DBUser;
import model.Todouser;

/**
 * Servlet implementation class ServletRegisterUser
 */
@WebServlet("/RegisterUser")
public class ServletRegisterUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletRegisterUser() {
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
		System.out.println("dopost");

		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		
		//set default userRole 

		
		
		
		Todouser user = new Todouser();

		user.setUserName(userName);
		user.setUserPassword(password);

		if (DBUser.isAvailable(user))
		{
			DBUser.insert(user);
			request.setAttribute("goodMessage", "Congratz! Account created");
			getServletContext().getRequestDispatcher("/LoginForm.jsp").forward(request, response);
		}
		
		else
		{
			request.setAttribute("errorMessage", "Error! Username and Email have already taken");
			getServletContext().getRequestDispatcher("/RegisterUser.jsp").forward(request, response);
		}
		
		

		
	}

}
