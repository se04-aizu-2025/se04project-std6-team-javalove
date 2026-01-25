package model;

import java.util.List;

public class SelectionSort<T extends Comparable<? super T>> extends SortAlgorithm<T> {
  public void sort(List<T> list) {
    int n = list.size();
    for (int i = 0; i < n - 1; i++) {
      int minIndex = i;
      for (int j = i + 1; j < n; j++) {
        if (comp.compare(list.get(j), list.get(minIndex)) < 0) {
          minIndex = j;
        }
      }
      if (i != minIndex) {
        T temp = list.get(i);
        list.set(i, list.get(minIndex));
        list.set(minIndex, temp);
      }
    }
  }
  
  public void sortWithVisualization(List<T> list) {
    fixedIndexes.clear();
    int n = list.size();
    record(list, -1, -1);
    for (int i = 0; i < n - 1; i++) {
      int minIndex = i;
      for (int j = i + 1; j < n; j++) {
        record(list, j, minIndex);
        if (comp.compare(list.get(j), list.get(minIndex)) < 0) {
          minIndex = j;
        }
      }
      if (i != minIndex) {
        T temp = list.get(i);
        list.set(i, list.get(minIndex));
        list.set(minIndex, temp);
      }
      fixedIndexes.add(i);
    }
    fixedIndexes.add(n - 1);
    record(list, -1, -1);
  }
}
