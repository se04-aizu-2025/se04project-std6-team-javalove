package servlet;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.util.List;

import com.madgag.gif.fmsware.AnimatedGifEncoder;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.SortAlgorithm;

@WebServlet("/gif")
public class GifServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession();
    int delay = Integer.parseInt((String) session.getAttribute("delay"));
    SortAlgorithm<?> out = (SortAlgorithm<?>) session.getAttribute("out");
    List<BufferedImage> frames = out.getFrames();
    response.setContentType("image/gif");
    try (var os = new BufferedOutputStream(response.getOutputStream())) {
      AnimatedGifEncoder gif = new AnimatedGifEncoder();
      gif.start(os);
      gif.setQuality(40);
      gif.setRepeat(0);
      gif.setDelay(delay);
      for (BufferedImage img : frames) {
        gif.addFrame(img);
      }
      gif.setDelay(2000);
      gif.addFrame(frames.get(frames.size() - 1));
      gif.finish();
    }
  }
}
