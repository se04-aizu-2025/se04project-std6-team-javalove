package model;

import java.util.List;

public class SelectionSort implements SortAlgorithm {
  public <T extends Comparable<? super T>> void execute(List<T> list) {
    int n = list.size();
    for (int i = 0; i < n - 1; i++) {
      int minIndex = i;
      for (int j = i + 1; j < n; j++) {
        if (list.get(j).compareTo(list.get(minIndex)) < 0) {
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
}
