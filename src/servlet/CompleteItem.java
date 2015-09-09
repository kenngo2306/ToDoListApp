package servlet;

import java.io.IOException;
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
 * Servlet implementation class CompleteItem
 */
@WebServlet("/CompleteItem")
public class CompleteItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CompleteItem() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String itemIdStr = request.getParameter("itemId");
		long itemId = Long.parseLong(itemIdStr);
		
		Todoitem item = DBItem.getItem(itemId);
		
		HttpSession session = request.getSession();
		Todouser user = (Todouser) session.getAttribute("user");
		
		item.setTodouser(user);
		
		Date completedDate = new Date();
		item.setCompletedDate(completedDate);
		item.setStatusCode("DONE");
		
		DBItem.update(item);
		getServletContext().getRequestDispatcher("/ToDoList").forward(request, response);
		
	}

}
