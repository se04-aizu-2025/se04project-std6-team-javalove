package model;

import java.util.List;

public interface SortAlgorithm {
  public <T extends Comparable<? super T>> void execute(List<T> list);
}
