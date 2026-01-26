package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class MergeSort<T extends Comparable<? super T>> extends SortAlgorithm<T> {
  public void sort(List<T> A, int sleepMs) {
    mergeSort(A, 0, A.size() - 1, sleepMs);
  }

  private void mergeSort(List<T> A, int left, int right, int sleepMs) {
    if (left >= right) {
      return;
    }
    int mid = (left + right) / 2;
    mergeSort(A, left, mid, sleepMs);
    mergeSort(A, mid + 1, right, sleepMs);
    merge(A, left, mid, right, sleepMs);
  }

  private void merge(List<T> A, int left, int mid, int right, int sleepMs) {
    int i = left;
    int j = mid + 1;
    List<T> temp = new ArrayList<>();
    while (i <= mid && j <= right) {
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
      A.set(left + k, temp.get(k));
      sleep(sleepMs);
    }
  }

  public void sortWithVisualization(List<T> A, int sleepMs) {
    fixedIndexes.clear();
    record(A, -1, -1);
    mergeSortWithVisualization(A, 0, A.size() - 1, sleepMs);
    for (int i = 0; i < A.size(); i++) {
      fixedIndexes.add(i);
    }
    record(A, -1, -1);
  }

  private void mergeSortWithVisualization(List<T> A, int left, int right, int sleepMs) {
    if (left >= right) {
      return;
    }
    int mid = (left + right) / 2;
    mergeSortWithVisualization(A, left, mid, sleepMs);
    mergeSortWithVisualization(A, mid + 1, right, sleepMs);
    mergeWithVisualization(A, left, mid, right, sleepMs);
  }

  private void mergeWithVisualization(List<T> A, int left, int mid, int right, int sleepMs) {
    int i = left;
    int j = mid + 1;
    List<T> temp = new ArrayList<>();
    while (i <= mid && j <= right) {
      record(A, i, j, left, mid, right);
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
      A.set(left + k, temp.get(k));
      sleep(sleepMs);
    }
  }
  
  protected void record(List<T> list, int i, int j, int left, int mid, int right) {
    frames.add(draw(list, i, j, left, mid, right));
  }
  
  private BufferedImage draw(List<T> list, int highlightIndex1, int highlightIndex2, int left, int mid, int right) {
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
      int y = (v >= 0) ? baselineY - barHeight : baselineY;
      if (i == highlightIndex1 || i == highlightIndex2) {
        g.setColor(new Color(255, 159, 67));
      } else if (i >= left && i <= mid) {
        g.setColor(new Color(74, 144, 226));
      } else if (i >= mid + 1 && i <= right) {
        g.setColor(new Color(46, 204, 113));
      } else {
        g.setColor(new Color(200, 200, 200));
      }
      g.fillRect(x + 1, y, barWidth - 1, barHeight);
    }
    g.dispose();
    return img;
  }
}
