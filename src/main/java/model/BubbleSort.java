package model;

import java.util.Comparator;
import java.util.List;

public class BubbleSort implements SortAlgorithm {
  public <T> void execute(List<T> list, Comparator<? super T> comp) {
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
}
