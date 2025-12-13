package model;

import java.util.Comparator;
import java.util.List;

public interface SortAlgorithm {
  public <T> void execute(List<T> list, Comparator<? super T> comp);
  
  public default <T extends Comparable<? super T>> void execute(List<T> list, boolean rev) {
    Comparator<? super T> comp = rev ? Comparator.reverseOrder() : Comparator.naturalOrder();
    execute(list, comp);
  }
}
