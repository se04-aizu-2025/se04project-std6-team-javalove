package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.List;

public class QuickSort<T extends Comparable<? super T>> extends SortAlgorithm<T> {
  public void sort(List<T> list) {
    quickSort(list, 0, list.size() - 1);
  }

  private void quickSort(List<T> list, int left, int right) {
    if (left >= right) return;
    int pivotIndex = (left + right) / 2;
    T pivot = list.get(pivotIndex);
    int i = left;
    int j = right;
    while (i <= j) {
      while (comp.compare(list.get(i), pivot) < 0) i++;
      while (comp.compare(list.get(j), pivot) > 0) j--;
      if (i <= j) {
        if (i != j) {
          swap(list, i, j);
        }
        i++;
        j--;
      }
    }
    quickSort(list, left, j);
    quickSort(list, i, right);
  }
  
  protected void record(List<T> list, int highlightIndex1, int highlightIndex2, int pivotIndex, int left, int right) {
    frames.add(draw(list, highlightIndex1, highlightIndex2, pivotIndex, left, right));
  }

  protected BufferedImage draw(List<T> list, int highlightIndex1, int highlightIndex2, int pivotIndex, int left, int right) {
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
      if (i < left || i > right) {
        g.setColor(new Color(210, 210, 210));
      } else if (i == pivotIndex) {
        g.setColor(new Color(231, 76, 60));
      } else if (i == highlightIndex1 || i == highlightIndex2) {
        g.setColor(new Color(255, 159, 67));
      } else {
        g.setColor(new Color(74, 144, 226));
      }
      g.fillRect(x + 1, y, barWidth - 1, barHeight);
    }
    g.dispose();
    return img;
  }

  public void sortWithVisualization(List<T> list) {
    fixedIndexes.clear();
    record(list, -1, -1, -1, 0, list.size() - 1);
    quickSortWithVisualization(list, 0, list.size() - 1);
    for (int i = 0; i < list.size(); i++) {
      fixedIndexes.add(i);
    }
    record(list, -1, -1, -1, 0, list.size() - 1);
  }

  private void quickSortWithVisualization(List<T> list, int left, int right) {
    if (left >= right) return;
    int pivotIndex = (left + right) / 2;
    T pivot = list.get(pivotIndex);
    int i = left;
    int j = right;
    while (i <= j) {
      while (true) {
        record(list, i, -1, pivotIndex, left, right);
        if (comp.compare(list.get(i), pivot) < 0) {
          i++;
        } else {
          break;
        }
      }
      while (true) {
        record(list, -1, j, pivotIndex, left, right);
        if (comp.compare(list.get(j), pivot) > 0) {
          j--;
        } else {
          break;
        }
      }
      if (i <= j) {
        if (i != j) {
          swap(list, i, j);
          if (i == pivotIndex) {
            pivotIndex = j;
          } else if (j == pivotIndex) {
            pivotIndex = i;
          }
          record(list, i, j, pivotIndex, left, right);
        }
        i++;
        j--;
      }
    }
    quickSortWithVisualization(list, left, j);
    quickSortWithVisualization(list, i, right);
  }

  private void swap(List<T> list, int i, int j) {
    T tmp = list.get(i);
    list.set(i, list.get(j));
    list.set(j, tmp);
  }
}
