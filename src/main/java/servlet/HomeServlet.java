package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.BubbleSort;
import model.InsertionSort;
import model.SelectionSort;
import model.SortAlgorithm;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  request.getRequestDispatcher("WEB-INF/views/home.jsp").forward(request, response);
  }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String in = request.getParameter("in");
    String algo = request.getParameter("algo");
    String[] opt = request.getParameterValues("opt");
    
    SortAlgorithm sa;
    if (algo.equals("bubble")) {
      sa = new BubbleSort();
    } else if (algo.equals("selection")) {
      sa = new SelectionSort();
    } else if (algo.equals("insertion")) {
      sa = new InsertionSort();
    } else {
      response.sendRedirect("home");
      return;
    }
    
    Set<String> optSet = new HashSet<>();
    if (opt != null) {
      optSet = Set.of(opt);
    }
    boolean str = optSet.contains("str");
    double time;
    List<?> out;
    if (str) {
      List<String> list = new ArrayList<>(Arrays.asList(in.trim().split("\\s+")));
      long start = System.nanoTime();
      sa.execute(list);
      long end = System.nanoTime();
      time = (end - start) / 1_000_000.0;
      out = list;
    } else {
      String[] tokens = in.trim().split("\\s+");
      for (String t : tokens) {
        if (!t.matches("-?\\d+")) {
          HttpSession session = request.getSession();
          session.setAttribute("in", in);
          session.setAttribute("str", str);
          session.removeAttribute("time");
          session.removeAttribute("out");
          session.setAttribute("err", "If you want to enter characters other than numbers, check \"String Mode\".");
          response.sendRedirect("home");
          return;
        }
      }
      List<Integer> list = new ArrayList<>(Arrays.stream(tokens).map(Integer::parseInt).toList());
      long start = System.nanoTime();
      sa.execute(list);
      long end = System.nanoTime();
      time = (end - start) / 1_000_000.0;
      out = list;
    }
    
    HttpSession session = request.getSession();
    session.setAttribute("in", in);
    session.setAttribute("str", str);
    session.setAttribute("time", time);
    session.setAttribute("out", out);
    session.removeAttribute("err");
    response.sendRedirect("home");
  }
}
