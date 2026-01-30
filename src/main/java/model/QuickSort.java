package model;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.List;

public class QuickSort<T extends Comparable<? super T>> extends SortAlgorithm<T> {
  public void sort(List<T> A, int sleepMs) {
    quickSort(A, 0, A.size() - 1, sleepMs);
  }

  protected void quickSort(List<T> A, int low, int high, int sleepMs) {
    if (low < high) {
      int p = partition(A, low, high, sleepMs);
      quickSort(A, low, p - 1, sleepMs);
      quickSort(A, p + 1, high, sleepMs);
    }
  }
  
  protected int partition(List<T> A, int low, int high, int sleepMs) {
    T pivot = A.get(high);
    int i = low - 1;
    for (int j = low; j < high; j++) {
      sleep(sleepMs);
      if (comp.compare(A.get(j), pivot) <= 0) {
        i++;
        swap(A, i, j);
      }
    }
    swap(A, i + 1, high);
    return i + 1;
  }
  
  public void sortWithVisualization(List<T> A, int sleepMs) {
    fixedIndexes.clear();
    record(A, -1, -1);
    quickSortWithVisualization(A, 0, A.size() - 1, sleepMs);
    record(A, -1, -1);
  }

  protected void quickSortWithVisualization(List<T> A, int low, int high, int sleepMs) {
    if (low < high) {
      int p = partitionWithVisualization(A, low, high, sleepMs);
      fixedIndexes.add(p);
      quickSortWithVisualization(A, low, p - 1, sleepMs);
      quickSortWithVisualization(A, p + 1, high, sleepMs);
    } else {
      fixedIndexes.add(low);
    }
  }
  
  protected int partitionWithVisualization(List<T> A, int low, int high, int sleepMs) {
    T pivot = A.get(high);
    int i = low - 1;
    for (int j = low; j < high; j++) {
      sleep(sleepMs);
      record(A, i, j, high);
      if (comp.compare(A.get(j), pivot) <= 0) {
        i++;
        swap(A, i, j);
      }
    }
    swap(A, i + 1, high);
    return i + 1;
  }
  
  protected void record(List<T> list, int i, int j, int pivotIndex) {
    frames.add(draw(list, i, j, pivotIndex, false));
  }
  
  protected void record(List<T> list, int i, int j, int pivotIndex, boolean isSwaped) {
    frames.add(draw(list, i, j, pivotIndex, isSwaped));
  }
  
  protected BufferedImage draw(List<T> list, int highlightIndex1, int highlightIndex2, int pivotIndex, boolean isSwaped) {
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
    if (range == 0) range = 1;
    int drawableHeight = canvasHeight - margin * 2;
    int baselineY = margin + (int) ((double) max / range * drawableHeight);
    g.setColor(BLACK);
    g.drawLine(margin, baselineY, canvasWidth - margin, baselineY);
    for (int i = 0; i < size; i++) {
      int value = values[i];
      int barHeight = (int) (Math.abs((double) value) / range * drawableHeight);
      int x = margin + i * barWidth;
      int y = value >= 0 ? baselineY - barHeight : baselineY;
      if (i == pivotIndex) {
        g.setColor(PINK);
      } else if (i == highlightIndex1 || i == highlightIndex2) {
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
}
