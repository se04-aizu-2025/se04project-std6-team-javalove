package model;

import java.util.List;

public class OptimizedBubbleSort<T extends Comparable<? super T>> extends BubbleSort<T> {
  public void sort(List<T> A, int sleepMs) {
    int n = A.size();
    for (int i = 0; i < n - 1; i++) {
      boolean swapped = false;
      for (int j = 0; j < n - 1 - i; j++) {
        sleep(sleepMs);
        if (comp.compare(A.get(j), A.get(j + 1)) > 0) {
          swap(A, j, j + 1);
          swapped = true;
        }
      }
      if (!swapped) {
        break;
      }
    }
  }
  
  public void sortWithVisualization(List<T> A, int sleepMs) {
    fixedIndexes.clear();
    record(A, -1, -1);
    int n = A.size();
    for (int i = 0; i < n - 1; i++) {
      boolean swapped = false;
      for (int j = 0; j < n - 1 - i; j++) {
        sleep(sleepMs);
        record(A, j, j + 1);
        if (comp.compare(A.get(j), A.get(j + 1)) > 0) {
          swap(A, j, j + 1);
          swapped = true;
        }
      }
      fixedIndexes.add(n - 1 - i);
      if (!swapped) {
        break;
      }
    }
    fixedIndexes.add(0);
    record(A, -1, -1);
  }
}
