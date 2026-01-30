package model;

import java.util.List;

public class OptimizedQuickSort<T extends Comparable<? super T>> extends QuickSort<T> {  
  protected static final int THRESHOLD = 16;

  protected void quickSort(List<T> A, int low, int high, int sleepMs) {
    if (high - low < THRESHOLD) {
      insertionSort(A, low, high, sleepMs);
      return;
    }
    int p = partition(A, low, high, sleepMs);
    quickSort(A, low, p - 1, sleepMs);
    quickSort(A, p + 1, high, sleepMs);
  }
  
  protected int partition(List<T> A, int low, int high, int sleepMs) {
    int mid = (low + high) / 2;
    if (comp.compare(A.get(low), A.get(mid)) > 0) {
      swap(A, low, mid);
    }
    if (comp.compare(A.get(low), A.get(high)) > 0) {
      swap(A, low, high);
    }
    if (comp.compare(A.get(mid), A.get(high)) > 0) {
      swap(A, mid, high);
    }
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

  protected void quickSortWithVisualization(List<T> A, int low, int high, int sleepMs) {
    if (high - low < THRESHOLD) {
      insertionSortWithVisualization(A, low, high, sleepMs);
      return;
    }
    int p = partitionWithVisualization(A, low, high, sleepMs);
    fixedIndexes.add(p);
    quickSortWithVisualization(A, low, p - 1, sleepMs);
    quickSortWithVisualization(A, p + 1, high, sleepMs);
  }
  
  protected int partitionWithVisualization(List<T> A, int low, int high, int sleepMs) {
    int mid = (low + high) / 2;
    record(A, low, mid);
    if (comp.compare(A.get(low), A.get(mid)) > 0) {
      swap(A, low, mid);
    }
    record(A, low, high);
    if (comp.compare(A.get(low), A.get(high)) > 0) {
      swap(A, low, high);
    }
    record(A, mid, high);
    if (comp.compare(A.get(mid), A.get(high)) > 0) {
      swap(A, mid, high);
    }
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
  
  protected void insertionSort(List<T> A, int low, int high, int sleepMs) {
    for (int i = low + 1; i <= high; i++) {
      T key = A.get(i);
      int j = i - 1;
      while (j >= low) {
        sleep(sleepMs);
        if (comp.compare(A.get(j), key) > 0) {
          set(A, j + 1, A.get(j));
          j--;
        } else {
          break;
        }
      }
    }
  }
  
  public void insertionSortWithVisualization(List<T> A, int low, int high, int sleepMs) {
    fixedIndexes.add(low);
    for (int i = low + 1; i <= high; i++) {
      T key = A.get(i);
      int j = i - 1;
      while (j >= 0) {
        sleep(sleepMs);
        record(A, j, i);
        if (comp.compare(A.get(j), key) > 0) {
          set(A, j + 1, A.get(j));
          j--;
        } else {
          break;
        }
      }
      fixedIndexes.add(i);
    }
  }
}
