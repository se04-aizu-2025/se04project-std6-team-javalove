package model;

import java.util.List;

public class InsertionSort<T extends Comparable<? super T>> extends SortAlgorithm<T> {
  public void sort(List<T> list) {
    int n = list.size();
    for (int i = 1; i < n; i++) {
      T key = list.get(i);
      int j = i - 1;
      boolean moved = false;
      while (j >= 0 && comp.compare(list.get(j), key) > 0) {
        list.set(j + 1, list.get(j));
        j--;
        moved = true;
      }
      if (moved) {
        list.set(j + 1, key);
      } else {
        list.set(j + 1, key);
      }
    }
  }

  public void sortWithVisualization(List<T> list) {
    fixedIndexes.clear();
    int n = list.size();
    record(list, -1, -1);
    for (int i = 1; i < n; i++) {
      T key = list.get(i);
      int j = i - 1;
      while (j >= 0) {
        record(list, j, j + 1);
        if (comp.compare(list.get(j), key) > 0) {
          list.set(j + 1, list.get(j));
          j--;
        } else {
          break;
        }
      }
      list.set(j + 1, key);
      for (int k = 0; k <= i; k++) {
        fixedIndexes.add(k);
      }
    }
    for (int i = 0; i < n; i++) {
      fixedIndexes.add(i);
    }
    record(list, -1, -1);
  }
}
