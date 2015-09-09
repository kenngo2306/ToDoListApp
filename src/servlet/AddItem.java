package servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import db.DBUser;
import model.Todoitem;
import model.Todouser;

/**
 * Servlet implementation class AddItem
 */
@WebServlet("/AddItem")
public class AddItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddItem() {
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
		
		String description = request.getParameter("description");
		String dueDateStr = request.getParameter("dueDate");
		String status = request.getParameter("status");
		System.out.println("status = " + status);
		String priorityStr = request.getParameter("priority");
		
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Date dueDate = new Date();
		try
		{
			dueDate = sdf.parse(dueDateStr);
		} catch (ParseException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int priority = Integer.parseInt(priorityStr);
		
		HttpSession session = request.getSession();
		Todouser user= (Todouser)session.getAttribute("user");
		
		Todoitem todoitem =new Todoitem();
		todoitem.setDescription(description);
		todoitem.setDueDate(dueDate);
		todoitem.setItemPriority(priority);
		todoitem.setStatusCode(status);
		todoitem.setTodouser(user);
		
		user.addTodoitem(todoitem);
		
		DBUser.update(user);
		System.out.println("user updated");
		
		getServletContext().getRequestDispatcher("/ToDoList").forward(request, response);
	}

}
