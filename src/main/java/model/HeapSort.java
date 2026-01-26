package model;

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
      swap(A, 0, i);
      fixedIndexes.add(i);
      record(A, 0, i);
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
}
