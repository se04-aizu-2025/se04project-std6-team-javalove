package model;

import java.util.List;

public class OptimizedMergeSort<T extends Comparable<? super T>> extends MergeSort<T> {
  protected void mergeSort(List<T> A, int left, int right, int sleepMs) {
    if (left >= right) {
      return;
    }
    int mid = (left + right) / 2;
    mergeSort(A, left, mid, sleepMs);
    mergeSort(A, mid + 1, right, sleepMs);
    if (comp.compare(A.get(mid), A.get(mid + 1)) <= 0) {
      return;
    }
    merge(A, left, mid, right, sleepMs);
  }

  protected void mergeSortWithVisualization(List<T> A, int left, int right, int sleepMs) {
    if (left >= right) {
      return;
    }
    int mid = (left + right) / 2;
    mergeSortWithVisualization(A, left, mid, sleepMs);
    mergeSortWithVisualization(A, mid + 1, right, sleepMs);
    if (comp.compare(A.get(mid), A.get(mid + 1)) <= 0) {
      return;
    }
    mergeWithVisualization(A, left, mid, right, sleepMs);
  }
}
