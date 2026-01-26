package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.SortAlgorithm;
import util.SortFactory;

@WebServlet("/sort")
public class SortServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String in = request.getParameter("in");
    String algo = request.getParameter("algo");
    String[] opt = request.getParameterValues("opt");
    String count = request.getParameter("count");
    String min = request.getParameter("min");
    String max = request.getParameter("max");
    String delay = request.getParameter("delay");
    int cost = Integer.parseInt(request.getParameter("cost"));
    Set<String> optSet = new HashSet<>();
    if (opt != null) {
      optSet = Set.of(opt);
    }
    boolean rev = optSet.contains("rev");
    boolean visu = optSet.contains("visu");
    HttpSession session = request.getSession();
    session.removeAttribute("out");
    session.removeAttribute("err");
    session.setAttribute("in", in);
    session.setAttribute("algo", algo);
    session.setAttribute("rev", rev);
    session.setAttribute("count", count);
    session.setAttribute("min", min);
    session.setAttribute("max", max);
    session.setAttribute("visu", visu);
    session.setAttribute("delay", delay);
    session.setAttribute("cost", cost);
    String[] tokens = in.trim().split("\\s+");
    for (String t : tokens) {
      if (!t.matches("-?\\d+")) {
        session.setAttribute("err", "Please enter numbers only.");
        response.sendRedirect("home");
        return;
      }
    }
    SortAlgorithm<Integer> out = SortFactory.create(algo);
    try {
      out.execute(new ArrayList<>(Arrays.stream(tokens).map(Integer::parseInt).toList()), rev, visu, cost);
    } catch (NumberFormatException e) {
      session.setAttribute("err", "Please enter a value between -2^31 and 2^31-1.");
      response.sendRedirect("home");
      return;
    }
    session.setAttribute("out", out);
    response.sendRedirect("home");
  }
}
