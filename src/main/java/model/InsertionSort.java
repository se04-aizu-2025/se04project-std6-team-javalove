package model;

import java.util.Comparator;
import java.util.List;

public class InsertionSort implements SortAlgorithm {
  public <T> void execute(List<T> list, Comparator<? super T> comp) {
    int n = list.size();
    for (int i = 1; i < n; i++) {
      T key = list.get(i);
      int j = i - 1;
      while (j >= 0 && comp.compare(list.get(j), key) > 0) {
        list.set(j + 1, list.get(j));
        j--;
      }
      list.set(j + 1, key);
    }
  }
}
