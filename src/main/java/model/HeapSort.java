package model;

import java.util.List;

public class HeapSort<T extends Comparable<? super T>> extends SortAlgorithm<T> {
  public void sort(List<T> list) {
    int n = list.size();
    for (int i = n / 2 - 1; i >= 0; i--) {
      heapify(list, n, i);
    }
    for (int i = n - 1; i > 0; i--) {
      swap(list, 0, i);
      heapify(list, i, 0);
    }
  }

  private void heapify(List<T> list, int size, int root) {
    int largest = root;
    int left = 2 * root + 1;
    int right = 2 * root + 2;
    if (left < size && comp.compare(list.get(left), list.get(largest)) > 0)
      largest = left;
    if (right < size && comp.compare(list.get(right), list.get(largest)) > 0)
      largest = right;
    if (largest != root) {
      swap(list, root, largest);
      heapify(list, size, largest);
    }
  }
  
  public void sortWithVisualization(List<T> list) {
    fixedIndexes.clear();
    int n = list.size();
    record(list, -1, -1);
    for (int i = n / 2 - 1; i >= 0; i--) {
      heapifyWithVisualization(list, n, i);
    }
    for (int i = n - 1; i > 0; i--) {
      swap(list, 0, i);
      fixedIndexes.add(i);
      record(list, 0, i);
      heapifyWithVisualization(list, i, 0);
    }
    fixedIndexes.add(0);
    record(list, -1, -1);
  }

  private void heapifyWithVisualization(List<T> list, int size, int root) {
    int largest = root;
    int left = 2 * root + 1;
    int right = 2 * root + 2;
    if (left < size) {
      record(list, root, left);
      if (comp.compare(list.get(left), list.get(largest)) > 0) {
        largest = left;
      }
    }
    if (right < size) {
      record(list, largest, right);
      if (comp.compare(list.get(right), list.get(largest)) > 0) {
        largest = right;
      }
    }
    if (largest != root) {
      swap(list, root, largest);
      heapifyWithVisualization(list, size, largest);
    }
  }

  private void swap(List<T> list, int i, int j) {
    T tmp = list.get(i);
    list.set(i, list.get(j));
    list.set(j, tmp);
  }
}
