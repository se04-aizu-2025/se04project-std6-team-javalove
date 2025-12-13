package model;

import java.util.List;

public class BubbleSort implements SortAlgorithm {
  public <T extends Comparable<? super T>> void execute(List<T> list) {
    int n = list.size();
    boolean swapped;
    do {
      swapped = false;
      for (int i = 1; i < n; i++) {
        if (list.get(i - 1).compareTo(list.get(i)) > 0) {
          T tmp = list.get(i - 1);
          list.set(i - 1, list.get(i));
          list.set(i, tmp);
          swapped = true;
        }
      }
      n--;
    } while (swapped);
  }
}
