package model;

import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.List;

public class HeapSort<T extends Comparable<? super T>> extends SortAlgorithm<T> {
  public void sort(List<T> A, int sleepMs) {
    int n = A.size();
    for (int i = n / 2 - 1; i >= 0; i--) {
      heapify(A, n, i, sleepMs);
    }
    for (int i = n - 1; i > 0; i--) {
      swap(A, 0, i);
      heapify(A, i, 0, sleepMs);
    }
  }

  protected void heapify(List<T> A, int size, int root, int sleepMs) {
    int largest = root;
    int left = 2 * root + 1;
    int right = 2 * root + 2;
    if (left < size) {
      sleep(sleepMs);
      if (comp.compare(A.get(left), A.get(largest)) > 0) {
        largest = left;
      }
    }
    if (right < size) {
      sleep(sleepMs);
      if (comp.compare(A.get(right), A.get(largest)) > 0) {
        largest = right;
      }
    }
    if (largest != root) {
      swap(A, root, largest);
      heapify(A, size, largest, sleepMs);
    }
  }

  public void sortWithVisualization(List<T> A, int sleepMs) {
    fixedIndexes.clear();
    record(A, -1, -1);
    int n = A.size();
    for (int i = n / 2 - 1; i >= 0; i--) {
      heapifyWithVisualization(A, n, i, sleepMs);
    }
    for (int i = n - 1; i > 0; i--) {
      record(A, 0, i);
      swap(A, 0, i);
      fixedIndexes.add(i);
      heapifyWithVisualization(A, i, 0, sleepMs);
    }
    fixedIndexes.add(0);
    record(A, -1, -1);
  }

  protected void heapifyWithVisualization(List<T> A, int size, int root, int sleepMs) {
    int largest = root;
    int left = 2 * root + 1;
    int right = 2 * root + 2;
    if (left < size) {
      sleep(sleepMs);
      record(A, left, largest);
      if (comp.compare(A.get(left), A.get(largest)) > 0) {
        largest = left;
      }
    }
    if (right < size) {
      sleep(sleepMs);
      record(A, right, largest);
      if (comp.compare(A.get(right), A.get(largest)) > 0) {
        largest = right;
      }
    }
    if (largest != root) {
      swap(A, root, largest);
      heapifyWithVisualization(A, size, largest, sleepMs);
    }
  }
  
  protected BufferedImage draw(List<T> list, int highlightIndex1, int highlightIndex2, boolean isSwaped) {
    final int size = list.size();
    if (size == 0) {
      return new BufferedImage(1, 1, BufferedImage.TYPE_BYTE_INDEXED);
    }
    final int baseWidth = 800;
    final int barWidth = baseWidth / size + 1;
    final int margin = barWidth * size / 40;
    final int barAreaHeight;
    final int heapAreaHeight = 220;
    final int canvasWidth = barWidth * size + margin * 2;
    barAreaHeight = canvasWidth / 4;
    final int canvasHeight = barAreaHeight + heapAreaHeight + margin;
    BufferedImage image = new BufferedImage(canvasWidth, canvasHeight, BufferedImage.TYPE_BYTE_INDEXED);
    Graphics2D g = image.createGraphics();
    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
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
    int range = Math.max(1, max - min);
    int drawableHeight = barAreaHeight - margin * 2;
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
    int heapTop = barAreaHeight + margin;
    int maxLevel = (int) (Math.log(size) / Math.log(2));
    int levels = maxLevel + 1;
    int levelHeight = heapAreaHeight / levels;
    int nodeRadius = Math.min(14, levelHeight / 3);
    int maxLeafCount = 1 << maxLevel;
    int heapDrawableWidth = canvasWidth - margin * 2;
    double unitX = (double) heapDrawableWidth / (2 * maxLeafCount);
    Point[] positions = new Point[size];
    for (int i = 0; i < size; i++) {
      int level = (int) (Math.log(i + 1) / Math.log(2));
      int levelStart = (1 << level) - 1;
      int indexInLevel = i - levelStart;
      int virtualIndex = (1 << (maxLevel - level)) * (2 * indexInLevel + 1);
      int x = margin + (int) (virtualIndex * unitX);
      int y = heapTop + level * levelHeight + levelHeight / 2;
      positions[i] = new Point(x, y);
    }
    g.setColor(BLACK);
    for (int i = 1; i < size; i++) {
      int parent = (i - 1) / 2;
      g.drawLine(positions[parent].x, positions[parent].y, positions[i].x, positions[i].y);
    }
    for (int i = 0; i < size; i++) {
      Point p = positions[i];
      if (i == highlightIndex1 || i == highlightIndex2) {
        g.setColor(isSwaped ? RED : ORANGE);
      } else if (fixedIndexes.contains(i)) {
        g.setColor(GRAY);
      } else {
        g.setColor(BLUE);
      }
      g.fillOval(p.x - nodeRadius, p.y - nodeRadius, nodeRadius * 2,nodeRadius * 2);
      g.setColor(WHITE);
      String text = String.valueOf(values[i]);
      FontMetrics fm = g.getFontMetrics();
      g.drawString(text, p.x - fm.stringWidth(text) / 2, p.y + fm.getAscent() / 2 - 2);
    }
    g.dispose();
    return image;
  }
}
