package model;

import java.util.List;

public class BubbleSort<T extends Comparable<? super T>> extends SortAlgorithm<T> {
  public void sort(List<T> list) {
    int n = list.size();
    boolean swapped;
    do {
      swapped = false;
      for (int i = 1; i < n; i++) {
        if (comp.compare(list.get(i - 1), list.get(i)) > 0) {
          T tmp = list.get(i - 1);
          list.set(i - 1, list.get(i));
          list.set(i, tmp);
          swapped = true;
        }
      }
      n--;
    } while (swapped);
  }
  
  public void sortWithVisualization(List<T> list) {
    fixedIndexes.clear();
    int n = list.size();
    record(list, -1, -1);
    boolean swapped;
    do {
      swapped = false;
      for (int i = 1; i < n; i++) {
        record(list, i - 1, i);
        if (comp.compare(list.get(i - 1), list.get(i)) > 0) {
          T tmp = list.get(i - 1);
          list.set(i - 1, list.get(i));
          list.set(i, tmp);
          swapped = true;
        }
      }
      n--;
      fixedIndexes.add(n);
    } while (swapped);
    for (int i = 0; i < n; i++) {
      fixedIndexes.add(i);
    }
    record(list, -1, -1);
  }
}
