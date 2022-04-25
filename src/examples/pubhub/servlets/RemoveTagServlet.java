package examples.pubhub.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import examples.pubhub.model.Book;
import examples.pubhub.model.Tag;
import examples.pubhub.dao.BookDAO;
import examples.pubhub.dao.TagDAO;
import examples.pubhub.dao.TagDAOImpl;
import examples.pubhub.utilities.DAOUtilities;




// Servlet implementation class RemoveTagServlet

@WebServlet("/RemoveTag")
public class RemoveTagServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean isSuccess = false;
		
		
		String isbn13 = request.getParameter("isbn13");
		Tag tag = new Tag();
		
		tag.setIsbn13(isbn13);
		tag.setTag(request.getParameter("tags"));
		
		TagDAO dao = DAOUtilities.getTagDAO();
		isSuccess = dao.RemoveTag(tag);
		
		
		
		
		
		if(isSuccess) {
			request.getSession().setAttribute("message", "Book Tag Successfully Removed.");
			request.getSession().setAttribute("messageClass", "alert-success");
			response.sendRedirect("ViewBookDetails?isbn13=" + isbn13);
		} else {
			request.getSession().setAttribute("message", "There was a problem updating this tag");
			request.getSession().setAttribute("messageClass", "alert-danger");
			request.getRequestDispatcher("bookDetails.jsp").forward(request, response);
		}
	}
}