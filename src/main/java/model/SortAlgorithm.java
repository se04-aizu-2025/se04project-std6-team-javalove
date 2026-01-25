package model;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class SortAlgorithm<T extends Comparable<? super T>> {
  private List<T> list;
  protected Comparator<? super T> comp;
  private double time;
  protected final List<BufferedImage> frames = new ArrayList<>();
  protected final Set<Integer> fixedIndexes = new HashSet<>();
  
  public List<T> getList() {
    return List.copyOf(list);
  }
  
  public double getTime() {
    return time;
  }
  
  public List<BufferedImage> getFrames() {
    return List.copyOf(frames);
  }
  
  public void execute(List<T> list, boolean rev, boolean visu) {
    comp = rev ? Comparator.reverseOrder() : Comparator.naturalOrder();
    long start = System.nanoTime();
    if (visu) {
      sortWithVisualization(list);
    } else {
      sort(list);
    }
    long end = System.nanoTime();
    this.list = list;
    time = (end - start) / 1_000_000.0;
  }

  protected abstract void sort(List<T> list);
  protected abstract void sortWithVisualization(List<T> list);
  
  protected void record(List<T> list, int i, int j) {
    frames.add(draw(list, i, j));
  }
  
  protected BufferedImage draw(List<T> list, int highlightIndex1, int highlightIndex2) {
    int barWidth = 800 / list.size() + 1;
    int margin = barWidth * list.size() / 40;
    int w = barWidth * list.size() + 2 * margin;
    int h = w / 4;
    BufferedImage img = new BufferedImage(w, h, BufferedImage.TYPE_BYTE_INDEXED);
    Graphics2D g = img.createGraphics();
    g.setColor(new Color(245, 246, 248));
    g.fillRect(0, 0, w, h);
    int max = list.stream().mapToInt(e -> Integer.parseInt(e.toString())).max().orElse(0);
    int min = list.stream().mapToInt(e -> Integer.parseInt(e.toString())).min().orElse(0);
    max = Math.max(max, 0);
    min = Math.min(min, 0);
    int drawableHeight = h - 2 * margin;
    int range = max - min;
    int baselineY = margin + (int) ((double) max / range * drawableHeight);
    g.setColor(new Color(180, 180, 180));
    g.drawLine(margin, baselineY, w - margin, baselineY);
    for (int i = 0; i < list.size(); i++) {
      int v = Integer.parseInt(list.get(i).toString());
      int barHeight = (int) (Math.abs((double) v) / range * drawableHeight);
      int x = margin + i * barWidth;
      int y;
      if (v >= 0) {
        y = baselineY - barHeight;
      } else {
        y = baselineY;
      }
      if (i == highlightIndex1 || i == highlightIndex2) {
        g.setColor(new Color(255, 159, 67));
      } else if (fixedIndexes.contains(i)) {
        g.setColor(new Color(160, 160, 160));
      } else {
        g.setColor(new Color(74, 144, 226));
      }
      g.fillRect(x + 1, y, barWidth - 1, barHeight);
    }
    g.dispose();
    return img;
  }
}
