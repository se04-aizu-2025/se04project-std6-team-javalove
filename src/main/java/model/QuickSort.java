package model;

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
      record(A, j, high);
      if (comp.compare(A.get(j), pivot) <= 0) {
        i++;
        swap(A, i, j);
      }
    }
    swap(A, i + 1, high);
    return i + 1;
  }
}
