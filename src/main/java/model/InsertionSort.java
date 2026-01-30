package model;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.List;

public class InsertionSort<T extends Comparable<? super T>> extends SortAlgorithm<T> {
  public void sort(List<T> A, int sleepMs) {
    for (int i = 1; i < A.size(); i++) {
      T key = A.get(i);
      int j = i - 1;
      while (j >= 0) {
        sleep(sleepMs);
        if (comp.compare(A.get(j), key) > 0) {
          set(A, j + 1, A.get(j));
          j--;
        } else {
          break;
        }
      }
      set(A, j + 1, key);
    }
  }
  
  public void sortWithVisualization(List<T> A, int sleepMs) {
    fixedIndexes.clear();
    record(A, -1, -1, null);
    fixedIndexes.add(0);
    for (int i = 1; i < A.size(); i++) {
      T key = A.get(i);
      int j = i - 1;
      while (j >= 0) {
        sleep(sleepMs);
        record(A, j, -1, key);
        if (comp.compare(A.get(j), key) > 0) {
          set(A, j + 1, A.get(j));
          record(A, j + 1, j, true, key);
          j--;
        } else {
          break;
        }
      }
      set(A, j + 1, key);
      fixedIndexes.add(i);
    }
    record(A, -1, -1, null);
  }
  
  protected void record(List<T> list, int i, int j, T key) {
    frames.add(draw(list, i, j, false, key));
  }
  
  protected void record(List<T> list, int i, int j, boolean isSwaped, T key) {
    frames.add(draw(list, i, j, isSwaped, key));
  }
  
  protected BufferedImage draw(List<T> list, int highlightIndex1, int highlightIndex2, boolean isSwaped, T key) {
    final int size = list.size();
    if (size == 0) {
      return new BufferedImage(1, 1, BufferedImage.TYPE_BYTE_INDEXED);
    }
    final int baseWidth = 800;
    final int barWidth = baseWidth / (size + 1) + 1;
    final int margin = barWidth * (size + 1) / 40;
    final int canvasWidth = barWidth * (size + 1) + margin * 2;
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
    if (key != null) {
      int keyValue =  Integer.parseInt(key.toString());
      int barHeight = (int) (Math.abs((double) keyValue) / range * drawableHeight);
      int keyX = margin + size * barWidth;
      int keyY = keyValue >= 0 ? baselineY - barHeight : baselineY;
      g.setColor(PINK);
      g.fillRect(keyX + 1, keyY, barWidth - 1, barHeight);
      g.setColor(BLACK);
      g.drawString("key", keyX + 4, margin - 4);
    }
    g.dispose();
    return image;
  }
}
