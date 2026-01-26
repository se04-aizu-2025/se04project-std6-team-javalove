package model;

import java.util.List;

public class BubbleSort<T extends Comparable<? super T>> extends SortAlgorithm<T> {
  public void sort(List<T> A, int sleepMs) {
    int n = A.size();
    for (int i = 0; i < n - 1; i++) {
      for (int j = 0; j < n - 1 - i; j++) {
        if (comp.compare(A.get(j), A.get(j + 1)) > 0) {
          swap(A, j, j + 1);
        }
        sleep(sleepMs);
      }
    }
  }
  
  public void sortWithVisualization(List<T> A, int sleepMs) {
    fixedIndexes.clear();
    record(A, -1, -1);
    int n = A.size();
    for (int i = 0; i < n - 1; i++) {
      for (int j = 0; j < n - 1 - i; j++) {
        record(A, j, j + 1);
        if (comp.compare(A.get(j), A.get(j + 1)) > 0) {
          swap(A, j, j + 1);
        }
        sleep(sleepMs);
      }
      fixedIndexes.add(n - 1 - i);
    }
    fixedIndexes.add(0);
    record(A, -1, -1);
  }
}
