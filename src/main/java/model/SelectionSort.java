package model;

import java.util.List;

public class SelectionSort<T extends Comparable<? super T>> extends SortAlgorithm<T> {
  public void sort(List<T> A, int sleepMs) {
    int n = A.size();
    for (int i = 0; i < n - 1; i++) {
      int minIndex = i;
      for (int j = i + 1; j < n; j++) {
        if (comp.compare(A.get(j), A.get(minIndex)) < 0) {
          minIndex = j;
        }
        sleep(sleepMs);
      }
      swap(A, i, minIndex);
    }
  }
  
  public void sortWithVisualization(List<T> A, int sleepMs) {
    fixedIndexes.clear();
    record(A, -1, -1);
    int n = A.size();
    for (int i = 0; i < n - 1; i++) {
      int minIndex = i;
      for (int j = i + 1; j < n; j++) {
        record(A, j, minIndex);
        if (comp.compare(A.get(j), A.get(minIndex)) < 0) {
          minIndex = j;
        }
        sleep(sleepMs);
      }
      swap(A, i, minIndex);
      fixedIndexes.add(i);
    }
    fixedIndexes.add(n - 1);
    record(A, -1, -1);
  }
}
