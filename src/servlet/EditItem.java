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

import model.Todoitem;
import model.Todouser;
import db.DBItem;

/**
 * Servlet implementation class EditItem
 */
@WebServlet("/EditItem")
public class EditItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditItem() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String itemIdStr = request.getParameter("itemId");
		
		long itemId = Long.parseLong(itemIdStr);
		Todoitem item = DBItem.getItem(itemId);
		
		request.setAttribute("item", item);
		getServletContext().getRequestDispatcher("/EditItem.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String itemIdStr = request.getParameter("itemId");
		long itemId = Long.parseLong(itemIdStr);
		
		Todoitem item = DBItem.getItem(itemId);
		
		
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
		
		item.setDescription(description);
		item.setDueDate(dueDate);
		item.setItemPriority(priority);
		item.setStatusCode(status);
		item.setTodouser(user);
		
		DBItem.update(item);
		getServletContext().getRequestDispatcher("/ToDoList").forward(request, response);
		
	}

}
