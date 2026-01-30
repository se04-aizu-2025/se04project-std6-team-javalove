package model;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MergeSort<T extends Comparable<? super T>> extends SortAlgorithm<T> {
  public void sort(List<T> A, int sleepMs) {
    mergeSort(A, 0, A.size() - 1, sleepMs);
  }

  protected void mergeSort(List<T> A, int left, int right, int sleepMs) {
    if (left >= right) {
      return;
    }
    int mid = (left + right) / 2;
    mergeSort(A, left, mid, sleepMs);
    mergeSort(A, mid + 1, right, sleepMs);
    merge(A, left, mid, right, sleepMs);
  }

  protected void merge(List<T> A, int left, int mid, int right, int sleepMs) {
    int i = left;
    int j = mid + 1;
    List<T> temp = new ArrayList<>();
    while (i <= mid && j <= right) {
      sleep(sleepMs);
      if (comp.compare(A.get(i), A.get(j)) <= 0) {
        temp.add(A.get(i++));
      } else {
        temp.add(A.get(j++));
      }
    }
    while (i <= mid) {
      temp.add(A.get(i++));
    }
    while (j <= right) {
      temp.add(A.get(j++));
    }
    for (int k = 0; k < temp.size(); k++) {
      set(A, left + k, temp.get(k));
    }
  }

  protected void sortWithVisualization(List<T> A, int sleepMs) {
    fixedIndexes.clear();
    record(A, Collections.emptyList(), -1, -1, -1, -1, -1);
    mergeSortWithVisualization(A, 0, A.size() - 1, sleepMs);
    for (int i = 0; i < A.size(); i++) {
      fixedIndexes.add(i);
    }
    record(A, Collections.emptyList(), -1, -1, -1, -1, -1);
  }

  protected void mergeSortWithVisualization(List<T> A, int left, int right, int sleepMs) {
    if (left >= right) {
      return;
    }
    int mid = (left + right) / 2;
    mergeSortWithVisualization(A, left, mid, sleepMs);
    mergeSortWithVisualization(A, mid + 1, right, sleepMs);
    mergeWithVisualization(A, left, mid, right, sleepMs);
  }

  protected void mergeWithVisualization(List<T> A, int left, int mid, int right, int sleepMs) {
    int i = left;
    int j = mid + 1;
    List<T> temp = new ArrayList<>();
    while (i <= mid && j <= right) {
      sleep(sleepMs);
      record(A, temp, i, j, left, mid, right);
      if (comp.compare(A.get(i), A.get(j)) <= 0) {
        temp.add(A.get(i++));
      } else {
        temp.add(A.get(j++));
      }
    }
    while (i <= mid) {
      temp.add(A.get(i++));
    }
    while (j <= right) {
      temp.add(A.get(j++));
    }
    for (int k = 0; k < temp.size(); k++) {
      set(A, left + k, temp.get(k));
    }
  }
  
  protected void record(List<T> list, List<T> temp, int i, int j, int left, int mid, int right) {
    frames.add(draw(list, temp, i, j, left, mid, right, false));
  }
  
  protected void record(List<T> list, List<T> temp, int i, int j, int left, int mid, int right, boolean isSwaped) {
    frames.add(draw(list, temp, i, j, left, mid, right, isSwaped));
  }
  
  protected BufferedImage draw(
    List<T> list,
    List<T> temp,
    int highlightIndex1,
    int highlightIndex2,
    int leftStart,
    int mid,
    int rightEnd,
    boolean isSwaped
  ) {
    final int size = list.size();
    if (size == 0) {
        return new BufferedImage(1, 1, BufferedImage.TYPE_BYTE_INDEXED);
    }
    final int baseWidth = 800;
    final int barWidth = baseWidth / size + 1;
    final int margin = barWidth * size / 40;
    final int canvasWidth = barWidth * size + margin * 2;
    final int canvasHeight = canvasWidth / 2;
    final int rowHeight = canvasHeight / 2;
    BufferedImage image = new BufferedImage(canvasWidth, canvasHeight, BufferedImage.TYPE_BYTE_INDEXED);
    Graphics2D g = image.createGraphics();
    g.setColor(WHITE);
    g.fillRect(0, 0, canvasWidth, canvasHeight);
    int min = Integer.MAX_VALUE;
    int max = Integer.MIN_VALUE;
    for (T v : list) {
      int n = Integer.parseInt(v.toString());
      min = Math.min(min, n);
      max = Math.max(max, n);
    }
    for (T v : temp) {
      int n = Integer.parseInt(v.toString());
      min = Math.min(min, n);
      max = Math.max(max, n);
    }
    max = Math.max(max, 0);
    min = Math.min(min, 0);
    int range = Math.max(1, max - min);
    drawRow(
      g, list,
      margin,
      0,
      rowHeight,
      barWidth,
      range,
      min,
      max,
      0,
      highlightIndex1,
      highlightIndex2,
      leftStart,
      mid,
      rightEnd,
      isSwaped
    );
    drawRow(
      g, temp,
      margin,
      rowHeight,
      rowHeight,
      barWidth,
      range,
      min,
      max,
      leftStart,
      -1, -1,
      -1, -1, -1,
      isSwaped
    );
    g.dispose();
    return image;
  }

  private void drawRow(
    Graphics2D g,
    List<T> list,
    int margin,
    int offsetY,
    int height,
    int barWidth,
    int range,
    int min,
    int max,
    int startIndex,
    int highlightIndex1,
    int highlightIndex2,
    int leftStart,
    int mid,
    int rightEnd,
    boolean isSwaped
  ) {
    int drawableHeight = height - margin * 2;
    int baselineY = offsetY + margin + (int) ((double) max / range * drawableHeight);
    g.setColor(BLACK);
    g.drawLine(margin, baselineY, margin + barWidth * list.size(), baselineY);
    for (int i = 0; i < list.size(); i++) {
      int value = Integer.parseInt(list.get(i).toString());
      int barHeight = (int) (Math.abs((double) value) / range * drawableHeight);
      int x = margin + (startIndex + i) * barWidth;
      int y = value >= 0 ? baselineY - barHeight : baselineY;
      if (i == highlightIndex1 || i == highlightIndex2) {
        g.setColor(isSwaped ? RED : ORANGE);
      } else if (i >= leftStart && i <= mid) {
        g.setColor(GREEN);
      } else if (i > mid && i <= rightEnd) {
        g.setColor(PURPLE);
      } else if (fixedIndexes.contains(i)) {
        g.setColor(GRAY);
      } else {
        g.setColor(BLUE);
      }
      g.fillRect(x + 1, y, barWidth - 1, barHeight);
    }
  }
}
