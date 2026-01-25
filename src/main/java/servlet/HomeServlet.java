package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  HttpSession session = request.getSession();
	  if (session.getAttribute("count") == null) {
	    session.setAttribute("count", 20);
	  }
	  if (session.getAttribute("min") == null) {
	    session.setAttribute("min", 0);
    }
	  if (session.getAttribute("max") == null) {
	    session.setAttribute("max", 100);
    }
    if (session.getAttribute("visu") == null) {
      session.setAttribute("visu", true);
    }
    if (session.getAttribute("delay") == null) {
      session.setAttribute("delay", 500);
    }
	  request.getRequestDispatcher("WEB-INF/views/home.jsp").forward(request, response);
  }
}
