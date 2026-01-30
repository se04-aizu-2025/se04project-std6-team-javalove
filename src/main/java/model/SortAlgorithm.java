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
  protected static final Color WHITE = new Color(245, 246, 248);
  protected static final Color BLACK = new Color(90, 90, 90);
  protected static final Color BLUE = new Color(74, 144, 226);
  protected static final Color GRAY = new Color(160, 160, 160);
  protected static final Color ORANGE = new Color(243, 156, 18);
  protected static final Color RED = new Color(231, 76, 60);
  protected static final Color GREEN = new Color(46, 204, 113);
  protected static final Color PURPLE = new Color(155, 89, 182);
  protected static final Color PINK = new Color(233, 30, 99);
  
  private List<T> list;
  private double time;
  private long maxUsedMemory = 0;
  protected Comparator<? super T> comp;
  protected final List<BufferedImage> frames = new ArrayList<>();
  protected final Set<Integer> fixedIndexes = new HashSet<>();
  
  public List<T> getList() {
    return List.copyOf(list);
  }
  
  public String getFormattedTime() {
    return String.format("%f ms", time);
  }
  
  public String getFormattedMaxUsedMemory() {
    return String.format("%f MB", time / 1024.0 / 1024.0);
  }
  
  public List<BufferedImage> getFrames() {
    return List.copyOf(frames);
  }
  
  public void execute(List<T> list, boolean rev, boolean visu, int cost) {
    comp = rev ? Comparator.reverseOrder() : Comparator.naturalOrder();
    long start, end;
    int sleepMs = (int) Math.pow(2, cost) - 1;
    if (visu) {
      start = System.nanoTime();
      sortWithVisualization(list, sleepMs);
      end = System.nanoTime();
    } else {
      start = System.nanoTime();
      sort(list, sleepMs);
      end = System.nanoTime();
    }
    this.list = list;
    time = (end - start) / 1_000_000.0;
  }

  protected abstract void sort(List<T> A, int sleepMs);
  protected abstract void sortWithVisualization(List<T> A, int sleepMs);

  protected void swap(List<T> list, int i, int j) {
    T tmp = list.get(i);
    list.set(i, list.get(j));
    list.set(j, tmp);
    record(list, i, j, true);
  }
  
  protected void set(List<T> A, int index, T value) {
    A.set(index, value);
  }
  
  protected void record(List<T> list, int i, int j) {
    frames.add(draw(list, i, j, false));
  }
  
  protected void record(List<T> list, int i, int j, boolean isSwaped) {
    frames.add(draw(list, i, j, isSwaped));
  }
  
  protected BufferedImage draw(List<T> list, int highlightIndex1, int highlightIndex2, boolean isSwaped) {
    final int size = list.size();
    if (size == 0) {
      return new BufferedImage(1, 1, BufferedImage.TYPE_BYTE_INDEXED);
    }
    final int baseWidth = 800;
    final int barWidth = baseWidth / size + 1;
    final int margin = barWidth * size / 40;
    final int canvasWidth = barWidth * size + margin * 2;
    final int canvasHeight = canvasWidth / 4;
    BufferedImage image = new BufferedImage(canvasWidth, canvasHeight, BufferedImage.TYPE_BYTE_INDEXED);
    Graphics2D g = image.createGraphics();
    g.setColor(WHITE);
    g.fillRect(0, 0, canvasWidth, canvasHeight);
    int[] values = new int[size];
    int min = Integer.MAX_VALUE;
    int max = Integer.MIN_VALUE;
    for (int i = 0; i < size; i++) {
      int v = Integer.parseInt(list.get(i).toString());
      values[i] = v;
      min = Math.min(min, v);
      max = Math.max(max, v);
    }
    max = Math.max(max, 0);
    min = Math.min(min, 0);
    int range = max - min;
    if (range == 0) {
      range = 1;
    }
    int drawableHeight = canvasHeight - margin * 2;
    int baselineY = margin + (int) ((double) max / range * drawableHeight);
    g.setColor(BLACK);
    g.drawLine(margin, baselineY, canvasWidth - margin, baselineY);
    for (int i = 0; i < size; i++) {
      int value = values[i];
      int barHeight = (int) (Math.abs((double) value) / range * drawableHeight);
      int x = margin + i * barWidth;
      int y = value >= 0 ? baselineY - barHeight : baselineY;
      if (i == highlightIndex1 || i == highlightIndex2) {
        g.setColor(isSwaped ? RED : ORANGE);
      } else if (fixedIndexes.contains(i)) {
        g.setColor(GRAY);
      } else {
        g.setColor(BLUE);
      }
      g.fillRect(x + 1, y, barWidth - 1, barHeight);
    }
    g.dispose();
    return image;
  }
  
  protected void sleep(int ms) {
    recordMemory();
    if (ms == 0) {
      return;
    }
    try {
      Thread.sleep(ms);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }

  private void recordMemory() {
    Runtime rt = Runtime.getRuntime();
    long used = rt.totalMemory() - rt.freeMemory();
    maxUsedMemory = Math.max(maxUsedMemory, used);
  }
}
