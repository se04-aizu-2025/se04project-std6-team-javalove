package model;

import java.util.List;

public class InsertionSort implements SortAlgorithm {
  public <T extends Comparable<? super T>> void execute(List<T> list) {
    int n = list.size();
    for (int i = 1; i < n; i++) {
      T key = list.get(i);
      int j = i - 1;
      while (j >= 0 && list.get(j).compareTo(key) > 0) {
        list.set(j + 1, list.get(j));
        j--;
      }
      list.set(j + 1, key);
    }
  }
}
