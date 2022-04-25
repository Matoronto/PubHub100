package examples.pubhub.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import examples.pubhub.dao.TagDAO;
import examples.pubhub.model.Tag;
import examples.pubhub.utilities.DAOUtilities;
/**
 * Servlet implementation class AddTag
 */
@WebServlet("/AddTag")
public class AddTagServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException { boolean isSuccess = false;
			
			String isbn13 = request.getParameter("isbn13");
		    Tag tag = new Tag();

		    tag.setIsbn13(isbn13);
		    tag.setTag(request.getParameter("tag"));

		    TagDAO tagdao = DAOUtilities.getTagDAO();
		    isSuccess = tagdao.AddTag(tag);
			
			if(isSuccess) {
				request.getSession().setAttribute("message", "Book Tag Successfully Added.");
				request.getSession().setAttribute("messageClass", "alert-success");
				response.sendRedirect(request.getContextPath() + "/ViewBookDetails?isbn13=" + isbn13);
			} else {
				request.getSession().setAttribute("message", "There was a problem updating this tag.");
				request.getSession().setAttribute("messageClass", "alert-danger");
				request.getRequestDispatcher("bookDetails.jsp").forward(request, response);
				
			}
	}
}
	
			
		
	
