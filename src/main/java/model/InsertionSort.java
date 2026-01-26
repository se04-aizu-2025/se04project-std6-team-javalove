package model;

import java.util.List;

public class InsertionSort<T extends Comparable<? super T>> extends SortAlgorithm<T> {
  public void sort(List<T> A, int sleepMs) {
    for (int i = 1; i < A.size(); i++) {
      T key = A.get(i);
      int j = i - 1;
      while (j >= 0 && comp.compare(A.get(j), key) > 0) {
        A.set(j + 1, A.get(j));
        j--;
        sleep(sleepMs);
      }
      A.set(j + 1, key);
    }
  }
  
  public void sortWithVisualization(List<T> A, int sleepMs) {
    fixedIndexes.clear();
    record(A, -1, -1);
    fixedIndexes.add(0);
    for (int i = 1; i < A.size(); i++) {
      T key = A.get(i);
      int j = i - 1;
      while (j >= 0 && comp.compare(A.get(j), key) > 0) {
        record(A, j, i);
        A.set(j + 1, A.get(j));
        j--;
        sleep(sleepMs);
      }
      A.set(j + 1, key);
      fixedIndexes.add(i);
    }
    record(A, -1, -1);
  }
}
